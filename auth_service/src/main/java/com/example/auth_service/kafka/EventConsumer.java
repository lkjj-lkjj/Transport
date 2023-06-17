package com.example.auth_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @KafkaListener(topics = "auth", groupId = "my_group")
    public void receiveEvent(String message) {
        // 处理接收到的事件消息
        System.out.println("Received event: " + message);
    }
}