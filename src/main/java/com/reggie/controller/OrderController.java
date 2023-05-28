package com.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
import com.reggie.dto.OrdersDto;
import com.reggie.pojo.OrderDetail;
import com.reggie.pojo.Orders;
import com.reggie.service.OrderDetailService;
import com.reggie.service.OrderService;
import com.reggie.vo.DataVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("submit")
    public String addOrder(@RequestBody Orders order, HttpSession session){
        Long uid = (Long) session.getAttribute("user");
        order.setId(IdUtil.getSnowflakeNextId());
        order.setUserId(uid);
        orderService.insertOrders(order);
        return JSON.toJSONString(R.success("成功 "));
    }

    @GetMapping("userPage")
    public String getList(int page,int pageSize,HttpSession session){
        Long uid = (Long) session.getAttribute("user");
        List<Orders> orders = orderService.findOrdersByUserIdAndLimit(uid, page, pageSize);
        List<OrdersDto> dtos=new ArrayList<>();
        for (Orders order : orders) {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(order,ordersDto);

            List<OrderDetail> orderDetailByOrderId = orderDetailService.findOrderDetailByOrderId(order.getId());
            ordersDto.setOrderDetails(orderDetailByOrderId);
            dtos.add(ordersDto);
        }
        int count = orderService.findOrdersCountByUid(uid);
        return JSON.toJSONString(R.success(new DataVo(dtos,count)));
    }
}
