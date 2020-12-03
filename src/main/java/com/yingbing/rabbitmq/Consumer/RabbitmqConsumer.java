package com.yingbing.rabbitmq.Consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@PropertySource("transaction_mq.properties")
@Slf4j
public class RabbitmqConsumer {

    @RabbitListener(queues = "${FIRST_QUEUE}")
    public void receive(Message message, Channel channel){
        log.info("接收到的消息：" + new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
