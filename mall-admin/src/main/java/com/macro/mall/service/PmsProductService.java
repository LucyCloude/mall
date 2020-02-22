package com.macro.mall.service;

import com.macro.mall.model.PmsProduct;
import com.macro.mall.query.PmsProductParam;
import com.macro.mall.query.PmsProductQueryParam;
import com.macro.mall.query.PmsProductResult;

import java.util.List;

/**
 * 商品管理业务接口
 */
public interface PmsProductService {

    /**
     * 保存商品
     * @param pmsProductParam 商品及关联的属性
     * @return
     */
    Integer create(PmsProductParam pmsProductParam);

    /**
     * 查询单个商品及属性
     * @param id 商品的id
     * @return
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * 分页查询商品
     * @param pmsProductQueryParam 查询条件
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @return
     */
    List<PmsProduct> list(PmsProductQueryParam pmsProductQueryParam,Integer pageNum,Integer pageSize);


    /**
     * 移除商品
     * @param ids
     * @param deleteStatus 0:未移除 1:已移除
     * @return
     */
    Integer updateStatus(List<Long> ids,Integer deleteStatus);


    /**
     * 批量修改商品的上下架
     * @param ids  商品的id
     * @param publishStatus 商品的上下架 0:下架  1:上架
     * @return
     */
    Integer updatePublishStatus(List<Long> ids,Integer publishStatus);

    /**
     *批量修改商品的推荐
     * @param ids 商品的id
     * @param recommandStatus 0:不推荐  1:推荐
     * @return
     */
    Integer updateRecommandStatus(List<Long> ids,Integer recommandStatus);

    /**
     * 批量修改商品的新品
     * @param ids 商品的id
     * @param newStatus 0:不新品  1:新品
     * @return
     */
    Integer updateNewStatus(List<Long> ids,Integer newStatus);

    /**
     * 修改商品
     * @param id 修改的商品的id
     * @param pmsProductParam 新的商品
     * @return
     */
    Integer update(Long id,PmsProductParam pmsProductParam);
}
