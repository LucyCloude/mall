package com.macro.mall.controller;

import com.macro.mall.common.CommonResult;
import com.macro.mall.model.OmsCompanyAddress;
import com.macro.mall.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "omsCompanyAddressController",produces = "公司收货地址管理")
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Autowired
    private OmsCompanyAddressService omsCompanyAddressService;
    @ApiOperation("获取所有公司地址")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public CommonResult<List<OmsCompanyAddress>> getList(){
        List<OmsCompanyAddress> list = omsCompanyAddressService.getList();
        return CommonResult.success(list);
    }

}
