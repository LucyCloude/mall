package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.query.PmsProductParam;
import com.macro.mall.query.PmsProductQueryParam;
import com.macro.mall.query.PmsProductResult;
import com.macro.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "pmsProductController",description = "商品管理")
@Controller
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService pmsProductService;

    @ApiOperation("保存商品")
    @RequestMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:create')")
    public CommonResult create(@RequestBody PmsProductParam productParam){
        Integer success = pmsProductService.create(productParam);
        if (success>0){
            return CommonResult.success(success);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据商品的id查询商品修改商品")
    @GetMapping("/updateInfo/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:read')")
    public  CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Long id){
        PmsProductResult updateInfo = pmsProductService.getUpdateInfo(id);
        return CommonResult.success(updateInfo);
    }

    @ApiOperation("更新商品")
    @PostMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult update(@PathVariable Long id,@RequestBody PmsProductParam pmsProductParam){
        Integer count = pmsProductService.update(id, pmsProductParam);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }



    @ApiOperation("分页查询商品")
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:read')")
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam pmsProductQueryParam,
                                @RequestParam("pageNum") Integer pageNum,
                                @RequestParam("pageSize") Integer pageSize){
        List<PmsProduct> list = pmsProductService.list(pmsProductQueryParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("批量修改商品的上下架")
    @PostMapping("/update/publishStatus")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:update')")
    public  CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                             @RequestParam("publishStatus") Integer publishStatus){
        Integer count = pmsProductService.updatePublishStatus(ids, publishStatus);
        if (count>0){
            return  CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量修改商品的推荐")
    @PostMapping("/update/recommendStatus")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult updateRecommandStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus")Integer recommendStatus){
        Integer count = pmsProductService.updateRecommandStatus(ids, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }
        return  CommonResult.failed();
    }


    @ApiOperation("批量修改商品的新品")
    @PostMapping("/update/newStatus")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:update')")
    public  CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("newStatus") Integer newStatus){
        Integer count = pmsProductService.updateNewStatus(ids, newStatus);
        if (count>0){
            return CommonResult.success(count);
        }
        return  CommonResult.failed();
    }
}
