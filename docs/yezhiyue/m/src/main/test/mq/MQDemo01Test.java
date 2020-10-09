package mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * MQ单个队列的消息发送和接收
 * 说明：AMQP 0-9-1 ，消息开放式传递的通用协议
 *
 * @author 叶之越
 * Description
 * Date 2020/8/30
 * Time 17:52
 * Mail 739153436@qq.com
 */
public class MQDemo01Test {

    private final static String QUEUE_NAME = "hello";
    private final String HOST = "59.110.213.92";
    private final int PORT = 5672;

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
             // 帮我们处理了协议版本和认证
             Channel channel = connection.createChannel()) {

            // 2. 接下来指定我们需要发送消息的队列
            // 注意：队列不需要我们手动创建，只要这个队列不存在，MQ就会帮我们自动创建
            // 消息都是以字节存放的
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message1 = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message1.getBytes());
            String message2 = "END !";
            channel.basicPublish("", QUEUE_NAME, null, message2.getBytes());
            System.out.println(" [x] Sent '" + message1 + "'");

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
    public void responseTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 获取消息数量信息
        System.out.println(declareOk);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }

}
