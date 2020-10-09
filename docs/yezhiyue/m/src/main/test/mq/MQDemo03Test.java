package mq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 演示MQ中真正的消息处理系统，之前的案例中都是生产者直接发送消息到MQ的Queue中，
 * 但是MQ不推荐这样的做法。MQ推荐使用Exchange。
 * <p>
 * Exchange：可以理解为小代理，一方面接收生产者发送过来的消息，一方面发送这个消
 * 息到消息队列中去。Exchange需要精确处理每一个发送过来的消息，判断需要把这个消
 * 息发送到已经存在的一个队列中？还是发送到多个队列中？还是丢弃不处理？(direct,
 * topic, headers and fanout 四种处理模式)
 *
 * @author 叶之越
 * Description
 * Date 2020/8/30
 * Time 17:52
 * Mail 739153436@qq.com
 */
public class MQDemo03Test {

    private final static String QUEUE_NAME = "hello";
    private final String HOST = "59.110.213.92";
    private final int PORT = 5672;
    private static final String EXCHANGE_NAME = "logs";

    /**
     * @description 发送单个消息然后退出程序
     * @author 叶之越
     * @email 739153436@qq.com
     * @date 2020/8/30 18:14
     */
    @Test
    public void requestTest() {

        // 1. 和目标服务创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 通过Exchange来指定消息和Queue匹配规则
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String message = "info: Hello World!";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @description 接收消息然后退出程序
     * @author 叶之越
     * @email 739153436@qq.com
     * @date 2020/8/30 18:52
     */
    @Test
    public void responseTest() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //  指定Exchange来获取Queue的名字
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        System.out.println(queueName);
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Thread.sleep(4000);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
        Thread.sleep(4000);
    }
}
