package com.mybatis;

import com.mybatis.mapper.CategoryMapper;
import com.mybatis.mapper.ProductMapper;
import com.mybatis.pojo.Category;
import com.mybatis.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestProduct {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        ProductMapper productMapper=session.getMapper(ProductMapper.class);
        CategoryMapper categoryMapper=session.getMapper(CategoryMapper.class);


        insert(productMapper,categoryMapper);
        System.out.println(get(productMapper,7).getName());
        System.out.println("-------------------------");


        listAll(productMapper);
        session.commit();

    }

    public static Product get(ProductMapper productMapper,int id){
        return productMapper.get(id);
    }

    public static void insert(ProductMapper productMapper,CategoryMapper categoryMapper){
        Category category=categoryMapper.get(1);
        Product product=new Product(7,"product d",88.88f);
        product.setCategory(category);
        productMapper.add(product);

    }

    public static void listAll(ProductMapper productMapper){
        List<Product> productList=productMapper.list();
        for (Product p:productList
             ) {
            System.out.println(p+"对应的分类是"+p.getCategory().getName());
        }
        System.out.println();
    }
}
