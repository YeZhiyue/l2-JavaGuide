# RabbitMQ学习案例

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>安装+环境配置</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.1" rel="nofollow" target="_self">Docker安装</a>
### <a href="#_2" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>入门案例</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.1" rel="nofollow" target="_self">通过制定队列名称进行发送和接收(Hello World)</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.2" rel="nofollow" target="_self">任务队列&&MQ的防止数据丢失机制</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.3" rel="nofollow" target="_self">Exchange faint(随机分配队列)</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.4" rel="nofollow" target="_self">Exchange direct(直接指定routKey匹配到指定Queue)</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.5" rel="nofollow" target="_self">Exchange topic(通配符指定routKey，更加复杂的消息分发)</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.6" rel="nofollow" target="_self">MQ在RPC框架中的使用</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.7" rel="nofollow" target="_self">配置说明</a>
### <a href="#_3" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>部分技术要点</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.1" rel="nofollow" target="_self">MQ如何防止信息丢失</a>
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91

---

<a id="_1"></a>

## `安装+环境配置`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. Docker安装**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

<<<<<<< HEAD
=======
这里我选择Docker安装，一条命令直接完成，其他的安装方式可以参考官方文档

>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

---

<a id="_2"></a>

## `入门案例`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<<<<<<< HEAD
[官方HelloWord教程](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)

---

<a id="_2"></a>
=======
[官方向导案例](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)

参考代码：https://github.com/YeZhiyue/RabbitMQDemo

案例提前资源准备

1.1 Maven项目，需要用到Jar包

```java
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>5.6.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.25</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-simple -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.25</version>
</dependency>
```

1.2 个人配置文件，方便学习

```java
package RabbitMQ;

/**
 * @author yzy
 * Description
 * Date 2020/8/31
 * Time 21:07
 */
public class MQConfig {
    public final static String QUEUE_NAME = "hello";
    public final static String HOST = "59.110.213.92";
    public final static int PORT = 5672;
}
```

---

<a id="_2.1"></a>

**1. 通过制定队列名称进行发送和接收(Hello World)**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

一个消息生产者和一个消息消费者的交互案例，案例中直接制定了Queue名称(不太推荐)。

![](https://www.rabbitmq.com/img/tutorials/python-one.png)

```java
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
```


---

<a id="_2.2"></a>

**2. 任务队列&&MQ的防止数据丢失机制**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

任务队列，就是一个Queue分发了多个消息，需要多个工作者一起来处理这些消息，减轻压力。并且案例中还有一些数据安全措施的配置。

![](https://www.rabbitmq.com/img/tutorials/prefetch-count.png)

```java
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

            // 2. 消息配置 参数二表示是否进行持久化配置
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

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
```

---

<a id="_2.3"></a>

**3. Exchange faint(随机分配队列)**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

faint类型的Exchange，通过Exchange来创建Queue和匹配key，这里就不需要再额外指定Queue的名称，名称就是根据指定规则来生成的。

![](https://www.rabbitmq.com/img/tutorials/python-three-overall.png)

```java
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
```

---

<a id="_2.4"></a>

**4. Exchange direct(直接指定routKey匹配到指定Queue)**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

direct类型的Exchange，需要指定routKey来和指定的Queue来匹配

![](https://www.rabbitmq.com/img/tutorials/direct-exchange-multiple.png)

```java
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
```

---

<a id="_2.5"></a>

**5. Exchange topic(通配符指定routKey，更加复杂的消息分发)**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

topic类型的Exchange，可以配置更加复杂的routKey规则，可以使用通配符。

![](https://www.rabbitmq.com/img/tutorials/python-five.png)

```java
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
```

---

<a id="_2.6"></a>

**6. MQ在RPC框架中的使用**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

https://www.rabbitmq.com/tutorials/tutorial-six-java.html

---

<a id="_2.7"></a>

**7. 配置说明**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 持久化配置(生产者)

```java
// 消息配置 参数二表示是否进行持久化配置
channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
// 3. 消息推送
channel.basicPublish("", TASK_QUEUE_NAME,
        // 防止MQ宕机配置,MQ会对消息进行持久化
        MessageProperties.PERSISTENT_TEXT_PLAIN,
        message.getBytes("UTF-8"));
```

1.2 配置工作队列中工作者单次消息处理最大量(消费者)

```java
channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
// 配置消息单次最大接收量为 1
channel.basicQos(1);
```

1.3 配置消费者告知任务完成(消费者)

```java
// true表示自动处理，false表示自定义告知MQ我消费这条数据完成了，MQ就可以删除这个消息了
// 设置是否让MQ自动确认消息处理是否成功
// 设置为false表示然工作者自己发送适当的请求来告知MQ我把这个消息消费了
channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
});
```

---

<a id="_3"></a>
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91

## `部分技术要点`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<<<<<<< HEAD
<a id="_2.1"></a>
=======
<a id="_3.1"></a>
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91

**1. MQ如何防止信息丢失**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 防止消息在消费者手中丢失的机制

在工作队列中，多个工作者处理集中式的任务，如果其中有一个工作者在处理任务的途中不幸牺牲。那么消息队列就会回收这个消息并且重新指派这个message任务。工作完成者会发送一个Acknowledge请求告知MQ我完成任务了。

```java
        // 设置是否让MQ自动确认消息处理是否成功
        // 设置为false表示然工作者自己发送适当的请求
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });
```

1.2 防止消息在MQ中间件发生意外丢失

MQ可以通过持久化技术防止自己发生意外导致信息丢失，可以在重启服务后重新读取持久化数据。

注意：已经生产的队列不能再改变其参数配置


```java
// 消息生产者
channel.basicPublish("", "task_queue",
            MessageProperties.PERSISTENT_TEXT_PLAIN,
            message.getBytes());
```

```java
// 消息消费者
AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(TASK_QUEUE_NAME,
        // durable：防止MQ宕机导致消息丢失
        false,
        false, 
        false, 
        null);
```
