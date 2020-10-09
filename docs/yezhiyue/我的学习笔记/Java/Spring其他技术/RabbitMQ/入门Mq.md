使用场景：让不同的进程之间可以进行定时的数据通信。

生产者：门锁
消费者：后端
服务器：MQ

---

<a id="_1"></a>

## `生产者`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

生产者并不能直接操作MQ的Queue对象，需要通过Exchange操作间接对消息进行处理。

生产者操作Exchange的时候需要指定routing key的路由规则

通过binding将exchange和queue关联起来

生产者通过发送routing key和binding key进行匹配来将数据发送到queue队列

Exchange Types
- fanout：直接将消息发送到与它绑定的queue当中
- direct：将消息路由到那些binding key 与routing key完全匹配的queue当中
- topic：这个和direct类似，不过其中key的名称用 . 分格，并且可以使用* #来进行通配符匹配 (*匹配一个单词，#匹配多个)

---

<a id="_2"></a>

## `消费者`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

多个消费者可以订阅同一个queue，消息会分摊给多个消费者处理。

消息处理：接收到一个消息并且处理完毕之后，我们需要清理这个mq消息，防止消息不断堆积。

---

<a id="_3"></a>

## `MQ服务器`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

Queue：RabbitMQ的内部对象，用于存储消息

消息持久化：防止一些意外情况导致消息数据丢失，MQ使得Queue和Message都可以进行持久化操作

设置消费者负载：因为同一个queue可以有着多个消费者对这个queue进行消费，所以需要根据消费者对消息的处理效率设置如何合理分发消息的比例。



