package com.lksun.service2.task;

import com.lksun.service2.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DeadLetterMessage {
    @RabbitListener(queues = RabbitMqConfig.LKSUN_TEST_DEAD_LETTER_QUEUE)
    public void receiveA(Message message, Channel channel) throws IOException {
        System.out.println("死信队列得到消息：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
