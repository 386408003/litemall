package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.AdminOrderCourseService;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/orderCourse")
@Validated
public class AdminOrderCourseController {
    private final Log logger = LogFactory.getLog(AdminOrderCourseController.class);

    @Autowired
    private AdminOrderCourseService adminOrderService;

    /**
     * 查询订单
     *
     * @param userId
     * @param orderSn
     * @param mobile
     * @param orderStatusArray
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("admin:orderCourse:list")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "订单管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(Integer userId, String orderSn, String mobile,
                       @RequestParam(required = false) List<Short> orderStatusArray,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        return adminOrderService.list(userId, orderSn, mobile, orderStatusArray, page, limit, sort, order);
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:orderCourse:read")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "订单管理"}, button = "详情")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return adminOrderService.detail(id);
    }

    /**
     * 回复订单商品
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @RequiresPermissions("admin:orderCourse:reply")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "订单管理"}, button = "订单商品回复")
    @PostMapping("/reply")
    public Object reply(@RequestBody String body) {
        return adminOrderService.reply(body);
    }

}
