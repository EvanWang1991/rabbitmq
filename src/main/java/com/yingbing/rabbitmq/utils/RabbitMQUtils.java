package com.yingbing.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {

    private static ConnectionFactory factory = new ConnectionFactory();

    static {
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("test");
        factory.setPassword("test");
        factory.setVirtualHost("/test");
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
