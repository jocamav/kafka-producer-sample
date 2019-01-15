package com.jc.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jc.dto.SevenSendersMessage;

@Component
public class SevenSendersListener {

    Logger logger = LoggerFactory.getLogger(SevenSendersListener.class);

    @KafkaListener(id = "sevenSenderListener", topics = "myTopic", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(SevenSendersMessage data) {

        logger.info(String.format("Receive message <%s>", data.toString()));
    }
}
