package com.example.demo0001.demos.web.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.demo0001.demos.web.Mapper.OperateLogMapper;
import com.example.demo0001.demos.web.pojo.OperateLog;
import com.example.demo0001.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect///AOP类
public class TimeAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;
    //@Pointcut("execution(* com.example.demo0001.demos.web.Service.*.*(..))")
    @Pointcut("@annotation(com.example.demo0001.demos.web.aop.Log)")
    public void pt(){}
    @Around("pt()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateId = (Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
       String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        //操作方法返回值
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        log.info(joinPoint.getSignature()+"使用的时间是：{}ms",end-begin);
        long costTime = end-begin;
        OperateLog operateLog = new OperateLog(null,operateId,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("记录操作日志",operateLog);
        return result;
    }
//    @Before("pt()")
//    public void before(JoinPoint joinPoint){
//        log.info("运行了");
//    }
//    @Around("pt()")
//    public Object Test(ProceedingJoinPoint joinPoint) throws Throwable {
//        //开始时间
//        long begin = System.currentTimeMillis();
//        log.info("开始");
//        //1、获取目标对象的类名
//        String className = joinPoint.getTarget().getClass().getName();
//        log.info("类名：{}",className);
//        //2、获取目标方法的方法名
//        String methodName= joinPoint.getSignature().getName();
//        log.info("目标方法的方法名：{}",methodName);
//        //3、获取目标方法运行时传入的参数
//        Object[] objects = joinPoint.getArgs();
//        log.info("传入的参数：{}", Arrays.toString(objects));
//        //4、获取目标方法执行
//        Object result = joinPoint.proceed();
//        //5、获取目标方法运行的返回值
//        log.info("返回值：{}",result);
//        //记录运行时间并计算结束时间
//        long end = System.currentTimeMillis();
//        log.info(joinPoint.getSignature()+"使用的时间是：{}ms",end-begin);
//        return result;
//    }
}
