package com.example.transporter_service.service;

import com.example.transporter_service.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrderByTransporterIdAndState(int transporterid, int state);

    List<Order> findOrderByState(int state);

    int updateOrderState(int state, String starttime, int transporterid, int orderid);

    int completeOrder(int state, String endtime, int orderid);
}
