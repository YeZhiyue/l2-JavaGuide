package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * 演示MQ中真正的消息处理系统，之前的案例中都是生产者直接发送消息到MQ的Queue中，
 * 但是MQ不推荐这样的做法。MQ推荐使用Exchange。
 * <p>
 * Exchange：可以理解为小代理，一方面接收生产者发送过来的消息，一方面发送这个消
 * 息到消息队列中去。Exchange需要精确处理每一个发送过来的消息，判断需要把这个消
 * 息发送到已经存在的一个队列中？还是发送到多个队列中？还是丢弃不处理？(direct,
 * topic, headers and fanout 四种处理模式)
 * <p>
 * Exchange类型：faint
 *
 * @author yzy
 * Description
 * Date 2020/8/30
 * Time 17:52

 */
public class Demo03 {
}

class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Exchange配置：
            // 通过Exchange来指定消息和Queue匹配规则
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = "info: Hello World!";

            // 推送Exchange消息
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("Sent '" + message + "'");
        }
    }

}

class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //  指定Exchange来获取Queue的名字
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        System.out.println(queueName);
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println("Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}


