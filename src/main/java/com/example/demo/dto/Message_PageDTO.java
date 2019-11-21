package com.example.demo.dto;

import com.example.demo.modle.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
public class Message_PageDTO {
    private int id;
    private int user_id;
    private String title;
    private String content;
    private Date time;
    private int clicks;
    private int comment;
    private long gmt_create;
    private long gmt_modified;
    private User user;
    private int total_page;
}
