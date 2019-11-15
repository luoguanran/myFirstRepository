package com.example.demo.modle;

import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String login;
    private String avatar_url;
}
