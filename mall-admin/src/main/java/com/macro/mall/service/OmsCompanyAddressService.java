package com.macro.mall.service;

import com.macro.mall.model.OmsCompanyAddress;

import java.util.List;

/**
 * 公司收发货地址表业务接口
 */
public interface OmsCompanyAddressService {
    /**
     * 获取所有公司地址
     * @return
     */
    List<OmsCompanyAddress> getList();
}
