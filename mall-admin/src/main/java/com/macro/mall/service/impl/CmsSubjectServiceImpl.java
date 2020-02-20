package com.macro.mall.service.impl;

import com.macro.mall.mapper.CmsSubjectMapper;
import com.macro.mall.model.CmsSubject;
import com.macro.mall.model.CmsSubjectExample;
import com.macro.mall.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品专题业务实现类
 */
@Service
@Transactional
public class CmsSubjectServiceImpl implements CmsSubjectService {

    @Autowired
    private CmsSubjectMapper cmsSubjectMapper;

    @Override
    @Transactional(readOnly = true)//查询所有商品专题
    public List<CmsSubject> getList() {
        return cmsSubjectMapper.selectByExample(new CmsSubjectExample());
    }
}
