package com.jc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jc.dto.PriceMessage;
import com.jc.dto.SevenSendersMessage;

@Service
public class VariantPricesProducerService {

    Logger logger = LoggerFactory.getLogger(VariantPricesProducerService.class);

    @Autowired
    @Qualifier("kafkaJsonTemplate")
    private KafkaTemplate<String, Object> template;

    @Value(value = "${spring.kafka.prices.topic}")
    private String topic;

    public void sendMessage(PriceMessage message) {
        logger.info(String.format("Sending message <%s>", message));
        template.send(topic, message.getMessageId(), message);
    }

}
