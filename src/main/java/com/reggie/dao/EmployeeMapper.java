package com.reggie.dao;

import com.reggie.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    Employee selectEmployeeById(int id);

    Employee selectEmployeeByUsername(String username);

    Integer selectEmployeeCount();

    List<Employee> selectEmployees(@Param("name") String name,@Param("offset") Integer offset
            ,@Param("limit") Integer limit);

    int insertEmployee(Employee employee);

}
