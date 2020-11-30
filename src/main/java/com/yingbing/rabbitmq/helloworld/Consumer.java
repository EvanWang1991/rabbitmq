package com.yingbing.rabbitmq.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yingbing.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("helloworld", false, false, false, null);

        channel.basicConsume("helloworld", false, new Reciver(channel));

        System.out.println("发送数据：" + message);
    }

}

class Reciver extends DefaultConsumer{
    private  Channel channel;

    public Reciver(Channel channel) {
        super(channel);
        this.channel = channel;

    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body);
        System.out.println("收到消息:" + message);
        System.out.println("消息的标签:" + envelope.getDeliveryTag());
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
