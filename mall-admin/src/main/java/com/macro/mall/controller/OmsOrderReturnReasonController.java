package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.OmsOrderReturnReason;
import com.macro.mall.service.OmsOrderReturnReasonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "omsOrderReturnReasonController",description = "退货原因设置管理")
@RestController
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {

    @Autowired
    private OmsOrderReturnReasonService omsOrderReturnReasonService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:order:read')")//分页查询退货原因设置
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        List<OmsOrderReturnReason> list = omsOrderReturnReasonService.list(pageNum, pageSize);
        CommonPage<OmsOrderReturnReason> commonPage = CommonPage.restPage(list);
        return CommonResult.success(commonPage);
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('oms:order:update')")//保存退货原因设置
    public CommonResult create(@RequestBody OmsOrderReturnReason omsOrderReturnReason){
        int count = omsOrderReturnReasonService.create(omsOrderReturnReason);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('oms:order:read')")//查询单个退货原因设置
    public CommonResult<OmsOrderReturnReason> getReturnReason(@PathVariable Long id){
        OmsOrderReturnReason returnReason = omsOrderReturnReasonService.getReturnReason(id);
        return  CommonResult.success(returnReason);
    }
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('oms:order:update')")//查询单个退货原因设置
    public CommonResult updateReturnReason(@PathVariable Long id,@RequestBody OmsOrderReturnReason omsOrderReturnReason){
        int count = omsOrderReturnReasonService.updateReturnReason(id, omsOrderReturnReason);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('oms:order:delete')")//查询单个退货原因设置
    public CommonResult deleteReturnReason(@RequestParam("ids") List<Long> ids){
        int count = omsOrderReturnReasonService.deleteReturnReason(ids);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }
}
