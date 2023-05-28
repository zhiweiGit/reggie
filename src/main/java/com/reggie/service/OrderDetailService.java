package com.reggie.service;

import com.reggie.dao.OrderDetailMapper;
import com.reggie.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public void insertOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.insertOrderDetail(orderDetail);
    }

    public List<OrderDetail> findOrderDetailByOrderId(Long orderId) {
        return orderDetailMapper.selectOrderDetailByOrderId(orderId);
    }
}
