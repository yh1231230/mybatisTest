package com.mybatis;

import com.mybatis.mapper.CategoryMapper;
import com.mybatis.pojo.Category;
import com.mybatis.pojo.Product;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestCategory {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session= sqlSessionFactory.openSession();
        CategoryMapper categoryMapper=session.getMapper(CategoryMapper.class);

     //   update(categoryMapper);

        listAll(categoryMapper);

    }

    public static  void update(CategoryMapper categoryMapper){
        Category c1= categoryMapper.get(1);
        c1.setName("category1");
        categoryMapper.update(c1);


        Category c2= categoryMapper.get(2);
        c2.setName("category2");
        categoryMapper.update(c2);

        listAll(categoryMapper);


    }

    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(1);
        System.out.println(c.getName());
    }

    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getId()+c.getName());
            List<Product> products=c.getProducts();
            for (Product p:products
                 ) {
                System.out.println("\t"+p.getName());
            }
        }
    }

}
