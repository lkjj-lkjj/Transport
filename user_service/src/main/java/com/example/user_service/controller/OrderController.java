package com.example.user_service.controller;

import com.example.user_service.entity.Order;
import com.example.user_service.service.OrderService;
import com.example.user_service.util.Result;
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

    @HystrixCommand(fallbackMethod = "waitingFallback")  //失败了就会调用下面的这个备选方案
    @GetMapping("/waiting/{id}")
    public Result<?> getWaitingOrders(@PathVariable("id") int id){
        List<Order> orders = orderService.findOrderByUseridAndState(id, 0);
        if(orders.isEmpty()){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success(orders);
    }

    //备选方案
    public Result<?> waitingFallback(@PathVariable("id") int id) {
        return Result.error("204","No waiting orders");
    }


    @HystrixCommand(fallbackMethod = "inTransFallback")  //失败了就会调用下面的这个备选方案
    @GetMapping("/intrans/{id}")
    public Result<?> getInTransOrders(@PathVariable("id") int id){
        List<Order> orders = orderService.findOrderByUseridAndState(id, 1);
        if(orders.isEmpty()){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success(orders);
    }

    //备选方案
    public Result<?> inTransFallback(@PathVariable("id") int id) {
        return Result.error("204","No in transport orders");
    }

    @HystrixCommand(fallbackMethod = "myHistoryFallback")  //失败了就会调用下面的这个备选方案
    @GetMapping("/myhistory/{id}")
    public Result<?> getMyHistoryOrders(@PathVariable("id") int id){
        List<Order> orders = orderService.findOrderByUseridAndState(id, 2);
        if(orders.isEmpty()){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success(orders);
    }

    //备选方案
    public Result<?> myHistoryFallback(@PathVariable("id") int id) {
        return Result.error("204","No my history orders");
    }

    @HystrixCommand(fallbackMethod = "insertFallback")  //失败了就会调用下面的这个备选方案
    @PostMapping("/insert")
    public Result<?> insertAnOrder(@RequestBody Order order){
        int res = orderService.insertOrder(order);
        if(res != 1){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success();
    }

    //备选方案
    public Result<?> insertFallback(@RequestBody Order order) {
        return Result.error("204","Fail to insert");
    }

    @HystrixCommand(fallbackMethod = "revokeFallback")  //失败了就会调用下面的这个备选方案
    @PostMapping("/revoke")
    public Result<?> revokeOrder(@RequestBody Order order){
        int res = orderService.deleteOrder(order.getOrderid());
        if(res != 1){
            throw new RuntimeException("orders throw RuntimeException");
        }
        return Result.success();
    }

    //备选方案
    public Result<?> revokeFallback(@RequestBody Order order) {
        return Result.error("204","Fail to revoke");
    }
}
