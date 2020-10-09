# SpringBoot 学习

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>创建一个简单的SpringBoot项目</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.1" rel="nofollow" target="_self">官网创建</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.2" rel="nofollow" target="_self">idea集成插件创建</a>
### <a href="#_2" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>原理初探</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.1" rel="nofollow" target="_self">pom文件依赖继承</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.2" rel="nofollow" target="_self">SpringBoot的启动器</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.3" rel="nofollow" target="_self">主程序启动器</a>
### <a href="#_3" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SpringBoot的yaml配置文件</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.1" rel="nofollow" target="_self">yaml配置文件的语法和在SpringBoot中的作用</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.2" rel="nofollow" target="_self">yaml中普通变量值定义</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.3" rel="nofollow" target="_self">yaml中map和对象定义</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.4" rel="nofollow" target="_self">数组</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.5" rel="nofollow" target="_self">yaml中配置默认端口号</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.6" rel="nofollow" target="_self">通过@Value注入值和通过yaml注入值</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.7" rel="nofollow" target="_self">@PropertySource && @ConfigurationProperties 加载配置文件注解</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.8" rel="nofollow" target="_self">配置文件占位符生成随机数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.9" rel="nofollow" target="_self">SpringBoot的property配置</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.10" rel="nofollow" target="_self"> SpringBoot配置文件小结</a>
### <a href="#_4" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SpringBoot中的JSR303数据校验</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.1" rel="nofollow" target="_self">简单示例</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.2" rel="nofollow" target="_self">常见参数校验</a>
### <a href="#_5" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>自定义SpringBoot的Start启动器</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.1" rel="nofollow" target="_self">命名规约</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.2" rel="nofollow" target="_self">案例</a>
### <a href="#_6" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SpringBoot整合</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.1" rel="nofollow" target="_self">整合JDBC</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.2" rel="nofollow" target="_self">整合Druid连接池</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.3" rel="nofollow" target="_self">修改SpringBoot的默认配置参数类(这里以Druid连接池为例)</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.4" rel="nofollow" target="_self">配置Druid的数据源监控</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.5" rel="nofollow" target="_self">SpringBoot整合Mybatis</a>
### <a href="#_7" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SpringBoot的Web开发处理</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_7.1" rel="nofollow" target="_self">SpringBoot静态资源原理初探</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_7.2" rel="nofollow" target="_self">关于webjars</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_7.3" rel="nofollow" target="_self">SpringBoot是如何扫描静态资源的</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_7.4" rel="nofollow" target="_self">自定义的静态资源路径</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_7.5" rel="nofollow" target="_self">Thymeleaf模板引擎</a>
### <a href="#_8" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SpringBoot的MVC原理</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_8.1" rel="nofollow" target="_self">官方文档</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_8.2" rel="nofollow" target="_self">官方术语简单解析</a>

---

<a id="_1"></a>

## `创建一个简单的SpringBoot项目`

---

<a id="_1.1"></a>

**1. 官网创建**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

