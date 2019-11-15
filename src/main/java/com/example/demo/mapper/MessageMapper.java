package com.example.demo.mapper;

import com.example.demo.modle.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MessageMapper {
    @Insert("INSERT INTO message(user_id,title,content,time,clicks,gmt_create,gmt_modified,comment) " +
            "VALUES (#{user_id},#{title},#{content},#{time},#{clicks},#{gmt_create},#{gmt_modified},#{comment})")
    void create(Message message);
}
