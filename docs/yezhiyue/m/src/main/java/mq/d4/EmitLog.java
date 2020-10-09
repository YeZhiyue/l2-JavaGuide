package mq.d4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {

    private final static String QUEUE_NAME = "hello";
    private final static String HOST = "59.110.213.92";
    private final static int PORT = 5672;
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) {
        // 1. 和目标服务创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("59.110.213.92");factory.setPort(5672);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 通过Exchange来指定消息和Queue匹配规则
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String message = "info: Hello World!";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (
                TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}