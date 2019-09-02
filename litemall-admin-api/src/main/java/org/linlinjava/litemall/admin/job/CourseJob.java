package org.linlinjava.litemall.admin.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.service.AdminCourseService;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.service.TianyuCoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 检测课程是否需要自动添加
 */
@Component
public class CourseJob {
    private final Log logger = LogFactory.getLog(CourseJob.class);

    @Autowired
    private TianyuCoursePlanService coursePlanService;
    @Autowired
    private AdminCourseService adminCourseService;

    /**
     * 自动批量添加课程
     * <p>
     * 定时检查课程情况，如果没有课程则自动添加
     * 定时时间是每天凌晨2点。
     * <p>
     * TODO
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkCoursePlan() {
        logger.info("系统开启任务检查7天后课程是否已经安排");
        LocalDate addDate = LocalDate.now();
        LocalDate cronDate = addDate.plusDays(7);
        List<TianyuCoursePlan> coursePlanList = coursePlanService.findByCDate(cronDate);
        if(coursePlanList != null && coursePlanList.size() > 0) {
            logger.info(cronDate + " 已有课程数据存在，无需批量添加");
        } else {
            adminCourseService.addBat(cronDate);
            logger.info(cronDate + " 课程数据添加成功");
        }
    }

}
