package com.reggie.service;

import cn.hutool.core.util.IdUtil;
import com.reggie.dao.DishFlavorMapper;
import com.reggie.dao.DishMapper;
import com.reggie.dto.DishDto;
import com.reggie.pojo.Dish;
import com.reggie.pojo.DishFlavor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishService {
    
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    public List<Dish> findAllDish(){
        return dishMapper.selectAllDish();
    }

    public int findDishCount(String name){
        return dishMapper.selectDishCount(name);
    }

    public Dish findDishById(Long id){
        return dishMapper.selectDishById(id);
    }

    public List<Dish> findDishByCategory(Long id){
        return dishMapper.selectDishByCategory(id);
    }

    public List<Dish> findByNameAndLimit(String name,int page,int pageSize){
        return dishMapper.selectDishByNameAndLimit(name,(page-1)*pageSize,pageSize);
    }

    @Transactional
    public void insertDish(DishDto dishDto,Long uid){
        dishDto.setId(IdUtil.getSnowflakeNextId());
        dishDto.setCreateUser(uid);
        dishDto.setUpdateUser(uid);
        dishMapper.insertDish(dishDto);

        List<DishFlavor> flavors = dishDto.getFlavors();
        if(flavors!=null){
        for (DishFlavor flavor : flavors) {
            flavor.setId(IdUtil.getSnowflakeNextId());
            flavor.setCreateUser(uid);
            flavor.setUpdateUser(uid);
            flavor.setDishId(dishDto.getId());
            dishFlavorMapper.insertDishFlavor(flavor);
        }}
    }

    @Transactional
    public void updateDish(DishDto dishDto){
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto,dish);
        dishMapper.updateDish(dish);

        //删除原数据
        //List<DishFlavor> oldFlavor = dishFlavorMapper.selectFlavorByDish(dish.getId());
        //for (DishFlavor f : oldFlavor) {
        //    dishFlavorMapper.deleteFlavorById(f.getId());
        //}
        dishFlavorMapper.deleteFlavorByDishId(dish.getId());

        //添加新数据
        List<DishFlavor> newFlavor = dishDto.getFlavors();
        if(newFlavor!=null) {
            for (DishFlavor f : newFlavor) {
                f.setId(IdUtil.getSnowflakeNextId());
                f.setDishId(dish.getId());
                f.setCreateUser(dish.getUpdateUser());
                f.setUpdateUser(dish.getUpdateUser());
                dishFlavorMapper.insertDishFlavor(f);
            }
        }
    }

    public void updateStatus(String i,int status){
        String[] split = i.split(",");
        for (String s : split) {
            long id = Long.parseLong(s);
            dishMapper.updateStatus(id,status);
        }
    }

    @Transactional
    public void deleteDish(String i){
        String[] split = i.split(",");
        for (String s : split) {
            long id = Long.parseLong(s);
            dishFlavorMapper.deleteFlavorByDishId(id);
            dishMapper.deleteDish(id);
        }
    }
}
