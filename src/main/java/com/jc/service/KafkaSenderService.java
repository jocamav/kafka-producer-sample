package com.jc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderService {

    Logger logger = LoggerFactory.getLogger(KafkaSenderService.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value(value = "${spring.kafka.sevensenders.topic}")
    private String topic;

    public void sendMessage(String message) {
        logger.info(String.format("Sending message <%s>", message));
        template.send(topic, message);
    }

}
