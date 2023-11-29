package com.example.demo0001.demos.web.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Delete("DELETE FROM users WHERE id = #{id}")
    public void  deleteId(Integer id);
}
