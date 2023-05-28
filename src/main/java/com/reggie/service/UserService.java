package com.reggie.service;

import com.reggie.dao.UserMapper;
import com.reggie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public User findUserById(Long id) {
        return userMapper.selectUserById(id);
    }
}
