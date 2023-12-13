package com.example.demo0001.demos.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo0001.demos.web.pojo.Result;
import com.example.demo0001.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源之前运行，true放行，反之不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        //获取请求的url
        String url = req.getRequestURL().toString();
        log.info("要请求的url：{}",url);
        //判断url是否带login登录信息，带则为包含，直接放行
        if(url.contains("login")){
            log.info("放行的url：{}",url);
            return true;
        }
        //获取请求头的令牌
        String jwt = req.getHeader("token");
        //判断令牌是否存在且正确
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空");
            Result result = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(result);
            res.getWriter().write(notLogin);
            return false;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//出现异常说明解析失败
            e.printStackTrace();
            log.info("解析失败，返回错误信息");
            Result result = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(result);
            res.getWriter().write(notLogin);
            return false;
        }
        //放行
        log.info("令牌合法");
        return true;
    }

    @Override//目标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行，最后运行的
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
