package com.reggie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRuleFormVo {
    private Long id;
    private String name;
    private String username;
    private String phone;
    private String sex;
    private String idNumber;
}
