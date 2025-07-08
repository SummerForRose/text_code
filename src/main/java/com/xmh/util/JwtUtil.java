package com.xmh.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



import java.util.Date;

public class JwtUtil {
    //token过期时间,2 分钟
    public static final long EXPIRE = 1000 * 60 * 2;

    //32位密钥
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //生成Token
    public static String getJwtToken(String username) {
        Date date = new Date();
        Date expiration = new Date(date.getTime() + EXPIRE);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }
    //解析Token
    public static Claims getCheckToken(String jwtToken) {
       return Jwts.parser()
                .setSigningKey(APP_SECRET)  //这个表示密钥
                .parseClaimsJws(jwtToken)//这个含义是解析token
                .getBody();
    }
}
