package com.reggie.dao;

import com.reggie.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    int insertOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> selectOrderDetailByOrderId(Long orderId);
}
