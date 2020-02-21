package com.macro.mall.controller;

import com.macro.mall.common.CommonResult;
import com.macro.mall.model.CmsSubject;
import com.macro.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "cmsSubjectController",description = "商品专题管理")
@RestController
@RequestMapping("/subject")
public class CmsSubjectController {

    @Autowired
    private CmsSubjectService cmsSubjectService;

    @ApiOperation("查询全部商品专题")
    @RequestMapping("/listAll")
    public CommonResult<List<CmsSubject>> getList(){
        List<CmsSubject> list = cmsSubjectService.getList();
        return CommonResult.success(list);
    }
}
