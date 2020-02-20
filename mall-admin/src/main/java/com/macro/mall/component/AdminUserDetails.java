package com.macro.mall.component;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;//当前用户
    private List<UmsPermission> umsPermissions;//当前用户的权限

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> umsPermissions) {
        this.umsAdmin = umsAdmin;
        this.umsPermissions = umsPermissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       //获取用户的权限
        return   umsPermissions.stream()
                //拦截掉权限为空的对象
                .filter(umsPermission -> umsPermission.getValue() != null)
                //权限归纳到SimpleGranteAuthority对象中
                .map(umsPermission -> new SimpleGrantedAuthority(umsPermission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
