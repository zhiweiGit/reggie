package com.reggie.controller;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.dto.DishDto;
import com.reggie.pojo.Category;
import com.reggie.pojo.Dish;
import com.reggie.pojo.DishFlavor;
import com.reggie.service.CategoryService;
import com.reggie.service.DishFlavorService;
import com.reggie.service.DishService;
import com.reggie.vo.DataVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishFlavorService dishFlavorService;


    @GetMapping("page")
    public String list(String name,Integer page,Integer pageSize){
        DataVo dataVo = new DataVo();
        dataVo.setTotal(dishService.findDishCount(name));
        List<Dish> dishes = dishService.findByNameAndLimit(name, page, pageSize);
        List<DishDto> dishDtos=new ArrayList<>();
        for (Dish dish : dishes) {
            Category category = categoryService.findCategoryById(dish.getCategoryId());
            DishDto dishDto = Convert.convert(DishDto.class, dish);
            dishDto.setCategoryName(category.getName());
            dishDtos.add(dishDto);
        }
        dataVo.setRecords(dishDtos);
        return JSON.toJSONString(R.success(dataVo));
    }


    @PostMapping
    public String addDish(@RequestBody DishDto dishDto, HttpSession session){
        Long employee = (Long) session.getAttribute("employee");
        dishService.insertDish(dishDto,employee);
        return JSON.toJSONString(R.success("成功"));
    }

    @GetMapping("{id}")
    public String queryDishById(@PathVariable("id") Long id){
        Dish dish = dishService.findDishById(id);
        DishDto dishDto = Convert.convert(DishDto.class, dish);
        dishDto.setFlavors(dishFlavorService.findFlavorByDish(dish.getId()));
        return JSON.toJSONString(R.success(dishDto));
    }

    @PutMapping
    public String editDish(@RequestBody DishDto dishDto, HttpSession session){
        dishDto.setUpdateUser((Long)session.getAttribute("employee"));
        dishService.updateDish(dishDto);
        return JSON.toJSONString(R.success("成功"));
    }

    @PostMapping("status/{status}")
    public String editStatus(@PathVariable("status") int status,String ids){
        dishService.updateStatus(ids,status);
        return JSON.toJSONString(R.success("成功"));
    }

    @DeleteMapping
    public String deleteDish(String ids){
        dishService.deleteDish(ids);
        return JSON.toJSONString(R.success("成功"));
    }

    @GetMapping("list")
    public String dishList(Long categoryId){
        List<Dish> dishes = dishService.findDishByCategory(categoryId);
        List<DishDto> dishDtos=new ArrayList<>();
        for (Dish dish : dishes) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish,dishDto);
            List<DishFlavor> flavorByDish = dishFlavorService.findFlavorByDish(dishDto.getId());
            dishDto.setFlavors(flavorByDish);
            dishDtos.add(dishDto);

        }

        return  JSON.toJSONString(R.success(dishDtos));
    }
}
