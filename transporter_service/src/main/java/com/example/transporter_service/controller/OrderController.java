package com.example.transporter_service.controller;

import com.example.transporter_service.entity.Order;
import com.example.transporter_service.service.OrderService;
import com.example.transporter_service.util.Result;
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

    @GetMapping("/receive")
    public Result<?> getReceiveOrders(){
        List<Order> orders = orderService.findOrderByState(0);
        return Result.success(orders);
    }

    @GetMapping("/myintrans/{transporterid}")
    public Result<?> getMyInTransOrders(@PathVariable("transporterid") int transporterid){
        List<Order> orders = orderService.findOrderByTransporterIdAndState(transporterid, 1);
        return Result.success(orders);
    }

    @GetMapping("/mytranshistory/{transporterid}")
    public Result<?> getMyTransHistoryOrders(@PathVariable("transporterid") int transporterid){
        List<Order> orders = orderService.findOrderByTransporterIdAndState(transporterid, 2);
        return Result.success(orders);
    }

    @PostMapping("/receiveorder")
    public Result<?> receiveOrderByTransporter(@RequestBody Order order){
        LocalDateTime now = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化时间
        String formattedDateTime = now.format(formatter);
        orderService.updateOrderState(1, formattedDateTime, order.getTransporterid(), order.getOrderid());
        return Result.success();
    }

    @PostMapping("/complete")
    public Result<?> completeOrder(@RequestBody Order order){
        LocalDateTime now = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化时间
        String formattedDateTime = now.format(formatter);
        orderService.completeOrder(2, formattedDateTime, order.getOrderid());
        return Result.success();
    }
}
