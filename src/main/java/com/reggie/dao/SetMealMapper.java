package com.reggie.dao;

import com.reggie.pojo.Dish;
import com.reggie.pojo.SetMeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealMapper {

    List<SetMeal> selectAllSetMeal();

    Dish selectSetMealById(Long id);

    List<Dish> selectSetMealByCategory(Long id);
}
