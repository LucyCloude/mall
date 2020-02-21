package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.query.PmsProductAttributeParam;
import com.macro.mall.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "pmsProductAttribiteController",description = "商品属性管理")
@Controller
@RequestMapping("/productAttribute")
public class PmsProductAttribiteController {

    @Autowired
    private PmsProductAttributeService pmsProductAttributeService;


    @ApiOperation("根据id查询指定商品属性")
    @GetMapping("{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productAttribute:read')")
    public CommonResult<PmsProductAttribute> getProAttri(@PathVariable Long id){
        return CommonResult.success(pmsProductAttributeService.getProAttri(id));
    }

    @ApiOperation("保存商品属性")
    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productAttribute:create')")
    public  CommonResult create(@RequestBody PmsProductAttributeParam pmsProductAttributeParam){
        Integer count = pmsProductAttributeService.create(pmsProductAttributeParam);
        if (count>0){
            return  CommonResult.success(count);
        }
        return  CommonResult.failed();
    }


    @ApiOperation("修改商品属性")
    @PostMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productAttribute:update')")
    public  CommonResult update(@PathVariable Long id,
                                @RequestBody PmsProductAttributeParam pmsProductAttributeParam){
        Integer count = pmsProductAttributeService.update(id, pmsProductAttributeParam);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除商品属性")
    @PostMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productAttribute:delete')")
    public  CommonResult delete(@RequestParam("ids") List<Long> ids){
        Integer count = pmsProductAttributeService.delete(ids);
        if (count>0){
            return  CommonResult.success(count);
        }
        return  CommonResult.failed();
    }

    @ApiOperation("根据商品属性分类的id查询 属性列表 或 参数列表 type 0:代表规格  1:参数")
    @GetMapping("/list/{cid}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productAttribute:read')")
    private CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                  @RequestParam("type") Integer type,
                                                                  @RequestParam(value = "pageNum",defaultValue ="1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        List<PmsProductAttribute> list = pmsProductAttributeService.getList(cid, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据商品分类的id查询出商品属性分类和商品属性")
    @GetMapping("/attrInfo/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:productAttribute:read')")
    public CommonResult<List<Map<String,Long>>> getAttriIdAndCateId(@PathVariable Long id){
        List<Map<String, Long>> attriInfo = pmsProductAttributeService.getAttriIdAndCateId(id);
        return CommonResult.success(attriInfo);
    }
}
