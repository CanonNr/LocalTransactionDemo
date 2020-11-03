package com.lksun.service2.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    public static final String LKSUN_TEST_EXCHANGE = "lksun_test_exchange";
    public static final String LKSUN_TEST_QUEUE = "lksun_test_queue";
    public static final String LKSUN_TEST_ROUTING_KEY = "lksun_test_routingKey";
    public static final String LKSUN_TEST_DEAD_LETTER_EXCHANGE = "lksun_test_dead_letter_exchange";
    public static final String LKSUN_TEST_DEAD_LETTER_QUEUE = "lksun_test_dead_letter_queue";
    public static final String LKSUN_TEST_DEAD_LETTER_ROUTING_KEY = "lksun_test_dead_letter_queue";

    @Bean
    public DirectExchange OrderExchange() {
        return new DirectExchange(LKSUN_TEST_EXCHANGE);
    }

    @Bean
    public Queue OrderQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", LKSUN_TEST_DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", LKSUN_TEST_DEAD_LETTER_ROUTING_KEY);
        return QueueBuilder.durable(LKSUN_TEST_QUEUE).withArguments(args).build();
    }

    @Bean
    public Binding OrderBinding() {
        return BindingBuilder.bind(OrderQueue()).to(OrderExchange()).with(LKSUN_TEST_ROUTING_KEY);
    }

    // 声明死信交换机
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(LKSUN_TEST_DEAD_LETTER_EXCHANGE);
    }

    // 声明死信队列
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue(LKSUN_TEST_DEAD_LETTER_QUEUE);
    }

    @Bean
    public Binding deadLetterBinding(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(LKSUN_TEST_DEAD_LETTER_ROUTING_KEY);
    }

}
