

# 安装

参考博客：https://www.yuque.com/helloz/ccaa/install

```docker
docker run --name="l-ccaa" -d -p 6080:6080 -p 6081:6081 -p 6800:6800 -p 51413:51413 \
    -v /data/ccaaDown:/data/ccaaDown \
    -e PASS="xiaoz.me" \
    helloz/ccaa \
    sh -c "dccaa pass && dccaa start"
```

## 放行端口号

6080/6800/6081/6998/51413

## 初始账号密码

登录：http://59.110.213.92:6080

账号：ccaa
密码：admin

## 每周更新BT列表

/etc/ccaa/upbt.sh

## 其他常用命令

```java
#进入CCAA管理界面
ccaa
#查看ccaa状态
ccaa status
#启动ccaa
ccaa start
#停止ccaa
ccaa stop
#重启ccaa
ccaa restart
#查看当前版本
ccaa -v
```