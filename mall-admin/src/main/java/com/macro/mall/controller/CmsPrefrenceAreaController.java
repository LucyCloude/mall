package com.macro.mall.controller;

import com.macro.mall.common.CommonResult;
import com.macro.mall.model.CmsPrefrenceArea;
import com.macro.mall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "cmsPrefrenceAreaController",description = "商品优选管理")
@Controller
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService cmsPrefrenceAreaService;

    @ApiOperation("查询全部商品优选")
    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<CmsPrefrenceArea>> getList(){
        return CommonResult.success(cmsPrefrenceAreaService.getList());
    }
}
