package com.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.pojo.Category;
import com.reggie.service.CategoryService;
import com.reggie.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("page")
    public String page(Integer page, Integer pageSize){
        DataVo vo = new DataVo();
        vo.setRecords(categoryService.findAllCategory(page,pageSize));
        vo.setTotal(categoryService.findCategoryCount());
        return JSON.toJSONString(R.success(vo));
    }

    @PostMapping
    public String insert(@RequestBody Category category, HttpSession session){
        category.setId(IdUtil.getSnowflakeNextId());
        Long id = (Long)session.getAttribute("employee");
        if (id == null) {
            return JSON.toJSONString(R.error("用户未登录"));
        }
        category.setCreateUser(id);
        category.setUpdateUser(id);
        categoryService.insertCategory(category);
        return JSON.toJSONString(R.success("成功"));
    }

    @PutMapping
    public String update(@RequestBody Category category,HttpSession session){
        Long id = (Long)session.getAttribute("employee");
        if (id == null) {
            return JSON.toJSONString(R.error("用户未登录"));
        }
        category.setUpdateUser(id);
        categoryService.updateCategory(category);
        return JSON.toJSONString(R.success("成功"));
    }

    @DeleteMapping
    public String delete(Long ids,HttpSession session){
        if ((Long)session.getAttribute("employee") == null) {
            return JSON.toJSONString(R.error("用户未登录"));
        }
        categoryService.deleteCategory(ids);
        return JSON.toJSONString(R.success("成功"));
    }
}
