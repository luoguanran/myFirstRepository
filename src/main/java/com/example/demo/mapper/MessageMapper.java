package com.example.demo.mapper;

import com.example.demo.modle.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Mapper
@Import(EnableAutoConfiguration.class)
public interface MessageMapper {
    @Select("SELECT * FROM message")
    List<Message> getMessageList();

    @Insert("INSERT INTO message(user_id,title,content,time,clicks,gmt_create,gmt_modified,comment) " +
            "VALUES (#{user_id},#{title},#{content},#{time},#{clicks},#{gmt_create},#{gmt_modified},#{comment})")
    void create(Message message);

    @Select("SELECT * FROM message ORDER BY id LIMIT #{firstMessage},10")
    List<Message> getMessageListByPage(@Param("firstMessage") int firstMessage);

    @Select("SELECT COUNT(1) FROM message")
    int totalMessage();

    @Select("SELECT * FROM message WHERE user_id=#{user_id} ORDER BY gmt_create LIMIT #{firstMessage},5")
    List<Message> getMessageListByUser(@Param("firstMessage") int firstMessage,
                                       @Param("user_id") int user_id
                                       );
    @Select("SELECT COUNT(1) FROM message WHERE user_id=#{user_id}")
    int totalMessageOfUser(@Param("user_id") int user_id);

    @Select("SELECT * FROM message WHERE id=#{id}")
    Message findMessageById(@Param("id") int id);

}
