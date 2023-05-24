package com.reggie.vo;

import com.reggie.pojo.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeVo {
    private List<Employee> records;

    private Integer total;
}
