
## Mybatis框架简单介绍

> mybatis 是一个优秀的基于 java 的持久层框架，它内部封装了 jdbc，使开发者只需要关注 sql 语句本身而不需要花费精力去处理加载驱动、创建连接、创建 statement 等繁杂的过程。 mybatis 通过 xml 或注解的方式将要执行的各 statement 配置起来，并通过 java 对象和 statement 中 sql 的动态参数进行映射生成最终执行的 sq 语句，最后由 mybatis 框架执行 sql 并将结果映射为 java 对象并 返回。 采用 ORM 思想解决了实体和数据库映射的问题，dbc 进行了封装，屏蔽了 jdbc api 底层访问细节，使我 们不用与 jdbc api 打交道，就可以完成对数据库的持久化操作。
> 

>这里附上源码(可以下载参考使用)： [https://download.csdn.net/download/qq_43230007/12173926](https://download.csdn.net/download/qq_43230007/12173926)
## Mybatis框架实现简单的CRUD操作实现步骤
### 步骤一：前期资源准备
1. 创建maven工程，同时创建数据库表

```sql
	DROP DATABASE IF EXISTS demo;
	CREATE DATABASE IF NOT EXISTS demo;
	USE demo;
	DROP TABLE IF EXISTS `user`;
	SELECT DATABASE();
	CREATE TABLE `user` (
	  `id` INT(11) NOT NULL AUTO_INCREMENT,
	  `username` VARCHAR(32) NOT NULL COMMENT '用户名称',
	  `birthday` DATETIME DEFAULT NULL COMMENT '生日',
	  `sex` CHAR(1) DEFAULT NULL COMMENT '性别',
	  `address` VARCHAR(256) DEFAULT NULL COMMENT '地址',
	  PRIMARY KEY  (`id`)
	) ENGINE=INNODB DEFAULT CHARSET=utf8;
	
	INSERT  INTO `user`(`id`,`username`,`birthday`,`sex`,`address`) VALUES (41,'老王','2018-02-27 17:47:08','男','北京'),(42,'小二王','2018-03-02 15:09:37','女','宁波'),(43,'小二王','2018-03-04 11:34:34','女','杭州'),(45,'张三','2018-03-04 12:04:06','男','天津'),(46,'老王','2018-03-07 17:37:26','男','北京'),(48,'马七','2018-03-08 11:44:00','女','河南');
	
	SELECT * FROM USER;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200220094803461.png)

3. 配置pom.xml，导入相关依赖坐标
```sql
    <dependencies>
        <!--Mybatis包-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.5</version>
        </dependency>
        <!--日志包-->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.12</version>
        </dependency>
        <!--测试类包-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!--JDBC驱动包-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
    </dependencies>
```


4. 完善目录结构
![](https://img-blog.csdnimg.cn/20200220094351396.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

5. 创建Dao层接口

```sql
package com.demo.dao;

import com.demo.domain.User;

import java.util.List;

/**
 * CRUD 这个是一个数据库最基础的操作
 *  增删改
 *  查询
 *  模糊查询
 *  聚合函数查询
 * @author Ye
 */
public interface UserDao {
    /**
     * 为什么我下面的参数选择user，因为这样就方便选择user
     * 属性中的任意属性进行数据库表的操作
     * @param user
     */
    void updateUser(User user);

    /**
     * 插入操作
     * @param user
     */
    void insertUser(User user);

    /**
     * 删除操作
     * @param user
     */
    void deleteUser(User user);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 通过名称查找
     * @param user
     * @return
     */
    List<User> findUserByName(User user);

    /**
     * 通过关键字查找
     * @param user
     * @return
     */
    List<User> findByKeyWord(User user);

    /**
     * 查询总数
     * @param user
     * @return
     */
    Integer findTotalCount(User user);


}
```

7. 创建数据模型User对应数据库的User表

```sql
package com.demo.domain;

import java.util.Date;

/**
 * 定义的User类，对应了数据库中的User表
 */
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
```


9. 配置文件的准备
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200220094615107.png) 
### 步骤二
1. 测试类准备

```sql
package com.demo.test;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    /**
     * 初始化资源
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    /**
     * 关闭资源
     * @throws Exception
     */
    @After
    public void destroy() throws Exception {
        //关闭资源
        sqlSession.close();
        in.close();
    }

    /**
     * 插入操作
     */
    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("李四");
        userDao.insertUser(user);
    }

    /**
     * 更新操作
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setUsername("张三2");
        userDao.updateUser(user);
    }

    /**
     * 删除操作
     */
    @Test
    public void deleteUser() {
        User user = new User();
        user.setUsername("李四");
        userDao.deleteUser(user);
    }

    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        User user = new User();
        user.setUsername("张三");
        List<User> li = userDao.findAll();
        for (User user1 : li) {
            System.out.println(user);
        }
    }

    /**
     * 通过姓名查询
     */
    @Test
    public void findUserByName() {
        User user = new User();
        user.setUsername("老王");
        List<User> li = userDao.findUserByName(user);
        for (User user1 : li) {
            System.out.println(user);
        }
    }

    /**
     * 关键字模糊查询
     */
    @Test
    public void findByKeyWord() {
        User user = new User();
        //模糊查询需要传入带通配符的参数，例如下面就是查询姓名中包含"王"的用户
        user.setUsername("%王%");
        List<User> li = userDao.findByKeyWord(user);
        for (User u : li) {
            System.out.println(u);
        }
    }

    /**
     * 查询总数
     */
    @Test
    public void findTotalCount() {        User user = new User();
        user.setUsername("小二王");
        Integer li = userDao.findTotalCount(user);
        System.out.println("查询到小二王的数量是："+li);

    }
}
```

3. 完善相关xml配置文件

```sql
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.demo.dao.UserDao">

    <!-- 定义User类的返回值模板 resultMap-->
    <!--1.id是user对象的模板
        2.type是指指定的模板类名称为User
        -->
    <resultMap id="userMap" type="User">
        <!--<id>是主键字段别名标签：如果User类中的id字段对应数
            据库中的id字段是带有主键约束的话，那么就需要使用<id>标签-->
        <!--<result>标签是非对应主键字段所使用的标签-->
        <!--属性：
                property：指定的是User类中的字段名称
                column：指定的是数据库表的字段名称
                -->
        <id property="id" column="id"></id>
        <result property="username" column="username"></result>
        <result property="address" column="address"></result>
        <result property="sex" column="sex"></result>
        <result property="birthday" column="birthday"></result>
    </resultMap>
    <!--
        说明：以下都是数据库查询语句的标签内容，其实质就是在写数据库操作的方法，
            只不过就是通过标签的形式书写代码而已。
        标签介绍：对应的就是CRUD操作
            <insert>
            <delete>
            <update>
            <select>
        属性介绍：
            id：对应实现的接口的方法名，这里实现了接口"com.demo.dao.UserDao"
            parameterType:对应了方法中的参数，这里主要使用的参数都是user
            resultType：返回值类型属性赋值，可以赋值全类名或者已经配置的别名
            resultMap：返回值类型，一些引用类型对象的返回值使用这个标签，通常配合<resultMap>标签使用

        -->
    <!--增、删、改操作，因为这些操作-->
    <!--新增用户-->
    <insert id="insertUser" parameterType="user">
        insert into user(username) values(#{username});
    </insert>
    <!--删除用户-->
    <delete id="deleteUser" parameterType="user">
        DELETE FROM USER WHERE username=#{username};
    </delete>
    <!--更新用户-->
    <update id="updateUser" parameterType="user">
        UPDATE USER SET username=#{username} WHERE username='张三';
    </update>
    <!--普通查询、模糊查询、聚合查询-->
    <!--查询所有-->
    <select id="findAll" resultMap="userMap">
        SELECT * FROM USER;
    </select>
    <!--通过名称查询-->
    <select id="findUserByName" parameterType="user" resultMap="userMap">
   SELECT * FROM USER WHERE username=#{username};
    </select>
    <!--模糊查询-->
    <select id="findByKeyWord" parameterType="user" resultMap="userMap">
        SELECT * FROM USER WHERE username LIKE #{username};
    </select>
    <!--聚合函数查询-->
    <select id="findTotalCount" parameterType="user" resultType="Integer">
       SELECT COUNT(*) FROM USER WHERE username=#{username};
    </select>
</mapper>
```

5. 测试
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020022009514442.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)### 总结
> Mybatis框架极大的方便了我们对数据库的操作，只需要学习其中的标签，我们就可以非常方便的通过对xml配置文件的修改从而改变对数据库的操作，而不是去修改java源码，这样就方便了对程序的维护和测试。











