package com.example.demo0001.demos.web.Controller;

import com.example.demo0001.demos.web.service.EmpService;
import com.example.demo0001.demos.web.pojo.Emp;
import com.example.demo0001.demos.web.pojo.Result;
import com.example.demo0001.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("输入的登录参数：{}",emp);
        Emp e = empService.login(emp);
        if(e!=null){
            //登录成功下发令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);//有当前登陆的员工信息
//            JwtUtils.parseJWT(jwt);
            return Result.success(jwt);
        }else return Result.error("用户或者密码错误");//登录失败下发错误信息
    }
}
