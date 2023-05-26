package com.reggie.dao;

import com.reggie.dto.DishDto;
import com.reggie.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishMapper {

    List<Dish> selectAllDish();

    int selectDishCount(String name);

    Dish selectDishById(Long id);

    List<Dish> selectDishByNameAndLimit(@Param("name") String name, @Param("offset") Integer offset
            , @Param("limit") Integer limit);

    List<Dish> selectDishByCategory(Long id);

    int insertDish(DishDto dishDto);

    int updateDish(Dish dish);

    int updateStatus(@Param("id") Long id,@Param("status") Integer status);

    int deleteDish(Long id);
}
