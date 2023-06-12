package com.example.transporter_service.service.impl;

import com.example.transporter_service.dao.OrderDao;
import com.example.transporter_service.entity.Order;
import com.example.transporter_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findOrderByTransporterIdAndState(int transporterid, int state) {
        return orderDao.findOrderByTransporterIdAndState(transporterid, state);
    }

    @Override
    public List<Order> findOrderByState(int state) {
        return orderDao.findOrderByState(state);
    }

    @Override
    public void updateOrderState(int state, String starttime, int transporterid, int orderid) {
        orderDao.updateOrderState(state, starttime, transporterid, orderid);
    }

    @Override
    public void completeOrder(int state, String endtime, int orderid) {
        orderDao.completeOrder(state, endtime, orderid);
    }

}
