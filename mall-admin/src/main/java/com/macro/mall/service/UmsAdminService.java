package com.macro.mall.service;


import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UmsAdminService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return token
     */
    String  login(String username,String password);



    /**
     * 根据用户名去数据库查用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /***
     * 获取用户+权限
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     *获取用户的所有权限
     * @param id
     * @return
     */
    List<UmsPermission> getPermissionList(Long id);
}
