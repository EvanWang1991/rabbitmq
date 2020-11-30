package com.yingbing.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yingbing.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("helloworld", false, false, false, null);
        try {
            String message;
            while (scanner.hasNext()){
                message = scanner.nextLine();
                channel.basicPublish("", "helloworld", null, message.getBytes());
                System.out.println("发送数据：" + message);
            }
        }finally {
            connection.close();
        }
    }
}
