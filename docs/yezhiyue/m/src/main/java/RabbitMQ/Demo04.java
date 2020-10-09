package RabbitMQ;

import com.rabbitmq.client.*;

/**
 * Exchange:direct使用
 * 可以精确匹配某个Queue的Exchange路由，有着自己的匹配规则
 *
 * @author yzy
 * Description
 * Date 2020/8/31
 * Time 20:58
 */
public class Demo04 {
}

class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // Exchange配置为direct类型，可以配置路由
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String severity1 = "severity1";
            String message1 = "message1";

            String severity2 = "severity2";
            String message2 = "message2";

            // 一个Exchange绑定两个Queue
            channel.basicPublish(EXCHANGE_NAME, severity1, null, message1.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE_NAME, severity2, null, message2.getBytes("UTF-8"));
            System.out.println("Sent '" + severity1 + "':'" + message1 + "'");
            System.out.println("Sent '" + severity2 + "':'" + message2 + "'");
        }
    }
}

class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 同一个exchange绑定了两个不同的Queue，并且可以通过routingkey去精确匹配
        // https://www.rabbitmq.com/img/tutorials/direct-exchange-multiple.png
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();

        // 可以同时对两个消息队列进行消费
        channel.queueBind(queueName, EXCHANGE_NAME, "severity1");
        channel.queueBind(queueName, EXCHANGE_NAME, "severity2");

        System.out.println("Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}