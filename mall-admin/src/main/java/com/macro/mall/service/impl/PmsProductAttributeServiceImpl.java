package com.macro.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.PmsProductAttributeDao;
import com.macro.mall.mapper.PmsProductAttributeCategoryMapper;
import com.macro.mall.mapper.PmsProductAttributeMapper;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsProductAttributeCategory;
import com.macro.mall.model.PmsProductAttributeExample;
import com.macro.mall.model.PmsProductExample;
import com.macro.mall.query.PmsProductAttributeParam;
import com.macro.mall.service.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品属性业务实现类
 */
@Service
@Transactional
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;

    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Autowired
    private PmsProductAttributeDao pmsProductAttributeDao;

    @Override//根据id查询商品属性
    public PmsProductAttribute getProAttri(Long id) {
        return pmsProductAttributeMapper.selectByPrimaryKey(id);
    }

    @Override//新增商品属性
    public Integer create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute pmsProductAttribute=new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam,pmsProductAttribute);
        pmsProductAttributeMapper.insertSelective(pmsProductAttribute);//保存商品属性
        //根据商品属性关联的外键id 查询商品属性分类
        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if (pmsProductAttribute.getType()==0){//如果商品属性分类为0 代表为规格   加1
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){//如果商品属性分类为1 代表为参数   加1
            pmsProductAttributeCategory.setParamCount( pmsProductAttributeCategory.getParamCount()+1);
        }
        //修改商品属性分类
        return pmsProductAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
    }

    @Override//修改商品属性
    public Integer update(Long id, PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute pmsProductAttribute=new PmsProductAttribute();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(pmsProductAttributeParam,pmsProductAttribute);
        return pmsProductAttributeMapper.updateByPrimaryKeySelective(pmsProductAttribute);
    }

    @Override
    public Integer delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)){
            return 0;
        }
        //先查询出这些商品属性 后面判断该商品属性类型做增减
        PmsProductAttribute pmsProductAttribute = pmsProductAttributeMapper.selectByPrimaryKey(ids.get(0));
        PmsProductAttributeExample pmsProductAttributeExample=new PmsProductAttributeExample();
        pmsProductAttributeExample.createCriteria().andIdIn(ids);
        int count = pmsProductAttributeMapper.deleteByExample(pmsProductAttributeExample);//删除商品属性

        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        //对应的商品属性分类中的规格或参数数量 根据要删除的商品属性数量做出相应的减少
        if (pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
        }
        return pmsProductAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
    }

    @Override
    @Transactional(readOnly = true)//cid属性分类的id  tyep 0:规格 1:参数
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductAttributeExample pmsProductAttributeExample = new PmsProductAttributeExample();
        pmsProductAttributeExample.createCriteria().andProductAttributeCategoryIdEqualTo(cid).andTypeEqualTo(type);
        return pmsProductAttributeMapper.selectByExample(pmsProductAttributeExample);
    }

    @Override//据商品分类的id查询出商品属性分类和商品属性的id
    public List<Map<String, Long>> getAttriIdAndCateId(Long id) {
        return pmsProductAttributeDao.getAttriIdAndCateId(id);
    }
}
