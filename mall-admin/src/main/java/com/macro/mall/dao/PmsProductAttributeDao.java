package com.macro.mall.dao;

import java.util.List;
import java.util.Map;

/**
 * 编辑商品分类所用的dao
 */
public interface PmsProductAttributeDao {
    //根据商品分类的id查询出商品属性分类和商品属性的id
   List<Map<String,Long>> getAttriIdAndCateId(Long id);
}
