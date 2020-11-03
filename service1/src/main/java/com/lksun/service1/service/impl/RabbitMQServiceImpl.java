package com.lksun.service1.service.impl;

import com.lksun.service1.config.RabbitMqConfig;
import com.lksun.service1.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.LKSUN_TEST_EXCHANGE,RabbitMqConfig.LKSUN_TEST_ROUTING_KEY,msg);
    }
}
