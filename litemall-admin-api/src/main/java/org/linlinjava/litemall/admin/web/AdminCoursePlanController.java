package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.AdminCourseService;
import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.service.TianyuCoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.COURSE_PLAN_EXIST;

@RestController
@RequestMapping("/admin/coursePlan")
@Validated
public class AdminCoursePlanController {
    private final Log logger = LogFactory.getLog(AdminCoursePlanController.class);

    @Autowired
    private TianyuCoursePlanService coursePlanService;
    @Autowired
    private AdminCourseService adminCourseService;

    @RequiresPermissions("admin:coursePlan:list")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程安排"}, button = "查询")
    @GetMapping("/list")
    public Object list(String startDate, String endDate,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<TianyuCoursePlan> coursePlanList = coursePlanService.querySelective(DateTimeUtil.stringToLocalDate(startDate), DateTimeUtil.stringToLocalDate(endDate), page, limit, sort, order);
        return ResponseUtil.okList(coursePlanList);
    }

    private Object validate(TianyuCoursePlan coursePlan) {
        return null;
    }

    @RequiresPermissions("admin:coursePlan:create")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程安排"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody TianyuCoursePlan coursePlan) {
        Object error = validate(coursePlan);
        if (error != null) {
            return error;
        }
        coursePlanService.add(coursePlan);
        return ResponseUtil.ok(coursePlan);
    }

    @RequiresPermissions("admin:coursePlan:read")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程安排"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        TianyuCoursePlan coursePlan = coursePlanService.findById(id);
        return ResponseUtil.ok(coursePlan);
    }

    @RequiresPermissions("admin:coursePlan:update")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程安排"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody TianyuCoursePlan coursePlan) {
        Object error = validate(coursePlan);
        if (error != null) {
            return error;
        }
        if (coursePlanService.update(coursePlan) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok(coursePlan);
    }

    @RequiresPermissions("admin:coursePlan:delete")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程安排"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TianyuCoursePlan coursePlan) {
        Integer id = coursePlan.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        coursePlanService.deleteById(id);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:coursePlan:createBat")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程安排"}, button = "批量添加")
    @PostMapping("/createBat")
    public Object createBat(String addDate) {
        if (addDate == null || "".equals(addDate)) {
            return ResponseUtil.badArgument();
        }
        List<TianyuCoursePlan> coursePlanList = coursePlanService.findByCDate(DateTimeUtil.stringToLocalDate(addDate));
        if(coursePlanList != null && coursePlanList.size() > 0) {
            return ResponseUtil.fail(COURSE_PLAN_EXIST, "当日已有课程数据存在，不可批量添加");
        }
        List<TianyuCoursePlan> rtnCoursePlanList = adminCourseService.addBat(DateTimeUtil.stringToLocalDate(addDate));
        return ResponseUtil.okList(rtnCoursePlanList);
    }

}
