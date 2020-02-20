package com.macro.mall.service;

import com.macro.mall.model.PmsSkuStock;

import java.util.List;

/**
 * 商品SUK业务
 */
public interface PmsSkuStockService {

    /**
     * 根据商品的id和货号模糊查询  查询出SUK
     * @param id 商品的id
     * @param keyword 商品的SUK
     * @return 商品的suk
     */
    List<PmsSkuStock> getPmsSkuStorckAll(Long id,String keyword);
}
