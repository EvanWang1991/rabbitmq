package com.yingbing.rabbitmq.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@PropertySource("classpath:transaction_mq.properties")
@Service
@Slf4j
public class ProducerService {
    @Value("${TRANSACTION_EXCHANGE_NAME}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        //创建对象的同时，开启channel事务模式
        rabbitTemplate.setChannelTransacted(true);
    }


    @Transactional
    public void send(String msg){
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
        log.info("已发送消息：" + msg);
        if(msg.equals("**")){
            throw new RuntimeException("抛出异常了");
        }
    }
}
