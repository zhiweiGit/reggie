package com.reggie.service;

import cn.hutool.core.util.IdUtil;
import com.reggie.dao.OrdersMapper;
import com.reggie.pojo.AddressBook;
import com.reggie.pojo.OrderDetail;
import com.reggie.pojo.Orders;
import com.reggie.pojo.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private UserService userService;


    public List<Orders> findOrdersByUserId(Long uid) {
        return ordersMapper.selectOrdersByUserId(uid);
    }

    public List<Orders> findOrdersByUserIdAndLimit(Long uid,int page,int pageSize){
        return ordersMapper.selectOrdersByUserIdAndLimit(uid,(page - 1) * pageSize, pageSize);
    }

    public int findOrdersCountByUid(Long uid){
        return ordersMapper.selectOrderCountByUid(uid);
    }


    @Transactional
    public void insertOrders(Orders orders) {
        List<ShoppingCart> carts = shoppingCartService.findShoppingCartByUserId(orders.getUserId());
        shoppingCartService.deleteCartByUid(orders.getUserId());
        BigDecimal total=new BigDecimal(0);
        for (ShoppingCart cart : carts) {
            OrderDetail detail = new OrderDetail();
            BigDecimal price = cart.getAmount().multiply(new BigDecimal(cart.getNumber()));
            detail.setId(IdUtil.getSnowflakeNextId());
            detail.setName(cart.getName());
            detail.setAmount(price);
            detail.setImage(cart.getImage());
            detail.setNumber(cart.getNumber());
            detail.setOrderId(orders.getId());
            detail.setDishId(cart.getDishId());
            detail.setDishFlavor(cart.getDishFlavor());
            detail.setSetmealId(cart.getSetmealId());
            orderDetailService.insertOrderDetail(detail);
            total = total.add(price);
        }
        AddressBook addressBook = addressBookService.findAddressBookById(orders.getAddressBookId());
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setAmount(total);
        orders.setUserName(userService.findUserById(orders.getUserId()).getName());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        ordersMapper.insertOrders(orders);
    }
}
