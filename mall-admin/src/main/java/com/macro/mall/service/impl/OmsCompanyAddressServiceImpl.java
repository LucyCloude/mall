package com.macro.mall.service.impl;

import com.macro.mall.mapper.OmsCompanyAddressMapper;
import com.macro.mall.model.OmsCompanyAddress;
import com.macro.mall.model.OmsCompanyAddressExample;
import com.macro.mall.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公司收发货地址表业务接口实现类
 */
@Service
@Transactional
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {

    @Autowired
    private OmsCompanyAddressMapper omsCompanyAddressMapper;

    @Override//获取所有公司地址
    @Transactional(readOnly = true)
    public List<OmsCompanyAddress> getList() {
        return omsCompanyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
