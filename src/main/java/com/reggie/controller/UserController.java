package com.reggie.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.pojo.User;
import com.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("sendMsg")
    public String getCode(@RequestBody Map map, HttpSession session){
        String code = RandomUtil.randomNumbers(4);
        session.setAttribute("code",code);
        log.info("验证码为："+code);
        return JSON.toJSONString(R.success("成功"));
    }

    @PostMapping("login")
    public String login(@RequestBody Map map,HttpSession session){
        String code =  (String)session.getAttribute("code");
        if(code==null){
            return JSON.toJSONString(R.error("请重新获取验证码"));
        }
        if (code.equals(map.get("code"))) {
            String phone = (String) map.get("phone");
            User user = userService.findUserByPhone(phone);
            if(user==null){
                user=new User();
                user.setId(IdUtil.getSnowflakeNextId());
                user.setPhone(phone);
                userService.insertUser(user);
            }
            session.setAttribute("user",user.getId());
            return JSON.toJSONString(R.success(user));
        }
        return JSON.toJSONString(R.error("验证码错误"));
    }
}
