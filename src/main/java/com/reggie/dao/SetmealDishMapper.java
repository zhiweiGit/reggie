package com.reggie.dao;

import com.reggie.pojo.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    SetmealDish selectSetmealDishById(Long id);

    List<SetmealDish> selectSetmealDishBySid(Long sid);

    int insertSetmealDish(SetmealDish setmealDish);

    int deleteSetmealDishBySid(Long sid);



}
