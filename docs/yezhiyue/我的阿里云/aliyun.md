

# 我的阿里云服务

## 我的服务

1.1 公网

```java
59.110.213.92
```

1.2 私网

```java
172.17.60.195
```

## Redis

1.1 地址

```java
http://59.110.213.92:6380
```

1.2 连接命令

```java
redis-cli -h 59.110.213.92 -p 6380
// 完整命令
redis-cli -h 59.110.213.92 -p 6380 -a password
```

1.3 windows命令

```java
redis-cli.exe -h 59.110.213.92 -p 6380
```

1.4 Docker安装Redis补充

```java
// 安装
docker run -p 6379:6379 -d redis:latest redis-server
// 连接
docker exec -ti d0b86 redis-cli -h localhost -p 6379 
```

## RabbitMQ服务地址

1.1 服务地址

```java
http://59.110.213.92:5672
```

1.2 图形管理界面

```java
http://59.110.213.92:15672
```

## MySQL 服务

```java
http://59.110.213.92:3307
```
