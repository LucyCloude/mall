package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.PmsProductCategoryDao;
import com.macro.mall.domain.PmsProductCategoryWithChildrenItem;
import com.macro.mall.mapper.PmsProductCategoryAttributeRelationMapper;
import com.macro.mall.mapper.PmsProductCategoryMapper;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.*;
import com.macro.mall.query.PmsProductCategoryParam;
import com.macro.mall.service.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品分类业务
 */
@Service
@Transactional
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryDao pmsProductCategoryDao;

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

    @Autowired
    private PmsProductCategoryAttributeRelationMapper pmsProductCategoryAttributeRelationMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;


    @Override//保存商品分类
    public Integer create(PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory pmsProductCategory =new PmsProductCategory();
        BeanUtils.copyProperties(pmsProductCategoryParam,pmsProductCategory);
        setLevel(pmsProductCategory);//判断是否有父级
        int count = pmsProductCategoryMapper.insertSelective(pmsProductCategory);//保存商品分类
        //保存商品分类与商品属性之间的关联
        insertRelation(pmsProductCategoryParam.getProductAttributeIdList(),pmsProductCategory.getId());
        return count;
    }


    @Override//修改属性分类
    public Integer update(Long id, PmsProductCategoryParam pmsProductAttributeParam) {
        PmsProductCategory productCategory=new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductAttributeParam,productCategory);
        setLevel(productCategory);//判断是否是一级或子级
        //修改商品中的分类名称
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setProductCategoryName(productCategory.getName());
        PmsProductExample pmsProductExample=new PmsProductExample();
        pmsProductExample.createCriteria().andProductCategoryIdEqualTo(productCategory.getId());
        pmsProductMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
        //删除原来的关联的商品属性分类
        PmsProductCategoryAttributeRelationExample pmsProductCategoryAttributeRelationExample=new PmsProductCategoryAttributeRelationExample();
        pmsProductCategoryAttributeRelationExample.createCriteria().andProductCategoryIdEqualTo(id);
        pmsProductCategoryAttributeRelationMapper.deleteByExample(pmsProductCategoryAttributeRelationExample);
        //更新现在关联的商品属性分类
        insertRelation(pmsProductAttributeParam.getProductAttributeIdList(),id);
        //根据id修改商品分类信息
        return pmsProductCategoryMapper.updateByPrimaryKey(productCategory);
    }

    @Override//删除商品分类
    public Integer delete(Long id) {
        int count=pmsProductCategoryMapper.deleteByPrimaryKey(id);
        if (count>0){//删除商品分类与关联的商品属性的关系表中的数据
            PmsProductCategoryAttributeRelationExample pmsProductCategoryAttributeRelationExample=new PmsProductCategoryAttributeRelationExample();
            pmsProductCategoryAttributeRelationExample.createCriteria().andProductCategoryIdEqualTo(id);
            pmsProductCategoryAttributeRelationMapper.deleteByExample(pmsProductCategoryAttributeRelationExample);
        }
        return count;
    }

    @Override//修改导航栏是否显示
    public Integer updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory pmsProductCategory=new PmsProductCategory();
        pmsProductCategory.setNavStatus(navStatus);
        PmsProductCategoryExample pmsProductCategoryExample=new PmsProductCategoryExample();
        pmsProductCategoryExample.createCriteria().andIdIn(ids);
        return pmsProductCategoryMapper.updateByExampleSelective(pmsProductCategory,pmsProductCategoryExample);
    }

    @Override//修改是否显示状态
    public Integer updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory pmsProductCategory=new PmsProductCategory();
        pmsProductCategory.setShowStatus(showStatus);
        PmsProductCategoryExample pmsProductCategoryExample=new PmsProductCategoryExample();
        pmsProductCategoryExample.createCriteria().andIdIn(ids);
        return pmsProductCategoryMapper.updateByExampleSelective(pmsProductCategory,pmsProductCategoryExample);
    }

    //判断该保存的商品分类是否有父级
    private void setLevel(PmsProductCategory pmsProductCategory){
        if (pmsProductCategory.getParentId()==0){//判断是否是一级分类
            pmsProductCategory.setLevel(0);
        }else {                                                          //parentId是父级的id
            PmsProductCategory parentCategory = pmsProductCategoryMapper.selectByPrimaryKey(pmsProductCategory.getParentId());
            if (parentCategory!=null){//如果有父级 设置当前商品分类为子级
                pmsProductCategory.setLevel(parentCategory.getLevel()+1);
            }else {
                pmsProductCategory.setLevel(0);
            }
        }
    }
    //保存商品分类与商品属性之间的关联
    private void insertRelation(List<Long> attriIds,Long cateId){
        if(!CollectionUtils.isEmpty(attriIds)){
            for (Long attriId:attriIds) {
                PmsProductCategoryAttributeRelation ppcar=new PmsProductCategoryAttributeRelation();
                ppcar.setProductAttributeId(attriId);
                ppcar.setProductCategoryId(cateId);
                pmsProductCategoryAttributeRelationMapper.insertSelective(ppcar);
            }
        }
    }


    /**
     * 查询商品分类
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return pmsProductCategoryDao.listWithChildren();
    }

    @Override//分页查询商品分类信息 根据父级id分页查询
    public List<PmsProductCategory> getList(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsProductCategoryExample pmsProductCategoryExample =new PmsProductCategoryExample();
        pmsProductCategoryExample.createCriteria().andParentIdEqualTo(parentId);
        return pmsProductCategoryMapper.selectByExample(pmsProductCategoryExample);
    }

    @Override//根据id查询商品分类
    public PmsProductCategory getCategory(Long id) {
        return pmsProductCategoryMapper.selectByPrimaryKey(id);
    }

}
