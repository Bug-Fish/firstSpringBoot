package com.example.demo0001.demos.web.Service.impl;


import com.example.demo0001.demos.web.Mapper.DeptMapper;
import com.example.demo0001.demos.web.Mapper.EmpMapper;
import com.example.demo0001.demos.web.Service.DeptLogService;
import com.example.demo0001.demos.web.Service.DeptService;
import com.example.demo0001.demos.web.pojo.Dept;
import com.example.demo0001.demos.web.pojo.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.delete(id);//只是根据ID删除了部门，部门对应的员工还有
            empMapper.deleteByDeptId(id);//也删除对应的员工信息
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
    @Override
    public Dept getById(Integer id) {
        Dept dept = deptMapper.getById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }



}
