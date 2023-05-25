package com.reggie.service;

import com.reggie.common.CustomExcept;
import com.reggie.dao.CategoryMapper;
import com.reggie.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishService dishService;

    @Autowired
    private SetMealService setMealService;

    public int findCategoryCount(){
        return categoryMapper.selectCategoryCount();
    }

    public List<Category> findAllCategory(Integer page, Integer pageSize){
        return categoryMapper.selectAllCategory((page-1)*pageSize,pageSize);
    }

    public void insertCategory(Category category){
        categoryMapper.insertCategory(category);
    }

    public void updateCategory(Category category){
        categoryMapper.updateCategory(category);
    }

    public void deleteCategory(Long id){
        if(dishService.findDishByCategory(id)!=null){
            throw new CustomExcept("当前分类关联了菜品，不能删除");
        }
        if( setMealService.findSetMealByCategory(id)!=null){
            throw new CustomExcept("当前分类关联了套餐，不能删除");
        }
        categoryMapper.deleteCategory(id);
    }
}
