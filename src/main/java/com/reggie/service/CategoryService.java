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
    private SetmealService setMealService;

    public int findCategoryCount(){
        return categoryMapper.selectCategoryCount();
    }

    public List<Category> findAllCategoryAndLimit(Integer page, Integer pageSize){
        return categoryMapper.selectAllCategoryAndLimit((page-1)*pageSize,pageSize);
    }

    public List<Category> findAllCategory(String type){
        return categoryMapper.selectCategoryByType(type);
    }

    public Category findCategoryById(Long id){return categoryMapper.selectCategoryById(id);}

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
