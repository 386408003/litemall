package org.linlinjava.litemall.wx.service;

import org.linlinjava.litemall.db.domain.TianyuCourse;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.service.TianyuCoursePlanService;
import org.linlinjava.litemall.db.service.TianyuCourseService;
import org.linlinjava.litemall.wx.vo.WxCourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WxCourseService {

    @Autowired
    private TianyuCourseService courseService;

    @Autowired
    private TianyuCoursePlanService coursePlanService;

    /**
     * 通过日期查询当天的课程计划
     * @param addDate
     * @return
     */
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
            // 参数时间在当前时间之后
            if(addDate.isAfter(LocalDate.now())) {
                courseInfo.setPeopleLeft(coursePlan.getPeopleLeft());
            // 参数时间与当前时间相同
            } else if(addDate.isEqual(LocalDate.now())) {
                if(coursePlan.getStartTime().isAfter(LocalTime.now())) {
                    courseInfo.setPeopleLeft(coursePlan.getPeopleLeft());
                } else {
                    courseInfo.setPeopleLeft(0);
                }
            // 参数时间在当前时间之前
            } else {
                courseInfo.setPeopleLeft(0);
            }
            courseInfo.setcDate(coursePlan.getcDate());
            courseInfo.setStartTime(coursePlan.getStartTime());
            courseInfo.setEndTime(coursePlan.getEndTime());
            courseInfos.add(courseInfo);
        }
        return  courseInfos;
    }
}
