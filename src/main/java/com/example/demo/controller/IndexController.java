package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.modle.Message;
import com.example.demo.modle.User;
import com.example.demo.service.MessageService;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        UserUtils userUtils = new UserUtils();
        User user = userUtils.getUser(request,userMapper);
        if(user != null)
            request.getSession().setAttribute("user",user);

        MessageService messageService = new MessageService();
        List<MessageDTO> messageDTOList = messageService.get(messageMapper,userMapper);
        model.addAttribute("messageDTOList",messageDTOList);
        return "index";
    }
}
