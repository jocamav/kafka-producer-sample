package com.jc.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jc.dto.SevenSendersMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSevenSendersProducerServiceTest {

    @Autowired
    private SevenSendersProducerService sevenSendersProducerService;

    @Test
    public void sendRandomMessages() throws Exception {
        for (int i = 0; i < 100; i++) {
            SevenSendersMessage message = getSevenSendersMessage(i);
            sevenSendersProducerService.sendMessage(message);
            Thread.sleep(500);
        }

    }

    private SevenSendersMessage getSevenSendersMessage(int i) {
        SevenSendersMessage message = new SevenSendersMessage();
        message.setId(String.valueOf(i));
        message.setOrderId(String.format("code-%d", i));
        message.setStatus("Delivered");
        message.setStatusTime(Calendar.getInstance().getTime());
        message.setTrackingCode(String.format("track-code-%d", i));
        return message;
    }
}