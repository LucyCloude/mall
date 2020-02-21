package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.PmsBrand;
import com.macro.mall.query.PmsBrandParam;
import com.macro.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "pmsdBranController",description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsdBranController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("查询所有商品品牌")
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> listAll(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @ApiOperation("分页查询商品品牌")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword",required = false)String keyword,
                                @RequestParam(value = "pageNum",defaultValue ="1")Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue ="5")Integer pageSize){
        List<PmsBrand> pmsBrands = pmsBrandService.listBrand(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrands));
    }

    @ApiOperation("添加商品品牌")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public  CommonResult create(@RequestBody PmsBrandParam pmsBrand){
        Integer count = pmsBrandService.create(pmsBrand);
        if (count>0){
            return CommonResult.success(count);
        }
        return  CommonResult.failed();
    }

    @ApiOperation("查询单个商品品牌")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public  CommonResult<PmsBrand> getPmsBrand(@PathVariable Long id){
        return CommonResult.success(pmsBrandService.getPmsBrand(id));
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody PmsBrandParam pmsBrandParam){
        Integer count = pmsBrandService.update(id, pmsBrandParam);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除商品品牌")
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommonResult delete(@PathVariable Long id){
        Integer count = pmsBrandService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }
        return  CommonResult.failed();
    }



    @ApiOperation("批量修改商品品牌制造商")
    @PostMapping("/update/factoryStatus")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus){
        Integer count = pmsBrandService.updateFactoryStatus(ids, factoryStatus);
        if (count>0){
            return  CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation("批量修改商品品牌显示")
    @PostMapping("/update/showStatus")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus){
        Integer count = pmsBrandService.updateShowStatus(ids, showStatus);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
