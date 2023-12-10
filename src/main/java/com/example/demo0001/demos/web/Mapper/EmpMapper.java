package com.example.demo0001.demos.web.Mapper;

import com.example.demo0001.demos.web.pojo.Emp;
import com.example.demo0001.demos.web.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select count(*) from emp")
    public  Integer count();

   List<Emp> page(@Param("start") Integer start,@Param("pageSize") Integer pageSize,
                         @Param("name") String name,@Param("gender") Short gender,
                         @Param("begin") LocalDate begin,@Param("end") LocalDate end);

    void deleteById(@Param("ids") List<Integer> ids);
    @Insert("insert into emp (username,name,gender,image,job,entrydate,dept_id,create_time,update_time)" +
            " values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addById(Emp emp);
    @Select("select * from emp where id=#{id}")
    Emp selectById(Integer id);
    //@Update("update emp set image=#{image},username=#{username},name=#{name},gender=#{gender},job=#{job},entrydate=#{entrydate},dept_id=#{deptId},update_time=#{updateTime} where id=#{id}")
    void updateById(Emp emp );
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
//    @Select("select * from emp limit #{page},#{pageSize}")
//    Emp pages(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
}
