package com.example.demo.utils;

import com.example.demo.mapper.UserMapper;
import com.example.demo.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtils {

    public User getUser(HttpServletRequest request,UserMapper userMapper){
        Cookie[] cookies = request.getCookies();
        User user = null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                  String token = cookie.getValue();
                  user = userMapper.findByToken(token);
                  break;

            }
        }
        return user;
    }

}
