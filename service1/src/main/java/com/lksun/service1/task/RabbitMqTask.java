package com.lksun.service1.task;

import com.lksun.service1.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class RabbitMqTask {
//    @RabbitListener(queues = RabbitMqConfig.LKSUN_TEST_QUEUE)
//    public void task(Message message, Channel channel) throws IOException {
//        String msg = new String(message.getBody());
//
//    }
}