[官网项目生成地址](https://start.spring.io/)

<a id="_1.2"></a>

**2. idea集成插件创建**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809145937347.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_2"></a>

## `原理初探`

---

<a id="_2.1"></a>

**1. pom文件依赖继承**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 自己建立项目的直接父依赖

```java
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```

1.2 spring-boot-starter-parent 的父依赖

```java
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.3.2.RELEASE</version>
  </parent>
```

1.3 spring-boot-dependencies 这个文件管理者我们项目依赖的版本，所以我们在给SpringBoot项目配置依赖的时候通常不需要配置版本号

```java
    <activemq.version>5.15.13</activemq.version>
    <antlr2.version>2.7.7</antlr2.version>
    <appengine-sdk.version>1.9.81</appengine-sdk.version>
    <artemis.version>2.12.0</artemis.version>
    <aspectj.version>1.9.6</aspectj.version>
```

---

<a id="_2.2"></a>

**2. SpringBoot的启动器**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 SpringBoot的启动器，如果我们需要启动某一项功能，直接导入启动器的依赖即可

```java
spring-boot-starter
```

1.2 官方文档地址

---

<a id="_2.3"></a>

**3. 主程序启动器**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 注解

```java
@SpringBootConfiguration SpringBoot的配置
    @Configuration spring的配置类
    @Component 说明这个是一个spring的组件

@EnableAutoConfiguration 自动配置
    @AutoConfigurationPackage 自动配置包
        @Import({Registrar.class}) 自动配置包注册
    @Import({AutoConfigurationImportSelector.class})            
```

1.2 自动配置过程

1.2.1 关键注解

```java
// @Import({AutoConfigurationImportSelector.class}) 
List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
```

1.2.2 我们通过注解找到了下面这个读取配置文件的方法

```java
	protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
		List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
				getBeanClassLoader());
		Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
				+ "are using a custom packaging, make sure that file is correct.");
		return configurations;
	}
```

1.3 "No auto configuration classes found in META-INF/spring.factories. If you "通过这句话，我们可以知道spring.factories是一个关键的文件，我们可以前去探究

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200808163500453.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200808163553490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

1.4 细节就不在过多探究

流程就是其中的核心注解@EnableAutoConfiguration，这个注解会读取核心的配置文件工厂，然后通过这个工厂文件的信息去加载相关的配置类或者文件，读取出来到缓存或者文件中，然后加载这些配置。这个就是简单的自动配置原理流程。

1.5 问题：为什么这么多的配置文件，没有导入对应的spring-boot-start包的话这些配置文件就不会生效呢？

这里是SpringBoot自动配置中一个判断核心注解在控制 @ConditionalOnProperty，里面有着一系列的判断条件，只有这些判断条件都生效的时候配置文件才会生效

1.6 总结

SpringBoot的所有自动配置都是在启动的时候扫描并且加载，所有的自动配置类都在spring.factories文件中，但是只有导入对应的start包(通过@ConditionalOnProperty注解进行判断)，SpringBoot才会真正加载相关的配置。

自动配置特点：

- SpringBoot启动的时候从 /META-INF/spring.factories获取指定配置文件的路径来加载配置文件
- SpringBoot帮我们做了自动配置，这些配置都在 spring-boot-autoconfigure-2.2.0.RELEASE.jar包下
- SpringBoot的自动配置最终效果就是将配置类注入到我们的容器中，然后配置就生效了

---

<a id="_3"></a>

## `SpringBoot的yaml配置文件`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_3.1"></a>

**1. yaml配置文件的语法和在SpringBoot中的作用**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 语法

key: + 空格 + value

1.2 在SpringBoot中的作用

修改SpringBoot的默认配置

---

<a id="_3.2"></a>

**2. yaml中普通变量值定义**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
k: v
```

---

<a id="_3.3"></a>

**3. yaml中map和对象定义**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 map

```java
k: 
    v1: 
    v2: 
```

1.2 对象

```java
student:
    name: zhangsan
    age: 3
```

1.3 行内写法,注意空格不要忘记

```java
student: {name: zhangsan,age: 3}
```

---

<a id="_3.4"></a>

**4. 数组**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
pets:
    - cat
    - dog
    - pig
```

```java
pets: [cat,dog,pig]
```

---

<a id="_3.5"></a>

**5. yaml中配置默认端口号**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
server:
    port: 8082
```

---

<a id="_3.6"></a>

**6. 通过@Value注入值和通过yaml注入值**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 通过@Value注入值

```java
@Component //注册bean
public class Dog {
    @Value("阿黄")
    private String name;
    @Value("18")
    private Integer age;
}
```

1.2 通过yaml注入值

```java
<!-- 导入配置文件处理器，配置文件进行绑定就会有提示，需要重启 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-configuration-processor</artifactId>
  <optional>true</optional>
</dependency>
```

```java
person:
  name: qinjiang
  age: 3
  happy: false
  birth: 2000/01/01
  maps: {k1: v1,k2: v2}
  lists:
   - code
   - girl
   - music
  dog:
    name: 旺财
    age: 1
```

```java
/*
@ConfigurationProperties作用：
将配置文件中配置的每一个属性的值，映射到这个组件中；
告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
参数 prefix = “person” : 将配置文件中的person下面的所有属性一一对应
*/
@Component //注册bean
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
}
```

---

<a id="_3.7"></a>

**7. @PropertySource && @ConfigurationProperties 加载配置文件注解**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 说明

@PropertySource ：加载指定的配置文件；

@ConfigurationProperties：默认从全局配置文件中获取值；

1.2 示例

我们去在resources目录下新建一个person.properties文件

```java
name=张三
```

然后在我们的代码中指定加载person.properties文件

```java
@PropertySource(value = "classpath:person.properties")
@Component //注册bean
public class Person {

