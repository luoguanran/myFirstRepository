package com.example.demo.modle;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Message {
    private int id;
    private int user_id;
    private String title;
    private String content;
    private Date time;
    private int clicks;
    private int comment;
    private long gmt_create;
    private long gmt_modified;

}
