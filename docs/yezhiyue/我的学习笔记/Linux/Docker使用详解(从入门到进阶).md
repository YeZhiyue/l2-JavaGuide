
# ==主题:==`Docker使用详解(从入门到进阶)`

<hr>
<a id="_top"></a>

## 目录:
- <a href="#_1" rel="nofollow" target="_self">一、Docker 安装（CentOS 7）</a>
- <a href="#_2" rel="nofollow" target="_self">二、Docker 帮助命令</a>
- <a href="#_3" rel="nofollow" target="_self">三、Docker 镜像查看</a>
- <a href="#_4" rel="nofollow" target="_self">四、Docker 镜像搜索</a>
- <a href="#_5" rel="nofollow" target="_self">五、Docker 镜像下载</a>
- <a href="#_16" rel="nofollow" target="_self">六、Docker 镜像删除</a>
- <a href="#_7" rel="nofollow" target="_self">七、Docker 容器命令</a>
- <a href="#_8" rel="nofollow" target="_self">八、Docker 进阶命令</a>
- <a href="#_9" rel="nofollow" target="_self">九、Docker 运行 Nginx</a>
- <a href="#_10" rel="nofollow" target="_self">十、Docker 运行 Tomcat</a>
- <a href="#_11" rel="nofollow" target="_self">十一、Docker 运行 Elasticsearch</a>
- <a href="#_100" rel="nofollow" target="_self">补充:Docker 图形工具 Portainer</a> 
- <a href="#_101" rel="nofollow" target="_self">补充:Docker 制做镜像 (commit)</a>
- <a href="#_102" rel="nofollow" target="_self">补充:Docker 数据卷 (目录挂载技术)</a>
- <a href="#_103" rel="nofollow" target="_self">实战:Docker MySQL实战 (数据映射到主机)</a>
- <a href="#_104" rel="nofollow" target="_self">实战:Docker 具名和匿名挂载(Nginx作为示例)</a>
- <a href="#_105" rel="nofollow" target="_self">实战:Docker Dockerfile构建镜像(专业配置镜像)</a>
- <a href="#_106" rel="nofollow" target="_self">实战:Docker 容器数据卷(最后附加MySQL的数据共享示例)</a>
- <a href="#_107" rel="nofollow" target="_self">实战:Docker Dockerfile进阶(配置更加复杂的镜像)</a>
- <a href="#_108" rel="nofollow" target="_self">实战:Docker Tomcat的Dockerfile创建</a>
- <a href="#_109" rel="nofollow" target="_self">实战:Docker 发布自己的镜像(阿里云和DockerHub)</a>
- <a href="#_111" rel="nofollow" target="_self">高阶:Docker Docker --link 配置直接通过容器名称进行访问</a>
- <a href="#_112" rel="nofollow" target="_self">高阶:Docker Docker自定义网络</a>
- <a href="#_113" rel="nofollow" target="_self">高阶:Docker Docker打通网络</a>
- <a href="#_114" rel="nofollow" target="_self">高阶实战:Docker Redis集群搭建</a>

- <a href="#_button" rel="nofollow" target="_self"> 小结:命令总结汇总</a>
- <a href="#_button01" rel="nofollow" target="_self">小结:我的Docker网址整理</a>


<hr>
<a id="_1"></a>

## `一、Docker安装(CentOS 7)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>


**1.如果机器上有docker，那么首先移除前版本的docker**

```java
sudo yum remove docker \
              docker-client \
              docker-client-latest \
              docker-common \
              docker-latest \
              docker-latest-logrotate \
              docker-logrotate \
              docker-engine
```

**2.接着安装docker所必须的yum插件**

```java
sudo yum install -y yum-utils
```

3.接着配置我们的镜像仓库，这里我们选择阿里云仓库，这样子速度会比较快

```java
sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo 
```

**4. 接着我们就可以下载我们最新版本的docker了**

```java
sudo yum install docker-ce docker-ce-cli containerd.io
```

**5. 接着我们运行我们的docker，然后查看版本号。**

```java
sudo systemctl start docker
```

```java
$ docker version
```

```java
# 表示我们安装成功了

Client: Docker Engine - Community
 Version:           19.03.11
 API version:       1.40
 Go version:        go1.13.10
 Git commit:        42e35e61f3
 Built:             Mon Jun  1 09:13:48 2020
 OS/Arch:           linux/amd64
 Experimental:      false
```

**6. 最后运行我们的可以接着在进行我们第一个镜像的拖入运行测试**

```java
sudo docker run hello-world
```

```java
# 首先docker发现我们没有 hello-world 的镜像

Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
0e03bdcc26d7: Already exists 
Digest: sha256:d58e752213a51785838f9eed2b7a498ffa1cb3aa7f946dda11af39286c3db9a9
Status: Downloaded newer image for hello-world:latest

# 接着docker去中央仓库寻找目标进行并且进行下载

Hello from Docker!
This message shows that your installation appears to be working correctly.
```

**7. 最后如果有疑惑的话，可以直接去官网查看安装教程。下面是CentOS下载教程的链接，不过是外网，访问起来可能有点慢。**

