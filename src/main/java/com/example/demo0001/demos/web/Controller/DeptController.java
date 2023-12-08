package com.example.demo0001.demos.web.Controller;

import com.example.demo0001.demos.web.Service.DeptService;
import com.example.demo0001.demos.web.pojo.Dept;
import com.example.demo0001.demos.web.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@RestController
@RequestMapping("/depts")
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list(){
        log.info("查询全部日志");
        List<Dept>deptList = deptService.list();
        return Result.success(deptList);
    }
    @DeleteMapping("/{id}")
    //PathVariable作用是可以将{id}的值直接传递给id
    public Result delete(@PathVariable Integer id){
        log.info("删除对应的部门信息：{}",id);
        deptService.delete(id);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("对应部门的信息：{}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
