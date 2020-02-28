package com.macro.mall.component;

import com.macro.mall.util.JwtTokenUtil;
import com.macro.mall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt拦截器  OncePerRequestFilter 他能够确保在一次请求只通过一次filter，而不需要重复执行
 *如果一个单一请求的过程中，filter能够被多个线程调用，也就是意味着一个filter可能在一次请求中被多次执行
 */
public class JwtAuthenTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
/*    @Autowired
    private RedisUtil redisUtil;
    @Value("${redis.redisTime}")
    private Long redisTime;*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);//得到token
        if (authHeader!=null&&authHeader.startsWith(tokenHead)){//判断token是否为null并且前缀是否是tokenHead
            String token = authHeader.substring(tokenHead.length());//截取得到真实的token
            String username = jwtTokenUtil.getUsername(token);//根据token得到用户名
            if (username!=null&&SecurityContextHolder.getContext().getAuthentication()==null){
                /*UserDetails  userDetails = redisUtil.get(username); //从redis中得到用户信息和权限
                if (userDetails==null){
                    System.out.println("从数据库查询用户信息-----------------------------------");
                    userDetails = userDetailsService.loadUserByUsername(username);
                }*/
                UserDetails  userDetails =userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token,userDetails)){//验证token是否过期 true未过期 fakse以过期
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //redisUtil.set(userDetails.getUsername(),userDetails,redisTime);
                    request.setAttribute("adminName",username);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
