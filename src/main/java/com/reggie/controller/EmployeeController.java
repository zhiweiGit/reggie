package com.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.pojo.Employee;
import com.reggie.service.EmployeeService;
import com.reggie.vo.DataVo;
import com.reggie.vo.EmployeeRuleFormVo;
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
    public String page(Integer page, Integer pageSize, String name){
        DataVo dataVo = new DataVo();
        dataVo.setTotal(employeeService.findEmployeeCount());
        dataVo.setRecords(employeeService.findEmployees(page, pageSize, name));
        //System.out.println(JSON.toJSONString(R.success(employeeVo)));
        return JSON.toJSONString(R.success(dataVo));
    }

    @PostMapping
    public R<String> insert(@RequestBody Employee employee,HttpSession session){
        LocalDateTime now = LocalDateTime.now();
        //employee.setCreateTime(now);
        //employee.setUpdateTime(now);
        employee.setId(IdUtil.getSnowflakeNextId());
        Long empId=(Long) session.getAttribute("employee");
        employee.setUpdateUser(empId);
        employee.setCreateUser(empId);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.insertEmployee(employee);
        return R.success("成功");
    }

    @GetMapping("{id}")
    public String editEmployee(@PathVariable("id")String id){
        Employee employee = employeeService.findEmployeeById(Long.parseLong(id));
        EmployeeRuleFormVo employeeRuleFormVo=new EmployeeRuleFormVo(employee.getId(),employee.getName(), employee.getUsername(), employee.getPhone(), employee.getSex(), employee.getIdNumber());
        return JSON.toJSONString(R.success(employeeRuleFormVo));
    }

    @PutMapping()
    public R<String> update(@RequestBody Employee employee,HttpSession session) {
        Long id = (Long) session.getAttribute("employee");
        if (id == null) {
            return R.error("用户未登录");
        }
        employee.setUpdateUser(id);
        employeeService.updateEmployee(employee);
        return R.success("成功");
    }

}
