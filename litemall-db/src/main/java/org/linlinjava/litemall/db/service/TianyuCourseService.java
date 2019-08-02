package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TianyuCourseMapper;
import org.linlinjava.litemall.db.domain.TianyuCourse;
import org.linlinjava.litemall.db.domain.TianyuCourseExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TianyuCourseService {
    @Resource
    private TianyuCourseMapper courseMapper;

    public List<TianyuCourse> queryIndex() {
        TianyuCourseExample example = new TianyuCourseExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        return courseMapper.selectByExample(example);
    }

    public List<TianyuCourse> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        TianyuCourseExample example = new TianyuCourseExample();
        TianyuCourseExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return courseMapper.selectByExample(example);
    }

    public int updateById(TianyuCourse course) {
        course.setUpdateTime(LocalDateTime.now());
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    public void deleteById(Integer id) {
        courseMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(TianyuCourse course) {
        course.setAddTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.insertSelective(course);
    }

    public TianyuCourse findById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    public int count() {
        TianyuCourseExample example = new TianyuCourseExample();
        example.or().andDeletedEqualTo(false);

        return (int) courseMapper.countByExample(example);
    }
}