    @Value("${name}")
    private String name;

    ......  
}
```

---

<a id="_3.8"></a>

**8. 配置文件占位符生成随机数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
person:
    name: qinjiang${random.uuid} # 随机uuid
    age: ${random.int}  # 随机int
    happy: false
    birth: 2000/01/01
    maps: {k1: v1,k2: v2}
    lists:
      - code
      - girl
      - music
    dog:
      name: ${person.hello:other}_旺财
      age: 1
```

---

<a id="_3.9"></a>

**9. SpringBoot的property配置**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 编辑配置文件 user.properties

```java
user1.name=kuangshen
user1.age=18
user1.sex=男
```

1.2 我们在User类上使用@Value来进行注入！

```java
@Component //注册bean
@PropertySource(value = "classpath:user.properties")
public class User {
    //直接使用@value
    @Value("${user.name}") //从配置文件中取值
    private String name;
    @Value("#{9*2}")  // #{SPEL} Spring表达式
    private int age;
    @Value("男")  // 字面量
    private String sex;
}
```

---

<a id="_3.10"></a>

**10. SpringBoot配置文件小结**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- yaml可以进行复杂对象的封装
- yaml配置中只需要写一次@ConfigurationProperties即可 ， 但是@Value需要每个字段都添加

补充：配置文件优先级

```java
优先级1：项目路径下的config文件夹配置文件
优先级2：项目路径下配置文件
优先级3：资源路径下的config文件夹配置文件
优先级4：资源路径下配置文件
```

---

<a id="_4"></a>

## `SpringBoot中的JSR303数据校验`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_4.1"></a>

**1. 简单示例**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
@Component //注册bean
@ConfigurationProperties(prefix = "person")
@Validated  //数据校验
public class Person {

    @Email(message="邮箱格式错误") //name必须是邮箱格式
    private String name;
}
```

---

<a id="_4.2"></a>

**2. 常见参数校验**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
@NotNull(message="名字不能为空")
private String userName;
@Max(value=120,message="年龄最大不能查过120")
private int age;
@Email(message="邮箱格式错误")
private String email;

空检查
@Null       验证对象是否为null
@NotNull    验证对象是否不为null, 无法查检长度为0的字符串
@NotBlank   检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
@NotEmpty   检查约束元素是否为NULL或者是EMPTY.
    
Booelan检查
@AssertTrue     验证 Boolean 对象是否为 true  
@AssertFalse    验证 Boolean 对象是否为 false  
    
长度检查
@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
@Length(min=, max=) string is between min and max included.

日期检查
@Past       验证 Date 和 Calendar 对象是否在当前时间之前  
@Future     验证 Date 和 Calendar 对象是否在当前时间之后  
@Pattern    验证 String 对象是否符合正则表达式的规则

.......等等
除此以外，我们还可以自定义一些数据校验规则
```

---

<a id="_5"></a>

## `自定义SpringBoot的Start启动器`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. 命名规约**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
官方命名：

前缀：spring-boot-starter-xxx

比如：spring-boot-starter-web....

自定义命名：

xxx-spring-boot-starter

比如：mybatis-spring-boot-starter
```

---

<a id="_5.2"></a>

**2. 案例**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

[b站大佬博客](https://mp.weixin.qq.com/s/2eB2uT088BvzaqRULezdsw)

---

<a id="_6"></a>

## `SpringBoot整合`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. 整合JDBC**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 依赖

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

1.2 配置

```java
spring:
  datasource:
    username: root
    password: 123456
    #?serverTimezone=UTC解决时区的报错
    url: jdbc:mysql://localhost:3306/springboot?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
