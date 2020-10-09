package RabbitMQ;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * 这个案例中我们将创建工作队列(任务队列)，该队列将用
 * 于在多个工作人员之间分配耗时的任务。
 * <p>
 * 应用场景：资源密集型任务，可以将任务分配给多个工作
 * 者。这个案例中使用线程来模拟服务器很忙的场景。
 *
 * @author yzy
 * Description
 * Date 2020/8/30
 * Time 17:52
 */
public class MQDemo02Test {

    private final static String TASK_QUEUE_NAME = "busywork";
    private final String HOST = "59.110.213.92";
    private final int PORT = 5672;

    /**
     * @description 消息生产者
     * @author yzy
     * @email 739153436@qq.com
     * @date 2020/8/30 18:14
     */
    @Test
    public void product() {

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
            channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
            // Hello...World!
            String message1 = String.join("...", "Hello", "World!");
            // END...
            String message2 = "END";
            channel.basicPublish("", TASK_QUEUE_NAME,
                    // 防止MQ宕机配置
                    MessageProperties.PERSISTENT_TEXT_PLAIN, message1.getBytes());
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getBytes());
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getBytes());
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getBytes());
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getBytes());
            System.out.println("Sent '" + Arrays.asList(message1,message2) + "'");

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @description 模拟忙碌的服务器
     * @author yzy
     * @email 739153436@qq.com
     * @date 2020/8/30 19:22
     */
    private void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }

    @Test
    public void workers(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            worker1();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,"线程二"
        ).run();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread().getName());
                            worker2();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,"线程二"
        ).run();
    }

    /**
     * @description 工作者1
     * @author yzy
     * @email 739153436@qq.com
     * @date 2020/8/30 18:52
     */
    public void worker1() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 设置最大一次性最大消息分发数量，这告诉RabbitMQ一次不要给工人一个以上的消息。换句话说，在处理并确认上一条消息之前，不要将新消息发送给工作人员。而是将其分派给尚不繁忙的下一个工作人员。
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [Worker1] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [Worker1] Done");
            }
        };


        // 设置是否让MQ自动确认消息处理是否成功
        // 设置为false表示然工作者自己发送适当的请求
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

        Thread.sleep(6000);
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(TASK_QUEUE_NAME,
                // durable：防止MQ宕机导致消息丢失
                false,
                false,
                false,
                null);
        // 获取消息数量信息
        System.out.println(declareOk);

    }

    /**
     * @description 工作者2
     * @author yzy
     * @email 739153436@qq.com
     * @date 2020/8/30 18:52
     */
    public void worker2() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 设置最大一次性最大消息分发数量，这告诉RabbitMQ一次不要给工人一个以上的消息。换句话说，在处理并确认上一条消息之前，不要将新消息发送给工作人员。而是将其分派给尚不繁忙的下一个工作人员。
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [Worker2:] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [Worker2] Done");
            }
        };


        // 设置是否让MQ自动确认消息处理是否成功
        // 设置为false表示然工作者自己发送适当的请求
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

        Thread.sleep(6000);
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(TASK_QUEUE_NAME,
                // durable：防止MQ宕机导致消息丢失
                false,
                false,
                false,
                null);
        // 获取消息数量信息
        System.out.println(declareOk);

    }

}
