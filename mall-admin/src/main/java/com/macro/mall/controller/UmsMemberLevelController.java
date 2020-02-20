package com.macro.mall.controller;

import com.macro.mall.common.CommonPage;
import com.macro.mall.common.CommonResult;
import com.macro.mall.model.UmsMemberLevel;
import com.macro.mall.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags ="umsMemberLevelController",description = "业务等级管理")
@Controller
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService umsMemberLevelService;


    @ApiOperation("查询所有会员等级")
    @GetMapping("/list")
    @ResponseBody//defaultStatus 是否为默认等级 0不是 1是默认等级
    public CommonResult<List<UmsMemberLevel>> getList(@RequestParam("defaultStatus") Integer defaultStatus){
        List<UmsMemberLevel> list = umsMemberLevelService.getList(defaultStatus);
        return CommonResult.success(list);
    }

}
