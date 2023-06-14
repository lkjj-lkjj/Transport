package com.example.user_service.service.impl;

import com.example.user_service.dao.OrderDao;
import com.example.user_service.entity.Order;
import com.example.user_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public List<Order> findOrderByUseridAndState(int userid, int state) {
        return orderDao.findOrderByUseridAndState(userid, state);
    }
    @Override
    public int insertOrder(Order order) {
        int res = orderDao.insertOrder(order);
        return res;
    }

    @Override
    public int deleteOrder(int orderid) {
        int res = orderDao.deleteOrder(orderid);
        return res;
    }
}
