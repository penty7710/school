package com.hut.jsj.utils;

import com.hut.jsj.vo.Admin;
import com.hut.jsj.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwtutil {

    @Value("hutjsj!")
    private String secret;

    //签发token
    public String creatToken(User user){
        Map<String,Object> map =new HashMap();
        map.put("user",user);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(map)  //设置内容
                .setIssuedAt(new Date(System.currentTimeMillis()))   //设置签发时间
                .setExpiration(new Date(System.currentTimeMillis()+60*60*24*1000))  //设置过期时间
                .signWith(SignatureAlgorithm.HS256,secret); //设置算法和密钥
        return jwtBuilder.compact();
    }


    //解析jwt
    public Claims parseJwt(String token){
       return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
