package com.macro.mall.service;


import com.macro.mall.model.PmsBrand;
import com.macro.mall.query.PmsBrandParam;

import java.util.List;

/**
 * 商品品牌业务接口
 */
public interface PmsBrandService {
    /**
     * 查询所有的商品品牌
     * @return 所有的商品品牌
     */
    List<PmsBrand> listAllBrand();

    /**
     * 分页查询出商品品牌
     * @return
     */
    List<PmsBrand> listBrand(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 添加商品品牌
     * @param pmsBrandParam
     * @return
     */
    Integer create(PmsBrandParam pmsBrandParam);

    /**
     * 查询单个商品品牌
     * @param id
     * @return
     */
    PmsBrand getPmsBrand(Long id);

    /**
     * 更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    Integer update(Long id,PmsBrandParam pmsBrandParam);


    /**
     * 删除品牌
     * @param id
     * @return
     */
    Integer delete(Long id);

    /**
     * 批量修改商品品牌是否是制造商
     * @param ids
     * @param factoryStatus
     * @return
     */
    Integer updateFactoryStatus(List<Long> ids,Integer factoryStatus);

    /**
     * 批量修改商品品牌是否显示
     * @param ids
     * @param showStatus
     * @return
     */
    Integer updateShowStatus(List<Long> ids,Integer showStatus);
}
