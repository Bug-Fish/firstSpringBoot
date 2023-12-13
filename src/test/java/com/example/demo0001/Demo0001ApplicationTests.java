package com.example.demo0001;

import com.example.demo0001.demos.web.Mapper.DeptMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class Demo0001ApplicationTests {



    @Test
    void contextLoads() {
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",1);
        claims.put("name","zwh");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"demo")//签名算法
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为一个小时，Date是当前时间，System.currentTimeMillis()+3600*1000当前时间向后推一个小时
                .compact();
        System.out.println(jwt);
    }
    @Test
    //对Jwt令牌进行解码。
    public void testJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("demo")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiendoIiwiaWQiOjEsImV4cCI6MTcwMjI5MDk1NX0.YAkSlq0KF2dtK5JJ-UDe-tDtlMpVIxMkJepBK13SXk8")
                .getBody();
        System.out.println(claims);
    }
}
