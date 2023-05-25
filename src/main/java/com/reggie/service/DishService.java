package com.reggie.service;

import com.reggie.dao.DishMapper;
import com.reggie.pojo.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    
    @Autowired
    private DishMapper dishMapper;

    public List<Dish> findAllDish(){
        return dishMapper.selectAllDish();
    }

    public Dish findDishById(Long id){
        return dishMapper.selectDishById(id);
    }

    public List<Dish> findDishByCategory(Long id){
        return dishMapper.selectDishByCategory(id);
    }
}
