package com.macro.mall.controller;

import com.macro.mall.common.CommonResult;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.service.UmsAdminService;
import com.macro.mall.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 用户登录
     * @param umsAdmin
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdmin umsAdmin){
        String token = umsAdminService.login(umsAdmin.getUsername(), umsAdmin.getPassword());
        if (token==null){
            return  CommonResult.validateFailed("账号或密码错误!");
        }
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @PostMapping(value = "/info")
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        //根据用户名查询出用户信息
        UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(principal.getName());
        Map<String, Object> data = new HashMap<>();
        data.put("username",umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon",umsAdmin.getIcon());
        return CommonResult.success(data);
    }
}
