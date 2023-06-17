package com.example.transporter_service.controller;

import com.example.transporter_service.entity.Order;
import com.example.transporter_service.service.OrderService;
import com.example.transporter_service.util.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "receiveFallback")
    @GetMapping("/receive")
    public Result<?> getReceiveOrders(){
        List<Order> orders = orderService.findOrderByState(0);
        if(Result.success().getData() == null){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success(orders);
    }

    public Result<?> receiveFallback() {
        return Result.error("204","No receive orders");
    }

    @HystrixCommand(fallbackMethod = "inTransFallback")
    @GetMapping("/myintrans/{transporterid}")
    public Result<?> getMyInTransOrders(@PathVariable("transporterid") int transporterid){
        List<Order> orders = orderService.findOrderByTransporterIdAndState(transporterid, 1);
        if(Result.success().getData() == null){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success(orders);
    }

    //备选方案
    public Result<?> inTransFallback(@PathVariable("transporterid") int transporterid) {
        return Result.error("204","No in transport orders");
    }


    @HystrixCommand(fallbackMethod = "myTransHistoryFallback")
    @GetMapping("/mytranshistory/{transporterid}")
    public Result<?> getMyTransHistoryOrders(@PathVariable("transporterid") int transporterid){
        List<Order> orders = orderService.findOrderByTransporterIdAndState(transporterid, 2);
        if(Result.success().getData() == null){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success(orders);
    }

    public Result<?> myTransHistoryFallback(@PathVariable("transporterid") int transporterid) {
        return Result.error("204","No my transport history orders");
    }

    @HystrixCommand(fallbackMethod = "receiveFallback")
    @PostMapping("/receiveorder")
    public Result<?> receiveOrderByTransporter(@RequestBody Order order){
        LocalDateTime now = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化时间
        String formattedDateTime = now.format(formatter);
        int res = orderService.updateOrderState(1, formattedDateTime, order.getTransporterid(), order.getOrderid());
        if(res != 1){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success();
    }

    public Result<?> receiveFallback(@RequestBody Order order) {
        return Result.error("204","Fail to receive");
    }

    @HystrixCommand(fallbackMethod = "completeFallback")
    @PostMapping("/complete")
    public Result<?> completeOrder(@RequestBody Order order){
        LocalDateTime now = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化时间
        String formattedDateTime = now.format(formatter);
        int res = orderService.completeOrder(2, formattedDateTime, order.getOrderid());
        if(res != 1){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success();
    }

    public Result<?> completeFallback(@RequestBody Order order) {
        return Result.error("204","Fail to complete");
    }

}
