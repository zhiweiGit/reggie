package com.reggie;

import cn.hutool.core.util.IdUtil;
import com.reggie.dao.CategoryMapper;
import com.reggie.dao.EmployeeMapper;
import com.reggie.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class ReggieApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void empTest() throws SQLException {
        //System.out.println(employeeMapper.selectEmployeeById(2));
        //System.out.println(employeeMapper.selectEmployees("管",0,2));
        System.out.println(IdUtil.getSnowflakeNextId());
    }

    @Test
    void cateTest(){
        Category category = new Category();
        category.setId(IdUtil.getSnowflakeNextId());
        category.setName("123");
        category.setType(1);
        category.setSort(99);
        category.setCreateUser(2L);
        category.setUpdateUser(2L);
        categoryMapper.insertCategory(category);
    }

    @Test
    void test(){
        String name="123jpg";
        System.out.println(name.substring(name.lastIndexOf(".")));
    }

}
