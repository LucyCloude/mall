package com.macro.mall.service.impl;

import com.macro.mall.mapper.UmsMemberLevelMapper;
import com.macro.mall.model.UmsMemberLevel;
import com.macro.mall.model.UmsMemberLevelExample;
import com.macro.mall.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员等级业务实现类
 */
@Service
@Transactional
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    @Autowired
    private UmsMemberLevelMapper umsMemberLevelMapper;
    @Override
    @Transactional(readOnly = true)//查询所有会员等级
    public List<UmsMemberLevel> getList(Integer defaultStatus) {
        return umsMemberLevelMapper.selectByExample(new UmsMemberLevelExample());
    }
}
