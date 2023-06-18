package com.example.kafka_service.controller;

import com.example.kafka_service.kafka.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventProducer eventProducer;

    @Autowired
    public EventController(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping("/events")
    public void createEvent(@RequestBody String eventMessage) {
        // 处理创建事件的逻辑

        // 发送事件消息到Kafka
        eventProducer.sendEvent("user", eventMessage);
    }
}
