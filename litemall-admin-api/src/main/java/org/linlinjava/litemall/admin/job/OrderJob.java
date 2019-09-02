package org.linlinjava.litemall.admin.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.system.SystemConfig;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.domain.LitemallOrderGoods;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.domain.TianyuOrder;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 检测订单状态
 */
@Component
public class OrderJob {
    private final Log logger = LogFactory.getLog(OrderJob.class);

    @Autowired
    private LitemallOrderGoodsService orderGoodsService;
    @Autowired
    private LitemallOrderService orderService;
    @Autowired
    private LitemallGoodsProductService productService;
    @Autowired
    private TianyuOrderService tianyuOrderService;
    @Autowired
    private TianyuCoursePlanService coursePlanService;

    /**
     * 自动设置已上课
     * <p>
     * 定时检查课程订单是否超过上课时间
     * 定时时间是每个小时的第一分钟
     * <p>
     */
    @Scheduled(cron = "0 1 * * * ?")
    public void checkOrderUse() {
        logger.info("系统开启任务检查课程订单是否已经超期未上课");

        List<TianyuOrder> orderList = tianyuOrderService.queryUnuse();
        for (TianyuOrder order : orderList) {
            TianyuCoursePlan coursePlan = coursePlanService.findById(order.getPlanId());
            // 检测是否超期
            LocalDate limitDate = coursePlan.getcDate();
            if(LocalDate.now().compareTo(limitDate) > 0) {
                order.setOrderStatus(OrderUtil.STATUS_AUTO_USE);
                tianyuOrderService.updateWithOptimisticLocker(order);
            } else if(LocalDate.now().compareTo(limitDate) == 0) {
                LocalTime limitTime = coursePlan.getStartTime();
                if (LocalTime.now().compareTo(limitTime) > 0) {
                    order.setOrderStatus(OrderUtil.STATUS_AUTO_USE);
                    tianyuOrderService.updateWithOptimisticLocker(order);
                }
            }
            logger.info("订单 ID：" + order.getId() + " 已经超期自动确认上课");
        }
    }

    /**
     * 自动设置订单完成状态
     * <p>
     * 定时检查课程订单是否已上课，已经上课则修改为完成状态
     * 定时时间是每天半夜零点
     * <p>
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkOrderFinish() {
        logger.info("系统开启任务检查课程订单是否已经完成");

        List<TianyuOrder> orderList = tianyuOrderService.queryUnFinish();
        for (TianyuOrder order : orderList) {
            order.setComments(0);
            order.setOrderStatus(OrderUtil.STATUS_AUTO_CONFIRM);
            tianyuOrderService.updateWithOptimisticLocker(order);

            logger.info("订单 ID" + order.getId() + " 已经自动确认完成订单");
        }
    }

    /**
     * 自动取消订单
     * <p>
     * 定时检查订单未付款情况，如果超时 LITEMALL_ORDER_UNPAID 分钟则自动取消订单
     * 定时时间是每次相隔半个小时。
     * <p>
     * TODO
     * 注意，因为是相隔半小时检查，因此导致订单真正超时时间是 [LITEMALL_ORDER_UNPAID, 30 + LITEMALL_ORDER_UNPAID]
     */
    //@Scheduled(fixedDelay = 30 * 60 * 1000)
    @Transactional(rollbackFor = Exception.class)
    public void checkOrderUnpaid() {
        logger.info("系统开启任务检查订单是否已经超期自动取消订单");

        List<LitemallOrder> orderList = orderService.queryUnpaid(SystemConfig.getOrderUnpaid());
        for (LitemallOrder order : orderList) {
            // 设置订单已取消状态
            order.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
            order.setEndTime(LocalDateTime.now());
            if (orderService.updateWithOptimisticLocker(order) == 0) {
                throw new RuntimeException("更新数据已失效");
            }

            // 商品货品数量增加
            Integer orderId = order.getId();
            List<LitemallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(orderId);
            for (LitemallOrderGoods orderGoods : orderGoodsList) {
                Integer productId = orderGoods.getProductId();
                Short number = orderGoods.getNumber();
                if (productService.addStock(productId, number) == 0) {
                    throw new RuntimeException("商品货品库存增加失败");
                }
            }
            logger.info("订单 ID" + order.getId() + " 已经超期自动取消订单");
        }
    }

    /**
     * 自动确认订单
     * <p>
     * 定时检查订单未确认情况，如果超时 LITEMALL_ORDER_UNCONFIRM 天则自动确认订单
     * 定时时间是每天凌晨3点。
     * <p>
     * TODO
     * 注意，因为是相隔一天检查，因此导致订单真正超时时间是 [LITEMALL_ORDER_UNCONFIRM, 1 + LITEMALL_ORDER_UNCONFIRM]
     */
    //@Scheduled(cron = "0 0 3 * * ?")
    public void checkOrderUnconfirm() {
        logger.info("系统开启任务检查订单是否已经超期自动确认收货");

        List<LitemallOrder> orderList = orderService.queryUnconfirm(SystemConfig.getOrderUnconfirm());
        for (LitemallOrder order : orderList) {

            // 设置订单已取消状态
            order.setOrderStatus(OrderUtil.STATUS_AUTO_CONFIRM);
            order.setConfirmTime(LocalDateTime.now());
            if (orderService.updateWithOptimisticLocker(order) == 0) {
                logger.info("订单 ID=" + order.getId() + " 数据已经更新，放弃自动确认收货");
            } else {
                logger.info("订单 ID=" + order.getId() + " 已经超期自动确认收货");
            }
        }
    }

    /**
     * 可评价订单商品超期
     * <p>
     * 定时检查订单商品评价情况，如果确认商品超时 LITEMALL_ORDER_COMMENT 天则取消可评价状态
     * 定时时间是每天凌晨4点。
     * <p>
     * TODO
     * 注意，因为是相隔一天检查，因此导致订单真正超时时间是 [LITEMALL_ORDER_COMMENT, 1 + LITEMALL_ORDER_COMMENT]
     */
    //@Scheduled(cron = "0 0 4 * * ?")
    public void checkOrderComment() {
        logger.info("系统开启任务检查订单是否已经超期未评价");

        List<LitemallOrder> orderList = orderService.queryComment(SystemConfig.getOrderComment());
        for (LitemallOrder order : orderList) {
            order.setComments((short) 0);
            orderService.updateWithOptimisticLocker(order);

            List<LitemallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(order.getId());
            for (LitemallOrderGoods orderGoods : orderGoodsList) {
                orderGoods.setComment(-1);
                orderGoodsService.updateById(orderGoods);
            }
        }
    }
}
