package com.mybatis.mapper;

import com.mybatis.pojo.Test;
import com.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestMapper {
    @Insert("insert into test (id,name,age,power) values(#{id},#{name},#{age},#{user.id})")
    public int add(Test test);

    @Select("select * from test where id = #{id}")
    public Test get(int id);


    //一对多
    @Select("select * from test where power = #{uid}")
    public List<Test> getTestByPower(int uid);


    //多对一
    @Select("select * from test")
    @Results(
            @Result(property = "user",javaType = User.class,column = "power",
            one = @One(select = "com.mybatis.mapper.UserMapper.get"))
    )
    public List<Test> getList();

    @Delete("delete from test where id= #{id}")
    public void delete(int id);

    @Update("update test set name=#{name},age=#{age},power=#{user.id} where id = #{id} ")
    public void update(Test test);

}
