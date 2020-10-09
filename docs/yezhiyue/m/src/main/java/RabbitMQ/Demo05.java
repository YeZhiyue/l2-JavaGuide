package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.Arrays;
import java.util.List;

/**
 * Exchange：topic
 * 可以通过通配符来分配路由关系，并且routkey必须通过 . 来分
 * 隔，其中都是一个个的单词。
 * <p>
 * 通配符：
 * # 代表多个额单词
 * * 代表单个单词
 *
 * @author yzy
 * Description
 * Date 2020/8/31
 * Time 20:58
 */
public class Demo05 {
    // 规则制定："<speed>.<colour>.<species>"
    public static final List<String> ROUTING_KEY = Arrays.asList(
            "*.orange.*",
            "*.*.rabbit",
            "lazy.#"
    );
}

class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            // 制造了3个有着通配符规则的Exchange，我么之后创建的符合规则的routKey就会放到响应的队列当中去
            for (String routKey : Demo05.ROUTING_KEY) {
                channel.basicPublish(EXCHANGE_NAME, routKey, null, ("发送消息" + routKey).getBytes("UTF-8"));
            }
        }
    }
}


class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName;

        queueName = channel.queueDeclare().getQueue();


        for (String bindingKey : Demo05.ROUTING_KEY) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }

        System.out.println("Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}

