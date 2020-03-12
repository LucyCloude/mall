package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.query.OmsOrderDeliveryParam;
import com.macro.mall.query.OmsOrderDetails;
import com.macro.mall.query.OmsOrderQueryParam;
import com.macro.mall.query.OmsReceiverInfoParam;
import com.macro.mall.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "omsOrderController",description = "订单管理")
@RestController
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    private OmsOrderService omsOrderService;

    @ApiOperation("条件分页查询订单")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public CommonResult<CommonPage<OmsOrder>> getList(OmsOrderQueryParam queryParam,
                                                      @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        List<OmsOrder> list = omsOrderService.getList(queryParam, pageNum, pageSize);
        CommonPage<OmsOrder> omsOrderCommonPage = CommonPage.restPage(list);
        return CommonResult.success(omsOrderCommonPage);
    }

    @ApiOperation("查询订单详细信息")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public CommonResult<OmsOrderDetails> getDetails(@PathVariable Long id){
        OmsOrderDetails omsOderDetails = omsOrderService.getOmsOderDetails(id);
        return CommonResult.success(omsOderDetails);
    }

    @ApiOperation("备注订单")
    @PostMapping("/update/note")
    @PreAuthorize("hasAuthority('oms:order:update')")
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status,
                                   HttpServletRequest request){
        String adminName = (String) request.getAttribute("adminName");
        Integer count =omsOrderService.updateNote(id,note,status,adminName);
        return  count>0?CommonResult.success(count):CommonResult.failed();
    }

    @ApiOperation("修改订单收货人信息")
    @PostMapping("/update/receiverInfo")
    @PreAuthorize("hasAuthority('oms:order:update')")
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam omsReceiverInfoParam,
                                           HttpServletRequest request){
        String adminName = (String) request.getAttribute("adminName");
        Integer count = omsOrderService.updateReceiverInfo(omsReceiverInfoParam, adminName);
        return  count>0?CommonResult.success(count):CommonResult.failed();
    }

    @ApiOperation("关闭订单")
    @PostMapping("/update/close")
    @PreAuthorize("hasAuthority('oms:order:update')")
    public CommonResult updateColse(@RequestParam("ids") List<Long> ids,
                                    @RequestParam("note") String note,
                                    HttpServletRequest request){
        String adminName = (String) request.getAttribute("adminName");
        Integer count = omsOrderService.updateClose(ids, note, adminName);
        return  count>0?CommonResult.success(count):CommonResult.failed();
    }

    @ApiOperation("删除订单")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('oms:order:delete')")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        Integer count = omsOrderService.delete(ids);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }

    @ApiOperation("批量订单发货")
    @PostMapping("/update/delivery")
    @PreAuthorize("hasAuthority('oms:order:update')")
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> orderDeliveryParamList,
                                 HttpServletRequest request){
        String adminName =(String) request.getAttribute("adminName");
        Integer count = omsOrderService.delivery(orderDeliveryParamList, adminName);
        return count>0?CommonResult.success(count):CommonResult.failed();
    }
}
