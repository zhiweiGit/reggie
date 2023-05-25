package com.reggie.dao;

import com.reggie.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    List<Dish> selectAllDish();

    Dish selectDishById(Long id);

    List<Dish> selectDishByCategory(Long id);
}
