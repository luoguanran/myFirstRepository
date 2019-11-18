package com.example.demo.mapper;

import com.example.demo.modle.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM message")
    List<Message> getMessageList();

    @Insert("INSERT INTO message(user_id,title,content,time,clicks,gmt_create,gmt_modified,comment) " +
            "VALUES (#{user_id},#{title},#{content},#{time},#{clicks},#{gmt_create},#{gmt_modified},#{comment})")
    void create(Message message);
}
