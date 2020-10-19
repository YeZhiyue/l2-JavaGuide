---

# Windows

## 杀死一个进程

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

taskkill /f /t /photoshop.exe

![](http://img.yhzcmxq.cn/picgo/20201019161704.png)

![](http://img.yhzcmxq.cn/picgo/20201019161741.png)

## 启动服务管理

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

services.msc

## echo输出打印

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![](http://img.yhzcmxq.cn/picgo/20201019162022.png)

---

# MySQL

**登录和退出**

方式(登录)：

1. mysql -uroot -p密码
2. mysql -hip -uroot -p连接目标的密码
3. mysql --host=ip --user=root --password=连接目标的密码

方式(登出)：

exit  quit

cmd 命令：

备份
 
mysqldump -u用户名 -p密码 数据库名称 > 保存路径

还原 

source 文件路径

---

# Java

1.在DOS命令行中，进入Java源文件的目录，使用 javac 命令进行编译。
2.javac Java源文件名.后缀名   举例：javac HelloWorld.java
3.java 类名字  举例：java HelloWorld
4.java -version
5.java -jar test.jar 运行jar包