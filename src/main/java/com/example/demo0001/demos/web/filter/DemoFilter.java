package com.example.demo0001.demos.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demo0001.demos.web.pojo.Result;
import com.example.demo0001.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override//拦截方法会调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        //获取请求的url
        String url = req.getRequestURL().toString();
        log.info("要请求的url：{}",url);
       //判断url是否带login登录信息，带则为包含，直接放行
        if(url.contains("login")){
            log.info("放行的url：{}",url);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
       //获取请求头的令牌
        String jwt = req.getHeader("token");
       //判断令牌是否存在且正确
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空");
            Result result = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(result);
            res.getWriter().write(notLogin);
            return;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//出现异常说明解析失败
            e.printStackTrace();
            log.info("解析失败，返回错误信息");
            Result result = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(result);
            res.getWriter().write(notLogin);
            return;
        }
        //放行
        log.info("令牌合法");
        filterChain.doFilter(req,res);
    }
}
