package com.mybatis;

import com.mybatis.mapper.TestMapper;
import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.Test;
import com.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestT {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        TestMapper testMapper=session.getMapper(TestMapper.class);
        UserMapper userMapper=session.getMapper(UserMapper.class);

        listAllTest(testMapper);
        System.out.println();
        listAllUser(userMapper);
    }
    public static void listAllTest(TestMapper testMapper){
        List<Test> tests=testMapper.getList();
        for (Test test:tests
             ) {
            System.out.println(test+test.getUser().getName());
        }
    }

    public static void listAllUser(UserMapper userMapper){
        List<User> users=userMapper.getList();
        for (User user:users
        ) {
            System.out.println(user);
            List<Test> tests = user.getTests();
                for (Test test : tests
                ) {
                    System.out.println(test.getName());
            }
        }
    }
}
