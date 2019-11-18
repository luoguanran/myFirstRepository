package com.example.demo.service;

import com.example.demo.controller.IndexController;
import com.example.demo.dto.MessageDTO;
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


    public List<MessageDTO> get(MessageMapper messageMapper,UserMapper userMapper) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        //获取message

        List<Message> messageList = messageMapper.getMessageList();
        //遍历message中的属性user_id,根据user_id获取发帖者
        for (Message message : messageList) {
            User user = userMapper.findById(message.getUser_id());
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message,messageDTO);
            messageDTO.setUser(user);
            messageDTOList.add(messageDTO);
        }
        return messageDTOList;
    }
}
