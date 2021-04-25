package com.mybatis.pojo;

import com.mybatis.mapper.HeroMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestHeroMapper {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        HeroMapper heroMapper=session.getMapper(HeroMapper.class);
        delete(heroMapper,2);
        addHero(heroMapper);
        listAll(heroMapper);
    }

    public static void listAll(HeroMapper heroMapper){
        List<Hero> heroList= heroMapper.listHero();
        for (Hero hero:heroList
             ) {
            System.out.println(hero.toString());
        }
    }


    public static void addHero(HeroMapper heroMapper){
        Hero hero=new Hero(4,"盖伦",500,20);
        heroMapper.add(hero);
    }

    public static void delete(HeroMapper heroMapper,int id){
       heroMapper.delete(id);
    }
}
