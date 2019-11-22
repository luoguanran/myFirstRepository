package com.example.demo.service;

import com.example.demo.controller.IndexController;
import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.Message_PageDTO;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.modle.Message;
import com.example.demo.modle.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;

    public List<Message_PageDTO> getByPage(int page) {
        int firstMessage = (page - 1) * 10;
        List<Message_PageDTO> messageDTOList = new ArrayList<>();
        //获取当前页的message
        List<Message> messageList = messageMapper.getMessageListByPage(firstMessage);
        //获取总页数
        int totalMessage = messageMapper.totalMessage();
        int totalPage = totalMessage % 10 == 0 ? totalMessage / 10 : totalMessage / 10 + 1;
        //遍历message中的属性user_id,根据user_id获取发帖者
        for (Message message : messageList) {
            User user = userMapper.findById(message.getUser_id());
            Message_PageDTO message_pageDTO = new Message_PageDTO();
            BeanUtils.copyProperties(message, message_pageDTO);
            message_pageDTO.setUser(user);
            message_pageDTO.setTotal_page(totalPage);
            messageDTOList.add(message_pageDTO);
        }
        return messageDTOList;
    }

    public List<Message_PageDTO> getByUser(int page, int user_id) {
        int firstMessage = (page - 1) * 5;
        List<Message_PageDTO> messageDTOList = new ArrayList<>();
        //获取当前页的message
        List<Message> messageList = messageMapper.getMessageListByUser(firstMessage, user_id);
        //获取总页数
        int totalMessage = messageMapper.totalMessageOfUser(user_id);
        int totalPage = totalMessage % 5 == 0 ? totalMessage / 5 : totalMessage / 5 + 1;
        //遍历message中的属性user_id,根据user_id获取发帖者
        for (Message message : messageList) {
            User user = userMapper.findById(message.getUser_id());
            Message_PageDTO message_pageDTO = new Message_PageDTO();
            BeanUtils.copyProperties(message, message_pageDTO);
            message_pageDTO.setUser(user);
            message_pageDTO.setTotal_page(totalPage);
            messageDTOList.add(message_pageDTO);
        }

        return messageDTOList;
    }
}
