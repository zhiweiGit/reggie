package com.reggie.dao;

import com.reggie.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    int insertDishFlavor(DishFlavor dishFlavor);

    List<DishFlavor> selectFlavorByDish(Long dishId);

    DishFlavor selectFlavorById(Long id);

    int updateFlavor(DishFlavor dishFlavor);

    int deleteFlavorById(Long id);

    int deleteFlavorByDishId(Long id);

}
