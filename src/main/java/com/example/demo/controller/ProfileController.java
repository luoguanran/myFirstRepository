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
    private MessageService messageService;

    @RequestMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @PathVariable(name = "action") String action) {

        if (action.equals("question")) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的帖子");
        } else if (action.equals("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        } else if (action.equals("mentions")) {
            model.addAttribute("section", "mentions");
            model.addAttribute("sectionName", "提及我的");
        } else if (action.equals("letters")) {
            model.addAttribute("section", "letters");
            model.addAttribute("sectionName", "私信");
        }


        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return "redirect:/";

        int user_id = user.getId();
        List<Message_PageDTO> messageDTOList = messageService.getByUser(page, user_id);
        if (messageDTOList.size() != 0) {
            model.addAttribute("totalPage", messageDTOList.get(0).getTotal_page());
            model.addAttribute("messageDTOList", messageDTOList);
        } else
            model.addAttribute("totalPage", 1);
        model.addAttribute("page", page);

        return "profile";
    }

}
