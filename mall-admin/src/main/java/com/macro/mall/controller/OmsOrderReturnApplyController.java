package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.query.OmsOrderReturnApplyResult;
import com.macro.mall.query.OmsReturnApplyQueryParam;
import com.macro.mall.query.OmsUpdateStatusParam;
import com.macro.mall.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags ="omsOrderReturnApplyController",description = "订单退货管理")
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {

    @Autowired
    private OmsOrderReturnApplyService omsOrderReturnApplyService;

    @ApiOperation("分页查询退货订单")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public CommonResult<CommonPage<OmsOrderReturnApply>> getList(OmsReturnApplyQueryParam queryParam,
                                                                 @RequestParam("pageNum") Integer pageNum,
                                                                 @RequestParam("pageSize") Integer pageSize){
        List<OmsOrderReturnApply> list = omsOrderReturnApplyService.getList(queryParam, pageNum, pageSize);
        CommonPage<OmsOrderReturnApply> omsOrderReturnApplyCommonPage = CommonPage.restPage(list);
        return  CommonResult.success(omsOrderReturnApplyCommonPage);
    }

    @ApiOperation("查询单个退货订单及收货地址")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public CommonResult<OmsOrderReturnApplyResult> getReturnApply(@PathVariable Long id){
        OmsOrderReturnApplyResult returnApply = omsOrderReturnApplyService.getReturnApply(id);
        return CommonResult.success(returnApply);
    }

    @ApiOperation("查询单个退货订单及收货地址")
    @PostMapping("/update/status/{id}")
    @PreAuthorize("hasAuthority('oms:order:update')")
    public CommonResult updateStatus(@PathVariable Long id,
                                     @RequestBody OmsUpdateStatusParam updateStatusParam){
        Integer count = omsOrderReturnApplyService.updateStatus(id, updateStatusParam);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }
}
