package com.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.pojo.ShoppingCart;
import com.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("add")
    public String add(@RequestBody ShoppingCart shoppingCart, HttpSession session){
        shoppingCart.setId(IdUtil.getSnowflakeNextId());
        shoppingCart.setUserId((Long) session.getAttribute("user"));
        shoppingCartService.insertShoppingCart(shoppingCart);
        return JSON.toJSONString(R.success(shoppingCart));
    }

    @GetMapping("list")
    public String list(HttpSession session){
        Long uid = (Long) session.getAttribute("user");
        if(uid==null){
            return JSON.toJSONString(R.error("未登录"));
        }
        List<ShoppingCart> cart = shoppingCartService.findShoppingCartByUserId(uid);
        return JSON.toJSONString(R.success(cart));
    }

    //删除
    @DeleteMapping("clean")
    public String delete(HttpSession session){
        shoppingCartService.deleteCartByUid((Long) session.getAttribute("user"));
        return JSON.toJSONString(R.success("成功"));
    }

    @PostMapping("sub")
    public String sub(@RequestBody ShoppingCart shoppingCart, HttpSession session) {
        shoppingCart.setUserId((Long) session.getAttribute("user"));
        shoppingCartService.subCart(shoppingCart);
        return JSON.toJSONString(R.success("成功"));
    }
}
