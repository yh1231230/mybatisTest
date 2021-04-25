package com.mybatis;

import com.mybatis.pojo.Hero;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;
import java.io.InputStream;

public class TestHero {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        Hero h = new Hero();
        h.setName("盖伦");
        h.setHp(500);
        h.setDamage(40);
        session.insert("addHero", h);

        session.delete("deleteHero", 1);

        Hero h2 = session.selectOne("getHero", 2);
        h2.setName("娜美");
        session.update("updateHero", h2);

        session.selectOne("getHero", 3);

        List<Hero> heroes = session.selectList("listHero");
        for (Hero hero : heroes) {
            System.out.println(hero.getId()+"   " + hero.getName() +"   "+ hero.getHp()+"   " + hero.getDamage());
        }


    }
}
