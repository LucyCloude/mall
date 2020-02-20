package com.macro.mall.service;

import com.macro.mall.domain.PmsProductCategoryWithChildrenItem;
import com.macro.mall.model.PmsProductCategory;
import com.macro.mall.query.PmsProductCategoryParam;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 商品分类表业务类
 */
public interface PmsProductCategoryService {

    /**
     * 保存商品分类
     * @param pmsProductCategoryParam
     * @return
     */
    public Integer create(PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 修改商品分类
     * @param id 商品分类的id
     * @param pmsProductAttributeParam 新的商品分类
     * @return
     */
    Integer update(Long id,PmsProductCategoryParam pmsProductAttributeParam);

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    Integer delete(Long id);

    /**
     * 修改导航栏是否显示
     * @param ids 需要修改的商品分类的id
     * @param navStatus 0:不显示 1:显示
     * @return
     */
    Integer updateNavStatus(List<Long> ids, Integer navStatus);


    /**
     * 修改是否显示状态
     * @param ids 需要修改的商品分类的id
     * @param showStatus 0:不显示 1:显示
     * @return
     */
    Integer updateShowStatus(List<Long> ids,Integer showStatus);



    /**
     * 商品分类信息查询
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();

    /**
     * 分页查询商品分类
     * @param parentId 父级id
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @return
     */
    List<PmsProductCategory> getList(Long parentId,Integer pageNum,Integer pageSize);

    /**
     * 根据id查询商品分类
     * @param id 商品分类的id
     * @return
     */
    PmsProductCategory getCategory(Long id);

   }
