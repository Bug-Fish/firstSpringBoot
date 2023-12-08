package com.example.demo0001;

import com.example.demo0001.demos.web.Mapper.DeptMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo0001ApplicationTests {

    @Autowired
    private DeptMapper userMapper;

//    @Test
//    void contextLoads() {
//        userMapper.deleteId(16);
//    }

}
