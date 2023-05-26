package com.reggie.service;

import com.reggie.dao.DishFlavorMapper;
import com.reggie.pojo.DishFlavor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishFlavorService {

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    public void insertDishFlavor(DishFlavor dishFlavor){
        dishFlavorMapper.insertDishFlavor(dishFlavor);
    }

    public List<DishFlavor> findFlavorByDish(Long dishId){
        return dishFlavorMapper.selectFlavorByDish(dishId);
    }

    public DishFlavor findFlavorById(Long id){
        return dishFlavorMapper.selectFlavorById(id);
    }

    public int updateFlavor(DishFlavor dishFlavor){
        return dishFlavorMapper.updateFlavor(dishFlavor);
    }

    public void deleteFlavorById(Long id){
        dishFlavorMapper.deleteFlavorById(id);
    }

    public void deleteFlavorByDishId(Long id){
        dishFlavorMapper.deleteFlavorByDishId(id);
    }
}
