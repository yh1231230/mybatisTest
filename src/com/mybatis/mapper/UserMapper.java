package com.mybatis.mapper;

import com.mybatis.pojo.Test;
import com.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("insert into user (id,name) values(#{id},#{name}")
    public int add(User user);


    //多对一
    @Select("select * from user where id = #{id}")
    public User get(int id);

    //一对多
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "tests" ,javaType = List.class,column = "id",
            many = @Many(select = "com.mybatis.mapper.TestMapper.getTestByPower"))
    })
    public List<User> getList();

    @Delete("delete from user where id= #{id}")
    public void delete(int id);

    @Update("update user set name=#{name} where id = #{id} ")
    public void update(User user);

}
