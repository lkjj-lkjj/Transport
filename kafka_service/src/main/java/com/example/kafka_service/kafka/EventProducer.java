package com.example.kafka_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "user"; // Kafka主题名称

    @Autowired
    public EventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic ,String message) {
        kafkaTemplate.send(topic, message);
    }
}
