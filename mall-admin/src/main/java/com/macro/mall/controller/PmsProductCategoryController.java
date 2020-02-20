package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.domain.PmsProductCategoryWithChildrenItem;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsProductCategory;
import com.macro.mall.query.PmsProductCategoryParam;
import com.macro.mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsProductCategoryController",description = "商品分类管理")
@Controller
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;


    @ApiOperation("添加商品分类")
    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:read')")
    public CommonResult create(@RequestBody PmsProductCategoryParam pmsProductCategoryParam){
        Integer count = pmsProductCategoryService.create(pmsProductCategoryParam);
        if (count>0){
            return  CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据商品分类的id修改商品分类")
    @PostMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:update')")
    public  CommonResult update(@PathVariable Long id,
                                @RequestBody PmsProductCategoryParam pmsProductCategoryParam){
        Integer count = pmsProductCategoryService.update(id, pmsProductCategoryParam);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:delete')")
    public  CommonResult delete(@PathVariable Long id){
        Integer count = pmsProductCategoryService.delete(id);
        if (count>0){
            return  CommonResult.success(count);
        }
        return  CommonResult.failed();
    }

    @ApiOperation("修改商品分类的导航栏是否显示")
    @PostMapping("/update/navStatus")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:update')")
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("navStatus") Integer navStatus){
        Integer count = pmsProductCategoryService.updateNavStatus(ids, navStatus);
        if (count>0){
            return  CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation("修改商品分类的是否显示状态")
    @PostMapping("/update/showStatus")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:update')")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("showStatus") Integer showStatus){
        Integer count = pmsProductCategoryService.updateShowStatus(ids, showStatus);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("根据id获取商品分类")
    @GetMapping("{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:read')")
    public CommonResult<PmsProductCategory> getCategory(@PathVariable Long id){
        PmsProductCategory category = pmsProductCategoryService.getCategory(id);
        return  CommonResult.success(category);
    }


    @ApiOperation("查询一级分类及子分类")
    @GetMapping("/list/withChildren")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:read')")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> withChildren(){
        return CommonResult.success(pmsProductCategoryService.listWithChildren());
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping("/list/{parentId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productCategory:read')")
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageNum",defaultValue = "5") Integer pageNum,
                                                                @RequestParam(value = "pageSize",defaultValue = "1") Integer pageSize){
        List<PmsProductCategory> list = pmsProductCategoryService.getList(parentId, pageNum, pageSize);
        return  CommonResult.success(CommonPage.restPage(list));
    }

}
