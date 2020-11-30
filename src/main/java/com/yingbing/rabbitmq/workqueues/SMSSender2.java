package com.yingbing.rabbitmq.workqueues;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yingbing.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.yingbing.rabbitmq.utils.RabbitMQConstant.QUEUE_SMS;

public class SMSSender2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_SMS, false, false, false, null);
        channel.basicQos(1);
        channel.basicConsume(QUEUE_SMS, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
