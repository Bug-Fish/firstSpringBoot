package com.example.demo0001.demos.web.Service;

import com.example.demo0001.demos.web.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<Dept> list();

    void delete(Integer id);


    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);

}
