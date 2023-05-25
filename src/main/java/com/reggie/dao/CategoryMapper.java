package com.reggie.dao;

import com.reggie.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int selectCategoryCount();
    List<Category> selectAllCategory(@Param("offset") Integer offset,@Param("limit") Integer limit);

    int insertCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(Long id);

}
