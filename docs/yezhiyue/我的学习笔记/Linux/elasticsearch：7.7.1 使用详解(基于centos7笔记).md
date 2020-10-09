
# elasticsearch：7.7.1 使用详解(基于centos7笔记)


<a id="_top"></a>

## `目录:`

<hr>

##### <a href="#_1" rel="nofollow" target="_self">elasticsearch(非Docker安装）</a>
1. <a href="#_1.1" rel="nofollow" target="_self">目录介绍</a>
2. <a href="#_1.2" rel="nofollow" target="_self">配置文件配置</a>
3. <a href="#_1.3" rel="nofollow" target="_self">启动</a>
4. <a href="#_1.4" rel="nofollow" target="_self">ik分词器安装</a>

##### <a href="#_2" rel="nofollow" target="_self">elasticsearch(Docker安装)</a>
1. <a href="#_2.1" rel="nofollow" target="_self">elasticsearch安装启动</a>
2. <a href="#_2.2" rel="nofollow" target="_self">kibana安装配置</a>
3. <a href="#_2.3" rel="nofollow" target="_self">可视化界面补充</a>

##### <a href="#_3" rel="nofollow" target="_self">elasticsearch数据结构和基本原理</a>

##### <a href="#_4" rel="nofollow" target="_self">elasticsearch基本 API 命令</a>

- <a href="#_4.01" rel="nofollow" target="_self">入门命令</a>
1. <a href="#_4.02" rel="nofollow" target="_self">创建文档</a>
2. <a href="#_4.03" rel="nofollow" target="_self">创建索引</a>
3. <a href="#_4.04" rel="nofollow" target="_self">查看索引默认信息</a>
4.  <a href="#_4.05" rel="nofollow" target="_self">查看索引详细信息</a>

- <a href="#_4.11" rel="nofollow" target="_self">命令进阶</a>
1. <a href="#_4.12" rel="nofollow" target="_self">添加数据</a>
2. <a href="#_4.13" rel="nofollow" target="_self">修改、删除数据</a>
3. <a href="#_4.14" rel="nofollow" target="_self">查询数据</a>
4. <a href="#_4.15" rel="nofollow" target="_self">高亮显示</a>








 <a id="_1"></a>

## `elasticsearch(非Docker安装）`

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<hr>


### 下载解压

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

过程不再多说了，直接镜像网站下载 .gz 包然后解压

 [华为镜像](https://mirrors.huaweicloud.com/elasticsearch/7.7.1/)

<hr>
 <a id="_1.1"></a>

### elasticsearch目录介绍

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<hr>

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616155740219.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

bin     启动文件
config  配置文件
    log4j2 日志配置文件
    jvm.options java虚拟机参数配置
    elasticsearch.yml elasticsearch的配置文件，默认9200端口，可能会涉及到跨域问题
lib     相关jar包
logs    日志
modules 功能模块
plugins 比如ik分词器

<hr>
 <a id="_1.2"></a>
 
### 配置elasticsearch配置文件

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. elasticsearch本身配置文件配置**

config目录下:

- jvm.options:配置堆内存，Xms=256m Xmx=256m(可以根据自己的电脑性能来进行配置)

- elasticsearch.yml配置文件中配置你的ip，这样的话可以让外网进行访问

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616161932912.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**2. elasticsearch对系统的配置同样也是有着苛刻的要求**

2.1 首先对系统最大文件数量有要求(65536)

编辑配置文件

```java
vi /etc/security/limits.conf
```

添加配置

```java
* soft nofile 65536
* hard nofile 65536
```

然后重启linux或者重新登录 su root 或者其他用户使得配置文件生效

2.2 接着，对最大的线程同样有着要求(262144)

编辑配置文件

```java
vi /etc/sysctl.conf
```

进行参数配置

```java
vm.max_map_count=262144
```

最后使得配置生效

```java
sysctl -p
```
**3. 跨域的配置**

config目录下的elasticsearch.yml配置跨域设置

```java
# 设置全部ip都可以进行跨域访问
http.cors.enabled: true
http.cors.allow-origin: "*"
```

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616162807563.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
<hr>

 <a id="_1.3"></a>
 
### 启动elasticsearch

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

这个时候，elasticsearch启动不允许在root用户状态下面启动，需要在非root用户下启动，所以我们有需要有额外的配置了。

**1. 我们先关闭防火墙，进行访问测试**

```java
systemctl stop firewalld
systemctl disable firewalld
```

**2. 我们创建用户**

```java
groupadd es
useradd es -g es
chown -R es:es ./elasticsearch-7.7.1
```

**3. 切换用户**

```java
su es
```

**4. 进入bin目录进行启动**

```java
./elasticsearch
```

如果需要后台启动的话是下面这个命令

```java
./elasticsearch -d 
```

**5. 访问效果如下**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616162311736.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
<hr>
 <a id="_1.4"></a>
 
### 插件的安装(ik分词器)

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 新建⽬录ik并且将分词器解压在这个目录下**

```java
cd /opt/elasticsearch/elasticsearch-7.7.1/plugins/elasticsearch-analysis-ik-7.7.1
```

```java
mkdir ik
```

```java
cd ik
```

**2. 根据自己的版本选择对应的ik分词器，下面是官网的地址**

```java
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.7.1//elasticsearch-analysis-ik-7.7.1.zip
```

```java
unzip elasticsearch-analysis-ik-7.7.1.zip
```

**3. 启动后会有以下的效果**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616161723102.png)

