package com.mybatis.mapper;

import com.mybatis.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {
    @Insert(" insert into category (name) values(#{name})")
    public  int add(Category category);

    @Delete("delete from category where id=#{id}")
    public  void delete(int id);

    @Update("update category set name=#{name} where id=#{id}")
    public int update(Category category);


    //一对多，一个category对应多个product（一个column = "id"对应多个property）
    @Select(" select * from category ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "products", javaType = List.class, column = "id",
                    many = @Many(select = "com.mybatis.mapper.ProductMapper.listByCategory") )
    })
    public List<Category> list();


    //多对一,多个product对应一个category
    @Select("select * from category where id=#{id}")
    public  Category get(int id);


}
