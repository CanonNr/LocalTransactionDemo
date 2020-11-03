package com.lksun.service2.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.lksun.service2.config.RabbitMqConfig;
import com.lksun.service2.dto.OrderMqMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqTask {
    @RabbitListener(queues = RabbitMqConfig.LKSUN_TEST_QUEUE)
    public void task(Message message, Channel channel) throws IOException{
        try{
            String msg = new String(message.getBody());
            System.out.println(msg);
            OrderMqMessage orderMessage = JSON.parseObject(msg, OrderMqMessage.class);
            System.out.println(orderMessage);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (JSONException jsonException){
            System.out.println("JSON 解析错误... ");
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println(e.getMessage());
        }

    }
}
