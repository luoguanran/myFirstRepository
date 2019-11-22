package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/question")
    public String toQuestion(@RequestParam(name = "question_id")int question_id,
                             Model model){

        MessageDTO messageDTO =messageService.getById(question_id);
        model.addAttribute("message",messageDTO);

        return "question";
    }
}
