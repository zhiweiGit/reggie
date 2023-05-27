package com.reggie.service;

import com.reggie.dao.SetmealDishMapper;
import com.reggie.pojo.SetmealDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishService {

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    public SetmealDish findSetmealDishById(Long id){
        return setmealDishMapper.selectSetmealDishById(id);
    }

    public void insertSetmealDish(SetmealDish setmealDish){
        setmealDishMapper.insertSetmealDish(setmealDish);
    }

    public void deleteSetmealDishBySid(Long id){
        setmealDishMapper.deleteSetmealDishBySid(id);
    }

    public List<SetmealDish> findSetmealDishBySid(Long sid){
        return setmealDishMapper.selectSetmealDishBySid(sid);
    }


}