- [Docker官网CentOS 7 安装教程](https://docs.docker.com/engine/install/centos/)


<hr>
<a id="_2"></a>

## `二、Docker 基础命令`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 查看帮助命令**

```java
docker pull --help
```

```java
# 基本的命令使用格式，参数会在下面描述
Usage:  docker pull [OPTIONS] NAME[:TAG|@DIGEST]

# 表示这个命令可以拉取一个进行或者仓库从docker的中央仓库
Pull an image or a repository from a registry

# 下面是一些命令行参数的描述
Options:
  -a, --all-tags                Download all tagged images in the repository 
      --disable-content-trust   Skip image verification (default true)
      --platform string         Set platform if server is multi-platform capable
  -q, --quiet                   Suppress verbose output
```

**2. 如果需要详细的解释和Demo，可以直接去Docker官网查看，就是速度会比较慢**

- [Docker官网命令文档](https://docs.docker.com/engine/reference/commandline/run/)

<hr>
<a id="_3"></a>

## `三、Docker 镜像查看`

- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>


**1. 查看本地主机上的镜像**

1.1 列出所有镜像

```java
docker images
```

```java
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              bf756fb1ae65        5 months ago        13.3kB
```

1.2 只显示镜像的id

```java
docker images -q 
```

```java
bf756fb1ae65
```

<hr>
<a id="_4"></a>

## `四、Docker搜索镜像`

- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>


**1. 首先我们先到官网进行一波搜索，搜索我们需要的mysql**

- [官网镜像搜索地址](https://hub.docker.com/search?q=mysql&type=image)

1.1 这里我们找到比较常用的 mysql 5.7

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200611002120803.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**2. 我们使用Docker进行搜索**

2.1 不进行过滤直接搜索全部

```java
docker search mysql
```

2.1 进行过滤搜索,这里根据STARS数量进行过滤，筛选数量 > 3000 的目标。

```java
docker search mysql --filter=STARS=3000
```

```java
NAME                DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
mysql               MySQL is a widely used, open-source relation…   9604                [OK]                
mariadb             MariaDB is a community-developed fork of MyS…   3490                [OK]            
```

<hr>
<a id="_5"></a>

## `五、Docker下载镜像`

- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>


**1. 根据 四 中我们的搜索镜像命令，我么选择我们要拉取的镜像的版本进行拉取**

1.1 这个表示我们拉取最新的mysql版本

```java
docker pull mysql
```

1.2 接下来我们进行有版本选择的mysql拉取

```java
docker pull mysql:5.7
```

1.3 这里额外做一点补充，来看看docker的强大之处。注意这里的上面的`Pull complete`和下面的`Already exists `部分，我们可以观察到前面的UUID是相同的。说明Docker可以直接复用之前拉好的镜像的相同的部分，极大的减少了空间的浪费。

```java
root@10.200.25.224[23:12:47]:~
$ docker pull mysql
Using default tag: latest
latest: Pulling from library/mysq

# 下面这些部分会被复用
8559a31e96f4: Pull complete 
d51ce1c2e575: Pull complete 
c2344adc4858: Pull complete 
c75914a65ca2: Pull complete 
1ae8042bdd09: Pull complete 
Digest: sha256:8b7b328a7ff6de46ef96bcf83af048cb00a1c86282bfca0cb119c84568b4caf6
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest
root@10.200.25.224[23:14:16]:~
$ docker pull mysql:5.7
5.7: Pulling from library/mysql

# 新拉取的镜像会和之前的镜像进行关联，如果之前镜像有公用的部分，那么就不会进行下载
8559a31e96f4: Already exists 
d51ce1c2e575: Already exists 
c2344adc4858: Already exists 
d85174a87144: Pull complete 
a4ad33703fa8: Pull complete 
f7a5433ce20d: Pull complete 
Digest: sha256:32f9d9a069f7a735e28fd44ea944d53c61f990ba71460c5c183e610854ca4854
```

<hr>
<a id="_16"></a>

## `六、Docker删除镜像`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. 我们可以根据id删除镜像**

1.1 首先查出我们的有哪些镜像，主要是获取目标镜像的id

```java
docker images
```

```java
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
mysql               5.7                 9cfcce23593a        32 hours ago        448MB
mysql               latest              be0dbf01a0f3        32 hours ago        541MB
hello-world         latest              bf756fb1ae65        5 months ago        13.3kB
```

1.2 接着我们进行目标镜像的删除，可以一次性指定多个id。这里我们直接删除两个mysql。

```java
docker rmi -f be0dbf01a0f3 bf756fb1ae65
```

```java

# 删除mysql镜像
Untagged: mysql:latest
Untagged: mysql@sha256:8b7b328a7ff6de46ef96bcf83af048cb00a1c86282bfca0cb119c84568b4caf6
Deleted: sha256:be0dbf01a0f3f46fc8c88b67696e74e7005c3e16d9071032fa0cd89773771576
Deleted: sha256:086d66e8d1cb0d52e9337eabb11fb9b95960e2e1628d90100c62ea5e8bf72306
..

# 删除hello-world镜像
Untagged: hello-world:latest
Untagged: hello-world@sha256:d58e752213a51785838f9eed2b7a498ffa1cb3aa7f946dda11af39286c3db9a9
Deleted: sha256:bf756fb1ae65adf866bd8c456593cd24beb6a0a061dedf42b26a993176745f6b
```

2. 我们也可以直接删除所有镜像(这里我又拉取了一个hello-world镜像方便演示)

```java
docker rmi -f $(docker images -aq) 
```

```java

# 先删除mysql镜像
Untagged: mysql:5.7
Untagged: mysql@sha256:32f9d9a069f7a735e28fd44ea944d53c61f990ba71460c5c183e610854ca4854
Deleted: sha256:9cfcce23593a93135ca6dbf3ed544d1db9324d4c40b5c0d56958165bfaa2d46a
Deleted: sha256:98de3e212919056def8c639045293658f6e6022794807d4b0126945ddc8324be

# 接着删除hello-world镜像
Untagged: hello-world:latest
Untagged: hello-world@sha256:d58e752213a51785838f9eed2b7a498ffa1cb3aa7f946dda11af39286c3db9a9
Deleted: sha256:bf756fb1ae65adf866bd8c456593cd24beb6a0a061dedf42b26a993176745f6b
```

<hr>
<a id="_7"></a>

## `七、Docker容器命令`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. 容器运行(这里我们拉取了一个centos7)**

```java
docker pull centos:7
```

```java
docker run -it centos:7 
```

```java
# 注意看下面的 ip 地址的变化，这里是我之前的用户
root@10.200.25.224[23:25:54]:~
$ docker run -it centos:7
# 接着我进行运行容器后进入到容器中的centos中了
[root@6b91ac8e3d9d /]# ls
anaconda-post.log  dev  home  lib64  mnt  proc  run   srv  tmp  var
bin                etc  lib   media  opt  root  sbin  sys  usr
```

**2. 退出容器**

```java
exit
```

**3. 列出的容器运行信息**

3.1 列出当前运行的容器

```java
docker ps
```

```java
CONTAINER ID        IMAGE               COMMAND             CREATED              STATUS              PORTS               NAMES
79496fc1a948        centos:7            "/bin/bash"         About a minute ago   Up About a minute                       peaceful_austin
```

3.2 列出当前容器和容器运行历史

```java
docker ps -a
```

```java
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                         PORTS               NAMES
79496fc1a948        centos:7            "/bin/bash"         8 seconds ago       Up 8 seconds                                       peaceful_austin
6b91ac8e3d9d        centos:7            "/bin/bash"         2 minutes ago       Exited (130) 20 seconds ago                        peaceful_gagarin
```

**4. 删除容器**

4.1 删除指定的容器，但是不能删除正在运行的容器，如果需要强制删除 rm -f

```java
docker rm 容器id
```

```java
# 可以看到，这个容器正在运行,所以是不可以删除的
root@10.200.25.224[23:29:50]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED              STATUS              PORTS               NAMES
79496fc1a948        centos:7            "/bin/bash"         About a minute ago   Up About a minute                       peaceful_austin
# 然后我们进行删除，可以看到出现了错误
root@10.200.25.224[23:30:16]:~
$ docker rm 79496fc1a948
Error response from daemon: You cannot remove a running container 79496fc1a948a7110638e22014860894097505ea8fce16eebdce8963752c66cc. Stop the container before attempting removal or force remove
```

**4.2 删除所有的容器**

4.2.1 删除所有容器方式一

```java
docker rm -f $(docker ps -aq)
```

```java
root@10.200.25.224[23:29:50]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED              STATUS              PORTS               NAMES
79496fc1a948        centos:7            "/bin/bash"         About a minute ago   Up About a minute                       peaceful_austin
root@10.200.25.224[23:31:25]:~
$ docker rm -f $(docker ps -aq)
# 成功的删除了
79496fc1a948
```

4.2.1 删除所有容器方式二

```java
docker ps -a -q|xargs docker rm 
```


**5. 退出容器**

5.1 将容器停止然后退出

```java
exit
```

5.2 容器不停止然后退出，注意了，这个是一个组合快捷键操作

```java
Ctrl + P + Q
```

**6. 在容器外，也就是通过docker命令对容器进行操作**

6.1 启动容器

```java
docker start 容器id
```

6.2 重启容器

```java
docker restart 容器id
```

6.3 停止当前正在运行容器

```java
docker stop 容器id
```

6.4 强制停止当前容器

```java
docker kill 容器id
```

6.5 这里对上面的命令效果进行一次性的展示

```java

# 停止 -> 启动 -> 重启 -> 强行关闭
# stop -> start -> restart -> kill
# 每个阶段都进行当前运行容器的结果打印，我们可以通过这个观察到结果
root@10.200.25.224[00:06:10]:~
$ docker stop f81027372ded
f81027372ded
root@10.200.25.224[00:06:35]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
root@10.200.25.224[00:06:38]:~
$ docker start f81027372ded
f81027372ded
root@10.200.25.224[00:06:50]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
f81027372ded        centos:7            "/bin/bash"         31 minutes ago      Up 3 seconds                            goofy_robinson
root@10.200.25.224[00:06:53]:~
$ docker restart f81027372ded
f81027372ded
root@10.200.25.224[00:07:14]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
f81027372ded        centos:7            "/bin/bash"         31 minutes ago      Up 1 second                             goofy_robinson
root@10.200.25.224[00:07:16]:~
$ docker kill f81027372ded   
f81027372ded
root@10.200.25.224[00:07:34]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

<hr>

<a id="_8"></a>

## `八、Docker 进阶命令`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. Docker 后台启动**

1.1 直接启动容器，但是如果容器没有前台进程，docker没有发现有前台应用的运行，就会自动停止。 例如nginx容器启动后发现自己没有提供服务就会立刻停止。                         

```java
docker run -d centos
```

```java
root@10.185.25.224[10:07:41]:~
# centos后台的打开之后然后查看正在运行的容器，发现并没有这个容器的运行
# 这个就是由于没有前台程序而被关闭导致的
$ docker run -d centos
eaf19f1af3f82715293656d5f4f37fb5bd96dc0b565cf6cbdcb7e7e2622fe637
root@10.185.25.224[10:07:51]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```


1.2 给容器安排一些前台任务，然后进行后台启动

```java
docker run -d centos /bin/sh -c "while true;do echo centos is running;sleep 1;done"
```

```java
# 这里我们给centos的后台启动安排了一些前台的任务，发现启动centos之后并没有停止
root@10.185.25.224[10:08:00]:~
$ docker run -d centos /bin/sh -c "while true;do echo centos is running;sleep 1;done"
10fc22834a19c63e7446c03c32b11ef156cf5d2a550b52ae93b836db338ecfa4
root@10.185.25.224[10:08:10]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
10fc22834a19        centos              "/bin/sh -c 'while t…"   6 seconds ago       Up 5 seconds                            charming_bardeen
```

1.3 刚刚给centos安排了前台日志打印任务，接下来可以看看日志打印命令

```java
docker logs -tf --tail 10 容器id
```

```java
-tf  表示显示日志
--tail number 表示要显示的日志的条数
```

```java
# 我们进行日志打印
root@10.185.25.224[10:08:16]:~
$ docker logs -tf --tail 10 10fc22834a19
2020-06-11T02:08:23.769922850Z centos is running
2020-06-11T02:08:24.772259465Z centos is running
2020-06-11T02:08:25.774665998Z centos is running
```

**2. 进入到后台运行的容器**

2.1 新的后台命令窗口创建

```java
docker exec -it 容器id bashShell
```

查看我们当前正在运行的进程

```java
docker ps
```

```java
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
28cf2fa3a0bb        centos              "/bin/sh -c 'while t…"   18 seconds ago      Up 17 seconds                           kind_kilby
```

打开后台的命令行

```java
docker exec -it 28cf2fa3a0bb /bin/bash
```

```java
root@10.185.25.224[11:04:46]:~
$ docker exec -it 28cf2fa3a0bb /bin/bash
# 可以看到下面的root用户名变为了ID，可见打开了新的命令行
[root@28cf2fa3a0bb /]# ls
bin  etc   lib    lost+found  mnt  proc  run   srv  tmp  var
dev  home  lib64  media       opt  root  sbin  sys  usr
```

2.2 进入当前容器正在执行的终端，不会启动新的进程

我们进入后台正在执行的命令窗口

```java
docker attch 容器id
```

```java
# 我们之前添加的后台日志打印还在执行
root@10.185.25.224[11:07:06]:~
$ docker attach 28cf2fa3a0bb 
centos is running
centos is running
centos is running
centos is running
centos is running
...
```


**3. 从容器内拷贝文件到主机**

3.1 手动从容器中拷贝文件到主机

命令格式:

```java
docker cp 容器id:容器内路径 目的的主机路径
```

示例:

```java
docker cp 28cf2fa3a0bb:/home/test.java /home
```

接下来演示一波完整的操作:

```java
# 查看当前运行的容器
root@10.185.25.224[11:19:14]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
28cf2fa3a0bb        centos              "/bin/sh -c 'while t…"   14 minutes ago      Up 14 minutes                           kind_kilby
# 进入目标容器centos的容器命令行
root@10.185.25.224[11:20:21]:~
$ docker exec -it 28cf2fa3a0bb /bin/bash
# 进入容器centos /home 目录，并且创建test.java文件
[root@28cf2fa3a0bb /]# cd /home
[root@28cf2fa3a0bb home]# ls
[root@28cf2fa3a0bb home]# touch test.java
[root@28cf2fa3a0bb home]# ls
test.java
# 退出容器centos，回到我们主机的命令窗口
[root@28cf2fa3a0bb home]# exit
exit
# 查看我们当前运行容器
root@10.185.25.224[11:21:40]:~
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
28cf2fa3a0bb        centos              "/bin/sh -c 'while t…"   17 minutes ago      Up 17 minutes                           kind_kilby
# 进入到主机的 /home 目录，并且查看目录下没有其他文件
root@10.185.25.224[11:21:47]:~
$ cd /home
root@10.185.25.224[11:22:04]:/home
$ ll
total 0
# 将容器centos中 /home/test.java 文件复制到主机的 /home 目录下面
root@10.185.25.224[11:22:25]:/home
$ docker cp 28cf2fa3a0bb:/home/test.java /home
# 再次查看我们主机 /home 目录下的文件，可以看到文件已经成功复制过来了
root@10.185.25.224[11:22:36]:/home
$ ls
test.java
```

3.2 补充:上面的拷贝是一个手动的过程，我么也可以使用 -v 卷技术，实现自动的同步

- 比如同步容器中 /home 目录和主机目录 /home 中的文件

<hr>


<a id="_9"></a>

## `九、Docker Docker 运行Nginx`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. 拉取ngix镜像**


```java
docker ngix
```

```java
docker images
```

**2. 打开nginx**

我们指定启动容器的名称，同时指定主机端口和容器端口的映射

```java
docker run -d --name nginx01 -p 3344:80 nginx
```

这里我们可以看到启动成功了

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker run -d --name nginx01 -p 3344:80 nginx
d032c18eff37294910e41de5985df78f8a93e20eab2ce9025949a9e136c6201b
```

这个是对上面命令进行简单参数的讲解

```java
-d      表示后台运行
--name  表示给新建的容器进行命名
-p      指定 宿主机端口号:容器内部端口号
```

接着我么查看正在运行的容器

```java
docker ps   
```

这里可以看到nginx正常运行

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                  NAMES
d032c18eff37        nginx               "/docker-entrypoint.…"   8 seconds ago       Up 7 seconds        0.0.0.0:3344->80/tcp   nginx01
28cf2fa3a0bb        centos              "/bin/sh -c 'while t…"   3 hours ago         Up 3 hours                                 kind_kilby
```

然后通过宿主机对容器进行访问

```java
curl localhost:3344
```

返回了nginx的主页

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# curl localhost:3344
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
...
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>
...
</html>
```

当然我们也可以通过我们的windows对容器进行访问，但是你需要关闭你的防火墙

关闭你的防火墙

```java
 systemctl stop firewalld.service 
```



如果你是阿里云的话，那么还需要配置你的阿里云安全组

- [阿里云安全组配置教程](https://yq.aliyun.com/articles/745707)

最后来看一下效果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200611140825549.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
**2. 这里我们补充一下进入nginx命令行进行nginx配置文件查看的命令**

```java
docker exec -it nginx01 /bin/bash

whereis nginx
```




```java
# 进入nginx命令行
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it nginx01 /bin/bash
# 查看nginx的配置文件
root@d032c18eff37:/# whereis nginx
nginx: /usr/sbin/nginx /usr/lib/nginx /etc/nginx /usr/share/nginx
root@d032c18eff37:/# exit
exit
# 查看运行的容器
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                  NAMES
d032c18eff37        nginx               "/docker-entrypoint.…"   40 minutes ago      Up 40 minutes       0.0.0.0:3344->80/tcp   nginx01
28cf2fa3a0bb        centos              "/bin/sh -c 'while t…"   3 hours ago         Up 3 hours                                 kind_kilby
```


<a id="_10"></a>
## `十、Docker 运行Tomcat`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>


**1. 拉取tomcat:9.0的镜像**

```java
docker pull tomcat:9.0
```

**2. 运行tomcat:9.0的容器**

```java
docker run -d --name tomcat -p 8080:8080 tomcat:9.0 
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ tomcat]# docker run -d --name tomcat -p 8080:8080 tomcat:9.0    
482ef7e6ca1ab8e3c814b4429a7f25684d032d54d5fc3eeb20cda04a3a9373ff
[root@iZ2ze1ycujbqzy1mnrzufbZ tomcat]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                    NAMES
482ef7e6ca1a        tomcat:9.0          "catalina.sh run"   52 seconds ago      Up 51 seconds       0.0.0.0:8080->8080/tcp   tomcat
```

**3. 由于镜像中是简化版，所以想要让我们windows访问到的话还是需要其他的一些操作**

如果没有配置，我们是访问不了我们的tomcat的，因为容器中的tomcat的webapps目录下是没有文件的。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200611153426524.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
# 首先我们进入tomcat的命令行
[root@iZ2ze1ycujbqzy1mnrzufbZ tomcat]# docker exec -it 482ef7e6ca1a /bin/bash
# 进入到webapps目录中发现目录中是空的
root@482ef7e6ca1a:/usr/local/tomcat# ls
BUILDING.txt     LICENSE  README.md      RUNNING.txt  conf  logs            temp     webapps.dist
CONTRIBUTING.md  NOTICE   RELEASE-NOTES  bin          lib   native-jni-lib  webapps  work
root@482ef7e6ca1a:/usr/local/tomcat# cd webapps
# 发现是空的目录，所以我们访问tomcat的时候是 404
root@482ef7e6ca1a:/usr/local/tomcat# ls
```

这里我们开始修改

```java
# 接着我们退出webapps目录，然后删除这个目录
root@482ef7e6ca1a:/usr/local/tomcat# cd ../
root@482ef7e6ca1a:/usr/local/tomcat# rm -rf webapps
root@482ef7e6ca1a:/usr/local/tomcat# ls
BUILDING.txt     LICENSE  README.md      RUNNING.txt  conf  logs            temp          work
CONTRIBUTING.md  NOTICE   RELEASE-NOTES  bin          lib   native-jni-lib  webapps.dist
# 接着我们将webapps.dist目录进行复制，因为里面有我们需要的网页
root@482ef7e6ca1a:/usr/local/tomcat# cp -r webapps.dist/ webapps
root@482ef7e6ca1a:/usr/local/tomcat# ls
BUILDING.txt     LICENSE  README.md      RUNNING.txt  conf  logs            temp     webapps.dist
CONTRIBUTING.md  NOTICE   RELEASE-NOTES  bin          lib   native-jni-lib  webapps  work
# 接着我们再次进入webapps目录，可以发现里面有了我们需要的网页，于是这个时候我们可以进行访问
root@482ef7e6ca1a:/usr/local/tomcat# cd webapps
root@482ef7e6ca1a:/usr/local/tomcat/webapps# ls
ROOT  docs  examples  host-manager  manager
```


**4. 这个时候我们就可以进行访问了**


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200611153651215.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
<hr>
<a id="_11"></a>

## `十一、Docker 安装Elasticsearch`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. 首先还是进入到官方我们寻找我们需要下载的elasticsearch的版本**

```java
docker pull elasticsearch:7.7.1
```

**2. 接着我们可以启动elasticsearch**

说明:es 是非常耗费内存的，暴露的端口也是非常的多。我们es 的数据一般放到安全目录进行挂在。

2.1 直接或后台启动，并且配置端口号

```java
docker run -d --name elasticsearch01 -p 9000:9000 -p 9100:9100 \
-e "discovery.type=single-node" \
elasticsearch:7.7.1
```

查看我们docker容器的状态

```java
docker stats
```

如果没有配置elasticsearch的内存直接启动，可以看到这里内存占用率非常高

```java
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
a22dc2c201b5        elasticsearch01     0.43%               1.222GiB / 3.7GiB   33.01%              0B / 0B             0B / 0B             50
```

2.2 我们配置了JVM内存然后在启动，根据自己的内存进行配置

```java
docker run -d --name elasticsearch02 -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" elasticsearch:7.7.1
```

```java
docker stats
```

可以看到这里的内存占用就非常的少

```java
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
18589235188c        elasticsearch02     0.54%               389MiB / 3.7GiB     10.27%              0B / 0B             0B / 0B             49
```

接着我们可以进行内网测试

```java
curl localhost:9200
```

```java
{
  "name" : "18589235188c",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "zi31IZG8Th6QwiaU3jD-0A",
  "version" : {
    "number" : "7.7.1",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "ad56dce891c901a492bb1ee393f12dfff473a423",
    "build_date" : "2020-05-28T16:30:01.040088Z",
    "build_snapshot" : false,
    "lucene_version" : "8.5.1",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

外网测试

```java
http://ip:9200
```



![在这里插入图片描述](https://img-blog.csdnimg.cn/20200611220729502.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


<hr>
<a id="_100"></a>

## `补充:Docker 图形工具Portainer`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. 下载图形界面工具 Portainer** 

```java
docker run -d -p 8088:9000 \
--restart=always -v /var/run/docker.sock:/var/run/docker.sock \
--privileged=true portainer/portainer 
```

**2. 测试**


内网测试

```java
curl localhost:8088
```

外网测试

```java
http://ip:8088
```

内部界面可以管理你的 images、containers
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200611221633164.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

- [Portainer 简易教程补充](https://my.oschina.net/zhengqingya/blog/3066410)



<hr>
<a id="_101"></a>


## `补充:Docker 制作自己的镜像(images)`

- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

**1. 启动一个tomcat**

启动过程如下

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker ps -a
482ef7e6ca1a        tomcat:9.0            "catalina.sh run"        18 hours ago        Up 16 minutes                 0.0.0.0:8080->8080/tcp   tomcat
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker start 482ef7e6ca1a
482ef7e6ca1a
```

**2. 修改这个默认的tomcat(这里我们修改了其中的webapps，默认这个文件下面是没有文件的)**

这里我们直接打开tomcat进行修改，其中的过程就省略了。

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it 482ef7e6ca1a /bin/bash
...
root@482ef7e6ca1a:/usr/local/tomcat/webapps# pwd
/usr/local/tomcat/webapps
root@482ef7e6ca1a:/usr/local/tomcat/webapps# ls
ROOT  docs  examples  host-manager  manager
```

**3. 接着提交我们刚刚修改的tomcat作为本地的一个新的镜像**

首先我们可以看看基本的命令格式

```java
# 基本的命令格式
docker commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]
# 这边是帮助文档
Options:
  -a, --author string    Author (e.g., "John Hannibal Smith <hannibal@a-team.com>")
  -c, --change list      Apply Dockerfile instruction to the created image
  -m, --message string   Commit message
  -p, --pause            Pause container during commit (default true)
```

这里做一个示例

```java
docker commit -a="author" -m="add webapps app" 482ef7e6ca1a tomcatmyself:1.0
```

可以看到这里我们再次查看镜像的时候有了我们刚刚放入的新的image

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker commit -a="author" -m="add webapps app" 482ef7e6ca1a tomcatmyself:1.0           
sha256:f1a19a0d7ecbc742ec49d63518d570a6dd4a6f5cf9dacd72ad39d6283fa5b705
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
tomcatmyself          1.0                 f1a19a0d7ecb        7 seconds ago       652MB
```

**4. 接着我们进行自己提交的镜像测试**

命令都是一样的，我们下面进行一波演示

```java
# 启动我们的自己制作的镜像
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker run -d -p 8080:8080 tomcatmyself:1.0
6ed4d2c6a665af96ffce1899ee747d3fe74678b6cd7f1028d04d85de6fd3a413
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker ps
CONTAINER ID        IMAGE                 COMMAND             CREATED             STATUS              PORTS                    NAMES
6ed4d2c6a665        tomcatmyself:1.0      "catalina.sh run"   15 seconds ago      Up 14 seconds       0.0.0.0:8080->8080/tcp   stoic_stonebraker
26b01cf00919        portainer/portainer   "/portainer"        18 hours ago        Up 13 hours         0.0.0.0:8088->9000/tcp   eloquent_rosalind
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it 6ed4d2c6a665 /bin/bash
root@6ed4d2c6a665:/usr/local/tomcat# ls
BUILDING.txt  CONTRIBUTING.md  LICENSE  NOTICE  README.md  RELEASE-NOTES  RUNNING.txt  bin  conf  lib  logs  native-jni-lib  temp  webapps  webapps.dist  work
# 查看webapps是否存在目录
root@6ed4d2c6a665:/usr/local/tomcat# cd webapps
root@6ed4d2c6a665:/usr/local/tomcat/webapps# ls
ROOT  docs  examples  host-manager  manager
# 本地连接测试
root@6ed4d2c6a665:/usr/local/tomcat/webapps# curl localhost:8080
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Apache Tomcat/9.0.36</title>
        <link href="favicon.ico" rel="icon" type="image/x-icon" />
        <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
        <link href="tomcat.css" rel="stylesheet" type="text/css" />
    </head>
    ...
```




<hr>
<a id="_102"></a>

## `补充:Docker 容器数据卷(目录挂载技术)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

说明:如果我们的MySQL作为容器进行运行，那么很有可能这个容器会被不小心的删除。这样子的话就存在很严重的安全隐患，所以Docker中就存在着一种容器数据卷技术，使得容器和主机之间实现数据共享，使得我们可以将MySQL的数据可以存储在本地。这样子的话我们就可以将容器内的目录挂载到Linux上面。

总结:这个容器数据卷使得容器可以进行持久化和同步操作，容器间就可以实现数据共享了。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612101418987.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**1. 说明**

```java
# 基本格式
docker run -it -v 主机目录:容器目录 centos /bin/bash
# 绑定挂载卷 参数
-v, --volume list                    Bind mount a volume
```

**2. 示例**

我们进行文件的挂载

```java
docker run -it -v /home/centos:/home centos /bin/bash
```

我们可以查看我们容器的详细配置信息

```java
docker inspect 513af28f55c5  
```

下面进行一波演示

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ centos]# docker ps
CONTAINER ID        IMAGE                 COMMAND             CREATED             STATUS              PORTS                    NAMES
513af28f55c5        centos                "/bin/bash"         14 minutes ago      Up 14 minutes                                pedantic_volhard
[root@iZ2ze1ycujbqzy1mnrzufbZ centos]# docker inspect 513af28f55c5  
# 下面是其中关键的挂载信息
"Mounts": [
    {
        "Type": "bind",
        # 主机目录
        "Source": "/home/centos",
        # 容器目录 
        "Destination": "/home",
        "Mode": "",
        "RW": true,
        "Propagation": "rprivate"
    }
]
```

**3. 我们进行测试**

3.1 我们进行容器:主机同步

接着我们已经挂载好了我们的容器，我们先在容器中创建一个文件；然后到我们主机看一下是否有同步。

这个是容器目录

```java
[root@513af28f55c5 /]# pwd
/home
[root@513af28f55c5 home]# touch makeInContainer
[root@513af28f55c5 home]# ls
makeInContainer
```

这个是主机目录

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# pwd
/home/centos
# 这里我们看到了主机已经和容器已经同步了
[root@iZ2ze1ycujbqzy1mnrzufbZ centos]# ls
makeInContainer
```

3.1 我们进行主机:容器同步

主机创建文件

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ centos]# touch madeInMyLinux
[root@iZ2ze1ycujbqzy1mnrzufbZ centos]# ls
madeInMyLinux  makeInContainer
```

可以看到容器中也是已经同步了

```java
[root@513af28f55c5 home]# ls
madeInMyLinux  makeInContainer
```

**4. 思考**

我们以后就可以将一些关键的文件挂载到我们的主机，把一些容器中的配置文件进行挂载会大大提升我们的效率。





<hr>
<a id="_103"></a>

## `实战:Docker MySQL实战(数据卷的强大之处)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 镜像的拉取**

```java
docker pull myslq:5.7
```

**2. 运行镜像并且配置数据卷**

简单的命令格式

```java
 docker run \
 --name 容器命名 \
 -v 主机配置文件:容器映射配置文件 \
 -e MYSQL_ROOT_PASSWORD=密码 \
 -d \
 -p 主机端口:容器端口 \
 mysql:tag
```

示例

```java
 docker run \
 --name mymysql01 \
 -v /home/mysql/conf:/etc/mysql/conf.d \
 -v /home/mysql/data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d \
 -p 3306:3306 \
 mysql:5.7
```

然后查看效果

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ data]#  docker run \
>  --name mymysql01 \
>  -v /home/mysql/conf:/etc/mysql/conf.d \
>  -v /home/mysql/data:/var/lib/mysql \
>  -e MYSQL_ROOT_PASSWORD=root \
>  -d \
>  -p 3306:3306 \
>  mysql:5.7
4c46fefed66c5343860075a4a17e6b04253fcc24a32a89159240572f15c5a9b0
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# docker ps
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS              PORTS                               NAMES
4c46fefed66c        mysql:5.7             "docker-entrypoint.s…"   3 seconds ago       Up 2 seconds        0.0.0.0:3306->3306/tcp, 33060/tcp   mymysql01
6ed4d2c6a665        tomcatmyself:1.0      "catalina.sh run"        4 hours ago         Up 4 hours          0.0.0.0:8080->8080/tcp              stoic_stonebraker
26b01cf00919        portainer/portainer   "/portainer"             22 hours ago        Up 17 hours         0.0.0.0:8088->9000/tcp              eloquent_rosalind
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# docker ps
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS              PORTS                               NAMES
4c46fefed66c        mysql:5.7             "docker-entrypoint.s…"   13 seconds ago      Up 13 seconds       0.0.0.0:3306->3306/tcp, 33060/tcp   mymysql01
```

这里我们可以进入主机的与容器关联的数据卷，可以看到主机已经和容器的数据卷进行了关联。

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# pwd
/home/mysql/data
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# ls
auto.cnf    ca.pem           client-key.pem  ibdata1      ib_logfile1  mysql               private_key.pem  server-cert.pem  sys
ca-key.pem  client-cert.pem  ib_buffer_pool  ib_logfile0  ibtmp1       performance_schema  public_key.pem   server-key.pem
```

最后官网有更加详细的关于mysql的解释，可以自行查看

- [MySQL官方教程](https://hub.docker.com/_/mysql)

**3. 进行连接**

打开sqlyon进行连接

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612135815203.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

然后我们创建一个数据库

```java
CREATE DATABASE test;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612140601255.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

到主机的挂载目录进行查看，可以看到多了一个test数据库

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# ls
auto.cnf    ca.pem           client-key.pem  ibdata1      ib_logfile1  mysql               private_key.pem  server-cert.pem  sys
ca-key.pem  client-cert.pem  ib_buffer_pool  ib_logfile0  ibtmp1       performance_schema  public_key.pem   server-key.pem  
test
```






<hr>
<a id="_104"></a>

## `实战:Docker 具名和匿名挂载(Nginx作为示例)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 匿名挂载(不推荐使用)**

匿名挂载仅仅写了容器中的路径，但是没有明确指定主机的路径。

参数解释

```java
-v 容器内路径
```

示例

```java
docker run -d -P --name nginx01 -v /etc/nginx nginx
```

**2. 具名挂载(推荐使用)**

```java
docker run -d -P --name nginx01 -v ngnix:/etc/nginx nginx
```

**3. 我们查看所有 volume(卷) 的情况**

```java
docker volume ls
```

示例

```java
docker volume inspect ngnix 
```

可以看到我们多出来了一个分卷

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ _data]# docker volume ls
DRIVER              VOLUME NAME
...
local               e13d69859f2c8c5fb2efa5e5efef402fb1fbb5bf9a3e6a8d2a09ef73cb4d5009
local               ec3214d3261aee1842737e89b2c6e26fc4e2eb9c5b884677200e5927ecfe7620
local               f726a8e68358c026c239f523e8f2ae11fb65a25e8f85bb370fe61d6b895ca943
# 这个就是我们刚刚通通过 具名 创建的分卷
local               ngnix01
```

最后我们可以查看这个分卷里面有什么内容

我们所有的具名分卷信息都会被放到以下的路径中。

```java
/var/lib/docker/volumes
```

可以看看我们分卷信息

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ volumes]# ls
89847daf0586906d9f2916aea7ee27f778d55b76c6ffe0ad43d9dc0c30445166  ec3214d3261aee1842737e89b2c6e26fc4e2eb9c5b884677200e5927ecfe7620
95ce41af1f9cfd947a3e22088d30606674aafb0179cb689cad876f930062eaee  f726a8e68358c026c239f523e8f2ae11fb65a25e8f85bb370fe61d6b895ca943
a8b5172810b149629f1fcb702598e7579ca56dca8c26b2d30964d0d6469209e1  metadata.db
ngnix01
```

接着我们进入到我们的分卷

```java
这个是分卷中的内容
conf.d  fastcgi_params  koi-utf  koi-win  mime.types  modules  nginx.conf  scgi_params  uwsgi_params  win-utf
```

一下是具体操作过程

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ volumes]# cd ngnix01/
[root@iZ2ze1ycujbqzy1mnrzufbZ ngnix01]# ls
_data
[root@iZ2ze1ycujbqzy1mnrzufbZ ngnix01]# cd _data/
# 进入到具体目录可以看到下面主要的配置内容和数据文件
[root@iZ2ze1ycujbqzy1mnrzufbZ _data]# ls
conf.d  fastcgi_params  koi-utf  koi-win  mime.types  modules  nginx.conf  scgi_params  uwsgi_params  win-utf
[root@iZ2ze1ycujbqzy1mnrzufbZ _data]# cat nginx.conf 
user  nginx;
worker_processes  1;
error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;
events {
    worker_connections  1024;
}
...
```

**4. 区分具名、匿名、指定路径3中挂载方式**

直接看具体格式

```java
-v 容器内路径            # 匿名拓展
-v 卷名:容器内路径        # 具名拓展
-v /宿主机路径:容器路径    # 指定路径拓展
```

**5. 拓展，如何限制容器内数据卷文件的读写权限**

```java
ro  readonly   # 只读
rw  readwrite  # 只写
```

示例

```java
docker run -d -P --name nginx02 -v ngnix:/etc/nginx:ro nginx
docker run -d -P --name nginx02 -v ngnix:/etc/nginx:re nginx
```


<hr>
<a id="_105"></a>

## `实战:Docker Dockerfile构建镜像(可以配置我们的数据卷)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 编写脚本**

```java
# 这里我们选取一个基础镜像，就是我们的centos(本地需要已经有这个镜像文件了)
FROM centos

# 匿名数据卷的指定
VOLUME ["volume01","volume02"]

# 这里我们会进行打印
CMD echo "-----end------"
# 指定新镜像中默认的进入命令行参数
CMD /bin/bash
```

**2. 执行构建命令**

基本命令格式

```java
docker build [OPTIONS] PATH | URL | -
    # 配置参数
    -f, --file string             Name of the Dockerfile (Default is 'PATH/Dockerfile')
    -t, --tag list                Name and optionally a tag in the 'name:tag' format
```

```java
docker build -f /home/dockerfile  -t mycentos:1.0 .
```

```java
# 这里我们选择了 /home 目录
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# pwd
/home
# 编写我们的脚本
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# cat dockerfile 
FROM centos

VOLUME ["volume01","volume02"]

CMD echo "-----end------"
CMD /bin/bash
# 进行构建
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker build -f /home/dockerfile  -t mycentos:1.0 .
Sending build context to Docker daemon  2.048kB
Step 1/4 : FROM centos
 ---> 470671670cac
Step 2/4 : VOLUME ["volume01","volume02"]
 ---> Running in 39f9bb1881d3
Removing intermediate container 39f9bb1881d3
 ---> 3445937203bf
Step 3/4 : CMD echo "-----end------"
 ---> Running in 939d4f912c0d
Removing intermediate container 939d4f912c0d
 ---> 3a9420c3df19
Step 4/4 : CMD /bin/bash
 ---> Running in 92db989a2cf2
Removing intermediate container 92db989a2cf2
 ---> 6ca2017f1075
Successfully built 6ca2017f1075
Successfully tagged mycentos:1.0
```

**3. 测试最终的效果**

这里我们可以看到我们刚刚构建的镜像

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
mycentos              1.0                 6ca2017f1075        3 minutes ago       237M
```

然后我们运行我们的镜像，可以看到我们刚刚设置的数据卷 volume01 volume02

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ /]# docker run -it mycentos:1.0 /bin/bash
[root@a14ef03845bf /]# ls
bin  etc   lib    lost+found  mnt  proc  run   srv  tmp  var       volume02
dev  home  lib64  media       opt  root  sbin  sys  usr  volume01
```

接着我们进入我们主机查看我们容器映射的镜像

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker ps
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS              PORTS                               NAMES
a14ef03845bf        mycentos:1.0          "/bin/bash"              4 minutes ago       Up 4 minutes                                            jolly_euler
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker inspect a14ef03845bf
# 这里我们提取了关键的数据卷信息
"Mounts": [
{
    "Type": "volume",
    "Name": "a2657de74fbe0f882cae935a7393ea6862a0ae4ba427b7e5c5e6b03fb31ea727",
    # 这里是我们主机的目录
    "Source": "/var/lib/docker/volumes/a2657de74fbe0f882cae935a7393ea6862a0ae4ba427b7e5c5e6b03fb31ea727/_data",
    # 这里是我们容器中的目录
    "Destination": "volume01",
    "Driver": "local",
    "Mode": "",
    "RW": true,
    "Propagation": ""
},
{
    "Type": "volume",
    "Name": "47f556747d1650c4be3eb5adb8f3775e0f2d445e80201a414e2bf34bd46e7a24",
    "Source": "/var/lib/docker/volumes/47f556747d1650c4be3eb5adb8f3775e0f2d445e80201a414e2bf34bd46e7a24/_data",
    "Destination": "volume02",
    "Driver": "local",
    "Mode": "",
    "RW": true,
    "Propagation": ""
}
```

这里我们在容器中创建文件

```java
[root@a14ef03845bf volume01]# pwd
/volume01
[root@a14ef03845bf volume01]# ls   
[root@a14ef03845bf volume01]# touch mycentos
[root@a14ef03845bf volume01]# ls
mycentos
```

接着查看我们主机的目录，可以看到文件也是刷新了

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# cd /var/lib/docker/volumes/a2657de74fbe0f882cae935a7393ea6862a0ae4ba427b7e5c5e6b03fb31ea727/
[root@iZ2ze1ycujbqzy1mnrzufbZ a2657de74fbe0f882cae935a7393ea6862a0ae4ba427b7e5c5e6b03fb31ea727]# ls
_data
[root@iZ2ze1ycujbqzy1mnrzufbZ a2657de74fbe0f882cae935a7393ea6862a0ae4ba427b7e5c5e6b03fb31ea727]# cd _data/
[root@iZ2ze1ycujbqzy1mnrzufbZ _data]# ls
mycentos
```


<hr>
<a id="_106"></a>

## `实战:Docker 容器数据卷共享(最后附加MySQL的数据共享示例)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061310222251.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**1. 首先我们先启动3个容器并且进行关联**

1.1 我们使用之前用Dockerfile制作的镜像进行测

编写脚本

```java
FROM centos

VOLUME ["volume01","volume02"]

CMD echo "-----end------"
CMD /bin/bash
```

运行脚本

```java
docker build -f /home/dockerfile  -t mycentos:1.0 .
```

查看是否创建成功

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
mycentos              1.0                 6ca2017f1075        18 hours ago        237MB
```

1.2 进行容器的启动

启动一个容器

```java
docker run -it --name docker01 mycentos:1.0
```

启动第二个容器关联第一个容器

```java
docker run -it --name docker02 --volumes-from docker01 mycentos:1.0
```

启动第二个容器关联第一个容器

```java
docker run -it --name docker03 --volumes-from docker01 mycentos:1.0
```

**2. 进行测试**

2.1 我们在容器1和容器2中都进行文件创建观察是否同步

首先容器1创建文件，看容器2和3是否同步

容器1

```java
[root@31b5eb63485f volume01]# touch docker1
[root@31b5eb63485f volume01]# ls
docker1
```

容器2

```java
[root@daf0c50c9f13 volume01]# ls
docker1
```

容器3

```java
[root@09f7085f7be2 volume01]# ls
docker1
```

可以看到在这几个容器中的文件都实现了同步

接着我们删除容器1，查看容器2和容器3是否数据有小时

容器1

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker ps 
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS                        PORTS                    NAMES
09f7085f7be2        mycentos:1.0          "/bin/sh -c /bin/bash"   17 minutes ago      Up 17 minutes                                          docker03
daf0c50c9f13        mycentos:1.0          "/bin/sh -c /bin/bash"   17 minutes ago      Up 17 minutes                                          docker02
31b5eb63485f        mycentos:1.0          "/bin/sh -c /bin/bash"   17 minutes ago      Exited (130) 45 seconds ago                            docker01
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker rm 31b5eb63485f
31b5eb63485f
```

容器2

```java
[root@daf0c50c9f13 volume01]# ls
docker1
```

容器3

```java
[root@09f7085f7be2 volume01]# ls
docker1
```

可以看到，即使我们将容器1删除，但是其他两个容器的数据依旧存在

**3. 容器数据卷作用总结**

从上面的测试可以看出来，我们可以容器数据卷可以有效的进行数据的同步并且保护我们的数据，即使一个容器被删除了，但是其他容器中仍然有数据的备份。总而言之，容器之间的数据卷就是一种拷贝机制，实现数据的可以有多个备份。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613102505990.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**4. 这里来演示一波MySQL容器数据卷**

启动一个mysql

```java
 docker run \
 --name mymysql01 \
 -v /etc/mysql/conf.d \
 -v /var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d \
 -p 3306:3306 \
 mysql:5.7
```

启动另外一个mysql绑定其数据卷

```java
 docker run \
 --name mymysql02 \
 --volumes-from mymysql01 \
 -e MYSQL_ROOT_PASSWORD=root \
 -d \
 -p 3307:3306 \
 mysql:5.7
```

接着来简单的看一下效果

```java
# 进入到mymysql01容器中的/home目录下创建文件
root@0ab3d49fa7f2:/var/lib/mysql# cd /home
root@0ab3d49fa7f2:/home# touch mymysql01.test
root@0ab3d49fa7f2:/home# ls
mymysql01.test

# 进入到mymysql02容器/home目录下查看文件是否同步
root@40a8ec909fa4:/var/lib/mysql# cd /home
root@40a8ec909fa4:/home# ls
mymysql01.file
```




<hr>
<a id="_107"></a>

## `实战:Docker Dockerfile进阶`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 我们来看看Dockerfile构建的基本过程**

之前我们通过dockerfile简单构建过一个centos，过程也就是编写脚本文件，然后执行构建命令。

基本过程如下：



```java
# 这里我们选取一个基础镜像，就是我们的centos(本地需要已经有这个镜像文件了)
FROM centos

# 匿名数据卷的指定
VOLUME ["volume01","volume02"]

# 这里我们会进行打印
CMD echo "-----end------"
# 指定新镜像中默认的进入命令行参数
CMD /bin/bash
```

```java
docker build -f /home/dockerfile  -t mycentos:1.0 .
```


**2. 接着我们可以看看官网是如何编写这个Dockerfile文件的**


- [进入DockerHub搜索](https://hub.docker.com/_/centos?tab=description) 

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613113727170.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

- [构建文件网址(centos)](https://github.com/CentOS/sig-cloud-instance-images/blob/b521221b5c8ac3ac88698e77941a2414ce6e778d/docker/Dockerfile) 


下面这个文件中是最基本的构建命令，基本上就是一个纯净版的centos，没有安装其他常用的软件插件。只是简单的添加了一些标签信息。如果我们自己需要构建一个镜像文件的话就需要额外添加更加多的信息。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613113508987.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**3. 我们来看看Dockerfile的构建过程** 

Dockerfile的命令时从上到下执行的，每一个指令都会提交一个新的镜像层，并且提交。我们可以通过一幅图看到其中的效果。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613114641125.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**4. DockerFile指令**



```java
FROM        # 基础镜像，一切都是从这里开始构建
MAINTAINER  # 镜像是谁写的，姓名+邮箱
RUN         # 镜像构建的时候需要运行的命令
ADD         # 步骤：tomcat，这个tomcat压缩包！添加内容
WORKDIR     # 镜像的工作目录
VOLUME      # 挂载的目录
EXPOSE      # 暴露端口：比如80,8080，这样子启动镜像就可以直接暴露这个端口而不需要额外代入参数 -p 
CMD         # 指定这个容器启动的时候需要运行的命令，只有最后一个会生效，可被替代
ENTRYPOINT  # 指定这个容器启动的时候需要运行的命令，可以追加命令
ONBUILD     # 当构建一个被继承 mycentos 这个时候就会运行 ONBUILD 的指令。
COPY        # 类似于ADD，将我们的文件拷贝到镜像中
ENV         # 构建的时候设置环境变量。类似于我们配置es的时候配置的JVM内存大小。
```

**5. Dockfile指令实战测试**

5.1 创建一个centos的mycentos

创建文件

```java
touch mycentos
```

编辑文件

```java
# 我们的基础镜像
FROM centos
# 我们的作者
MAINTAINER root<73@qq.com>
# 环境变量
ENV MYPATH /usr/local
# 镜像的工作目录
WORKDIR $MYPATH
# 镜像构建的时候需要运行的命令
RUN yum -y install vim
RUN yum -y install net-tools
# 设置暴露的端口
EXPOSE 80
# 指定这个容器启动的时候需要运行的命令
CMD echo $MYPATH
CMD echo "----end----"
CMD ["/bin/bash"]
```

构建镜像

```java
docker build -f mycentos -t mycentos:0.1 .
```

看到构建成功

```java
Successfully built 0bc78e3e6895
Successfully tagged mycentos:0.1
```

5.2 测试镜像效果


```java
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker images
REPOSITORY            TAG                 IMAGE ID            CREATED              SIZE
mycentos              0.1                 0bc78e3e6895        About a minute ago   321MB
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker run -it mycentos:0.1
# 可以看到我们直接进入了 /usr/local
# 同时我们可以直接使用 yum 插件
[root@4d898d6bd6a1 local]# yum install git
Failed to set locale, defaulting to C.UTF-8
Last metadata expiration check: 0:02:53 ago on Sat Jun 13 04:23:16 2020.
Dependencies resolved.
```

**6. 我们可以尝试查看其他镜像的Dockerfile的信息**

```java
docker history 镜像id|镜像name
```

示例

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ home]# docker history tomcat
IMAGE               CREATED             CREATED BY                                      SIZE                COMMENT
2eb5a120304e        2 days ago          /bin/sh -c #(nop)  CMD ["catalina.sh" "run"]    0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  EXPOSE 8080                  0B                  
<missing>           2 days ago          /bin/sh -c set -e  && nativeLines="$(catalin…   0B                  
<missing>           2 days ago          /bin/sh -c set -eux;   savedAptMark="$(apt-m…   19.9MB              
<missing>           2 days ago          /bin/sh -c #(nop)  ENV TOMCAT_SHA512=75e16a0…   0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV TOMCAT_VERSION=9.0.36    0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV TOMCAT_MAJOR=9           0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV GPG_KEYS=05AB33110949…   0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV LD_LIBRARY_PATH=/usr/…   0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV TOMCAT_NATIVE_LIBDIR=…   0B                  
<missing>           2 days ago          /bin/sh -c #(nop) WORKDIR /usr/local/tomcat     0B                  
<missing>           2 days ago          /bin/sh -c mkdir -p "$CATALINA_HOME"            0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV PATH=/usr/local/tomca…   0B                  
<missing>           2 days ago          /bin/sh -c #(nop)  ENV CATALINA_HOME=/usr/lo…   0B                  
<missing>           3 days ago          /bin/sh -c #(nop)  CMD ["jshell"]               0B                  
<missing>           3 days ago          /bin/sh -c set -eux;   dpkgArch="$(dpkg --pr…   323MB               
<missing>           3 days ago          /bin/sh -c #(nop)  ENV JAVA_URL_VERSION=11.0…   0B                  
<missing>           3 days ago          /bin/sh -c #(nop)  ENV JAVA_BASE_URL=https:/…   0B                  
<missing>           3 days ago          /bin/sh -c #(nop)  ENV JAVA_VERSION=11.0.7      0B                  
<missing>           3 days ago          /bin/sh -c { echo '#/bin/sh'; echo 'echo "$J…   27B                 
<missing>           3 days ago          /bin/sh -c #(nop)  ENV PATH=/usr/local/openj…   0B                  
<missing>           3 days ago          /bin/sh -c #(nop)  ENV JAVA_HOME=/usr/local/…   0B                  
<missing>           3 days ago          /bin/sh -c #(nop)  ENV LANG=C.UTF-8             0B                  
<missing>           3 days ago          /bin/sh -c set -eux;  apt-get update;  apt-g…   11.1MB              
<missing>           4 days ago          /bin/sh -c apt-get update && apt-get install…   146MB               
<missing>           4 days ago          /bin/sh -c set -ex;  if ! command -v gpg > /…   17.5MB              
<missing>           4 days ago          /bin/sh -c apt-get update && apt-get install…   16.5MB              
<missing>           4 days ago          /bin/sh -c #(nop)  CMD ["bash"]                 0B                  
<missing>           4 days ago          /bin/sh -c #(nop) ADD file:1ab357efe422cfed5…   114MB 
```






<hr>
<a id="_108"></a>

## `实战:Docker Tomcat的Dockerfile创建`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>


**1. 编写Dockerfile脚本**


1.1 在编写Dockerfile脚本之前，我们需要先准备以下的资源

也就是两个 .gz 包，一个readme.txt文件

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ tomcat]# ls
apache-tomcat-9.0.36         jdk-8u151-linux-x64.tar.gz
apache-tomcat-9.0.36.tar.gz  mytomcat
```

1.2 接着我们编写脚本就可以了

这里的ADD命令我没有使用绝对路径，所以你需要先进入到我们的tomcat目录下再运行脚本。

```java
# 我们的基础镜像
FROM centos
# 我们的作者
MAINTAINER root<73@qq.com>
# 拷贝我们的文件到镜像中
COPY readme.txt /usr/local/readme.txt
# 添加我们的文件并且会自动解压到容器中的目标目录
ADD apache-tomcat-9.0.36.tar.gz /usr/local
ADD jdk-8u151-linux-x64.tar.gz /usr/local
# 环境变量
ENV MYPATH /usr/local
# 镜像的工作目录
WORKDIR $MYPATH
# 配置我们的环境
ENV JAVA_HOME /usr/local/jdk1.8.0_151
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.36
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.36
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
# 镜像构建的时候需要运行的命令,这里安装vim和网络插件
RUN yum -y install vim
RUN yum -y install net-tools
# 设置暴露的端口
EXPOSE 8080
# 指定这个容器启动的时候需要运行的命令
CMD echo $MYPATH
CMD /usr/local/apache-tomcat-9.0.36/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.36/bin/logs/catalina.out

```

**2. 运行脚本**

```java
docker build -f mytomcat -t mytomcat8080 .
```

效果如下

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ tomcat]# docker build -f mytomcat .
Sending build context to Docker daemon  217.1MB
Step 1/18 : FROM centos
 ---> 470671670cac
Step 2/18 : MAINTAINER root<73@qq.com>
 ---> Using cache
 ---> aae43b8767de
Step 3/18 : COPY readme.txt /usr/local/readme.txt
 ---> e905b2cf947f
Step 4/18 : ADD apache-tomcat-9.0.36.tar.gz /usr/local
 ---> e8ce0bf80922
Step 5/18 : ADD jdk-8u151-linux-x64.tar.gz /usr/local
 ---> b26dcb4d219f
Step 6/18 : ENV MYPATH /usr/local
 ---> Running in 113946c74133

```

**3. 启动我们刚刚做的tomcat**

```java
docker run \
-d \
-p 8080:8080 \
--name mytomcat8080 \
-v /home/tomcatDocker/build/tomcat8080:/usr/loal/apache-tomcat-9.0.36 \
mytomcat8080
```

**4. 进行测试运行**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613161928263.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


<hr>
<a id="_109"></a>

## `实战:Docker 发布自己的镜像(DokerHub && 阿里云)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 在DockerHub上发布自己的镜像**

1.1 在DockerHub上注册自己的账号

- [DockerHub地址](https://hub.docker.com/) 

注意注册完毕之后验证一下自己是否可以登录。

1.2 我们尝试提交我们服务器上的一个镜像

1.2.1 我们先来登录自己的docker账号

命令格式

```java
Usage:  docker login [OPTIONS] [SERVER]

Options:
  -p, --password string   Password
      --password-stdin    Take the password from stdin
  -u, --username string   Username
```

简单示例

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker login -uUserName
Password: 
```

1.2.2 接着我们尝试提交一波自己的镜像

命令格式

```java
Usage:  docker push [OPTIONS] NAME[:TAG]

Options:
      --disable-content-trust   Skip image signing (default true)
```

简单示例

```java
docker push mytomcat8080
```

我们这一次的提交被拒绝了，于是我们为其添加版本号，注意前面需要添加上自己的docker用户名，否则是会被拒绝的

- [错误解决参考博客](https://blog.csdn.net/baidu_19473529/article/details/70156144)

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker push mytomcat8080
The push refers to repository [docker.io/library/mytomcat8080]
6bfd41890e3b: Preparing 
f5d26df1cb59: Preparing 
841422a0b8e2: Preparing 
698b4f7b83a3: Preparing 
0ef92c7d2dca: Preparing 
0683de282177: Waiting 
denied: requested access to the resource is denied
```

改变镜像的tag标签再次尝试提交

```java
docker tag 1316c01e5d9d yezhiyue/mytomcat:1.0
```

```java
docker push yezhiyue/mytomcat:1.0
```

可以看到我们已经在上传镜像了，可能速度会比较慢

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker push yezhiyue/mytomcat:1.0
The push refers to repository [docker.io/yezhiyue/mytomcat]
6bfd41890e3b: Pushing  3.907MB/24.06MB
f5d26df1cb59: Pushing  1.663MB/59.78MB
841422a0b8e2: Pushing  3.292MB/384.4MB
```

**2. 在阿里云上发布自己的镜像**

2.1 登录你的阿里云，然后找到你的容器镜像服务

- [阿里云镜像地址](https://cr.console.aliyun.com/cn-beijing/instances/namespaces) 

2.2 创建你的命名空间，代表你的一个项目

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613182423629.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613182545336.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613183019700.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

2.3 创建你的容器镜像

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613182754373.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613182928159.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613183039656.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613183541168.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


2.4 查看阿里云给的教程

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613183438361.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

2.5 我们简单的通过阿里云给的教程进行一次提交

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker login --username=******* registry.cn-*******.aliyuncs.com
Password: 
WARNING! Your password will be stored unencrypted in /root/.docker/config.json.
Configure a credential helper to remove this warning. See
https://docs.docker.com/engine/reference/commandline/login/#credentials-store

Login Succeeded
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker tag 1316c01e5d9d registry.cn-beijing.aliyuncs.com/yezhiyueresponsity01/myresponsity01:1.0
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker push registry.cn-beijing.aliyuncs.com/yezhiyueresponsity01/myresponsity01:1.0
The push refers to repository [registry.cn-beijing.aliyuncs.com/yezhiyueresponsity01/myresponsity01]
6bfd41890e3b: Pushing  1.292MB/24.06MB
f5d26df1cb59: Pushing  1.663MB/59.78MB
841422a0b8e2: Pushing  1.621MB/384.4MB
698b4f7b83a3: Pushing  1.366MB/15.6MB
```

<hr>
<a id="_111"></a>

## `高阶:Docker Docker --link 配置直接通过容器名称进行访问`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>


**1. 容器之间直接进行ping连接**

是无法直接获取数据的

```java
docker exec -it tomcat02 ping tomcat01
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it tomcat02 ping tomcat01
ping: tomcat01: Name or service not known
```

**2. 指定--link进行连接**

配置连接

```java
docker run -d -P --name tomcat03 --link tomcat02 tomcat
```

现在我们可以直接通过名称进行数据传输

```java
docker exec -it tomcat03 ping tomcat02
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker run -d -P --name tomcat03 --link tomcat02 tomcat
774766747fbcc06e0983473f14d905075dbf15fe244855fc6e9fe48d07c6cc6c
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it tomcat03 ping tomcat02
PING tomcat02 (172.18.0.2) 56(84) bytes of data.
64 bytes from tomcat02 (172.18.0.2): icmp_seq=1 ttl=64 time=0.104 ms
64 bytes from tomcat02 (172.18.0.2): icmp_seq=2 ttl=64 time=0.088 ms
```

但是我们无法反向进行连接，最后是连接失败的

```java
docker exec -it tomcat02 ping tomcat03
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it tomcat02 ping tomcat03
ping: tomcat03: Name or service not known
```

**3. 进行docker桥接网络信息的查看**

3.1 首先查看我们的网络信息

```java
docker network ls
```

```java
NETWORK ID          NAME                DRIVER              SCOPE
b259290f477e        bridge              bridge              local
48e7078f2a8f        host                host                local
368354496e72        none                null                local
```

下面这个桥接网络是我们docker分析的关键。
b259290f477e        bridge              bridge              local


3.2 接着我们查看网络的详细信息

接下来我们会提取出其中的关键部分

```java
docker network inspect b259290f477e
```

下面是容器ip的详细信息

```java
"Containers": {
    "774766747fbcc06e0983473f14d905075dbf15fe244855fc6e9fe48d07c6cc6c": {
        "Name": "tomcat03",
        "EndpointID": "794256f132edaf168c288ab62dc8901ca72f5067ba9989be65b80051f41e645c",
        "MacAddress": "02:42:ac:12:00:04",
        "IPv4Address": "172.18.0.4/16",
        "IPv6Address": ""
    },
    "c377bc23fd497612104d471ffa9eb7e53e2293696ee37a5331f3d9334491ae7e": {
        "Name": "tomcat02",
        "EndpointID": "e7cf220aa4d557445aaba89b3eca0dbb1cf72c7fafb46a70fe9d0be2bf447301",
        "MacAddress": "02:42:ac:12:00:02",
        "IPv4Address": "172.18.0.2/16",
        "IPv6Address": ""
    },
    "d25b287e98a04e4c1e3f3069f7f394c08a28daa24c2e2730bd8244942b5366fd": {
        "Name": "tomcat01",
        "EndpointID": "515ae218b10fcde3583ec2e5095f6fe1a2e18f1fd2e205977cbc20f1c2aabf3c",
        "MacAddress": "02:42:ac:12:00:03",
        "IPv4Address": "172.18.0.3/16",
        "IPv6Address": ""
    }
}
```

**4. 进行目标容器的信息查看**

4.1 这里我们对容器tomcat03的信息进行查看

```java
docker inspect tomcat03
```

里面详细的网络设置，但是这些都不是关键，有兴趣可以自己看看

```java
"NetworkSettings": {
    "Bridge": "",
    "SandboxID": "7d133e35a8dbcfc525b8c1b47de56319351911f269e3c847b945f0fc83f9b343",
    "HairpinMode": false,
    "LinkLocalIPv6Address": "",
    "LinkLocalIPv6PrefixLen": 0,
    "Ports": {
        "8080/tcp": [
            {
                "HostIp": "0.0.0.0",
                "HostPort": "32772"
            }
        ]
    }
```

```java
"Networks": {
    "bridge": {
        "IPAMConfig": null,
        "Links": null,
        "Aliases": null,
        "NetworkID": "b259290f477ebdc0eb8ac342d1b47a10c7d9bb937620b7c36b2e9f9cfa9b8385",
        "EndpointID": "794256f132edaf168c288ab62dc8901ca72f5067ba9989be65b80051f41e645c",
        "Gateway": "172.18.0.1",
        "IPAddress": "172.18.0.4",
        "IPPrefixLen": 16,
        "IPv6Gateway": "",
        "GlobalIPv6Address": "",
        "GlobalIPv6PrefixLen": 0,
        "MacAddress": "02:42:ac:12:00:04",
        "DriverOpts": null
    }
}
```

4.2 接下来可以看我们的tomcat03 执行--link后添加的设置

我们直接进入容器中的hosts文件查看域名配置

```java
docker exec -it tomcat03 cat /etc/hosts
```

下面的是tomcat02就是这样的配合

```java
172.18.0.2      tomcat02 c377bc23fd49
```

下面是全部信息

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it tomcat03 cat /etc/hosts
127.0.0.1       localhost
::1     localhost ip6-localhost ip6-loopback
fe00::0 ip6-localnet
ff00::0 ip6-mcastprefix
ff02::1 ip6-allnodes
ff02::2 ip6-allrouters
172.18.0.2      tomcat02 c377bc23fd49
172.18.0.4      774766747fbc
```

**5.  小结**

我们通过--link配置的网络实质上就是在tomcat03的容器中的hosts文件中添加了一条静态的配置。
我们是不推荐这样的配置的，我们更加推荐的是使用自定义网络




<hr>
<a id="_112"></a>

## `高阶:Docker Docker自定义网络`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. Docker的网络模式**

bridge ： 桥接模式（默认）
none ： 不配置网络
host ： 和宿主机共享网络
container ： 容器网络连通(用的少！局限大)

**2. 命令**

2.1 默认使用docker0网络，并且网络模式就是桥接模式

```java
docker run -d -P --name tomcat01 tomcat
```

```java
docker run -d -P --name tomcat01 --net bridge tomcat
```

2.2 自定义一个网络

命令参数

```java
Usage:  docker network create [OPTIONS] NETWORK

下面是我们通常必须配置的参数
    1.网络模式
    2.网关
    3.子网掩码

Options:
  --driver string        Driver to manage the Network (default "bridge")
  --gateway strings      IPv4 or IPv6 Gateway for the master subnet
  --subnet strings       Subnet in CIDR format that represents a network segment
    ...
```

示例

```java
docker network create \
--driver bridge \
--subnet 192.168.0.0/16 \
--gateway 192.168.0.1 \
mynet
```

查看我们的网络信息

```java
docker network ls
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker network create \
> --driver bridge \
> --subnet 192.168.0.0/16 \
> --gateway 192.168.0.1 \
> mynet
48f9fe343fd1d1c49ae40f7d9fdb069687ed00be34c8cbca2da7ee21cf867ef5
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker network ls
NETWORK ID          NAME                DRIVER              SCOPE
b259290f477e        bridge              bridge              local
48e7078f2a8f        host                host                local
# 这个是我们新添加的自定义网络
48f9fe343fd1        mynet               bridge              local
368354496e72        none                null                local
```

**3. 我们进行测试**

3.1 启动我们的容器

```java
docker run -d -P --name tomcat-net-01 --net mynet tomcat
docker run -d -P --name tomcat-net-02 --net mynet tomcat
```

3.2 查看网络信息

```java
docker network inspect mynet
```

可以看到在这个网络里面多了两个我们刚刚启动的容器信息

```java
"Containers": {
    "2eaa3d1c0846893f55b05324f42e41a0c1e40c4fcf5822b23923c3342bc19ae5": {
        "Name": "tomcat-net-01",
        "EndpointID": "45e5f31ad6d2246795bfe17e29bdfec668c340890479ae114abc849f4f0be227",
        "MacAddress": "02:42:c0:a8:00:02",
        "IPv4Address": "192.168.0.2/16",
        "IPv6Address": ""
    },
    "dedc8ffc6ae2c91f684dd80efa5b315e093caf0fca267ff490e72dae4a01680d": {
        "Name": "tomcat-net-02",
        "EndpointID": "b94a7f94d1c9762012582a8159ed404bd1ade39627be1b20d393a71e7374c62d",
        "MacAddress": "02:42:c0:a8:00:03",
        "IPv4Address": "192.168.0.3/16",
        "IPv6Address": ""
    }
}
```

3.3 进行容器间连接测试

通过ip进行测试

```java
docker exec -it tomcat-net-01 ping 192.168.0.3
```

直接通过容器名称进行测试

```java
docker exec -it tomcat-net-01 ping tomcat-net-01
```

可以看到，两种方式都是可以成功进行连接的

**4. 小结**

我们自定义的网络docker都已经帮我们维护好了对应的关系，推荐我们平时使用这样使用网络。

好处：
redis-不同的集群使用不同的网络，保证集群是安全和健康的
mysql-不同的集群使用不同的网络，保证集群是安全和健康的





<hr>
<a id="_113"></a>

## `高阶:Docker Docker打通网络`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

如果两个容器位于两个不同的网络上，那么我们是不可以直接打通的，我们需要进行额外的配置才可以让不同网络间的容器进行打通通信。

**1. 未配置前进行打通测试**

```java
docker exec -it tomcat01 ping tomcat-net-01
```

由于这两个容器所在的网络不同，一个在docker0，一个在我们自定义的mynet，所以是无法ping通的

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it tomcat01 ping tomcat-net-01
ping: tomcat02: Name or service not known
```

**2. 进行打通并且进行测试**

2.1 使用打通命令

参数介绍

```java
Usage:  docker network connect [OPTIONS] NETWORK CONTAINER

Connect a container to a network

Options:
      --alias strings           Add network-scoped alias for the container
      --driver-opt strings      driver options for the network
      --ip string               IPv4 address (e.g., 172.30.100.104)
      --ip6 string              IPv6 address (e.g., 2001:db8::33)
      --link list               Add link to another container
      --link-local-ip strings   Add a link-local address for the container
```

示例

```java
docker network connect --help
```

执行连通命令之后我们查看，可以看到我们tomcat01加入到了我们的mynet网络下面。
官方说明：这个就是一个网络，两个ip

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker network inspect mynet

"Containers": {
    "2eaa3d1c0846893f55b05324f42e41a0c1e40c4fcf5822b23923c3342bc19ae5": {
        "Name": "tomcat-net-01",
        "EndpointID": "45e5f31ad6d2246795bfe17e29bdfec668c340890479ae114abc849f4f0be227",
        "MacAddress": "02:42:c0:a8:00:02",
        "IPv4Address": "192.168.0.2/16",
        "IPv6Address": ""
    },
    # 这里我们可以看到这里添加了我们刚刚打通的容器配置，并且这个容器获取了一个新的的ip也
    "d25b287e98a04e4c1e3f3069f7f394c08a28daa24c2e2730bd8244942b5366fd": {
        "Name": "tomcat01",
        "EndpointID": "c02f5062df7f5f1fd86a11d31c3d5675c0f62ca8d86193da28a295dcecd9f09a",
        "MacAddress": "02:42:c0:a8:00:04",
        "IPv4Address": "192.168.0.4/16",
        "IPv6Address": ""
    },
    "dedc8ffc6ae2c91f684dd80efa5b315e093caf0fca267ff490e72dae4a01680d": {
        "Name": "tomcat-net-02",
        "EndpointID": "b94a7f94d1c9762012582a8159ed404bd1ade39627be1b20d393a71e7374c62d",
        "MacAddress": "02:42:c0:a8:00:03",
        "IPv4Address": "192.168.0.3/16",
        "IPv6Address": ""
    }
}
```

2.2 进行测试

```java
docker exec -it tomcat01 ping tomcat-net-01
```

可以看到我们可以ping通在不同网络上的容器了

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it tomcat01 ping tomcat-net-01
PING tomcat-net-01 (192.168.0.2) 56(84) bytes of data.
64 bytes from tomcat-net-01.mynet (192.168.0.2): icmp_seq=1 ttl=64 time=0.098 ms
```






<hr>
<a id="_114"></a>

## `高阶实战:Docker Redis集群搭建`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 配置我们的自定义网络(方便容器间的通信)**

设置我们的子网掩码，也就是确定了我们的网段。

```java
docker network create redis --subnet 172.38.0.0/16
```

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker network ls
NETWORK ID          NAME                DRIVER              SCOPE
457e4f323561        redis               bridge              local
```

**2. 接着我们来创建我们的集群**

2.1 首先编写脚本创建6个redis的配置文件，他们的端口号需要时不同的

```java
for port in $(seq 1 6); \
do \
mkdir -p /mydata/redis/node-${port}/conf
cat << EOF >/mydata/redis/node-${port}/conf/redis.conf
port 6379
bind 0.0.0.0
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 172.38.0.1${port}
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
EOF
done
```

结果展示

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ conf]# pwd
/mydata/redis/node-1/conf
[root@iZ2ze1ycujbqzy1mnrzufbZ conf]# cat redis.conf 
port 6379
bind 0.0.0.0
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 172.38.0.11
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
```

2.2 接着我们开始搭建我们的集群，创建我们的6个容器

```java
for port in $(seq 1 6); \
do \
docker run -p 637${port}:6379 -p 1637${port}:16379 \
--name redis-${port} \
-v /mydata/redis/node-${port}/data:/data \
-v /mydata/redis/node-${port}/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.1${port} \
redis:5.0.9-alpine3.11 redis-server \
/etc/redis/redis.conf
done
```

运行我们的脚本，可以看到6个容器都是正常运行

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ redis]# for port in $(seq 1 6); \
> do \
> docker run -p 637${port}:6379 -p 1637${port}:16379 \
> --name redis-${port} \
> -v /mydata/redis/node-${port}/data:/data \
> -v /mydata/redis/node-${port}/conf/redis.conf:/etc/redis/redis.conf \
> -d --net redis --ip 172.38.0.1${port} \
> redis:5.0.9-alpine3.11 redis-server \
> /etc/redis/redis.conf
> EOF
> done
Unable to find image 'redis:5.0.9-alpine3.11' locally
5.0.9-alpine3.11: Pulling from library/redis
cbdbe7a5bc2a: Pull complete 
dc0373118a0d: Pull complete 
cfd369fe6256: Pull complete 
3e45770272d9: Pull complete 
558de8ea3153: Pull complete 
a2c652551612: Pull complete 
Digest: sha256:83a3af36d5e57f2901b4783c313720e5fa3ecf0424ba86ad9775e06a9a5e35d0
Status: Downloaded newer image for redis:5.0.9-alpine3.11
2abec85ad545493fefdccb5511e050b1a12898894c9ec39b14bfa2685c7db51c
fd076c840ecca41209d5f5100034b4d28b21c220230b9a8f810cfeb6dd816cc3
91ae11219a6849e24aadc6a24a219b12cd1f8bd5fd8175c4bbd458b944fa279e
9380d6b90678b957d04fc74102742139d3796ba0d40cdf6fcf62619e371800f0
9c2fa0db86f0fa3075fbaf64329207f4b1060b1423229190cf70496d697e7a3e
170f6d90334d69a99bd9c3a9a8e58473b1803b9faa74614ff36a721f91d63c6b
[root@iZ2ze1ycujbqzy1mnrzufbZ redis]# docker ps
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                                              NAMES
170f6d90334d        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   5 seconds ago       Up 4 seconds        0.0.0.0:6376->6379/tcp, 0.0.0.0:16376->16379/tcp   redis-6
9c2fa0db86f0        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   5 seconds ago       Up 4 seconds        0.0.0.0:6375->6379/tcp, 0.0.0.0:16375->16379/tcp   redis-5
9380d6b90678        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   6 seconds ago       Up 5 seconds        0.0.0.0:6374->6379/tcp, 0.0.0.0:16374->16379/tcp   redis-4
91ae11219a68        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   6 seconds ago       Up 5 seconds        0.0.0.0:6373->6379/tcp, 0.0.0.0:16373->16379/tcp   redis-3
fd076c840ecc        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   7 seconds ago       Up 6 seconds        0.0.0.0:6372->6379/tcp, 0.0.0.0:16372->16379/tcp   redis-2
2abec85ad545        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   8 seconds ago       Up 6 seconds        0.0.0.0:6371->6379/tcp, 0.0.0.0:16371->16379/tcp   redis-1
```

**3. 接着开始配置我们的集群**

3.1 进入到我们的redis后台进行集群的配置

```java
docker exec -it redis-1 /bin/sh
```

进行集群的搭建和分片配置

```java
redis-cli --cluster create \
172.38.0.11:6379 \
172.38.0.12:6379 \
172.38.0.13:6379 \
172.38.0.14:6379 \
172.38.0.15:6379 \
172.38.0.16:6379 \
--cluster-replicas 1
```

下面可以看到我们集群配置成功

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ conf]# docker exec -it redis-1 /bin/sh
/data # ls
appendonly.aof  nodes.conf
/data # redis-cli --cluster create \
> 172.38.0.11:6379 \
> 172.38.0.12:6379 \
> 172.38.0.13:6379 \
> 172.38.0.14:6379 \
> 172.38.0.15:6379 \
> 172.38.0.16:6379 \
> --cluster-replicas 1
>>> Performing hash slots allocation on 6 nodes...
# 下面有3个主机，3个从机
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
Adding replica 172.38.0.15:6379 to 172.38.0.11:6379
Adding replica 172.38.0.16:6379 to 172.38.0.12:6379
Adding replica 172.38.0.14:6379 to 172.38.0.13:6379
M: 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 172.38.0.11:6379
   slots:[0-5460] (5461 slots) master
M: ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 172.38.0.12:6379
   slots:[5461-10922] (5462 slots) master
M: 6f93f69ce3ba15a58de143cf366dde1e14af184f 172.38.0.13:6379
   slots:[10923-16383] (5461 slots) master
S: 4875a8ead4d1e10c9fda0eca8c548a356ca34526 172.38.0.14:6379
   replicates 6f93f69ce3ba15a58de143cf366dde1e14af184f
S: 29e0f774e54778d7a28b8eb5c063d989b0f4ee19 172.38.0.15:6379
   replicates 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf
S: b158f37ff07946ee317f779d22171b5a8f8bb6cb 172.38.0.16:6379
   replicates ae4d8b65fe0b8abfa2f227919294d34600f0d4a9
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
...
>>> Performing Cluster Check (using node 172.38.0.11:6379)
M: 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 172.38.0.11:6379
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
S: 4875a8ead4d1e10c9fda0eca8c548a356ca34526 172.38.0.14:6379
   slots: (0 slots) slave
   replicates 6f93f69ce3ba15a58de143cf366dde1e14af184f
M: 6f93f69ce3ba15a58de143cf366dde1e14af184f 172.38.0.13:6379
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
S: b158f37ff07946ee317f779d22171b5a8f8bb6cb 172.38.0.16:6379
   slots: (0 slots) slave
   replicates ae4d8b65fe0b8abfa2f227919294d34600f0d4a9
S: 29e0f774e54778d7a28b8eb5c063d989b0f4ee19 172.38.0.15:6379
   slots: (0 slots) slave
   replicates 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf
M: ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 172.38.0.12:6379
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```

3.2 进行集群的测试

3.2.1 首先简单的启动我们的集群并且查看集群的信息

首先我们启动我们的redis，但是注意后面添加参数 -c ，表示我们启动的方式是集群启动

```java
./redis.sh -c
```

可以看到我们的集群大小是3(cluster_size:3),并且有着6个节点(cluster_known_nodes:6)

```java
/data # redis-cli -c
127.0.0.1:6379> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:1
cluster_stats_messages_ping_sent:897
cluster_stats_messages_pong_sent:882
cluster_stats_messages_sent:1779
cluster_stats_messages_ping_received:877
cluster_stats_messages_pong_received:897
cluster_stats_messages_meet_received:5
cluster_stats_messages_received:1779
```

接着我们查看我们的节点信息
    其中主节点：
        0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 172.38.0.11:6379@16379 myself,master - 0 1592138733000 1 connected 0-5460
        ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 172.38.0.12:6379@16379 master - 0 1592138735255 2 connected 5461-10922
        6f93f69ce3ba15a58de143cf366dde1e14af184f 172.38.0.13:6379@16379 master - 0 1592138734000 3 connected 10923-16383
    其中从节点：
        29e0f774e54778d7a28b8eb5c063d989b0f4ee19 172.38.0.15:6379@16379 slave 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 0 1592138734252 5 connected
        b158f37ff07946ee317f779d22171b5a8f8bb6cb 172.38.0.16:6379@16379 slave ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 0 1592138734553 6 connected
        4875a8ead4d1e10c9fda0eca8c548a356ca34526 172.38.0.14:6379@16379 slave 6f93f69ce3ba15a58de143cf366dde1e14af184f 0 1592138734000 4 connected

    

```java
127.0.0.1:6379> cluster nodes
0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 172.38.0.11:6379@16379 myself,master - 0 1592138733000 1 connected 0-5460
6f93f69ce3ba15a58de143cf366dde1e14af184f 172.38.0.13:6379@16379 master - 0 1592138734000 3 connected 10923-16383
ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 172.38.0.12:6379@16379 master - 0 1592138735255 2 connected 5461-10922
4875a8ead4d1e10c9fda0eca8c548a356ca34526 172.38.0.14:6379@16379 slave 6f93f69ce3ba15a58de143cf366dde1e14af184f 0 1592138734000 4 connected
b158f37ff07946ee317f779d22171b5a8f8bb6cb 172.38.0.16:6379@16379 slave ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 0 1592138734553 6 connected
29e0f774e54778d7a28b8eb5c063d989b0f4ee19 172.38.0.15:6379@16379 slave 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 0 1592138734252 5 connected
```

3.2.2 接着我们进行集群的高可用测试

先存入一个key，可以看到是主节点172.38.0.13:6379@16379 master进行了存储，也就是我们的容器 redis-3

```java
127.0.0.1:6379> set key01 value01
-> Redirected to slot [13770] located at 172.38.0.13:6379
OK
172.38.0.13:6379> get key01
"value01"
```

接着我们关闭这个redis来模拟这个redis服务器的崩塌，然后验证是否能够在其从容器中查找到刚刚的key01

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# docker ps
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                                              NAMES
b526ad9d30ac        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6376->6379/tcp, 0.0.0.0:16376->16379/tcp   redis-6
dba2cd610a45        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6375->6379/tcp, 0.0.0.0:16375->16379/tcp   redis-5
2bc5977b1db3        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6374->6379/tcp, 0.0.0.0:16374->16379/tcp   redis-4
89f9f9dabae3        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6373->6379/tcp, 0.0.0.0:16373->16379/tcp   redis-3
4858e1cf270a        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6372->6379/tcp, 0.0.0.0:16372->16379/tcp   redis-2
cf810826f5a1        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6371->6379/tcp, 0.0.0.0:16371->16379/tcp   redis-1
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# docker stop redis-3
redis-3
[root@iZ2ze1ycujbqzy1mnrzufbZ data]# docker ps
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                                              NAMES
b526ad9d30ac        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6376->6379/tcp, 0.0.0.0:16376->16379/tcp   redis-6
dba2cd610a45        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6375->6379/tcp, 0.0.0.0:16375->16379/tcp   redis-5
2bc5977b1db3        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6374->6379/tcp, 0.0.0.0:16374->16379/tcp   redis-4
4858e1cf270a        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6372->6379/tcp, 0.0.0.0:16372->16379/tcp   redis-2
cf810826f5a1        redis:5.0.9-alpine3.11   "docker-entrypoint.s…"   19 minutes ago      Up 19 minutes       0.0.0.0:6371->6379/tcp, 0.0.0.0:16371->16379/tcp   redis-1
```

可以看到我们从172.38.0.14:6379@16379 slave 中命中了目标数据，可以看到集群实现了高可用

```java
/data # redis-cli -c
127.0.0.1:6379> get key01
-> Redirected to slot [13770] located at 172.38.0.14:6379
"value01"
```

这里我们再次查看节点信息，可以看到一个节点崩了，于是另外一个从节点成为主机进行运作
4875a8ead4d1e10c9fda0eca8c548a356ca34526 172.38.0.14:6379@16379 master - 0 1592139698552 7 connected 10923-16383
6f93f69ce3ba15a58de143cf366dde1e14af184f 172.38.0.13:6379@16379 master,fail - 1592139388313 1592139385809 3 connected

```java
127.0.0.1:6379> cluster nodes
4875a8ead4d1e10c9fda0eca8c548a356ca34526 172.38.0.14:6379@16379 master - 0 1592139698552 7 connected 10923-16383
6f93f69ce3ba15a58de143cf366dde1e14af184f 172.38.0.13:6379@16379 master,fail - 1592139388313 1592139385809 3 connected
0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 172.38.0.11:6379@16379 myself,master - 0 1592139698000 1 connected 0-5460
b158f37ff07946ee317f779d22171b5a8f8bb6cb 172.38.0.16:6379@16379 slave ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 0 1592139699000 6 connected
29e0f774e54778d7a28b8eb5c063d989b0f4ee19 172.38.0.15:6379@16379 slave 0ca28d74812a137dd8ff7e8c6eaaac12d7520ccf 0 1592139699554 5 connected
ae4d8b65fe0b8abfa2f227919294d34600f0d4a9 172.38.0.12:6379@16379 master - 0 1592139698652 2 connected 5461-10922
```


<hr>


<a id="_button"></a>

## `命令总结`
- <a href="#_top" rel="nofollow" rel="nofollow" target="_self">返回目录</a>

#### 镜像命令
<hr>

```java
docker images                                # 查看镜像详细信息
docker images -q                             # 仅仅查看镜像id
docker search mysql --filter=STARS=3000      # 查找STARTS > 3000 的mysql
docker pull mysql:5.7
docker rmi -f be0dbf01a0f3 bf756fb1ae65      # 删除指定id镜像
docker rmi -f $(docker images -aq)           # 删除所有镜像
docker run -it centos:7                      # 从镜像常见一个容器并且执行 
```

后台运行

```java
docker run \
-d \
-p 8080:8080 \
-v container01 \
-e 环境变量 \
容器id|容器name
```

前台运行

```java
docker run \
-it \
-p 8080:8080 \
-v container01 \
-e 环境变量 \
容器id|容器name \
/bin/bash
```

#### 容器命令
<hr>

```java
docker ps                                    # 列出正在执行的容器
docker ps -a                                 # 列出所有容器，包括没有执行的
exit                                         # 退出当前容器，后台不再运行
Ctrl + P + Q                                 # 退出当前容器，后台继续运行
docker rm 79496fc1a948                       # 删除指定id容器
docker rm -f $(docker ps -aq)                # 删除所有容器
docker start f81027372ded                    # 下面是一些通过容器id进行执行的命令
docker restart f81027372ded
docker stop f81027372ded
docker kill f81027372ded
docker run -d centos                            # 后台打开容器，但是如果没有前台程序执行的话可能会直接退出
docker run -d centos /bin/sh -c "while true;do echo centos is running;sleep 1;done"     # 后台运行容器并且为其指定前台
docker logs -tf --tail 10 f81027372ded          # 进行日志的打印
docker exec -it 28cf2fa3a0bb /bin/bash          # 打开目标容器新的命令窗口
docker attach 28cf2fa3a0bb                      # 执行已经容器中已经运行的命令窗口
```

后台运行并且制定前台程序

```java
docker run \
-d centos \
/bin/sh -c "while true;do echo centos is running;sleep 1;done"
```

#### 容器同步命令
<hr>

```java
docker cp 28cf2fa3a0bb:/home/test.java /home    # 复制容器中的文件到本地
```

具名数据卷

```java
docker run \
-it \
-p 8080:8080 \
-v container01:/ \
-e 环境变量 \
容器id|容器name \
/bin/bash
```

全路径数据卷

```java
docker run \
-it \
-p 8080:8080 \
-v /mymetadata/data:/ \
-e 环境变量 \
容器id|容器name \
/bin/bash
```

#### 容器数据分卷命令
<hr>

数据分卷目录

```java
/var/lib/docker/volumes 
```

```java
docker volume ls                # 查看所有数据卷
docker volume inspect ngnix     # 查看指定镜像的数据卷的详细信息
docker inspect a14ef03845bf     # 查看容器数据卷信息
```

#### Docker网络命令
<hr>

```java
docker network ls
docker network inspect 网络id|网络name
```

创建自定义ip

```java
docker network create \
--driver bridge \
--subnet 192.168.0.0/16 \
--gateway 192.168.0.1/16 \
网络name
```

通过自定义网络启动images

```java
docker run -d -P --name tomcat01 --net bridge tomcat
```

进行ping测试

```java
docker exec -it tomcat-net-01 ping 192.168.0.3
```

不同网络的容器间打印

```java
docker exec -it tomcat01 ping tomcat-net-01
```


#### 常见软件启动
<hr>

tomcat具体分卷

```java
docker run \
--name tomcat \
-d \
-v tomcat8080conf:/usr/local/tomcat/conf \
-v tomcat8080webapps:/usr/local/tomcat/webapps \
-p 8080:8080 \
tomcat:9.0 
```

nginx具体分卷

```java
docker run \
    --name nginx01 \
    -d  \  
    -p  80:80 \
    -v ngnix:/etc/nginx \
    nginx
```

MySQL具体分卷

```java
 docker run \
 --name mymysql01 \
 -v mysqlconf:/etc/mysql/conf.d \
 -v mysqldata:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d \
 -p 3306:3306 \
 mysql:5.7
```

elasticsearch启动

```java
docker run 
--name elasticsearch02 \
-d \
-p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
elasticsearch:7.7.1
```

#### Dockerfile命令
<hr>

```java
docker build -f mytomcat -t mytomcat8080 .
```

```java
# 我们的基础镜像
FROM centos
# 我们的作者
MAINTAINER root<73@qq.com>
# 拷贝我们的文件到镜像中
COPY readme.txt /usr/local/readme.txt
# 添加我们的文件并且会自动解压到容器中的目标目录
ADD apache-tomcat-9.0.36.tar.gz /usr/local
ADD jdk-8u151-linux-x64.tar.gz /usr/local
# 环境变量
ENV MYPATH /usr/local
# 镜像的工作目录
WORKDIR $MYPATH
# 配置我们的环境
ENV JAVA_HOME /usr/local/jdk1.8.0_151
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.36
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.36
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
# 镜像构建的时候需要运行的命令,这里安装vim和网络插件
RUN yum -y install vim
RUN yum -y install net-tools
# 设置暴露的端口
EXPOSE 8080
# 指定这个容器启动的时候需要运行的命令
CMD echo $MYPATH
CMD /usr/local/apache-tomcat-9.0.36/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.36/bin/logs/catalina.out

```







<hr>
<a id="_button01"></a>

## `小结:我的Docker网址整理`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>


- [DockerHub](https://hub.docker.com/)

- [Docker官网命令文档](https://docs.docker.com/engine/reference/commandline/run/)

- [阿里云实例](https://ecs.console.aliyun.com/?spm=5176.12818093.recommends.decs.488716d0NOale1&accounttraceid=d7d51864230148ba87132eec2bfd01d1ovux#/server/i-2ze1ycujbqzy1mnrzufb/detail?regionId=cn-beijing)

- [阿里云安全组](https://ecs.console.aliyun.com/?spm=5176.12818093.recommends.decs.488716d0NOale1&accounttraceid=d7d51864230148ba87132eec2bfd01d1ovux#/securityGroupDetail/region/cn-beijing/groupId/sg-2zehi6q15ypv5v9l8h2g/detail/intranetIngress)

- [阿里云镜像加速](https://cr.console.aliyun.com/cn-beijing/instances/mirrors)

- [阿里云快照](https://ecs.console.aliyun.com/?spm=5176.8351553.products-recent.decs.1dc81991Zua2QU#/disk/region/cn-beijing)

# 最后，希望大家点个赞。给博主充充电！！！
