package org.linlinjava.litemall.wx.service;

import org.linlinjava.litemall.core.notify.NotifyService;
import org.linlinjava.litemall.core.notify.NotifyType;
import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.db.util.OrderHandleOption;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.wx.util.WxResponseCode.*;

@Service
public class WxOrderCourseService {
    @Autowired
    private LitemallUserService userService;

    @Autowired
    private LitemallOrderService litemallOrderService;

    @Autowired
    private TianyuCourseService courseService;

    @Autowired
    private TianyuCoursePlanService coursePlanService;

    @Autowired
    private TianyuOrderService orderService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private LitemallCommentService commentService;

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息：
     *                 0，全部订单；
     *                 1，已预约；
     *                 3，已上课；
     *                 4，订单结束。
     * @param page     分页页数
     * @param limit     分页大小
     * @return 订单列表
     */
    public Object list(Integer userId, Integer showType, Integer page, Integer limit, String sort, String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<Short> orderStatus = OrderUtil.orderStatus(showType);
        List<TianyuOrder> orderList = orderService.queryByOrderStatus(userId, orderStatus, page, limit, sort, order);

        List<Map<String, Object>> orderVoList = new ArrayList<>(orderList.size());
        for (TianyuOrder o : orderList) {
            Map<String, Object> orderVo = new HashMap<>();
            orderVo.put("id", o.getId());
            orderVo.put("orderSn", o.getOrderSn());
            orderVo.put("orderStatusText", OrderUtil.orderStatusText(o));
            orderVo.put("handleOption", OrderUtil.build(o));
            orderVo.put("addTime", o.getAddTime());
            orderVoList.add(orderVo);
        }

        return ResponseUtil.okList(orderVoList, orderList);
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    public Object detail(Integer userId, Integer orderId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        // 订单信息
        TianyuOrder order = orderService.findById(orderId);
        if (null == order) {
            return ResponseUtil.fail(ORDER_UNKNOWN, "订单不存在");
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.fail(ORDER_INVALID, "不是当前用户的订单");
        }
        LitemallUser user = userService.findById(userId);
        TianyuCoursePlan coursePlan = coursePlanService.findById(order.getPlanId());
        TianyuCourse course = courseService.findById(coursePlan.getCourseId());

        Map<String, Object> orderVo = new HashMap<String, Object>();
        orderVo.put("id", order.getId());
        orderVo.put("orderSn", order.getOrderSn());
        orderVo.put("addTime", order.getAddTime());
        orderVo.put("consignee", order.getConsignee());
        orderVo.put("mobile", order.getMobile());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
        orderVo.put("comment", order.getComments());
        orderVo.put("handleOption", OrderUtil.build(order));
        orderVo.put("courseName", course.getName());
        orderVo.put("coursePeopleNum", course.getPeopleNum());
        orderVo.put("coursecDate", coursePlan.getcDate());
        orderVo.put("courseStartTime", coursePlan.getStartTime());
        orderVo.put("courseEndTime", coursePlan.getEndTime());
        orderVo.put("userCourseNum", user.getCourseNum());

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);

        return ResponseUtil.ok(result);

    }

