package com.jc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jc.dto.SevenSendersMessage;

@Service
public class SevenSendersProducerService {

    Logger logger = LoggerFactory.getLogger(SevenSendersProducerService.class);

    @Autowired
    @Qualifier("kafkaJsonTemplate")
    private KafkaTemplate<String, Object> template;

    @Value(value = "${spring.kafka.sevensenders.topic}")
    private String topic;

    public void sendMessage(SevenSendersMessage message) {
        logger.info(String.format("Sending message <%s>", message));
        template.send(topic, message.getId(), message);
    }

}
