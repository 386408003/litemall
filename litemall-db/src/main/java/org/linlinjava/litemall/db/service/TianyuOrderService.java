package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.OrderMapper;
import org.linlinjava.litemall.db.dao.TianyuOrderMapper;
import org.linlinjava.litemall.db.domain.TianyuOrder;
import org.linlinjava.litemall.db.domain.TianyuOrderExample;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TianyuOrderService {
    @Resource
    private TianyuOrderMapper tianyuOrderMapper;
    @Resource
    private OrderMapper orderMapper;

    public int add(TianyuOrder order) {
        order.setAddTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        return tianyuOrderMapper.insertSelective(order);
    }

    public int count(Integer userId) {
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) tianyuOrderMapper.countByExample(example);
    }

    public TianyuOrder findById(Integer orderId) {
        return tianyuOrderMapper.selectByPrimaryKey(orderId);
    }

    public int countByOrderSn(Integer userId, String orderSn) {
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return (int) tianyuOrderMapper.countByExample(example);
    }

    public List<TianyuOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus, Integer page, Integer limit, String sort, String order) {
        TianyuOrderExample example = new TianyuOrderExample();
        example.setOrderByClause(TianyuOrder.Column.addTime.desc());
        TianyuOrderExample.Criteria criteria = example.or();
        criteria.andUserIdEqualTo(userId);
        if (orderStatus != null) {
            criteria.andOrderStatusIn(orderStatus);
            criteria.andCommentsEqualTo(0);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return tianyuOrderMapper.selectByExample(example);
    }

    public List<TianyuOrder> querySelective(Integer userId, String orderSn, String mobile, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        TianyuOrderExample example = new TianyuOrderExample();
        TianyuOrderExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        if (orderStatusArray != null && orderStatusArray.size() != 0) {
            criteria.andOrderStatusIn(orderStatusArray);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return tianyuOrderMapper.selectByExample(example);
    }

    /**
     * 使用乐观锁机制更新数据库
     * @param order
     * @return
     */
    public int updateWithOptimisticLocker(TianyuOrder order) {
        LocalDateTime preUpdateTime = order.getUpdateTime();
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateTianyuOrderWithOptimisticLocker(preUpdateTime, order);
    }

    public void deleteById(Integer id) {
        tianyuOrderMapper.logicalDeleteByPrimaryKey(id);
    }

    public int count() {
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andDeletedEqualTo(false);
        return (int) tianyuOrderMapper.countByExample(example);
    }

    /**
     * 课程已创建未使用，即未上课
     * @return
     */
    public List<TianyuOrder> queryUnuse() {
        LocalDateTime now = LocalDateTime.now();
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_CREATE).andDeletedEqualTo(false);
        return tianyuOrderMapper.selectByExample(example);
    }

    /**
     * 已上课的课程
     * @return
     */
    public List<TianyuOrder> queryUnFinish() {
        LocalDateTime now = LocalDateTime.now();
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_AUTO_USE).andDeletedEqualTo(false);
        return tianyuOrderMapper.selectByExample(example);
    }

    public TianyuOrder findBySn(String orderSn) {
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return tianyuOrderMapper.selectOneByExample(example);
    }

    public List<TianyuOrder> queryComment(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        TianyuOrderExample example = new TianyuOrderExample();
        //example.or().andCommentsGreaterThan((short) 0).andConfirmTimeLessThan(expired).andDeletedEqualTo(false);
        return tianyuOrderMapper.selectByExample(example);
    }

    public Map<Object, Object> orderInfo(Integer userId) {
        TianyuOrderExample example = new TianyuOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<TianyuOrder> orders = tianyuOrderMapper.selectByExampleSelective(example, TianyuOrder.Column.orderStatus, TianyuOrder.Column.comments);

        int unuse = 0;// 已预约，未上课
        int unfinish = 0;// 已上课，未评论
        int uncomment = 0;// 未评论
        for (TianyuOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unuse++;
            } else if (OrderUtil.isConfirmUse(order)) {
                unfinish++;
            } else if(OrderUtil.isAutoFinishStatus(order)) {
                if(order.getComments()==0) {
                    uncomment ++;
                }
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unuse", unuse);
        orderInfo.put("unfinish", unfinish);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;
    }

}
