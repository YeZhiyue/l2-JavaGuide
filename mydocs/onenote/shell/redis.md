---

# 基础知识

## redis的数据类型

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

* redis存储的是：key,value格式的数据，其中key都是字符串，value有5种不同的数据结构
    * value的数据结构：
        1) 字符串类型 string
        2) 哈希类型 hash ： map格式
        3) 列表类型 list ： linkedlist格式，链表格式。支持重复元素
        4) 集合类型 set  ： 不允许重复元素
        5) 有序集合类型 sortedset：不允许重复元素，且元素有顺序

## 连接Redis 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

**Windows**

在CMD命令行进行操作(注意这里不能直接打开reids-cli.exe操作)

redis-cli -h 192.168.1.1 -p 6379 -a 12345

**Linux**

ps -ef|grep redis

./redis-server redis.conf
 
./redis-cli -h 192.168.100.109  -p  6379

---

# 数据类型操作

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

## 基础命令

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. keys * : 查询所有的键
2. type key ： 获取键对应的value的类型
3. del key：删除指定的key value

## 字符串类型 string

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. 存储： set key value
    127.0.0.1:6379> set username zhangsan
    OK
2. 获取： get key
    127.0.0.1:6379> get username
    "zhangsan"
3. 删除： del key
    127.0.0.1:6379> del age
    (integer) 1

## 哈希类型 hash

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. 存储： hset key field value
    127.0.0.1:6379> hset myhash username lisi
    (integer) 1
    127.0.0.1:6379> hset myhash password 123
    (integer) 1
2. 获取：
    * hget key field: 获取指定的field对应的值
        127.0.0.1:6379> hget myhash username
        "lisi"
    * hgetall key：获取所有的field和value
        127.0.0.1:6379> hgetall myhash
        1) "username"
        2) "lisi"
        3) "password"
        4) "123"

3. 删除： hdel key field
    127.0.0.1:6379> hdel myhash username
    (integer) 1

## 列表类型 list:可以添加一个元素到列表的头部（左边）或者尾部（右边）

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. 添加：
    1. lpush key value: 将元素加入列表左表

    2. rpush key value：将元素加入列表右边

        127.0.0.1:6379> lpush myList a
        (integer) 1
        127.0.0.1:6379> lpush myList b
        (integer) 2
        127.0.0.1:6379> rpush myList c
        (integer) 3
2. 获取：
    * lrange key start end ：范围获取
        127.0.0.1:6379> lrange myList 0 -1
        1) "b"
        2) "a"
        3) "c"
3. 删除：
    * lpop key： 删除列表最左边的元素，并将元素返回
    * rpop key： 删除列表最右边的元素，并将元素返回

## 集合类型 set ： 不允许重复元素

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. 存储：sadd key value
    127.0.0.1:6379> sadd myset a
    (integer) 1
    127.0.0.1:6379> sadd myset a
    (integer) 0
2. 获取：smembers key:获取set集合中所有元素
    127.0.0.1:6379> smembers myset
    1) "a"
3. 删除：srem key value:删除set集合中的某个元素
    127.0.0.1:6379> srem myset a
    (integer) 1
## 有序集合类型 sortedset：不允许重复元素，且元素有顺序.每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. 存储：zadd key score value
    127.0.0.1:6379> zadd mysort 60 zhangsan
    (integer) 1
    127.0.0.1:6379> zadd mysort 50 lisi
    (integer) 1
    127.0.0.1:6379> zadd mysort 80 wangwu
    (integer) 1
2. 获取：zrange key start end [withscores]
    127.0.0.1:6379> zrange mysort 0 -1
    1) "lisi"
    2) "zhangsan"
    3) "wangwu"

    127.0.0.1:6379> zrange mysort 0 -1 withscores
    1) "zhangsan"
    2) "60"
    3) "wangwu"
    4) "80"
    5) "lisi"
    6) "500"
3. 删除：zrem key value
    127.0.0.1:6379> zrem mysort lisi
    (integer) 1


