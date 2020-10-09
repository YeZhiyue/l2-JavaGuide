package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * MQ单个队列的消息发送和接收，单个消息的发送
 * 说明：AMQP 0-9-1 ，消息开放式传递的通用协议
 *
 * @author yzy
 * Description
 * Date 2020/8/30
 * Time 17:52
 */
public class Demo01 {
}

class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 1. 建立频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 2. 发送消息
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" Sent '" + message + "'");
        }
    }
}


class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        // 1. 频道建立
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 2. 指定接收消息的消息队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" Waiting for messages.");

        // 3. 开启两个线程 一个处理业务逻辑：一个处理和MQ的响应
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
