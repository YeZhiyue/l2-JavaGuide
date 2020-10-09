
# MybatisPlus使用示例参考

- [mybatis官方文档参考](https://mybatis.plus/guide/quick-start.html#%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96)

- [GitHub参考源码下载](https://github.com/YeZhiyue/MybatisPlusDemo)

<a id="_top"></a>

## `目录:`

<hr>

##### <a href="#_1" rel="nofollow" target="_self">配置入门</a>
1. <a href="#_1.1" rel="nofollow" target="_self">pom.xml</a>
2. <a href="#_1.2" rel="nofollow" target="_self">MyBatisPlusConfig</a>
3. <a href="#_1.3" rel="nofollow" target="_self">application.properties</a>
4. <a href="#_1.4" rel="nofollow" target="_self">Sql和实体类和自动填充策略处理程序</a>
##### <a href="#_2" rel="nofollow" target="_self">单元测试和效果展示</a>
1. <a href="#_2.1" rel="nofollow" target="_self">insert</a>
2. <a href="#_2.2" rel="nofollow" target="_self">update</a>
3. <a href="#_2.3" rel="nofollow" target="_self">delete</a>
4. <a href="#_2.4" rel="nofollow" target="_self">select</a>
4. <a href="#_2.5" rel="nofollow" target="_self">乐观锁</a>
4. <a href="#_2.6" rel="nofollow" target="_self">分页</a>
4. <a href="#_2.7" rel="nofollow" target="_self">多条件查询(order by\group by\多表查询)</a>
##### <a href="#_3" rel="nofollow" target="_self">逆向工程实现</a>
1. <a href="#_3.1" rel="nofollow" target="_self">编写逆向工程文件</a>
2. <a href="#_3.2" rel="nofollow" target="_self">添加配置文件</a>
### <a href="#_5" rel="nofollow" target="_self">MybatisPlus的Wrappers使用</a>
5.1 <a href="#_5.1" rel="nofollow" target="_self">Wrappers.<User>lambdaUpdate()使用</a>
5.2 <a href="#_5.2" rel="nofollow" target="_self">Wrappers.<User>lambdaQuery(User.class)使用</a>
### <a href="#_6" rel="nofollow" target="_self">我的MybatisPlus踩坑记录</a>
6.1 <a href="#_6.1" rel="nofollow" target="_self">getOne方法的使用</a>
6.2 <a href="#_6.2" rel="nofollow" target="_self">list方法使用错误</a>
6.3 <a href="#_6.3" rel="nofollow" target="_self">or和and的多条件查询的执行先后顺序逻辑</a>

<a id="_1"></a>

## 配置入门

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_1.1"></a>

**1. pom.xml**

<a id="_1.2"></a>

```java
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
```


**2. MyBatisPlusConfig**

> 配置清单：
> 乐观锁
> 分页查询
> 逻辑删除
> 性能测试

```java
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 这里设置这个类为配置类
@MapperScan("com.example.demo.mapper")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    // TODO 2020/6/24 配置类
    // 乐观锁组件，乐观锁拦截类
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页查询插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 这里我们简单的使用的话就只需要简单的配置下面这一句话，如果有需求添加注释中的配置
        return new PaginationInterceptor();
        //PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        //return paginationInterceptor;
    }

    // 配置逻辑删除
    // 3.1.1 之后不需要这个配置
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    // 配置性能测试
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启，否则会导致性能比较低
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置sql执行的最大时间，如果超时那么就执行失败
        performanceInterceptor.setMaxTime(100);
        // sql是否进行格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
```

<a id="_1.3"></a>

**3. application.properties**

```java
# 服务端口
server.port=8080

# jdbc
spring.datasource.password=root
spring.datasource.username=root
# 配置安全连接为false
spring.datasource.url=jdbc:mysql://localhost:3306/mybatisplus?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
# 配置驱动名称
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# 日志:配置为默认
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl


# ## mybatis的相关配置 ##

# 逻辑删除配置
# 删除之后的话我们就配置这个逻辑删除字段为 1
mybatis-plus.global-config.db-config.logic-delete-value=1
# 删除之前的话我们就配置这个逻辑删除字段为 0
mybatis-plus.global-config.db-config.logic-not-delete-value=0

# 性能分析配置，这里我们需要配置一下开发环境使得性能分析插件生效
spring.profiles.active=dev

# mybatis的代码自动生成,这里禁用模板缓存
spring.thymeleaf.cache=false
```

<a id="_1.4"></a>

**4. Sql和实体类和自动填充策略处理程序**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200625085929281.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020062509003319.png)

```java
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatisplus` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mybatisplus`;

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(11) DEFAULT '1' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

insert  into `person`(`id`,`name`,`age`,`email`,`create_time`,`update_time`,`version`,`deleted`) values (1,'Daming',18,'test5@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(2,'Jack',20,'test2@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(3,'Tom',28,'test3@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(4,'Sandy',21,'test4@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(5,'Billie',36,'test4@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(11) DEFAULT '1' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

insert  into `user`(`id`,`name`,`age`,`email`,`create_time`,`update_time`,`version`,`deleted`) values (1,'Daming',18,'test5@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(2,'Jack',20,'test2@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(3,'Tom',28,'test3@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(4,'Sandy',21,'test4@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0),(5,'Billie',36,'test4@baomidou.com','2020-06-24 17:59:08','2020-06-24 17:59:08',1,0);
```

```java
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class User implements Serializable {
//    主键自增策略，需要这个主键设置为自增
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private int age;
    private String email;
    // 自动更新，数据库或者代码级别的操作
    // 插入的时候进行自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    // 更新的的时候进行自动填充
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    // 乐观锁Version注解,最好默认值取 1
    @Version
    private Integer version;

    // 逻辑删除
    @TableLogic
    private Integer deleted;

    public User() {
    }

    public User(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
```

```java
// 日志
@Slf4j
// 需要加入到我们的IOC容器当中
@Component
public  class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入的时候的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
        this.setFieldValByName("createTime", new Date(),metaObject);
    }

    // 更新的时候的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
```

<a id="_2"></a>

## 单元测试和效果展示

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_2.1"></a>

### insert

```java
Execute SQL：
    INSERT 
    INTO
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@d277579]
        user
        ( name, age, email, create_time ) 
    VALUES
        ( 'ZhangSan', 25, '739153436@qq.com', '2020-06-25 09:05:33' )
```

```java
    @Test
    void insertTest() {
        int result = userMapper.insert(new User(null, "ZhangSan", 25, "739153436@qq.com"));
        System.out.println(result);
        userMapper.selectList(null).forEach(System.out::println);
    }
```

<a id="_2.2"></a>

### update

```java
    UPDATE
        user 
    SET
        name='LiSi',
        age=25,
        email='739153436@qq.com',
        update_time='2020-06-25 09:10:45' 
    WHERE
        id=6 
        AND deleted=0
```

```java
    @Test
    void updateTest() {
        User liSi = new User(6l, "LiSi", 25, "739153436@qq.com");
        int result = userMapper.updateById(liSi);
        System.out.println(result);
        userMapper.selectList(null).forEach(System.out::println);
    }
```

<a id="_2.3"></a>

### delete


```java
    // 通过id进行删除
    @Test
    void deleteByIdTest() {
        int result = userMapper.deleteById(6l);
        System.out.println(result);

        userMapper.selectList(null).
                forEach(System.out::println);
    }
```

```java
    // 多条件删除
    @Test
    void deleteByMapTest() {
        // 当前页和页面的大小
        HashMap<String, Object> map = new HashMap<>();
        // 多条件删除
        map.put("name", "Tom");
        map.put("age", 28);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);

        userMapper.selectList(null).
                forEach(System.out::println);
    }
```

<a id="_2.4"></a>

### select

```java
    // 查询所有
    @Test
    void selectListTest() {
        List<User> users = userMapper.selectList(null);
        System.out.println("查询所有的用户信息");
        users.forEach(System.out::println);
    }
```

```java
    // 批量查询
    @Test
    void selectBatchIdsTest() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        users.forEach(System.out::println);
    }
```

```java
    // 条件查询
    @Test
    void selectByMapTest() {
        HashMap<String, Object> map = new HashMap<>();
        // 通过字段里面多条件进行查询
        map.put("id", 1l);
        map.put("name", "Jone");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
```

<a id="_2.5"></a>

### 乐观锁

> ### 乐观锁
  
  概念：顾名思义，他总是认为不会出现问题，无论干什么都不去上锁！如果出现了问题，再次更新值测试
  
  乐观锁的实现方式
  
  **1. 取出当前记录，获取当前记录的version**
  
  **2. 更新时带上这个version，并且执行更新时更新这个version**
  
  **3. 如果version不对应，那么就执行失败**
  
  简单的示例
  
  -- A 如果执行过程中其他线程进行了更改，那么就会执行失败
  update user set name = "zhangsan", version = version + 1
  where id = 2 and version = 1
  
  -- B 如果这个时候B先执行，那么A就会执行失败
  update user set name = "zhangsan", version = version + 2
  where id = 2 and version = 1
  
  
  ### 悲观锁
  
  概念：他总是认为会出现问题，无论干什么都会去上锁，然后再进行操作


```java
    // 乐观锁的测试，单线程
    @Test
    void testOptimisticLocker() {
        // 查询用户信息
        User user = userMapper.selectById(5l);
//        log.info(TAG,user);
        System.out.println(user);
        user.setName("New Billie");
        // 进行更新
        userMapper.updateById(user);
        // ==>  Preparing: UPDATE user SET name=?, age=?, email=?, create_time=?, update_time=?, version=? WHERE id=? AND version=?
    }
```

```java
    // 乐观锁会出现的问题，多线程操作时可能会导致失败
    @Test
    void testOptimisticLockerMore() {
        // 查询用户信息
        User user = userMapper.selectById(5l);
        System.out.println(user);
        user.setName("New Billie01");

        // 插队的线程
        User user02 = userMapper.selectById(5l);
        System.out.println(user);
        user02.setName("New Billie02");
        userMapper.updateById(user02);
        
        // 进行更新
        userMapper.updateById(user);

        // 结果是 New Billie02 ，因为这个更新成功了，但是下面这个 New Billie01 的更新版本号对比失败，于是只执行了一次更新
    }
```

<a id="_2.6"></a>

### 分页

```java
    // 分页查询，但是效率不会太高，因为查询出来的信息比较多
    @Test
    void selectPageTest() {
        // 当前页和页面的大小
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        // 分页的循环遍历
        page.getRecords().forEach(System.out::println);

        // 下面可以直接获取其中的一些属性
        System.out.println(page.getTotal());
        System.out.println(page.getSize());
    }
```

<a id="_2.7"></a>

### 多条件查询(order by\group by\多表查询)



```java
    // 通过id进行删除
    @Test
    // SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND name IS NOT NULL AND email IS NOT NULL AND age >= 12
    void wrapper01Test() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                // 大于
                .ge("age", 12);

        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
        // SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND age BETWEEN 10 AND 30
    void wrapper02Test() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .between("age", 10, 30);

        userMapper.selectList(wrapper).forEach(System.out::println);

        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
        // SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND name = 'Tom'
    void wrapper03Test() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", "Tom");

        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
        // SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND name NOT LIKE '%ac%' AND email LIKE 't%'
    void wrapper04Test() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name", "ac")
                .likeRight("email", "t");

        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
        //SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND id IN (select id from user where id<3)
    void wrapper05Test() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .inSql("id","select id from user where id<4");

        userMapper.selectObjs(wrapper).forEach(System.out::println);
    }

    @Test
       //SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 ORDER BY id DESC
    void wrapper06Test() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                // 升序
                //.orderByAsc("id")
                // 降序
                .orderByDesc("id");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
```



<a id="_3"></a>

## 逆向工程实现

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

> ### 代码自动生成器
  
  作用:dao、pojo、service、controller 都可以直接使用代码生成器去生成


<a id="_3.1"></a>

**1. 编写逆向工程文件**

这里新建一个工程进行演示

```java
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class MyGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        /**
         * 配置你整体的包属性(service\controller\mapper)
         */
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 当前项目根目录的获取
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("YeZhiyue");
        // 设置是否打开资源管理器中的目录
        gc.setOpen(false);
        // 配置是否覆盖原文件
        gc.setFileOverride(false);
        // 各层文件名称方式，例如： %sAction 生成 UserAction
        gc.setServiceName("%sService");
       // id的自增策略
        gc.setIdType(IdType.ID_WORKER);
        // 配置日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 用于前后端分离,实体属性 Swagger2 注解
//        gc.setSwagger2(true);
        // 将这个配置扔到我们的全局配置中
        mpg.setGlobalConfig(gc);


        /**
         * 配置你的数据源
         */
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        /**
         * 这里配置你的存放不同层次的bao(Controller\Service\Mapper)
         */
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("innermodle");
        pc.setParent("com.example.demo");
        // 设置存放包的名称
        pc.setEntity("proj");
        pc.setService("service");
        pc.setController("controller");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);


        /**
         * 配置一些mybatispluse的特殊性质
         *  自动填充
         *  乐观锁
         *  逻辑删除
         */
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 设置需要进行映射的表名
        // TODO 2020/6/24 这里是需要根据需求添加表
        strategy.setInclude("user","person");
        // 这里直接可以配置将名称中的下划线 _ 转化为 驼峰命名
        // 表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 你的实体类的父类
//        strategy.setSuperEntityClass("Object");
        // 自动生成lombok
        strategy.setEntityLombokModel(true);
        // 设置自动逻辑删除设置 deleted
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充策略 create_time update_time 的自动填充策略
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        // 配置Rest的使用风格
        strategy.setRestControllerStyle(true);
        // 这里是对Controller进行配置命名的下划线风格
        strategy.setControllerMappingHyphenStyle(true);// localhost:8080/hello_id_2
        // 配置总的
        mpg.setStrategy(strategy);

        // 最后执行所有配置
        mpg.execute();
    }
}
```

代码生成之后需要自行对其中一些部分进行修改，比如讲对象放入我们的IOC容器，给对象添加构造器方法...

```java
public User(){

}
public User(Long id, String name, Integer age, String email) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
}
```

<a id="_3.2"></a>

**2. 添加配置文件**

pom.xml

```java
  <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>5.1.6</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
        <!-- 模板引擎 -->
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>


    </dependencies>
```

application.properties

```java
# 服务端口
server.port=8080

# jdbc
spring.datasource.password=root
spring.datasource.username=root
# 配置安全连接为false
spring.datasource.url=jdbc:mysql://localhost:3306/mybatisplus?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
# 配置驱动名称
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# 日志:配置为默认
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl


# ## mybatis的相关配置 ##

# 逻辑删除配置
# 删除之后的话我们就配置这个逻辑删除字段为 1
mybatis-plus.global-config.db-config.logic-delete-value=1
# 删除之前的话我们就配置这个逻辑删除字段为 0
mybatis-plus.global-config.db-config.logic-not-delete-value=0

# 性能分析配置，这里我们需要配置一下开发环境使得性能分析插件生效
spring.profiles.active=dev

# mybatis的代码自动生成,这里禁用模板缓存
spring.thymeleaf.cache=false
```

MyBatisPlusConfig.java

```java
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 这里设置这个类为配置类
@MapperScan("com.example.demo.mapper")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    // TODO 2020/6/24 配置类
    // 乐观锁组件，乐观锁拦截类
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页查询插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 这里我们简单的使用的话就只需要简单的配置下面这一句话，如果有需求添加注释中的配置
        return new PaginationInterceptor();
        //PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        //return paginationInterceptor;
    }

    // 配置逻辑删除
    // 3.1.1 之后不需要这个配置
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    // 配置性能测试
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启，否则会导致性能比较低
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置sql执行的最大时间，如果超时那么就执行失败
        performanceInterceptor.setMaxTime(100);
        // sql是否进行格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
```

---

<a id="_5"></a>

## `MybatisPlus的Wrappers使用`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. Wrappers.<User>lambdaUpdate()使用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
@Test
public void saveOrUpdateTest01() {
    Map<String, String> map = new HashMap<>();

    userService.saveOrUpdate(new User(), Wrappers.<User>lambdaUpdate().setEntity(new User())
                    .setSql("age = '77'")
                    // 设置 更新 SQL 的 SET 片段 , 示例： UPDATE user SET update_time=?, age = '44' WHERE deleted=0
                    .and(userLambdaUpdateWrapper -> userLambdaUpdateWrapper.eq(User::getAccounts, "77").eq(User::getUserName, "张三")) // 嵌套语句表示
                    .or()
                    .func(userLambdaUpdateWrapper -> userLambdaUpdateWrapper.eq(User::getUserName, "Daming"))
//                .apply() // 注入风险
//                .exists() // 注"入风险
//                        .last("limit 1")// 注入风险
//                        .first("mybatis")

            // groupBy orderBy having
                    .groupBy(User::getStatus, User::getUserName)
                    .having("sum(age) > 10")
                    .having("sum(age) > {0}", 10)
                    .orderByDesc(User::getStatus, User::getUserName)
                    .orderByAsc(User::getStatus, User::getUserName)

            // between notBetween allEq ne ge gt le lt
                    .between(User::getAge, 35, 50)
                    .notBetween(User::getAge, 33, 44)
                    .eq(User::getUserName, "Daming")
                    .allEq(Maps.newHashMap(User::getUserName, "Daming")) // 多等值条件判断
                    .ne(User::getAge, 66) // not equal to <> 不等于
                    .ge(User::getAge, 66)  // great or equal to >=
                    .gt(User::getAge, 66)  // great to >
                    .le(User::getAge, 66) // little or equal to <=
                    .lt(User::getAge, 66) // little to <

            // like in notIn isNotNull
                    .like(User::getUserName, "Daming")
                    .in(User::getId, Arrays.asList(1, 2, 3, 4))
                    .notIn(User::getId, Arrays.asList(1, 2, 3, 4))
                    .inSql(User::getId, "select id from table where id < 3")
                    .isNotNull(User::getId)

    );
}
```

---

<a id="_5.2"></a>

**2. Wrappers.<User>lambdaQuery(User.class)使用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
@Test
public void saveOrUpdateTest02() {
    Map<String, String> map = new HashMap<>();

    userService.saveOrUpdate(new User(), Wrappers.<User>lambdaQuery(User.class).setEntity(new User())
            .select() // 需要查询的字段选择
                    // 设置 更新 SQL 的 SET 片段 , 示例： UPDATE user SET update_time=?, age = '44' WHERE deleted=0
                    .and(userLambdaUpdateWrapper -> userLambdaUpdateWrapper.eq(User::getAccounts, "77").eq(User::getUserName, "张三")) // 嵌套语句表示
                    .or()
                    .func(userLambdaUpdateWrapper -> userLambdaUpdateWrapper.eq(User::getUserName, "Daming"))
//                .apply() // 注入风险
//                .exists() // 注"入风险
//                        .last("limit 1")// 注入风险
//                        .first("mybatis")

                    // groupBy orderBy having
                    .groupBy(User::getStatus, User::getUserName)
                    .having("sum(age) > 10")
                    .having("sum(age) > {0}", 10)
                    .orderByDesc(User::getStatus, User::getUserName)
                    .orderByAsc(User::getStatus, User::getUserName)

                    // between notBetween allEq ne ge gt le lt
                    .between(User::getAge, 35, 50)
                    .notBetween(User::getAge, 33, 44)
                    .eq(User::getUserName, "Daming")
                    .allEq(Maps.newHashMap(User::getUserName, "Daming")) // 多等值条件判断
                    .ne(User::getAge, 66) // not equal to <> 不等于
                    .ge(User::getAge, 66)  // great or equal to >=
                    .gt(User::getAge, 66)  // great to >
                    .le(User::getAge, 66) // little or equal to <=
                    .lt(User::getAge, 66) // little to <

                    // like in notIn isNotNull
                    .like(User::getUserName, "Daming")
                    .in(User::getId, Arrays.asList(1, 2, 3, 4))
                    .notIn(User::getId, Arrays.asList(1, 2, 3, 4))
                    .inSql(User::getId, "select id from table where id < 3")
                    .isNotNull(User::getId)

    );
}
```

---

<a id="_6"></a>

## `我的MybatisPlus踩坑记录`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. getOne方法的使用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 源码文档

```java
/**
 * 根据 Wrapper，查询一条记录
 *
 * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
 * @param throwEx      有多个 result 是否抛出异常
 */
T getOne(Wrapper<T> queryWrapper, boolean throwEx);
```

1.2 使用的时候出现的错误

使用时直接没有添加第二个参数，到时数据查询出来多条错误的时候回报出异常。所以以后如果需要通过这个方法的话可以考虑添加后面的参数。

---

<a id="_6.2"></a>

**2. list方法使用错误**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 方法源码文档如下

但是我在使用的过程中发现即使查询出来的结果集数量为0，仍然会返回一个空集合对象而不是null对象，导致当时null判断一度失灵，所以这里使用需要注意。

```java
/**
 * 查询列表
 *
 * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
 */
default List<T> list(Wrapper<T> queryWrapper) {
    return getBaseMapper().selectList(queryWrapper);
}
```

---

<a id="_6.3"></a>

**3. or和and的多条件查询的执行先后顺序逻辑**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 一篇博客上的结论如下 

[参考博客](https://blog.csdn.net/qq_32573251/article/details/78570251?utm_source=blogxgwz7)

经过自己的实践之后和结论相同。

问题的关键就在于AND和OR的执行顺序问题。
查阅资料，关系型运算符优先级高到低为：NOT>AND>OR
如果where 后面有OR条件的话，则OR自动会把左右的查询条件分开。

1.2 MybatisPlus的使用示例如下

```java
dormitoryService.getOne(Wrappers.lambdaQuery(Dormitory.class)
    // 先执行部分
    .notIn(Dormitory::getId, dormitory.getId()).eq(Dormitory::getDormitoryNo, dormitory.getDormitoryNo())
    .or()
    // 第二个执行部分语句段
    .notIn(Dormitory::getId, dormitory.getId()).eq(Dormitory::getDormitoryName, dormitory.getDormitoryName())
    ,false)
```

