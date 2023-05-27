package com.reggie.service;

import com.reggie.dao.ShoppingCartMapper;
import com.reggie.pojo.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public void insertShoppingCart(ShoppingCart shoppingCart) {
        if (findCartByCondition(shoppingCart) == null) {
            shoppingCartMapper.insertShoppingCart(shoppingCart);
        } else {
            addNumber(shoppingCart);
        }

    }

    public List<ShoppingCart> findShoppingCartByUserId(Long userId) {
        return shoppingCartMapper.selectShoppingCartByUserId(userId);
    }

    public ShoppingCart findShoppingCartById(Long id) {
        return shoppingCartMapper.selectShoppingCartById(id);
    }

    public void addNumber(ShoppingCart shoppingCart) {
        shoppingCartMapper.addNumber(shoppingCart);
    }

    public ShoppingCart  findCartByCondition(ShoppingCart shoppingCart){
        return shoppingCartMapper.selectCartByCondition(shoppingCart);
    }

    public void deleteCartByUid(Long uid){
        shoppingCartMapper.deleteCartByUid(uid);
    }

    public void deleteCartById(Long id){
        shoppingCartMapper.deleteCartById(id);
    }

    public void subCart(ShoppingCart shoppingCart){
        shoppingCartMapper.subCart(shoppingCart);
        ShoppingCart cart = findCartByCondition(shoppingCart);
        if(cart!=null&&cart.getNumber()==0){
            deleteCartById(cart.getId());
        }
    }
}
