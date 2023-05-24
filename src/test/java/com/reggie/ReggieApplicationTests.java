package com.reggie;

import com.reggie.dao.EmployeeMapper;
import com.reggie.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDateTime;

@SpringBootTest
class ReggieApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() throws SQLException {
        //System.out.println(employeeMapper.selectEmployeeById(1));
        //System.out.println(employeeMapper.selectEmployees("管",0,2));
        Employee employee = new Employee();
        employee.setName("test");
        employee.setUsername("testt");
        employee.setPassword("123");
        employee.setPhone("123123");
        employee.setSex("1");
        employee.setIdNumber("321312");
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(1L);
        employee.setUpdateUser(1L);
        employeeMapper.insertEmployee(employee);
    }

}
