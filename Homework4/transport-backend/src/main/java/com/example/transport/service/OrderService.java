package com.example.transport.service;

import com.example.transport.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrderByUseridAndState(int userid, int state);

    List<Order> findOrderByTransporterIdAndState(int transporterid, int state);

    List<Order> findOrderByState(int state);

    void updateOrderState(int state, String starttime, int transporterid, int orderid);

    void completeOrder(int state, String endtime, int orderid);
    void insertOrder(Order order);
    void deleteOrder(int orderid);
}
