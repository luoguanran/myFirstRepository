package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.Message_PageDTO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") int page
                        ){
        UserUtils userUtils = new UserUtils();
        User user = userUtils.getUser(request,userMapper);
        if(user != null)
            request.getSession().setAttribute("user",user);

        MessageService messageService = new MessageService();
        List<MessageDTO> messageDTOList = messageService.get(messageMapper,userMapper);
        List<Message_PageDTO> messageDTOList1 = messageService.getByPage(messageMapper,userMapper,page);

        model.addAttribute("messageDTOList",messageDTOList);
        model.addAttribute("messageDTOList1",messageDTOList1);
        model.addAttribute("page",page);
        int myPage = 1;
        if(request.getParameter("myPage") != null)
            myPage = Integer.parseInt(request.getParameter("myPage"));
        model.addAttribute("myPage",myPage);
        return "index";
    }

    @RequestMapping("/toMyPage")
    public String toMyPage(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "myPage",defaultValue = "1") int myPage
    ){
        if(request.getParameter("myPage") != null)
            myPage = Integer.parseInt(request.getParameter("myPage"));
        model.addAttribute("myPage",myPage);

        UserUtils userUtils = new UserUtils();
        User user = userUtils.getUser(request,userMapper);
        if(user != null)
            request.getSession().setAttribute("user",user);

        MessageService messageService = new MessageService();
        List<MessageDTO> messageDTOList = messageService.get(messageMapper,userMapper);
        List<Message_PageDTO> messageDTOList1 = messageService.getByPage(messageMapper,userMapper,myPage);

        model.addAttribute("messageDTOList",messageDTOList);
        model.addAttribute("messageDTOList1",messageDTOList1);
        model.addAttribute("page",myPage);

        return "index";
    }
}
