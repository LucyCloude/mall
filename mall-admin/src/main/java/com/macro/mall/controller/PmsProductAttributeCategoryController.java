package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.domain.PmsProductAttributeCategoryItem;
import com.macro.mall.model.PmsProductAttributeCategory;
import com.macro.mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "pmsProductAttributeCategoryController",description = "商品属性分类管理")
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService pmsProductAttributeCategoryService;


    @ApiOperation("保存商品属性分类")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('pms:productAttribute:create')")
    public CommonResult create(@RequestParam String name){
        Integer count = pmsProductAttributeCategoryService.create(name);
        if (count>0){
            return  CommonResult.success(count);
        }
        return  CommonResult.failed();
    }

    @ApiOperation("分页查询出所有 商品属性分类")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:productAttribute:read')")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){

        List<PmsProductAttributeCategory> list = pmsProductAttributeCategoryService.getList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("修改商品属性分类的名称")
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('pms:productAttribute:update')")
    public CommonResult update(@PathVariable Long id,@RequestParam String name){
        Integer count = pmsProductAttributeCategoryService.update(id, name);
        if (count>0){
            return  CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除商品属性分类")
    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('pms:productAttribute:delete')")
    public  CommonResult delete(@PathVariable Long id){
        Integer count = pmsProductAttributeCategoryService.delete(id);
        if (count>0){
            return  CommonResult.success(count);
        }
        return  CommonResult.failed();
    }


    @ApiOperation("商品分类页面  查询商品属性分类及商品属性")
    @GetMapping("/list/withAttr")
    @PreAuthorize("hasAuthority('pms:productAttribute:read')")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getAttriItem(){
        return CommonResult.success(pmsProductAttributeCategoryService.getAttriItem());
    }
}
