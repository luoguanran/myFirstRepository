package com.example.demo.mapper;

import com.example.demo.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
   @Insert("INSERT INTO gitUser(name,account_id,token,gmt_create,gmt_modified,login) VALUES (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{login})")
   void insert(User user);

   @Select("SELECT * FROM gitUser WHERE token = #{token}")
   User findByToken(@Param("token") String token);
}
