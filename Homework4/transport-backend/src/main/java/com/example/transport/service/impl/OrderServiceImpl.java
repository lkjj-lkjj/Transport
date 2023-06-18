package com.example.transport.service.impl;

import com.example.transport.dao.OrderDao;
import com.example.transport.entity.Order;
import com.example.transport.service.OrderService;
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

    @Override
    public void insertOrder(Order order) {
        orderDao.insertOrder(order);
    }

    @Override
    public void deleteOrder(int orderid) {
        orderDao.deleteOrder(orderid);
    }
}
