package com.macro.mall.dao;

import com.macro.mall.domain.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 商品属性分类及商品属性 dao
 */
public interface PmsProductAttributeCategoryDao {
    /**
     * 查询商品属性分类及商品属性
     * @return
     */
    List<PmsProductAttributeCategoryItem> getAttriItem();
}
