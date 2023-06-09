package com.reggie.dao;

import com.reggie.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUserByPhone(String phone);

    User selectUserById(Long id);

    int insertUser(User user);
}
