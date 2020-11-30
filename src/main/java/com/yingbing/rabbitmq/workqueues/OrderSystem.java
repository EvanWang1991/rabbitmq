package com.yingbing.rabbitmq.workqueues;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yingbing.rabbitmq.model.SMS;
import com.yingbing.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.yingbing.rabbitmq.utils.RabbitMQConstant.QUEUE_SMS;

public class OrderSystem {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_SMS, false, false, false, null);

        for (int i = 0; i < 100; i++) {
            SMS sms = new SMS("客户"+ i , "123", "下单成功");
            channel.basicPublish("", QUEUE_SMS, null, new Gson().toJson(sms).getBytes());
        }
        System.out.println("发送成功");
        channel.close();
        connection.close();
    }
}
