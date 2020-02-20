package com.macro.mall.dao;

import com.macro.mall.domain.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 查询商品分类的dao
 */
public interface PmsProductCategoryDao {
    /**
     * 查询商品的分类
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
