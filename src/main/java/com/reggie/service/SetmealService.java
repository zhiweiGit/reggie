package com.reggie.service;

import cn.hutool.core.util.IdUtil;
import com.reggie.dao.SetMealMapper;
import com.reggie.dto.SetmealDto;
import com.reggie.pojo.SetMeal;
import com.reggie.pojo.SetmealDish;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private SetmealDishService setmealDishService;

    public List<SetMeal> findAllSetMeal() {
        return setMealMapper.selectAllSetMeal();
    }

    public SetMeal findSetMealById(Long id) {
        return setMealMapper.selectSetMealById(id);
    }

    public List<SetMeal> findSetMealByCategory(Long id) {
        return setMealMapper.selectSetMealByCategory(id);
    }

    public List<SetMeal> findSetMealByNameAndLimit(String name, int page, int pageSize) {
        return setMealMapper.selectSetMealByNameAndLimit(name, (page - 1) * pageSize, pageSize);
    }

    public int findSetmealCount() {
        return setMealMapper.selectSeatMealCount();
    }


    @Transactional
    public void insertSetmeal(SetmealDto setmealDto, Long uid) {
        setmealDto.setCreateUser(uid);
        setmealDto.setUpdateUser(uid);
        setmealDto.setId(IdUtil.getSnowflakeNextId());
        for (SetmealDish setmealDish : setmealDto.getSetmealDishes()) {
            setmealDish.setId(IdUtil.getSnowflakeNextId());
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setCreateUser(uid);
            setmealDish.setUpdateUser(uid);
            setmealDishService.insertSetmealDish(setmealDish);
        }
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setmealDto, setMeal);
        setMealMapper.insertSeatMeal(setMeal);
    }

    @Transactional
    public void deleteSetmeal(Long[] ids) {
        for (Long id : ids) {
            setMealMapper.deleteSeatMeal(id);
            setmealDishService.deleteSetmealDishBySid(id);
        }
    }

    @Transactional
    public void updateSetmeal(SetmealDto setmealDto,Long uid) {
        setmealDto.setUpdateUser(uid);
        setMealMapper.updateSetMeal(setmealDto);
        setmealDishService.deleteSetmealDishBySid(setmealDto.getId());
        for (SetmealDish setmealDish : setmealDto.getSetmealDishes()) {
            setmealDish.setId(IdUtil.getSnowflakeNextId());
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setCreateUser(uid);
            setmealDish.setUpdateUser(uid);
            setmealDishService.insertSetmealDish(setmealDish);
        }
    }

    public void updateStatus(Long[] ids, Integer status) {
        for (Long id : ids) {
            setMealMapper.updateStatus(id, status);
        }
    }
}