也可以通过下面的命令进行查看

```java
elasticsearch-plugin list
```

```java
[root@aa30c6c3d444 elasticsearch]# elasticsearch-plugin list
ik
```

**4. 进入我们的kibana查看分词器的效果(如果没有安装图形界面请参照下面的教程)**

```java
GET _analyze
{
    "analyzer":"ik_smart",
    "text":"中国共产党"
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617093659726.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

最大拆分

```java
GET _analyze
{
    "analyzer":"ik_max_word",
    "text":"中国共产党"
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617093638740.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

没有被拆分

```java
GET _analyze
{
    "analyzer":"keyword",
    "text":"中国共产党"
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617094650722.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

标准拆分

```java
GET _analyze
{
    "analyzer":"standard",
    "text":"中国共产党"
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617094712867.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

 <a id="_2"></a>

## elasticsearch(Docker安装)

<hr>
 <a id="_2.1"></a>


### elasticsearch安装启动

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

话不多说，你只要安装了docker，下面一条命令就可以搞定了，配置一下内存(根据你的机器来配置)

如果没有安装，请参照[网址](https://blog.csdn.net/qq_43230007/article/details/104246620)

```java
docker run -d --name elasticsearch02 -p 9200:9200 -p 9300:9300 \
-v elasticsearch7.0:/usr/local/elasticsearch \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" elasticsearch:7.7.1
```

<hr>
 <a id="_2.2"></a>


### kibana安装启动

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
docker run \
-it \
--name kibana \
 -p 5601:5601 \
kibana:7.7.1 \
bin/bash
```

进入命令行配置kibana的配置文件

```java
[root@iZ2ze1ycujbqzy1mnrzufbZ ~]# docker exec -it 3b239fff5b6e /bin/bash
bash-4.2$ ls  
kibana.yml
bash-4.2$ vi kibana.yml 
server.name: kibana
server.host: "0"
elasticsearch.hosts: [ "http://59.110.213.92:9200" ]
monitoring.ui.container.elasticsearch.enabled: true
```

然后我们直接通过：http://你的ip:5601 进行访问

<hr>
 <a id="_2.3"></a>
 
### 可视化界面的安装的补充

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 我们可以选择head插件**

这里需要一些前端的安装环境

如果没有git，直接进行下载

```java
yum install git
```

```java
git clone git://github.com/mobz/elasticsearch-head.git
cd elasticsearch-head
npm install
npm run start
open http://localhost:9100/
```

连接测试，需要配置我们elasticsearch的跨域

**2.  ElisticHD，更加可视化的界面**

这里我们演示docker安装

```java
docker run \ 
-p \ 
9200:9200 \
-d \ 
--name elasticsearch01 \
elasticsearch
```

```java
docker run \
-p 9800:9800 \
-d \
--link elasticsearch01 \
containerize/elastichd
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617093156445.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

 <a id="_3"></a>

## `elasticsearch数据结构和基本原理`

<hr>

### 数据结构

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616171226131.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

这里和MySQL有着非常相似的关系

database --> indices    索引
tables   --> types      类型(这个可能描述的不太准确，type更加类似于java中的class)
rows     --> documents  文档
columns  --> field      字段

<hr>


### 启动elasticsearch之后就是以集群的方式进行工作

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

elasticsearch把后台的每个索引划分为了多个分片，每个分片可以在集群中的不同服务器间迁移。所以我们一开始就是搭建了一个集群，虽然看上去是单机的安装。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616171627247.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

一个集群至少有一个节点，一个节点就是一个elasticsearch进程，节点可以有多个索引，如果你创建了一个索引，那么索引将会有5个分片，每一个主分片都会有一个副本。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616173455422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

 <a id="_4"></a>

## `elasticsearch基本 API 命令`

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616215232842.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4.01"></a>

<hr>

### 入门命令演示

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_4.02"></a>

**1. 创建一个文档** 

PUT /索引名/~类型名~/文档id {请求体}

```java
PUT /test/type1/1
{
    "name":"zhangsan",
    "age":3    
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616222528367.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)










<a id="_4.03"></a>

**2. 创建一个索引**

```java
PUT /test2
{
    "mappings":{
        "properties":{
            "name":{
                "type":"text"
            },
            "age":{
                "type":"long"
            },
            "birthday":{
                "type":"date"
            }
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616222651106.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
GET test2
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616222847190.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4.04"></a>

**3. 查看默认信息**

```java
PUT /test3/_doc/1
{
    "name":"zhangsan",
    "age":21,
    "birth":"1997-01-05"
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616222919826.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
GET test3
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616222947588.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4.05"></a>

**4. 获取索引详细信息**

```java



GET _cat/indices?v
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616223102608.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<hr>

### 数据类型

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

字符串类型
text 、 keyword
数值类型
long, integer, short, byte, double, float, half_float, scaled_float
日期类型
date
PUT /索引名/~类型名~/文档id {请求体}
te布尔值类型
boolean
二进制类型
binary
等等......

<a id="_4.11"></a>

<hr>

### 命令进阶

<hr>

<a id="_4.12"></a>

#### 增加数据

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 添加数据**

```java
PUT /alibaba/user/1
{
    "name":"zhangsan",
    "age":21,
    "desc":"我是张三",
    "tags":["开发","专家"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616224821314.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
PUT /alibaba/user/2
{
    "name":"lisi",
    "age":22,
    "desc":"我是李四",
    "tags":["jvm","docker"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616224847891.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**2. 数据获取 GET**

```java
GET /alibaba/user/1
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616224929893.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
GET /alibaba/user
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616225459256.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4.13"></a>

<hr>

#### 修改数据,删除数据

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 更新数据 PUT**

```java
PUT /alibaba/user/1
{
    "name":"zhangsan:new",
    "age":21,
    "desc":"我是张三",
    "tags":["开发","专家"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616225714108.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
GET /alibaba/user/1
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616225747406.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**2. 更灵活的更新方式(推荐使用) POST _update**

```java
POST /alibaba/user/1/_update
{
    "doc":{
        "name":"ZHANGSAN:new"
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616230235918.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
GET /alibaba/user/1
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061623030828.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4.14"></a>

<hr>

#### 查询数据

<hr>

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**1. 简单搜索**

```java
GET /alibaba/user/1
```

**2. 条件查询**

```java
GET /alibaba/user/_search?q=name:lisi
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616230828188.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


**3. 复杂条件查询**

3.1 通过字段进行过滤

```java
GET alibaba/user/_search
{
    "query":{
        "match":{
            "name":"lisi"
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616231453239.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

3.2 对字段结果进行过滤，返回部分字段

```java
GET alibaba/user/_search
{
    "query":{
        "match":{
            "name":"lisi"
        }
    },
    "_source":["name","desc"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616231611459.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

3.3 对结果进行排序

```java
GET alibaba/user/_search
{
    "sort":[
        {
            "age":{
                "order":"asc"
            }
        }   
    ]   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616232040764.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


3.4 分页查询

```java
GET alibaba/user/_search
{
    "from":0,
    "size":1 
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616232241821.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

3.3 布尔查询,逻辑判断查询(& | !)

bool 表示返回满足所有条件的结果

must 表示 and(&)
should 表示 or(|)
must_not 表示 not(!)

```java
GET alibaba/user/_search
{
    "query":{
        "bool":{
            "must":[
                {
                    "match":{
                        "name":"lisi"
                            }       
                },
                {
                    "match":{
                        "age":"21"
                            }       
                }   
                ]
            }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616232947714.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
GET alibaba/user/_search
{
    "query":{
        "bool":{
            "must":[
                {
                    "match":{
                        "name":"lisi"
                            }       
                },
                {
                    "match":{
                        "age":"22"
                            }       
                }   
                ]
            }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616233056681.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)


3.4 过滤器(< > =)

类似于比较运算符

gt 大于
gte 大于等于
lt 小于
lte 小于等于

```java
GET alibaba/user/_search
{
    "query":{
        "bool":{
            "filter":{
                "range":{
                    "age":{
                        "gt":20,
                        "lt":22
                        }
                    }
                }
            }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616233758313.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**4. 精确查询**

term查询时直接通过倒排索引指定的词条进程精确查找的

- term，直接查询精确的

- match，会使用分词器解析(先分析文档，然后在分析的结果中进行查询)

多个词精确匹配

```java
GET /alibaba/user
{
    "query":{
        "bool":{
            "should":{
                "term":{
                    "age":"22"
                },
                "term":{
                    "age":"21"
                }
            }
        }
    }    
}
```

分词分析后在进行分析(就是经过了我们的ik分词器分词之后再对分词之后的结果进行查询)

```java
GET alibaba/user/_search
{
    "query":{
        "match":{
            "desc":"李"
        }
    },
    "_source":["name","desc"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617100223483.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4.15"></a>

**5. 高亮查询**

5.1 默认高亮显示

```java
GET alibaba/user/_search
{
    "query":{
        "match":{
            "desc":"李"
        }
    },
    "highlight":{
        "fields":{
            "desc":{}
        }
    },
    "_source":["desc"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617100950392.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

5.2 自定义高亮显示


```java
GET alibaba/user/_search
{
    "query":{
        "match":{
            "desc":"李"
        }
    },
    "highlight":{
        "pre_tags":"<p class='key' style='color:red'>",
        "post_tags":"</p>",
        "fields":{
            "desc":{}
        }
    },
    "_source":["desc"]
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617101502841.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

**6. 关于MySQL和elasticsearch**

其实elasticsearch的这些功能MySQL都可以实现，但是只不过效率会比较的低。


