package com.reggie.dao;

import com.reggie.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int selectCategoryCount();
    List<Category> selectAllCategoryAndLimit(@Param("offset") Integer offset,@Param("limit") Integer limit);

    List<Category> selectCategoryByType(String type);

    Category selectCategoryById(Long id);

    int insertCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(Long id);

}
