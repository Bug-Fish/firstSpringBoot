package com.example.demo0001.demos.web.Controller;

import com.example.demo0001.demos.web.Service.EmpService;
import com.example.demo0001.demos.web.pojo.Emp;
import com.example.demo0001.demos.web.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return Result.success();
        }else return Result.error("用户或者密码错误");
    }
}
