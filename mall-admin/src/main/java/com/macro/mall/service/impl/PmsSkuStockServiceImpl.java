package com.macro.mall.service.impl;

import com.macro.mall.mapper.PmsSkuStockMapper;
import com.macro.mall.model.PmsSkuStock;
import com.macro.mall.model.PmsSkuStockExample;
import com.macro.mall.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品SUK业务类
 */
@Service
@Transactional
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;

    @Transactional(readOnly = true)
    @Override//根据商品的id和sku编码的模糊查询
    public List<PmsSkuStock> getPmsSkuStorckAll(Long id, String keyword) {
        PmsSkuStockExample pmsSkuStockExample = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = pmsSkuStockExample.createCriteria();
        criteria.andProductIdEqualTo(id);
        if (!StringUtils.isEmpty(keyword)){
            criteria.andSkuCodeLike("%"+keyword+"%");
        }
        return pmsSkuStockMapper.selectByExample(pmsSkuStockExample);
    }
}
