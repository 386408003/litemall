package org.linlinjava.litemall.admin.service;

import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.db.domain.TianyuCourse;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.service.TianyuCoursePlanService;
import org.linlinjava.litemall.db.service.TianyuCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminCourseService {
    @Autowired
    private TianyuCourseService courseService;

    @Autowired
    private TianyuCoursePlanService coursePlanService;

    public List<TianyuCoursePlan> addBat(LocalDate addDate) {
        LocalTime startTime = DateTimeUtil.stringToLocalTimeShort("08:00");
        LocalTime endTime = null;
        List<TianyuCoursePlan> coursePlans = new ArrayList<TianyuCoursePlan>();
        while (true) {
            // 一对一私教
            TianyuCourse course = courseService.findById(1);
            endTime = addLocalTime(startTime, course.getTotalTime());
            TianyuCoursePlan coursePlan = new TianyuCoursePlan();
            coursePlan.setCourseId(course.getId());
            coursePlan.setcDate(addDate);
            coursePlan.setPeopleLeft(course.getPeopleNum());
            coursePlan.setStartTime(startTime);
            coursePlan.setEndTime(endTime);
            coursePlanService.add(coursePlan);
            coursePlans.add(coursePlan);
            startTime = endTime;
            endTime = null;
            LocalTime endStatus = DateTimeUtil.stringToLocalTimeShort("20:00");
            if(endStatus.getHour() <= startTime.getHour()) {
                break;
            }
        }
        return coursePlans;
    }

    /**
     * HH:mm 格式的 LocalTime 与分钟计算结果以 HH:mm 格式输出
     * @param localTimeParam
     * @param minuteParam
     * @return
     */
    private static LocalTime addLocalTime(LocalTime localTimeParam, int minuteParam) {
        LocalTime localTime = null;
        if(localTimeParam != null) {
            int hour = localTimeParam.getHour();
            int minute = localTimeParam.getMinute();
            int hour2 = ((minute + minuteParam) / 60);
            int minute2 = ((minute + minuteParam) % 60);
            localTime = LocalTime.of(hour + hour2,minute2);
        }
        return localTime;
    }

}
