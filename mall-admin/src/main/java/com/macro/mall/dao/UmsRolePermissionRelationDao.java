package com.macro.mall.dao;

import com.macro.mall.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和权限的dao
 */
public interface UmsRolePermissionRelationDao {
    //根据用户的id查询出对应的权限
    List<UmsPermission> getUserPermissionList(@Param("adminId") Long adminId);
}
