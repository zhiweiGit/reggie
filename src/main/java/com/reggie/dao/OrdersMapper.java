package com.reggie.dao;

import com.reggie.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper {

    List<Orders> selectOrdersByUserId(Long uid);

    List<Orders> selectOrdersByUserIdAndLimit(Long uid,int offset,int limit);

    int insertOrders(Orders orders);

    int selectOrderCountByUid(Long uid);

}
