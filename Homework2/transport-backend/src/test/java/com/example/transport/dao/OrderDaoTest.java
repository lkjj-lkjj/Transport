package com.example.transport.dao;

import com.example.transport.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void findOrderByUseridAndState() throws Exception{
        List<Order> orderList;
        orderList = orderDao.findOrderByUseridAndState(1,2);
        System.out.println(orderList);
    }

    @Test
    public void findOrderByTransporterIdAndState() {
        List<Order> orderList;
        orderList = orderDao.findOrderByTransporterIdAndState(2,2);
        System.out.println(orderList);
    }

    @Test
    public void findOrderByState() {
        List<Order> orderList;
        orderList = orderDao.findOrderByState(2);
        System.out.println(orderList);
    }

    @Test
    public void updateOrderState() {
        orderDao.updateOrderState(1,"2023-05-Mo 12:42:42",2,7);
        System.out.println("success");
    }

    @Test
    public void completeOrder() {
        orderDao.completeOrder(1,"2023-05-Mo 12:42:42",6);
        System.out.println("success");
    }

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setOrdername("happy");
        order.setUserid(9);
        order.setDescription("lalalala");
        order.setCreatetime("2023-05-Mo 21:56:10");
        order.setState(0);
        orderDao.insertOrder(order);
        System.out.println("success");
    }

    @Test
    public void deleteOrder() {
        orderDao.deleteOrder(8);
        System.out.println("success");
    }
}
