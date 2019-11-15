package com.example.demo.controller;


import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.modle.Message;
import com.example.demo.modle.User;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request,
            Model model
    ){
        User user = new UserUtils().getUser(request,userMapper);
        if(user != null)
            request.getSession().setAttribute("user",user);
        else {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        message.setUser_id(user.getId());
        message.setGmt_create(System.currentTimeMillis());
        message.setGmt_modified(message.getGmt_create());
        messageMapper.create(message);
        return "redirect:/";
    }
}
