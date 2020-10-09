
# ==Mybatis配置文件引入MySQL数据源的三种方式&&映射文件配置的三种方式==

> 这里是MySQL数据源的引入方式示例，其中还在每段代码的下面示范了映射文件配置的三种配置示例。

## 方式一：最基础的配置方式 
 ```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--*************************第一种环境配置方式********************************************-->
    <!--properties标签的作用
        1.引入内部配置文件的信息
        2.引入外部配置文件的信息
            属性介绍：
                1.resource 引入内部配置文件
                2.url 统一资源定位符 写法示例： url="file:///D:/IdeaProjects/day02_eesy_01mybatisCRUD/src/main/resources/jdbcConfig.properties"
                    注意：路径不要带有中文
        -->
    <properties>
        <property name="driver" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/eesy_mybatis"></property>
        <property name="username" value="root"></property>
        <property name="password" value="1234"></property>
    </properties>
    <!--配置环境-->
    <environments default="mysql">
        <!-- 配置mysql的环境-->
        <environment id="mysql">
            <!-- 配置事务 -->
            <transactionManager type="JDBC"></transactionManager>

            <!--配置连接池-->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"></property>
                <property name="url" value="${url}"></property>
                <property name="username" value="${username}"></property>
                <property name="password" value="${password}"></property>
            </dataSource>
        </environment>
    </environments

    <!--************************************************************************-->

    <!--使用typeAliases配置别名，它只能配置domain中类的别名 -->
    <typeAliases>
        <!--typeAlias用于配置别名。type属性指定的是实体类全限定类名。alias属性指定别名，当指定了别名就再区分大小写 
        <typeAlias type="com.demo.domain.User" alias="user"></typeAlias>-->

        <!-- 用于指定要配置别名的包，当指定之后，该包下的实体类都会注册别名，并且类名就是别名，不再区分大小写-->
        <package name="com.demo.domain"></package>
    </typeAliases>

    <!--****************************配置映射文件的位置 的三种方式*******************************************-->

    <!-- 配置映射文件的位置 的三种方式-->
    <mappers>
        <!--方式一-->
        <!-- package标签是用于指定dao接口所在的包,当指定了之后就不需要在写mapper以及resource或者class了 -->
        <!--<mapper resource="com/demo/dao/IUserDao.xml"></mapper>-->
        <!--方式二-->
        <!--使用 mapper 接口类路径-->
        <!--注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。-->
        <!--<mapper class="com.demo.dao.UserDao"/>-->
        <!--方式三-->
        <!--此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。-->
        <package name="com.demo.dao"></package>
    </mappers>
</configuration>

```
## 方式二：通过resource属性引入类路径下的配置文件

```c
## 配置文件示
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/eesy_mybatis
jdbc.username=root
jdbc.password=1234
```

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>




    <!--****************************第二种环境配置方式*******************************************-->
 	<properties resource="jdbcConfig.properties"></properties>
    <!--配置环境-->
    <environments default="mysql">
        <!-- 配置mysql的环境-->
        <environment id="mysql">
            <!-- 配置事务 -->
            <transactionManager type="JDBC"></transactionManager>

            <!--配置连接池-->
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"></property>
                <property name="url" value="${jdbc.url}"></property>
                <property name="username" value="${jdbc.username}"></property>
                <property name="password" value="${jdbc.password}"></property>
            </dataSource>
        </environment>
    </environments>


    <!--************************************************************************-->

    <!--使用typeAliases配置别名，它只能配置domain中类的别名 -->
    <typeAliases>
        <!--typeAlias用于配置别名。type属性指定的是实体类全限定类名。alias属性指定别名，当指定了别名就再区分大小写 
        <typeAlias type="com.demo.domain.User" alias="user"></typeAlias>-->

        <!-- 用于指定要配置别名的包，当指定之后，该包下的实体类都会注册别名，并且类名就是别名，不再区分大小写-->
        <package name="com.demo.domain"></package>
    </typeAliases>

    <!--****************************配置映射文件的位置 的三种方式*******************************************-->

    <!-- 配置映射文件的位置 的三种方式-->
    <mappers>
        <!--方式一-->
        <!-- package标签是用于指定dao接口所在的包,当指定了之后就不需要在写mapper以及resource或者class了 -->
        <!--<mapper resource="com/demo/dao/IUserDao.xml"></mapper>-->
        <!--方式二-->
        <!--使用 mapper 接口类路径-->
        <!--注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。-->
        <!--<mapper class="com.demo.dao.UserDao"/>-->
        <!--方式三-->
        <!--此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。-->
        <package name="com.demo.dao"></package>
    </mappers>
</configuration>

```



## 方式三：通过url属性引入任意路径下的配置文件
 ```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>


    <!--****************************第三环境配置方式*******************************************-->
    <properties
            url="file:///D:/IdeaProjects/day02_eesy_01mybatisCRUD/src/main/resources/jdbcConfig.properties"></properties>
    <!--配置环境-->
    <environments default="mysql">
        <!-- 配置mysql的环境-->
        <environment id="mysql">
            <!-- 配置事务 -->
            <transactionManager type="JDBC"></transactionManager>

            <!--配置连接池-->
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"></property>
                <property name="url" value="${jdbc.url}"></property>
                <property name="username" value="${jdbc.username}"></property>
                <property name="password" value="${jdbc.password}"></property>
            </dataSource>
        </environment>
    </environments>


    <!--************************************************************************-->

    <!--使用typeAliases配置别名，它只能配置domain中类的别名 -->
    <typeAliases>
        <!--typeAlias用于配置别名。type属性指定的是实体类全限定类名。alias属性指定别名，当指定了别名就再区分大小写 
        <typeAlias type="com.demo.domain.User" alias="user"></typeAlias>-->

        <!-- 用于指定要配置别名的包，当指定之后，该包下的实体类都会注册别名，并且类名就是别名，不再区分大小写-->
        <package name="com.demo.domain"></package>
    </typeAliases>

    <!--****************************配置映射文件的位置 的三种方式*******************************************-->

    <!-- 配置映射文件的位置 的三种方式-->
    <mappers>
        <!--方式一-->
        <!-- package标签是用于指定dao接口所在的包,当指定了之后就不需要在写mapper以及resource或者class了 -->
        <!--<mapper resource="com/demo/dao/IUserDao.xml"></mapper>-->
        <!--方式二-->
        <!--使用 mapper 接口类路径-->
        <!--注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。-->
        <!--<mapper class="com.demo.dao.UserDao"/>-->
        <!--方式三-->
        <!--此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。-->
        <package name="com.demo.dao"></package>
    </mappers>
</configuration>

```

