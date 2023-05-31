package com.reggie.controller;

import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.dto.SetmealDto;
import com.reggie.pojo.SetMeal;
import com.reggie.service.CategoryService;
import com.reggie.service.SetmealDishService;
import com.reggie.service.SetmealService;
import com.reggie.vo.DataVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SetmealDishService setmealDishService;

    @GetMapping("page")
    public String page(int page,int pageSize,String name){
        DataVo dataVo = new DataVo();
        dataVo.setTotal(setmealService.findSetmealCount());
        List<SetMeal> setMeals = setmealService.findSetMealByNameAndLimit(name, page, pageSize);

        List<SetmealDto> setmealDtos =new ArrayList<>();
        for (SetMeal setMeal : setMeals) {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(setMeal,setmealDto);
            setmealDto.setCategoryName(categoryService.findCategoryById(setMeal.getCategoryId()).getName());
            setmealDtos.add(setmealDto);
        }
        dataVo.setRecords(setmealDtos);
        return JSON.toJSONString(R.success(dataVo));
    }

    @PostMapping
    public String addSetmeal(@RequestBody SetmealDto setmealDto, HttpSession session){
        Long uid = (Long) session.getAttribute("employee");
        setmealService.insertSetmeal(setmealDto,uid);
        return JSON.toJSONString(R.success("成功"));
    }

    @DeleteMapping
    @CacheEvict(value = "setmealCache",allEntries = true)
    public String delete(Long[] ids){
        setmealService.deleteSetmeal(ids);
        return JSON.toJSONString(R.success("成功"));
    }

    @PostMapping("status/{status}")
    public String editStatus(@PathVariable("status")int status,Long[] ids){
        setmealService.updateStatus(ids,status);
        return JSON.toJSONString(R.success("成功"));
    }

    @GetMapping("{id}")
    public String getSetmeal(@PathVariable("id") Long id){
        SetMeal setMeal = setmealService.findSetMealById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setMeal,setmealDto);
        setmealDto.setSetmealDishes(setmealDishService.findSetmealDishBySid(setmealDto.getId()));
        return JSON.toJSONString(R.success(setmealDto));
    }

    @PutMapping
    public String editSetmeal(@RequestBody SetmealDto setmealDto,HttpSession session){
        setmealService.updateSetmeal(setmealDto,(Long)session.getAttribute("employee"));
        return JSON.toJSONString(R.success("成功"));
    }

    @GetMapping("list")
    @Cacheable(value = "setmealCache",key = "#setmeal.categoryId+':'+#setmeal.status")
    public String getList(SetMeal setmeal){
        List<SetMeal> setmeals = setmealService.findSetMealByCategory(setmeal.getCategoryId());
        return JSON.toJSONString(R.success(setmeals));
    }
}
