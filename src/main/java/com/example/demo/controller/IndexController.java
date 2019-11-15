package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.modle.User;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        UserUtils userUtils = new UserUtils();
        User user = userUtils.getUser(request,userMapper);
        if(user != null)
            request.getSession().setAttribute("user",user);
        return "index";
    }
}
