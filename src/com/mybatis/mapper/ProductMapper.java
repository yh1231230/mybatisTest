package com.mybatis.mapper;

import com.mybatis.pojo.Category;
import com.mybatis.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.PrimitiveIterator;

public interface ProductMapper {
    @Insert("insert into product (id,name,price,cid) values(#{id},#{name},#{price},#{category.id})")
    public int add(Product product);

    @Delete("delete from product where id=#{id}")
    public void delete(int id);

    @Update("update product set name=#{name},price=#{price},cid=#{category.id} where id=#{id} ")
    public int update(Product product);

    @Select("select * from product where id=#{id}")
    public Product get(int id);


    //一对多，一个category对应多个product
    @Select(" select * from product where cid = #{cid}")
    public List<Product> listByCategory(int cid);


    //多对一,多个product对应一个category(多个column="cid"对应一个category)
    @Select(" select * from product ")
    @Results({
            @Result(property="category",javaType = Category.class,column="cid",
                    one=@One(select="com.mybatis.mapper.CategoryMapper.get"))
    })
    public List<Product> list();

}
