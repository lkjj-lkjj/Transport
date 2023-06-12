package com.example.user_service.controller;

import com.example.user_service.entity.Order;
import com.example.user_service.service.OrderService;
import com.example.user_service.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/secure")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/waiting/{id}")
    public Result<?> getWaitingOrders(@PathVariable("id") int id){
        List<Order> orders = orderService.findOrderByUseridAndState(id, 0);
        return Result.success(orders);
    }

    @GetMapping("/intrans/{id}")
    public Result<?> getInTransOrders(@PathVariable("id") int id){
        List<Order> orders = orderService.findOrderByUseridAndState(id, 1);
        return Result.success(orders);
    }

    @GetMapping("/myhistory/{id}")
    public Result<?> getMyHistoryOrders(@PathVariable("id") int id){
        List<Order> orders = orderService.findOrderByUseridAndState(id, 2);
        return Result.success(orders);
    }

    @PostMapping("/insert")
    public Result<?> insertAnOrder(@RequestBody Order order){
        orderService.insertOrder(order);
        return Result.success();
    }

    @PostMapping("/revoke")
    public Result<?> revokeOrder(@RequestBody Order order){
        orderService.deleteOrder(order.getOrderid());
        return Result.success();
    }
}
