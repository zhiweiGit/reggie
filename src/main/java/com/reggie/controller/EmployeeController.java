package com.reggie.controller;

import com.reggie.common.R;
import com.reggie.pojo.Employee;
import com.reggie.service.EmployeeService;
import com.reggie.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("login")
    public R<Employee> login(@RequestBody Employee employee, HttpSession session) {
        Employee emp = employeeService.findEmployeeByUsername(employee.getUsername());
        if (emp == null) {
            return R.error("账号或密码错误");
        }
        if (DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()).equals(emp.getPassword())) {
            session.setAttribute("employee",emp.getId());
            return R.success(emp);
        } else if (emp.getStatus() == 0) {
            return R.error("账号被禁用");
        } else {
            return R.error("账号或密码错误");
        }
    }

    @PostMapping("/logout")
    public R<String> logout(HttpSession session){
        session.removeAttribute("employee");
        return R.success("成功");
    }

    @GetMapping("page")
    public R<EmployeeVo> page(Integer page, Integer pageSize, String name){
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setTotal(employeeService.findEmployeeCount());
        employeeVo.setRecords(employeeService.findEmployees(page, pageSize, name));
        return R.success(employeeVo);
    }

    @PostMapping
    public R<String> insert(@RequestBody Employee employee,HttpSession session){
        LocalDateTime now = LocalDateTime.now();
        employee.setCreateTime(now);
        employee.setUpdateTime(now);
        Long empId=(Long) session.getAttribute("employee");
        employee.setUpdateUser(empId);
        employee.setCreateUser(empId);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.insertEmployee(employee);
        return R.success("成功");
    }
}
