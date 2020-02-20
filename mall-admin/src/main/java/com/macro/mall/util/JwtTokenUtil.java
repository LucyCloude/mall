package com.macro.mall.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.model.UmsAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 */
@Component
public class JwtTokenUtil {
    private static final String USERNAME = "sub";
    private static final String CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 生成token
     * @param map 包含用户名和登录时间
     * @return
     */
    private String getToken(Map<String,Object> map){
       return Jwts.builder()
               .setClaims(map)//设置负载
               .setExpiration(getExpiration())//设置过期时间
               .signWith(SignatureAlgorithm.HS512,secret)//设置HS512签名算法 secret加盐
               .compact();//生成token
    }

    /**
     * 设置过期时间
     * @return token过期时间
     */
    private Date getExpiration(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    public String getToken(UserDetails userDetails){
        Map<String, Object> map = new HashMap<>();
        map.put(USERNAME,userDetails.getUsername());//用户名
        map.put(CREATED,new Date());//登录时间
        return getToken(map);
    }

    /**
     * 根据token获取负载
     * @param token
     * @return
     */
    private Claims getClaims(String token){
       try{
           return Jwts.parser()
                   .setSigningKey(secret)//加盐
                   .parseClaimsJws(token)//token
                   .getBody();
       }catch (Exception e){
           System.out.println(e.getMessage());
            return null;
       }
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsername(String token){
        Claims claims = getClaims(token);
        if (claims!=null){
            return claims.getSubject();//获取用户名
        }
        return  null;
    }

    /**
     * 验证token是否过期
     * @param token
     * @param userDetails
     * @return  true未过期 false已过期
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUsername(token);
        return username.equals(userDetails.getUsername())&& isExpiration(token);
    }

    /**
     *token是否过期
     * @return true未过期 false已过期
     */
    public boolean isExpiration(String token){
        Claims claims = getClaims(token);
        return new Date().before(claims.getExpiration());
    }

}
