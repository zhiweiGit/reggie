package com.reggie.service;

import com.reggie.dao.SetMealMapper;
import com.reggie.pojo.Dish;
import com.reggie.pojo.SetMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetMealService {
    
    @Autowired
    private SetMealMapper setMealMapper;

    public List<SetMeal> findAllSetMeal(){
        return setMealMapper.selectAllSetMeal();
    }

    public Dish findSetMealById(Long id){
        return setMealMapper.selectSetMealById(id);
    }

    public List<Dish> findSetMealByCategory(Long id){
        return setMealMapper.selectSetMealByCategory(id);
    }
}
