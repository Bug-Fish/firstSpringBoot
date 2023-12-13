package com.example.demo0001.demos.web.Service.impl;

import com.example.demo0001.demos.web.Mapper.EmpMapper;
import com.example.demo0001.demos.web.Service.EmpService;
import com.example.demo0001.demos.web.aop.Log;
import com.example.demo0001.demos.web.pojo.Emp;
import com.example.demo0001.demos.web.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end) {
        Integer  count = empMapper.count();
        Integer start = (page-1)*pageSize;
         List <Emp> empList = empMapper.page(start,pageSize,name,gender,begin,end);
         PageBean pagebean = new PageBean(count,empList);
        return pagebean;
    }
    @Override
    public void deleteById(List<Integer> ids) {
        empMapper.deleteById(ids);
    }

    @Override
    public void addById(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addById(emp);
    }
    @Log
    @Override
    public Emp selectById(Integer id) {
        return empMapper.selectById(id);
    }
    @Log
    @Override
    public void updateById(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
//    @Override
//    public Emp pages(Integer page, Integer pageSize) {
//        Emp emp = empMapper.pages(page,pageSize);
//        return emp;
//    }
}
