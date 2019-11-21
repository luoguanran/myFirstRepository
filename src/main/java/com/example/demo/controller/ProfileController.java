package com.example.demo.controller;

import com.example.demo.dto.Message_PageDTO;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.modle.User;
import com.example.demo.service.MessageService;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;


        @RequestMapping("/profile/{action}")
        public String profile(HttpServletRequest request,
                              Model model,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              @PathVariable(name = "action") String action){

            if(action.equals("question")){
                model.addAttribute("section","question");
                model.addAttribute("sectionName","我的帖子");
            }else if(action.equals("replies")){
                model.addAttribute("section","replies");
                model.addAttribute("sectionName","我的回复");
            }else if(action.equals("mentions")){
                model.addAttribute("section","mentions");
                model.addAttribute("sectionName","提及我的");
            }else if(action.equals("letters")){
                model.addAttribute("section","letters");
                model.addAttribute("sectionName","私信");
            }

            UserUtils userUtils = new UserUtils();
            User user = userUtils.getUser(request, userMapper);
            if (user != null)
                request.getSession().setAttribute("user", user);

            MessageService messageService = new MessageService();
            int user_id = user.getId();
            List<Message_PageDTO> messageDTOList = messageService.getByUser(messageMapper, userMapper, page,user_id);
            model.addAttribute("messageDTOList", messageDTOList);
            model.addAttribute("page", page);

            return "profile" ;
        }

}
