package com.reggie.service;

import com.reggie.dao.EmployeeMapper;
import com.reggie.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee findEmployeeById(Long id){
        return employeeMapper.selectEmployeeById(id);
    }

    public Employee findEmployeeByUsername(String username){
        return employeeMapper.selectEmployeeByUsername(username);
    }

    public Integer findEmployeeCount(){
        return employeeMapper.selectEmployeeCount();
    }

    public List<Employee> findEmployees(Integer page, Integer pageSize, String name){
        return employeeMapper.selectEmployees(name,(page-1)*pageSize,pageSize);
    }

    public void insertEmployee(Employee employee){
        employeeMapper.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee){
        employeeMapper.updateEmployeeById(employee);
    }
}