    /**
     * 提交订单
     * 1. 创建订单表项;
     * 2. CoursePlan表剩余人数减少;
     * 3. 用户课程数减少;
     *
     * @param userId 用户ID
     * @param body 订单信息
     * @return
     */
    public Object submit(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        LitemallUser user = userService.findById(userId);
        Integer planId = JacksonUtil.parseInteger(body, "coursePlanId");
        TianyuCoursePlan coursePlan = coursePlanService.findById(planId);

        if(coursePlan.getPeopleLeft() <= 0) {
            return ResponseUtil.fail(COURSE_PLAN_LIMIT, "当前课程已约满");
        }
        if(user.getCourseNum() <= 0) {
            return ResponseUtil.fail(USER_COURSENUM_LIMIT, "您的课程已使用完毕");
        }
        // CoursePlan表剩余人数减少
        coursePlan.setPeopleLeft(coursePlan.getPeopleLeft()-1);
        coursePlanService.update(coursePlan);
        // 用户课程数减少
        user.setCourseNum(user.getCourseNum()-1);
        userService.updateById(user);
        // 添加订单表项
        TianyuOrder order = new TianyuOrder();
        order.setUserId(userId);
        order.setPlanId(planId);
        order.setOrderSn(litemallOrderService.generateOrderSn(userId));
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(user.getNickname());
        order.setMobile(user.getMobile());
        orderService.add(order);
        Integer orderId = order.getId();

        TianyuCourse course = courseService.findById(coursePlan.getCourseId());
        // 请依据自己的模版消息配置更改参数
        String[] parms = new String[]{
            course.getName(),
            coursePlan.getcDate() + " " + coursePlan.getStartTime(),
            user.getNickname(),
            DateTimeUtil.getDateTimeDisplayString(LocalDateTime.now()),
            "您已经预约成功，记得上课哟~"
        };

        // 发送邮件和模板通知，这里采用异步发送
        // 订单预约成功后，发送邮件给管理员
        notifyService.notifyMail("新订单通知", order.toString() + "\n" + coursePlan.toString() + "\n" + course.toString());
        // 发送微信模板通知给用户
        notifyService.notifyWxTemplate(user.getWeixinOpenid(), NotifyType.ORDERCOURSE, parms, "pages/index/index?orderId=" + orderId);

        Map<String, Object> data = new HashMap<>();
        data.put("orderId", orderId);
        return ResponseUtil.ok(data);
    }

    /**
     * 取消订单
     * <p>
     * 1. 检测当前订单是否能够取消；
     * 2. 设置订单取消状态；
     * 3. 商品货品库存恢复；
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @Transactional
    public Object cancel(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        TianyuOrder order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }
        LitemallUser user = userService.findById(userId);
        Integer planId = order.getPlanId();
        TianyuCoursePlan coursePlan = coursePlanService.findById(planId);

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isCancel()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
        }

        LocalDate limitDate = coursePlan.getcDate();
        // 检测是否提前两个小时取消
        if (LocalDate.now().compareTo(limitDate) > 0) {
            return ResponseUtil.fail(ORDER_TIME_LIMIT, "订单不能取消");
        } else if(LocalDate.now().compareTo(limitDate) == 0) {
            LocalTime limitTime = coursePlan.getStartTime().minusMinutes(120);
            if (LocalTime.now().compareTo(limitTime) > 0) {
                return ResponseUtil.fail(ORDER_TIME_LIMIT, "订单不能取消");
            }
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        // CoursePlan表剩余人数增加
        coursePlan.setPeopleLeft(coursePlan.getPeopleLeft()+1);
        coursePlanService.update(coursePlan);
        // 用户课程数增加
        user.setCourseNum(user.getCourseNum()+1);
        userService.updateById(user);

        return ResponseUtil.ok();
    }

    /**
     * 删除订单
     * <p>
     * 1. 检测当前订单是否可以删除；
     * 2. 删除订单。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object delete(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        TianyuOrder order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isDelete()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能删除");
        }

        // 订单order_status没有字段用于标识删除
        // 而是存在专门的delete字段表示是否删除
        orderService.deleteById(orderId);

        return ResponseUtil.ok();
    }

    /**
     * 评价订单商品
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object comment(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        TianyuOrder order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!OrderUtil.isAutoFinishStatus(order)) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "当前商品不能评价");
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.fail(ORDER_INVALID, "当前商品不属于用户");
        }

        String content = JacksonUtil.parseString(body, "content");
        Integer star = JacksonUtil.parseInteger(body, "star");
        if (star == null || star < 0 || star > 5) {
            return ResponseUtil.badArgumentValue();
        }
        Boolean hasPicture = JacksonUtil.parseBoolean(body, "hasPicture");
        List<String> picUrls = JacksonUtil.parseStringList(body, "picUrls");
        if (hasPicture == null || !hasPicture) {
            picUrls = new ArrayList<>(0);
        }

        // 1. 创建评价
        LitemallComment comment = new LitemallComment();
        comment.setUserId(userId);
        comment.setType((byte) 0);
        comment.setValueId(order.getId());
        comment.setStar(star.shortValue());
        comment.setContent(content);
        comment.setHasPicture(hasPicture);
        comment.setPicUrls(picUrls.toArray(new String[]{}));
        commentService.save(comment);

        // 2. 更新订单表评论状态
        order.setComments(comment.getId());
        orderService.updateWithOptimisticLocker(order);

        return ResponseUtil.ok();
    }
}
