package com.example.demo0001.demos.web.Controller;

import com.example.demo0001.demos.web.Mapper.EmpMapper;
import com.example.demo0001.demos.web.Service.EmpService;
import com.example.demo0001.demos.web.aop.Log;
import com.example.demo0001.demos.web.pojo.Emp;
import com.example.demo0001.demos.web.pojo.PageBean;
import com.example.demo0001.demos.web.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    // TODO SpringBoot通过注解把new对象交给了Spring底层去做，你虽然你没有new这个对象，但是你加了@Component注解，系统就自动帮你new，你自己直接使用就行非常方便；具体可以参考这个链接：https://zhuanlan.zhihu.com/p/523343141
    @Autowired
    private EmpService empService;
//    @GetMapping
//    public Result pages(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue="10")  Integer pageSize){
//        log.info("分页查询：{}，{}",page,pageSize);
//        Emp emp = empService.pages(page,pageSize);
//        return Result.success(emp);
//    }
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue="10")  Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询：{}，{},{}，{},{}，{}",page,pageSize,name,gender,begin,end);
        PageBean pagebean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pagebean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public  Result deleteById(@PathVariable List<Integer> ids){
        log.info("要删除的员工：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result addById(@RequestBody Emp emp){
        log.info("增加的员工信息：{}",emp);
          empService.addById(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("要查询的人员id：{}",id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping
    public Result updateById(@RequestBody Emp emp){
        log.info("修改部门：{}",emp);
        empService.updateById(emp);
        return Result.success();
    }

}
