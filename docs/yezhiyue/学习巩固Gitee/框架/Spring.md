## Spring是什么?

Spring是一种轻量级的开发框架。框架就是多个模块的集合，使用这些模块可以方便我们的开发。

## Spring框架需要我们学习的地方？

Spring主要的几个模块就是：核心容器、数据访问、Web、AOP、工具模块、消息和测试模块。

Spring的6个特点：

1. 核心技术：依赖注入(DI),AOP,事件，资源，i18n，参数校验，数据绑定，类型转换，SpEL.
2. 测试：模拟对象，TestContext框架，SpringMVC测试，WebTestClient
3. 数据访问：事务，DAO支持，JDBC，ORM，编组XML
4. Web支持：SpringMVC和SpringWegFlux框架
5. 集成：远程处理，JMS，JCA，JMX，电子邮件，任务，调度，缓存
6. Kotlin，Groovy，动态语言

## @Controller和@RestController的工作机制

1. @Controller用于返回一个视图的时候进行使用

2. @RestController用于返回JSON或者XML数据的时候使用

## IOC(控制反转)

一种设计思想，就是将原本在程序中手动创建对象的控制权叫给Spring框架管理。IOC容器就是用来存放这些对象的容器，其实质就是Map

这样子就可以将创建对象的依赖交给IOC来处理，减少了系统的耦合。

## AOP面向切面编程

将那些与业务无关，但是确实业务模块所共同调用的逻辑或者责任(例如事务处理、日志管理、权限管理)封装起来，便于减少代码量，降低耦合。

## Spring中Bean的作用域

- singleton：唯一的bean示例，Spring中的bean默认都是单例的
- prototype：每次请求都会创建一个新的bean实例

## Spring中的单例Bean的线程安全问题

1. 在Bean对象中尽量避免可以定义的成员变量(不太现实)
2. 在类中定义一个ThreadLocal成员变量，将需要的成员变量保存在ThreadLocal中(推荐的一种方式)

## @Component和@Bean的区别是什么

1. 作用对象不同：@Component注解作用域类，而@Bean作用于方法
2. @Component通常是通过类路径扫描来自动侦测以及自动装配到Spring容器中(我们可以使用@Component注解定义要扫描的路径中找到表示了需要装配的类自动装配到Spring的bean容器中)。@Bean注解通常是我们在标有该注解的方法中定义产生这个bean，@Bean告诉了Spring这个是某个类的示例，当我们需要他的时候给我们
3. @Bean注解比Component注解的定义性更强，而且很多地方我们只能通过@Bean注解来注册bean。比如当我们需要引用第三方库中的类需要装配到Spring容器时，则只能通过@Bean来实现

## 对SpringMVC的了解

- Model1时代：几乎整个Web应用都是JSP页面组成的，只用了少量的JavaBean、来处理数据库的连接、访问等糙做。这个模式下JSP是控制层又是表现层。导致系统耦合度非常高，代码的重用也就是非常低。
- Model2时代：Servlet开发时代，就是MVC模型，Model+View+Controller。这种模式有了明确的分层结构，但是抽象和封装程度还是不够的。

现在，SpringMV成为了主流的框架，他将Web框架分为了Service层、Dao层、Entity层、Controller层，并且返回数据给前台。

## SpringMVC处理请求的流程

1. 客户端发送请求，直接亲求导DispatcherServlet
2. DispatchServlet根据请求信息调用HandlerMapping，解析请求对应的Handler
3. 解析到对应的Handler(也就是我们的Controller控控制器)后，开始由HandlerAdapter适配器处理
4. HandlerAdapter会根据Handler来调用真正的处理器来处理请求，并处理响应的业务逻辑
5. 处理器处理完业务后，会返回一个ModelAndView对象，Model是返回的数据对象，View是一个逻辑上的View
6. ViewResolver会根据逻辑View查找实际的View
7. DispatchServlet把返回的Model传给View
8. 把View返回给请求者(浏览器)

请求捕获器-映射处理器-控制器-处理业务代码-返回ModelAndView对象-视图解析器-查找到实际的视图-将Model中的数据填充进去-将处理好的View返回给浏览器

## Spring中的设计模式

- 工厂设计模式：Spring使用工厂模式通过BeanFactory、ApplicationContext创建bean对象
- 代理设计模式：SpringAOP功能的实现
- 单例设计模式：Spring中的bean默认都是单例的
- 包装器设计模式：我们的项目需要链接多个数据库，而且不同的客户在每次范围呢种会根据需要去访问不同的数据库。何种模式让我们可以根据客户的需要来切换不同的数据源
- 观察者模式：Spring事件驱动模型就是细观察者模式很经典的一个应用
- 适配器模式：SpringAOP的增强或者通知时用到了适配器模式、SpringMVC中也是用到了适配器模式来适配Controller

## Spring事务

编程式事务，在代码中硬编码，不推荐使用
声明式事务，在配置文件中配置，推荐使用
    - 基于XML的声明
    - 基于注解的声明
    
