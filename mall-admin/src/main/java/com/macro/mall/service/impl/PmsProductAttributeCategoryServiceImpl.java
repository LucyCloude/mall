package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.PmsProductAttributeCategoryDao;
import com.macro.mall.domain.PmsProductAttributeCategoryItem;
import com.macro.mall.mapper.PmsProductAttributeCategoryMapper;
import com.macro.mall.mapper.PmsProductAttributeMapper;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsProductAttributeCategory;
import com.macro.mall.model.PmsProductAttributeCategoryExample;
import com.macro.mall.model.PmsProductAttributeExample;
import com.macro.mall.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品的属性分类业务实现类
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;
    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;
    @Autowired
    private PmsProductAttributeCategoryDao pmsProductAttributeCategoryDao;

    @Override//保存商品属性分类
    public Integer create(String name) {
        PmsProductAttributeCategory pmsProductAttributeCategory=new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setName(name);
        pmsProductAttributeCategory.setParamCount(0);
        pmsProductAttributeCategory.setAttributeCount(0);
        return pmsProductAttributeCategoryMapper.insert(pmsProductAttributeCategory);
    }

    @Override//根据商品属性分类的id修改name
    public Integer update(Long id, String name) {
        PmsProductAttributeCategory pmsProductAttributeCategory=new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setName(name);
        PmsProductAttributeCategoryExample pmsProductAttributeCategoryExample=new PmsProductAttributeCategoryExample();
        pmsProductAttributeCategoryExample.createCriteria().andIdEqualTo(id);
        return pmsProductAttributeCategoryMapper.updateByExampleSelective(pmsProductAttributeCategory,pmsProductAttributeCategoryExample);
    }

    @Override//删除商品属性分类
    public Integer delete(Long id) {
        int count = pmsProductAttributeCategoryMapper.deleteByPrimaryKey(id);
        if (count>0){//删除商品属性 根据外键删除
            PmsProductAttributeExample pmsProductAttributeExample=new PmsProductAttributeExample();
            pmsProductAttributeExample.createCriteria().andProductAttributeCategoryIdEqualTo(id);
            pmsProductAttributeMapper.deleteByExample(pmsProductAttributeExample);
        }
        return count;
    }

    @Override//访问查询商品的属性分类
    public List<PmsProductAttributeCategory> getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return pmsProductAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }

    @Override//查询商品属性分类及商品属性
    public List<PmsProductAttributeCategoryItem> getAttriItem() {
        return pmsProductAttributeCategoryDao.getAttriItem();
    }
}
