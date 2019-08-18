package org.linlinjava.litemall.wx.service;

import org.linlinjava.litemall.db.domain.TianyuCourse;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.service.TianyuCoursePlanService;
import org.linlinjava.litemall.db.service.TianyuCourseService;
import org.linlinjava.litemall.wx.vo.WxCourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WxCourseService {

    @Autowired
    private TianyuCourseService courseService;

    @Autowired
    private TianyuCoursePlanService coursePlanService;

    public List<WxCourseInfo> findByCDate(LocalDate addDate) {
        List<WxCourseInfo> courseInfos = new ArrayList<WxCourseInfo>();
        List<TianyuCoursePlan> coursePlans = coursePlanService.findByCDate(addDate);
        for(TianyuCoursePlan coursePlan : coursePlans) {
            TianyuCourse tianyuCourse = courseService.findById(coursePlan.getCourseId());
            if (tianyuCourse == null)
                continue;
            WxCourseInfo courseInfo = new WxCourseInfo();
            courseInfo.setCoursePlanId(coursePlan.getId());
            courseInfo.setName(tianyuCourse.getName());
            courseInfo.setPeopleNum(tianyuCourse.getPeopleNum());
            courseInfo.setTotalTime(tianyuCourse.getTotalTime());
            courseInfo.setPeopleLeft(coursePlan.getPeopleLeft());
            courseInfo.setcDate(coursePlan.getcDate());
            courseInfo.setStartTime(coursePlan.getStartTime());
            courseInfo.setEndTime(coursePlan.getEndTime());
            courseInfos.add(courseInfo);
        }
        return  courseInfos;
    }
}
