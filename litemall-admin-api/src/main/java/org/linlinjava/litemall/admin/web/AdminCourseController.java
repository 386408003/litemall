package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.TianyuCourse;
import org.linlinjava.litemall.db.service.TianyuCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/course")
@Validated
public class AdminCourseController {
    private final Log logger = LogFactory.getLog(AdminCourseController.class);

    @Autowired
    private TianyuCourseService courseService;

    @RequiresPermissions("admin:course:list")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<TianyuCourse> courseList = courseService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(courseList);
    }

    private Object validate(TianyuCourse course) {
        String name = course.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @GetMapping("/options")
    public Object options() {
        List<TianyuCourse> courseList = courseService.queryAll();

        List<Map<String, Object>> options = new ArrayList<>(courseList.size());
        for (TianyuCourse course : courseList) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", course.getId());
            option.put("label", course.getName());
            option.put("totalTime", course.getTotalTime());
            option.put("peopleNum", course.getPeopleNum());
            options.add(option);
        }

        return ResponseUtil.okList(options);
    }

    @RequiresPermissions("admin:course:create")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody TianyuCourse course) {
        Object error = validate(course);
        if (error != null) {
            return error;
        }
        courseService.add(course);
        return ResponseUtil.ok(course);
    }

    @RequiresPermissions("admin:course:read")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        TianyuCourse course = courseService.findById(id);
        return ResponseUtil.ok(course);
    }

    @RequiresPermissions("admin:course:update")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody TianyuCourse course) {
        Object error = validate(course);
        if (error != null) {
            return error;
        }
        if (courseService.updateById(course) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok(course);
    }

    @RequiresPermissions("admin:course:delete")
    @RequiresPermissionsDesc(menu = {"天瑜瑜伽", "课程管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TianyuCourse course) {
        Integer id = course.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        courseService.deleteById(id);
        return ResponseUtil.ok();
    }

}
