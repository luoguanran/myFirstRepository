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
    private MessageService messageService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") int page
    ) {

        List<Message_PageDTO> messageDTOList = messageService.getByPage(page);
        model.addAttribute("messageDTOList", messageDTOList);
        model.addAttribute("page", page);
        return "index";
    }
}

