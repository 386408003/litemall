package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.service.TianyuCourseService;
import org.linlinjava.litemall.wx.service.WxCourseService;
import org.linlinjava.litemall.wx.vo.WxCourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/course")
@Validated
public class WxCourseController {
    private final Log logger = LogFactory.getLog(WxCourseController.class);

    @Autowired
    private TianyuCourseService courseService;
    @Autowired
    private WxCourseService courseInfoService;

    @GetMapping("/list")
    public Object list(String cDate) {
        List<Map<String, Object>> calendar = DateTimeUtil.initCalendar(7);
        for(Map<String, Object> tempMap : calendar) {
            List<WxCourseInfo> courseInfoList = courseInfoService.findByCDate((LocalDate) tempMap.get("date"));
            tempMap.put("courseInfo", courseInfoList);
        }
        return ResponseUtil.okList(calendar);
    }

    @GetMapping("/courseInfo")
    public Object courseInfo(@RequestParam String cDate) {
        List<WxCourseInfo> courseInfoList = courseInfoService.findByCDate(DateTimeUtil.stringToLocalDate(cDate));
        return ResponseUtil.okList(courseInfoList);
    }

}
