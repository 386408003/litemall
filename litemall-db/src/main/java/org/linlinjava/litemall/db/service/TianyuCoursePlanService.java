package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TianyuCourseMapper;
import org.linlinjava.litemall.db.dao.TianyuCoursePlanMapper;
import org.linlinjava.litemall.db.domain.TianyuCoursePlan;
import org.linlinjava.litemall.db.domain.TianyuCoursePlanExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        example.setOrderByClause("start_time asc");
        return coursePlanMapper.selectByExample(example);
    }

}
