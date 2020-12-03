package com.yingbing.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:transaction_mq.properties")
@Component
public class RabbitConfig {
    @Value("${TRANSACTION_EXCHANGE_NAME}")
    private String exchangeName;

    @Value("${FIRST_QUEUE}")
    private String firstQueue;

    @Bean("${TRANSACTION_EXCHANGE_NAME}")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange(exchangeName);
    }

    @Bean("${FIRST_QUEUE}")
    public Queue getQueue(){
        return new Queue(firstQueue);
    }

    @Bean
    public Binding bindingExchange(@Qualifier("${TRANSACTION_EXCHANGE_NAME}") FanoutExchange fanoutExchange,
                                   @Qualifier("${FIRST_QUEUE}") Queue firstQueue){
        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
    }

    @Bean
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory cachingConnectionFactory) {
        return new RabbitTransactionManager(cachingConnectionFactory);
    }
}
