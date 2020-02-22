package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.query.PmsProductParam;
import com.macro.mall.query.PmsProductQueryParam;
import com.macro.mall.query.PmsProductResult;
import com.macro.mall.service.PmsProductService;
import com.macro.mall.service.impl.PmsProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "pmsProductController",description = "商品管理")
@RestController
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService pmsProductService;

    @ApiOperation("保存商品")
    @RequestMapping("/create")
    @PreAuthorize("hasAuthority('pms:product:create')")
    public CommonResult create(@RequestBody PmsProductParam productParam){
        Integer count = pmsProductService.create(productParam);

        return count>0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据商品的id查询商品修改商品")
    @GetMapping("/updateInfo/{id}")
    @PreAuthorize("hasAuthority('pms:product:read')")
    public  CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Long id){
        PmsProductResult updateInfo = pmsProductService.getUpdateInfo(id);

        return CommonResult.success(updateInfo);
    }

    @ApiOperation("更新商品")
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult update(@PathVariable Long id,@RequestBody PmsProductParam pmsProductParam){

        Integer count = pmsProductService.update(id, pmsProductParam);

        return count>0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页查询商品")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:product:read')")
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam pmsProductQueryParam,
                                @RequestParam("pageNum") Integer pageNum,
                                @RequestParam("pageSize") Integer pageSize){
        List<PmsProduct> list = pmsProductService.list(pmsProductQueryParam, pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("移除商品")
    @RequestMapping("/update/deleteStatus")
    @PreAuthorize("hasAuthority('pms:product:delete')")
    public CommonResult updateStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("deleteStatus") Integer deleteStatus){
        Integer count = pmsProductService.updateStatus(ids, deleteStatus);

        return count>0 ? CommonResult.success(count) : CommonResult.failed();
    }


    @ApiOperation("批量修改商品的上下架")
    @PostMapping("/update/publishStatus")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public  CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                             @RequestParam("publishStatus") Integer publishStatus){
        Integer count = pmsProductService.updatePublishStatus(ids, publishStatus);

        return count>0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量修改商品的推荐")
    @PostMapping("/update/recommendStatus")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult updateRecommandStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus")Integer recommendStatus){
        Integer count = pmsProductService.updateRecommandStatus(ids, recommendStatus);

        return  count>0 ? CommonResult.success(count) : CommonResult.failed();
    }


    @ApiOperation("批量修改商品的新品")
    @PostMapping("/update/newStatus")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public  CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("newStatus") Integer newStatus){
        Integer count = pmsProductService.updateNewStatus(ids, newStatus);

        return  count>0?CommonResult.success(count):CommonResult.failed();
    }
}