```

1.3 测试

```java
@SpringBootTest
class SpringbootDataJdbcApplicationTests {

    //DI注入数据源
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);
        //关闭连接
        connection.close();
    }
}
```

1.4 小结

- SpringBoot默认配置的数据源是HikariDataSource，号称 Java WEB 当前速度最快的数据源，相比于传统的 C3P0 、DBCP、Tomcat jdbc 等连接池更加优秀
- 即使不使用第三方第数据库操作框架，如 MyBatis等，Spring 本身也对原生的JDBC 做了轻量级的封装，即JdbcTemplate。

```java
JdbcTemplate主要提供以下几类方法：

execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；

update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；

query方法及queryForXXX方法：用于执行查询相关语句；

call方法：用于执行存储过程、函数相关语句。

@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    /**
     * Spring Boot 默认提供了数据源，默认提供了 org.springframework.jdbc.core.JdbcTemplate
     * JdbcTemplate 中会自己注入数据源，用于简化 JDBC操作
     * 还能避免一些常见的错误,使用起来也不用再自己来关闭数据库连接
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询employee表中所有数据
    //List 中的1个 Map 对应数据库的 1行数据
    //Map 中的 key 对应数据库的字段名，value 对应数据库的字段值
    @GetMapping("/list")
    public List<Map<String, Object>> userList(){
        String sql = "select * from employee";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
    
    //新增一个用户
    @GetMapping("/add")
    public String addUser(){
        //插入语句，注意时间问题
        String sql = "insert into employee(last_name, email,gender,department,birth)" +
                " values ('狂神说','24736743@qq.com',1,101,'"+ new Date().toLocaleString() +"')";
        jdbcTemplate.update(sql);
        //查询
        return "addOk";
    }

    //修改用户信息
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id){
        //插入语句
        String sql = "update employee set last_name=?,email=? where id="+id;
        //数据
        Object[] objects = new Object[2];
        objects[0] = "秦疆";
        objects[1] = "24736743@sina.com";
        jdbcTemplate.update(sql,objects);
        //查询
        return "updateOk";
    }

    //删除用户
    @GetMapping("/delete/{id}")
    public String delUser(@PathVariable("id") int id){
        //插入语句
        String sql = "delete from employee where id=?";
        jdbcTemplate.update(sql,id);
        //查询
        return "deleteOk";
    }
    
}
```

---

<a id="_6.2"></a>

**2. 整合Druid连接池**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 依赖

```java
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.21</version>
</dependency>
```

1.2 配置

```java
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/springboot?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource # 自定义数据源
```

```java
spring:
  datasource:
    username: root
    password: 123456
    #?serverTimezone=UTC解决时区的报错
    url: jdbc:mysql://localhost:3306/springboot?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource

    #Spring Boot 默认是不注入这些属性值的，需要自己绑定
    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许时报错  java.lang.ClassNotFoundException: org.apache.log4j.Priority
    #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

1.3 补充

```java
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

---

<a id="_6.3"></a>

**3. 修改SpringBoot的默认配置参数类(这里以Druid连接池为例)**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 现在需要程序员自己为 DruidDataSource 绑定全局配置文件中的参数，再添加到容器中，而不再使用 Spring Boot 的自动生成了；我们需要 自己添加 DruidDataSource 组件到容器中，并绑定属性

```java
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    /*
       将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
       绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让它们生效
       @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
       前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

}
```

1.2 测试

```java
@SpringBootTest
class SpringbootDataJdbcApplicationTests {

    //DI注入数据源
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        //关闭连接
        connection.close();
    }
}
```

---

<a id="_6.4"></a>

**4. 配置Druid的数据源监控**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 Druid 数据源具有监控的功能，并提供了一个 web 界面方便用户查看，类似安装 路由器 时，人家也提供了一个默认的 web 页面。

所以第一步需要设置 Druid 的后台管理页面，比如 登录账号、密码 等；配置后台管理

```java
//配置 Druid 监控管理后台的Servlet；
//内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
@Bean
public ServletRegistrationBean statViewServlet() {
    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

    // 这些参数可以在 com.alibaba.druid.support.http.StatViewServlet 
    // 的父类 com.alibaba.druid.support.http.ResourceServlet 中找到
    Map<String, String> initParams = new HashMap<>();
    initParams.put("loginUsername", "admin"); //后台管理界面的登录账号
    initParams.put("loginPassword", "123456"); //后台管理界面的登录密码

    //后台允许谁可以访问
    //initParams.put("allow", "localhost")：表示只有本机可以访问
    //initParams.put("allow", "")：为空或者为null时，表示允许所有访问
    initParams.put("allow", "");
    //deny：Druid 后台拒绝谁访问
    //initParams.put("kuangshen", "192.168.1.20");表示禁止此ip访问

    //设置初始化参数
    bean.setInitParameters(initParams);
    return bean;
}
```

1.2 配置完毕后，我们可以选择访问 ：http://localhost:8080/druid/login.html

1.3 配置 Druid web 监控 filter 过滤器

```java
//配置 Druid 监控 之  web 监控的 filter
//WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
@Bean
public FilterRegistrationBean webStatFilter() {
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());

    //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
    Map<String, String> initParams = new HashMap<>();
    initParams.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
    bean.setInitParameters(initParams);

    //"/*" 表示过滤所有请求
    bean.setUrlPatterns(Arrays.asList("/*"));
    return bean;
}
```

---

<a id="_6.5"></a>

**5. SpringBoot整合Mybatis**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 依赖

```java
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.1</version>
</dependency>
```

1.2 基本配置不变

---

<a id="_7"></a>

## `SpringBoot的Web开发处理`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_7.1"></a>

**1. SpringBoot静态资源原理初探**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 SpringMVC的web配置都在 WebMvcAutoConfiguration 这个配置类里面

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        // 已禁用默认资源处理
        logger.debug("Default resource handling disabled");
        return;
    }
    // 缓存控制
    Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
    CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
    // webjars 配置
    if (!registry.hasMappingForPattern("/webjars/**")) {
        customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                                             .addResourceLocations("classpath:/META-INF/resources/webjars/")
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
    // 静态资源配置
    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    if (!registry.hasMappingForPattern(staticPathPattern)) {
        customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                                             .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
}
```

1.2 读一下源代码：比如所有的 /webjars/** ， 都需要去 classpath:/META-INF/resources/webjars/ 找对应的资源；

---

<a id="_7.2"></a>

**2. 关于webjars**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 说明

Webjars本质就是以jar包的方式引入我们的静态资源 ， 我们以前要导入一个静态资源文件，直接导入即可。

1.2 示例 

```java
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.4.1</version>
</dependency>
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809204414365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_7.4"></a>

**4. SpringBoot是如何扫描静态资源的**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 我们去找staticPathPattern发现第二种映射规则 ：/** , 访问当前的项目任意资源，它会去找 resourceProperties 这个类，我们可以点进去看一下分析：

```java
// 进入方法
public String[] getStaticLocations() {
    return this.staticLocations;
}
// 找到对应的值
private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
// 找到路径
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { 
    "classpath:/META-INF/resources/",
  "classpath:/resources/", 
    "classpath:/static/", 
    "classpath:/public/" 
};
```

1.2 结论

```java
"classpath:/META-INF/resources/"
"classpath:/resources/"
"classpath:/static/"
"classpath:/public/"
```

---

<a id="_7.5"></a>

**5. 自定义的静态资源路径**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
spring.resources.static-locations=classpath:/coding/,classpath:/kuang/
```

---

<a id="_7.6"></a>

**6. Thymeleaf模板引擎**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 引入依赖

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

1.2 Thymeleaf的自动配置规则

```java
@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML";
    private Charset encoding;
}
``` 

1.3 简单示例

```java
@RequestMapping("/t1")
public String test1(Model model){
    //存入数据
    model.addAttribute("msg","Hello,Thymeleaf");
    //classpath:/templates/test.html
    return "test";
}
```

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>狂神说</title>
</head>
<body>
<h1>测试页面</h1>

<!--th:text就是将div中的内容设置为它指定的值，和之前学习的Vue一样-->
<div th:text="${msg}"></div>
</body>
</html>
```

1.4 简单示例二

```java
@RequestMapping("/t2")
public String test2(Map<String,Object> map){
    //存入数据
    map.put("msg","<h1>Hello</h1>");
    map.put("users", Arrays.asList("qinjiang","kuangshen"));
    //classpath:/templates/test.html
    return "test";
}
```

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>狂神说</title>
</head>
<body>
<h1>测试页面</h1>

<div th:text="${msg}"></div>
<!--不转义-->
<div th:utext="${msg}"></div>

<!--遍历数据-->
<!--th:each每次遍历都会生成当前这个标签：官网#9-->
<h4 th:each="user :${users}" th:text="${user}"></h4>

<h4>
    <!--行内写法：官网#12-->
    <span th:each="user:${users}">[[${user}]]</span>
</h4>

</body>
</html>
```

---

<a id="_8"></a>

## `SpringBoot的MVC原理`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_8.1"></a>

**1. 官方文档**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

[SpringBoot的MVC配置官方文档](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-auto-configuration)

---

<a id="_8.2"></a>

**2. 官方术语简单解析**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
Spring MVC Auto-configuration
// Spring Boot为Spring MVC提供了自动配置，它可以很好地与大多数应用程序一起工作。
Spring Boot provides auto-configuration for Spring MVC that works well with most applications.
// 自动配置在Spring默认设置的基础上添加了以下功能：
The auto-configuration adds the following features on top of Spring’s defaults:
// 包含视图解析器
Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
// 支持静态资源文件夹的路径，以及webjars
Support for serving static resources, including support for WebJars 
// 自动注册了Converter：
// 转换器，这就是我们网页提交数据到后台自动封装成为对象的东西，比如把"1"字符串自动转换为int类型
// Formatter：【格式化器，比如页面给我们了一个2019-8-10，它会给我们自动格式化为Date对象】
Automatic registration of Converter, GenericConverter, and Formatter beans.
// HttpMessageConverters
// SpringMVC用来转换Http请求和响应的的，比如我们要把一个User对象转换为JSON字符串，可以去看官网文档解释；
Support for HttpMessageConverters (covered later in this document).
// 定义错误代码生成规则的
Automatic registration of MessageCodesResolver (covered later in this document).
// 首页定制
Static index.html support.
// 图标定制
Custom Favicon support (covered later in this document).
// 初始化数据绑定器：帮我们把请求数据绑定到JavaBean中！
Automatic use of a ConfigurableWebBindingInitializer bean (covered later in this document).

/*
如果您希望保留Spring Boot MVC功能，并且希望添加其他MVC配置（拦截器、格式化程序、视图控制器和其他功能），则可以添加自己
的@configuration类，类型为webmvcconfiguer，但不添加@EnableWebMvc。如果希望提供
RequestMappingHandlerMapping、RequestMappingHandlerAdapter或ExceptionHandlerExceptionResolver的自定义
实例，则可以声明WebMVCregistrationAdapter实例来提供此类组件。
*/
If you want to keep Spring Boot MVC features and you want to add additional MVC configuration 
(interceptors, formatters, view controllers, and other features), you can add your own 
@Configuration class of type WebMvcConfigurer but without @EnableWebMvc. If you wish to provide 
custom instances of RequestMappingHandlerMapping, RequestMappingHandlerAdapter, or 
ExceptionHandlerExceptionResolver, you can declare a WebMvcRegistrationsAdapter instance to provide such components.

// 如果您想完全控制Spring MVC，可以添加自己的@Configuration，并用@EnableWebMvc进行注释。
If you want to take complete control of Spring MVC, you can add your own @Configura
```

