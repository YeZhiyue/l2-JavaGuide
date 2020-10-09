## 会话技术Cookie&&Session

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self">Cookie</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">概述</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">常用方法</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">存活时间设置</a>
1.4 <a href="#_1.4" rel="nofollow" target="_self">Cookie共享设置</a>
### <a href="#_2" rel="nofollow" target="_self">Session</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">概述</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">常用方法</a>
2.3 <a href="#_2.3" rel="nofollow" target="_self">实现客户端关闭，服务器不关闭，两次获取的Session是同一个</a>
2.4 <a href="#_2.4" rel="nofollow" target="_self">关闭销毁session</a>
### <a href="#_3" rel="nofollow" target="_self">session与Cookie的区别</a>

---

<a id="_1"></a>

## `Cookie`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. 概述**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**基于响应头set-cookie和请求头cookie实现的客户端会话技术，将数据保存到客户端。**

- 我们可以发送多个Cookie给浏览器
- Cookie的存活时间可以设置，但是默认情况下浏览器关闭，Cookie生命周期结束
- Cookie不支持特殊字符的存储，tomcat8之后的Cookie支持中文了
- Cookie可以在不同服务器之间实现共享，但是需要进行配置
- Cookie的存储数据大小比较有限，并且同一个域名下的Cookie总数也是有限制的
- Cookie一般用来存放不太敏感的数据(比如购物车信息)

---

<a id="_1.2"></a>

**2. 常用方法**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
1. 创建Cookie对象，绑定数据
* new Cookie(String name, String value)
2. 发送Cookie对象
* response.addCookie(Cookie cookie)
3. 获取Cookie，拿到数据
* Cookie[]  request.getCookies()
```

---

<a id="_1.3"></a>

**3. 存活时间设置**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
1. 默认情况下，当浏览器关闭后，Cookie数据被销毁
2. 持久化存储：
* setMaxAge(int seconds)
1. 正数：将Cookie数据写到硬盘的文件中。持久化存储。并指定cookie存活时间，时间到后，cookie文件自动失效
2. 负数：默认值
3. 零：删除cookie信息
```

---

<a id="_1.4"></a>

**4. Cookie共享设置**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？
* 默认情况下cookie不能共享

* setPath(String path):设置cookie的获取范围。默认情况下，设置当前的虚拟目录
* 如果要共享，则可以将path设置为"/"


2. 不同的tomcat服务器间cookie共享问题？
* setDomain(String path):如果设置一级域名相同，那么多个服务器之间cookie可以共享
* setDomain(".baidu.com"),那么tieba.baidu.com和news.baidu.com中cookie可以共享
```

---

<a id="_2"></a>

## `Session`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. 概述**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。Session的实现还是基于Cookie的，**

---

<a id="_2.2"></a>

**2. 常用方法**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
1. 获取HttpSession对象：
HttpSession session = request.getSession();
2. 使用HttpSession对象：
Object getAttribute(String name)
void setAttribute(String name, Object value)
void removeAttribute(String name)
```

---

<a id="_2.3"></a>

**3. 实现客户端关闭，服务器不关闭，两次获取的Session是同一个**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
* 如果这种情况发生了，那么session在默认设置下两次获取的session不是同一个。
* 如果需要相同，则可以创建Cookie,键为JSESSIONID，设置最大存活时间，让cookie持久化保存。
Cookie c = new Cookie("JSESSIONID",session.getId());
c.setMaxAge(60*60);
response.addCookie(c);
```

`补充：`如果希望服务器关闭之后session数据不丢失，那么就需要将session敦化，也就是将session数据持久化到硬盘上面。

---

<a id="_2.4"></a>

**4. 关闭销毁session**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

session的销毁有下面这三种情况(我们可以主动销毁)

```java
1. 服务器关闭
2. session对象调用invalidate() 。
3. session默认失效时间 30分钟
    选择性配置修改
    <session-config>
    <session-timeout>30</session-timeout>
    </session-config>
```

---

<a id="_3"></a>

## `session与Cookie的区别`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
1. session存储数据在服务器端，Cookie在客户端
2. session没有数据大小限制，Cookie有大小限制
3. session数据安全，Cookie相对于不安全
```
