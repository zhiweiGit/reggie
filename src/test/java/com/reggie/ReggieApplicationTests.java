package com.reggie;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.reggie.dao.CategoryMapper;
import com.reggie.dao.EmployeeMapper;
import com.reggie.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.sql.SQLException;
import java.util.HashMap;

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
        //System.out.println(RandomUtil.randomNumbers(3));
        //POST请求
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "admin");
        paramMap.put("password", DigestUtils.md5DigestAsHex("123456".getBytes()));

        String result1 = HttpUtil.post("http://localhost:8080/employee/login", paramMap);
        System.out.println(result1);
    }

}
