package com.macro.mall.dao;


import com.macro.mall.query.PmsProductResult;
import org.apache.ibatis.annotations.Param;


/**
 * 查询出商品的及管理的属性
 */
public interface PmsProductResultDao {
    //根据商品的id获取商品及相关的属性
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
