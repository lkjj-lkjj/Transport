package com.example.user_service.service;

import com.example.user_service.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrderByUseridAndState(int userid, int state);
    void insertOrder(Order order);
    void deleteOrder(int orderid);
}
