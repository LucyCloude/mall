package com.macro.mall.domain;

import com.macro.mall.model.PmsProductCategory;

import java.util.List;

/**
 * 查询商品出商品分类
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    //多方集合
    private List<PmsProductCategory> children;

    public List<PmsProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategory> children) {
        this.children = children;
    }
}
