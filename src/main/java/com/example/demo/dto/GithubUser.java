package com.example.demo.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;
    private String avatar_url;

}
