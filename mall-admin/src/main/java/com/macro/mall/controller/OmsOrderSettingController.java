package com.macro.mall.controller;

import com.macro.mall.common.CommonResult;
import com.macro.mall.model.OmsOrderSetting;
import com.macro.mall.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "omsOrderSettingController",description = "订单设置管理")
@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService omsOrderSettingService;

    @ApiOperation("获取订单设置")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public CommonResult<OmsOrderSetting> orderSetting(@PathVariable Long id){
        OmsOrderSetting orderSetting = omsOrderSettingService.getOrderSetting(id);
        return CommonResult.success(orderSetting);
    }
    @ApiOperation("修改订单参数")
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('oms:order:update')")
    public CommonResult updateOrderSetting(@PathVariable Long id,
                                           @RequestBody OmsOrderSetting omsOrderSetting){
        Integer count = omsOrderSettingService.update(id, omsOrderSetting);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }
}
