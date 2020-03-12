package com.macro.mall.service;

import com.macro.mall.model.OmsOrderSetting;

/**
 *订单设置业务接口
 */
public interface OmsOrderSettingService {

    /**
     * 查询出订单设置信息
     * @param id
     * @return
     */
    OmsOrderSetting getOrderSetting(Long id);

    /**
     * 修改订单设置
     * @param id
     * @param omsOrderSetting
     * @return
     */
    Integer update(Long id,OmsOrderSetting omsOrderSetting);
}
