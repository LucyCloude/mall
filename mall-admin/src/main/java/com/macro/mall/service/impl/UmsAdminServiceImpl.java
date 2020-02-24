package com.macro.mall.service.impl;

import com.macro.mall.component.AdminUserDetails;
import com.macro.mall.dao.UmsRolePermissionRelationDao;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.service.UmsAdminService;
import com.macro.mall.util.JwtTokenUtil;
import com.macro.mall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsRolePermissionRelationDao umsRolePermissionRelationDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /*@Autowired
    private RedisUtil redisUtil;
    @Value("${redis.redisTime}")
    private Long redisTime;*/
    //用户登录
    @Override
    @Transactional(readOnly = true)
    public String login(String username, String password) {
        try {
            //得到用户信息和权限
            UserDetails userDetails = loadUserByUsername(username);
            //判断密码是否正确
            if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())){
                throw  new RuntimeException("密码不正确!");
            }
            //SpringSecurity保存当前用户的信息
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //redisUtil.set(userDetails.getUsername(),userDetails,redisTime);//根据用户名保存用户信息
            return jwtTokenUtil.getToken(userDetails);//获取token
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    //根据用户名从数据库获取用户
    @Override
    @Transactional(readOnly = true)
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample umsAdminExample=new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdmins!=null&&umsAdmins.size()>0){
            return umsAdmins.get(0);
        }
        return null;
    }

    @Override//获取用户+权限
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);//得到用户信息
        if (admin!=null){
            List<UmsPermission> permissionList = getPermissionList(admin.getId());//得到用户的所有权限
            return new AdminUserDetails(admin,permissionList);//返回UserDetails对象
        }
        throw new UsernameNotFoundException("账号或密码错误!");
    }

    @Override//获取用户的所有权限
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsRolePermissionRelationDao.getUserPermissionList(adminId);
    }

}
