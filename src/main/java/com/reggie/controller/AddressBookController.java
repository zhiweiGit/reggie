package com.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.pojo.AddressBook;
import com.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     */
    @PostMapping
    public String save(@RequestBody AddressBook addressBook, HttpSession session) {
        addressBook.setId(IdUtil.getSnowflakeNextId());
        Long user = (Long) session.getAttribute("user");
        addressBook.setUserId(user);
        addressBook.setCreateUser(user);
        addressBook.setUpdateUser(user);
        addressBookService.insertAddressBook(addressBook);
        return JSON.toJSONString(R.success(addressBook));
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public String setDefault(@RequestBody AddressBook addressBook,HttpSession session) {
        addressBook.setUserId((Long) session.getAttribute("user"));
        addressBookService.updateDefault(addressBook);
        return JSON.toJSONString(R.success(addressBook));
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.findAddressBookById(id);
        if (addressBook != null) {
            return JSON.toJSONString(R.success(addressBook));
        } else {
            return JSON.toJSONString(R.error("没有找到该对象"));
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public String getDefault(HttpSession session) {
        AddressBook addressBook = addressBookService.findDefaultAddressByUserId((Long) session.getAttribute("user"));
        if (null == addressBook) {
            return JSON.toJSONString(R.error("没有找到该对象"));
        } else {
            return JSON.toJSONString(R.success(addressBook));
        }
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public String list(HttpSession session) {
        List<AddressBook> address = addressBookService.findAddressBookByUserId((Long)session.getAttribute("user"));
        return JSON.toJSONString(R.success(address));

    }

    @PutMapping
    public String editAddress(@RequestBody AddressBook addressBook){
        addressBookService.updateAddress(addressBook);
        return JSON.toJSONString(R.success("成功"));
    }

    @DeleteMapping
    public String deleteAddress(Long[] ids){
        addressBookService.deleteAddress(ids);
        return JSON.toJSONString(R.success("成功"));
    }
}
