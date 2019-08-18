package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallAdMapper;
import org.linlinjava.litemall.db.domain.LitemallAd;
import org.linlinjava.litemall.db.domain.LitemallAdExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAdService {
    @Resource
    private LitemallAdMapper adMapper;

    public List<LitemallAd> queryIndex() {
        LitemallAdExample example = new LitemallAdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return adMapper.selectByExample(example);
    }

    public List<LitemallAd> queryByPosition(byte position) {
        LitemallAdExample example = new LitemallAdExample();
        LitemallAdExample.Criteria criteria1 = example.or();
        LitemallAdExample.Criteria criteria2 = example.or();
        LitemallAdExample.Criteria criteria3 = example.or();
        LitemallAdExample.Criteria criteria4 = example.or();

        criteria1.andStartTimeIsNull().andEndTimeIsNull();
        criteria2.andStartTimeIsNull().andEndTimeGreaterThanOrEqualTo(LocalDateTime.now());
        criteria3.andStartTimeLessThanOrEqualTo(LocalDateTime.now()).andEndTimeIsNull();
        criteria4.andStartTimeLessThanOrEqualTo(LocalDateTime.now()).andEndTimeGreaterThanOrEqualTo(LocalDateTime.now());

        criteria1.andPositionEqualTo(position).andDeletedEqualTo(false).andEnabledEqualTo(true);
        criteria2.andPositionEqualTo(position).andDeletedEqualTo(false).andEnabledEqualTo(true);
        criteria3.andPositionEqualTo(position).andDeletedEqualTo(false).andEnabledEqualTo(true);
        criteria4.andPositionEqualTo(position).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return adMapper.selectByExample(example);
    }

    public List<LitemallAd> querySelective(String name, String content, Integer position, Integer page, Integer limit, String sort, String order) {
        LitemallAdExample example = new LitemallAdExample();
        LitemallAdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        if (!StringUtils.isEmpty(position)) {
            criteria.andPositionEqualTo(position.byteValue());
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adMapper.selectByExample(example);
    }

    public int updateById(LitemallAd ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        adMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallAd ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        adMapper.insertSelective(ad);
    }

    public LitemallAd findById(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }
}
