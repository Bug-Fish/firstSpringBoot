
package com.example.demo0001.demos.web.Service;

import com.example.demo0001.demos.web.pojo.Emp;
import com.example.demo0001.demos.web.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteById(List<Integer> ids);

    void addById(Emp emp);

    Emp selectById(Integer id);

    void updateById(Emp emp);


//    Emp pages(Integer page, Integer pageSize);
}
