package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TianyuCourseMapper;
import org.linlinjava.litemall.db.dao.TianyuCoursePlanMapper;
import org.linlinjava.litemall.db.domain.TianyuCourse;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.domain.TianyuCoursePlanExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TianyuCoursePlanService {
    @Resource
    private TianyuCoursePlanMapper coursePlanMapper;

    @Resource
    private TianyuCourseMapper courseMapper;

    public List<TianyuCoursePlan> queryIndex() {
        TianyuCoursePlanExample example = new TianyuCoursePlanExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        return coursePlanMapper.selectByExample(example);
    }

    public List<TianyuCoursePlan> querySelective(LocalDate startDate, LocalDate endDate, Integer page, Integer limit, String sort, String order) {
        TianyuCoursePlanExample example = new TianyuCoursePlanExample();
        TianyuCoursePlanExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
            criteria.andCDateBetween(startDate,endDate);
        } else if (!StringUtils.isEmpty(startDate)) {
            criteria.andCDateGreaterThanOrEqualTo(startDate);
        } else if (!StringUtils.isEmpty(endDate)) {
            criteria.andCDateLessThanOrEqualTo(endDate);
        }

        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return coursePlanMapper.selectByExample(example);
    }

    public int updateById(TianyuCoursePlan coursePlan) {
        coursePlan.setUpdateTime(LocalDateTime.now());
        return coursePlanMapper.updateByPrimaryKeySelective(coursePlan);
    }

    public void deleteById(Integer id) {
        coursePlanMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(TianyuCoursePlan coursePlan) {
        coursePlan.setAddTime(LocalDateTime.now());
        coursePlan.setUpdateTime(LocalDateTime.now());
        coursePlanMapper.insertSelective(coursePlan);
    }

    public TianyuCoursePlan findById(Integer id) {
        return coursePlanMapper.selectByPrimaryKey(id);
    }

    public int count() {
        TianyuCoursePlanExample example = new TianyuCoursePlanExample();
        example.or().andDeletedEqualTo(false);

        return (int) coursePlanMapper.countByExample(example);
    }

    public List<TianyuCoursePlan> findByCDate(LocalDate addDate) {
        TianyuCoursePlanExample example = new TianyuCoursePlanExample();
        TianyuCoursePlanExample.Criteria criteria = example.createCriteria();
        criteria.andCDateEqualTo(addDate);
        criteria.andDeletedEqualTo(false);
        return coursePlanMapper.selectByExample(example);
    }

    // TODO 此处 开始结束时间，课程ID后续优化放置配置表中，下面两个日期处理可放置util类中
    public List<TianyuCoursePlan> addBat(LocalDate addDate) {
        LocalTime startTime = stringToLocalTime("08:00");
        LocalTime endTime = null;
        List<TianyuCoursePlan> coursePlans = new ArrayList<TianyuCoursePlan>();
        while (true) {
            // 一对一私教
            TianyuCourse course = courseMapper.selectByPrimaryKey(1);
            endTime = addLocalTime(startTime, course.getTotalTime());
            TianyuCoursePlan coursePlan = new TianyuCoursePlan();
            coursePlan.setCourseId(course.getId());
            coursePlan.setcDate(addDate);
            coursePlan.setPeopleLeft(course.getPeopleNum());
            coursePlan.setStartTime(startTime);
            coursePlan.setEndTime(endTime);
            coursePlan.setAddTime(LocalDateTime.now());
            coursePlan.setUpdateTime(LocalDateTime.now());
            coursePlanMapper.insertSelective(coursePlan);

            coursePlans.add(coursePlan);
            startTime = endTime;
            endTime = null;

            LocalTime endStatus = stringToLocalTime("20:00");
            if(endStatus.getHour() <= startTime.getHour()) {
                break;
            }
        }
        return coursePlans;
    }

    /**
     * 格式 HH:mm
     *
     * @param localTimeStr
     * @return
     */
    private static LocalTime stringToLocalTime(String localTimeStr) {
        LocalTime localTime = null;
        if(localTimeStr == null || "".equals(localTimeStr)) {
            //localTime = LocalTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            localTime = LocalTime.parse(localTimeStr, formatter);
        }
        return localTime;
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
