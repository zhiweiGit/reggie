package com.reggie.dao;

import com.reggie.pojo.SetMeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetMealMapper {

    List<SetMeal> selectAllSetMeal();

    SetMeal selectSetMealById(Long id);

    List<SetMeal> selectSetMealByCategory(Long id);

    List<SetMeal> selectSetMealByNameAndLimit(@Param("name") String name, @Param("offset") Integer offset
            , @Param("limit") Integer limit);

    int selectSeatMealCount();

    int insertSeatMeal(SetMeal setMeal);

    int deleteSeatMeal(Long id);

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    int updateSetMeal(SetMeal setMeal);
}
