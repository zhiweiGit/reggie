package com.reggie.dao;

import com.reggie.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    int insertShoppingCart(ShoppingCart shoppingCart);

    List<ShoppingCart> selectShoppingCartByUserId(Long userId);

    ShoppingCart selectShoppingCartById(Long id);

    int addNumber(ShoppingCart shoppingCart);

    ShoppingCart selectCartByCondition(ShoppingCart shoppingCart);

    int deleteCartByUid(Long uid);

    int deleteCartById(Long id);

    int subCart(ShoppingCart shoppingCart);

}
