package com.jc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSenderServiceTest {

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Test
    public void getHello() throws Exception {
        for (int i = 0; i < 10; i++) {
            kafkaSenderService.sendMessage(String.format("This is a message %d", i));
            Thread.sleep(500);
        }
    }
}