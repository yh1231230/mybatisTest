package com.mybatis.mapper;

import com.mybatis.pojo.Hero;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HeroMapper {
  //  @Insert("insert into hero (id,name,hp,damage) values (#{id},#{name},#{hp},#{damage})")
    public int add(Hero hero);

 //   @Delete("delete from hero where id=#{id}")
    public void delete(int id);

  //  @Select("select * from hero where id=#{id}")
    public Hero get(int id);

  //  @Select("select * from hero")
    public List<Hero> listHero();
}
