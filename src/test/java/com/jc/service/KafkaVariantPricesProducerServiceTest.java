package com.jc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jc.dto.PriceMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaVariantPricesProducerServiceTest {

    @Autowired
    private VariantPricesProducerService variantPricesProducerService;

    @Test
    public void sendRandomMessages() throws Exception {
        for (int i = 0; i < 1; i++) {
            PriceMessage message = getPriceMessage(i);
            variantPricesProducerService.sendMessage(message);
            Thread.sleep(500);
        }

    }

    private PriceMessage getPriceMessage(int i) {
        PriceMessage message = new PriceMessage();
        message.setMessageId("priceMessage" + String.valueOf(i));
        message.setBaseProductId(String.format("code-%d", i));
        message.setVariantCode(String.format("code-%d-001", i));
        message.setRecommendedRetailPrice(100.0);
        message.setRetailPrice(70.0);
        message.setColorId("color");
        message.setSize("35");
        message.setSizeRun("35");
        return message;
    }
}