package com.macro.mall.service.impl;

import com.macro.mall.mapper.OmsOrderSettingMapper;
import com.macro.mall.model.OmsOrderSetting;
import com.macro.mall.model.OmsOrderSettingExample;
import com.macro.mall.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单设置业务实现类
 */
@Service
@Transactional
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    @Autowired
    private OmsOrderSettingMapper omsOrderSettingMapper;

    @Override//查询订单设置
    @Transactional(readOnly = true)
    public OmsOrderSetting getOrderSetting(Long id) {
        return omsOrderSettingMapper.selectByPrimaryKey(id);
    }

    @Override//修改订单设置
    public Integer update(Long id, OmsOrderSetting omsOrderSetting) {
        OmsOrderSettingExample omsOrderSettingExample=new OmsOrderSettingExample();
        omsOrderSettingExample.createCriteria().andIdEqualTo(id);
        omsOrderSetting.setId(id);
        return omsOrderSettingMapper.updateByExampleSelective(omsOrderSetting,omsOrderSettingExample);
    }

}
