package RabbitMQ;

import com.rabbitmq.client.*;

import java.util.Arrays;

/**
 * 这个案例中我们将创建工作队列(任务队列)，该队列将用
 * 于在多个工作人员之间分配耗时的任务。
 * <p>
 * 应用场景：资源密集型任务，可以将任务分配给多个工作
 * 者。这个案例中使用线程来模拟服务器很忙的场景。
 * <p>
 * MQ持久化配置：
 * 生产者：
 * // 第二个参数是持久化配置
 * channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
 * channel.basicPublish("", TASK_QUEUE_NAME,
 * // 防止MQ宕机配置,MQ会对消息进行持久化
 * MessageProperties.PERSISTENT_TEXT_PLAIN,
 * message.getBytes("UTF-8"));
 *
 * MQ工作者接收消息配置：
 * 消费者：
 * // 参数表示每次接收的消息数量
 * channel.basicQos(1);
 *
 * @author yzy
 * Description
 * Date 2020/8/30
 * Time 17:52

 */
public class Demo02 {
}

class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // 1. 建立频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {


            String message = String.join(" ", Arrays.asList("Hello", "World", "!"));

            // 3. 消息推送
            channel.basicPublish("", TASK_QUEUE_NAME,
                    // 防止MQ宕机配置,MQ会对消息进行持久化
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println("Sent '" + message + "'");
        }
    }

}

class Worker {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // 1. 建立连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.HOST);
        factory.setPort(MQConfig.PORT);
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // 2. 配置任务队列接收方式
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("Waiting for messages.");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println("Received '" + message + "'");
            try {
                doWork(message);
            } finally {
                System.out.println("Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        // 设置是否让MQ自动确认消息处理是否成功
        // 设置为false表示然工作者自己发送适当的请求来告知MQ我把这个消息消费了
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

