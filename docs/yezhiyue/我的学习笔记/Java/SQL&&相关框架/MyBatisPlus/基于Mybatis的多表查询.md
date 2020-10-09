

## 基于MybatisPlus的多表查询


---

<a id="_1"></a>

[mybaitsPlus配置文档](https://mybatis.plus/config/#typealiasespackage)

[mybatis3.x文档](https://mybatis.org/mybatis-3/zh/index.html)

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>一对一查询</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.1" rel="nofollow" target="_self">方式一：非select配置方式</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.2" rel="nofollow" target="_self">方式二，通过select进行多表查询</a>
### <a href="#_2" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>一对多查询(用户对应多账户示例)</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.1" rel="nofollow" target="_self">方式一：非select配置方式</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.2" rel="nofollow" target="_self">方式二，通过select进行多表查询</a>
### <a href="#_3" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>多对多查询(用户通过中间表对应多个角色示例)</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.1" rel="nofollow" target="_self">方式一：非select配置方式</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.2" rel="nofollow" target="_self">方式二，通过select进行多表查询</a>
### <a href="#_4" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>懒加载配置(基于MybatisPlus)</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.1" rel="nofollow" target="_self">配置懒加载的配置说明</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.2" rel="nofollow" target="_self">懒加载演示一对多关系的查询</a>
### <a href="#_5" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>MySQL函数使用</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.1" rel="nofollow" target="_self">自定义函数示例</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.2" rel="nofollow" target="_self">定义变量实现函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.3" rel="nofollow" target="_self">执行自己创建的函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.4" rel="nofollow" target="_self">Mybatis中执行函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.5" rel="nofollow" target="_self">业务中常用的函数</a>
### <a href="#_6" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>补充</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.1" rel="nofollow" target="_self">Mybatis的in查询，使用xml映射的<foreach>标签</a>

## `一对一查询`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

- 这里我们可以简单的看一下我们的数据表

```java
SELECT u.`id`,u.`user_name`,a.`uid`,a.`account_name` FROM USER u,account a
	WHERE u.id=a.uid;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704092323212.png)

```java
SELECT u.id,u.`user_name`,ur.`uid`,ur.`rid`,r.`id`,r.`role_name` FROM USER u,user_role ur,role r
	WHERE u.id=ur.uid 
	AND ur.rid=r.id
	ORDER BY u.`id`;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704092326967.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_1.1"></a>

**1. 方式一：非select配置方式**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明(关键参数和优缺点)

- 这里我们说的主表表示resultMap下面对应的第一个映射对象

resultMap参数 | 作用
--- | ---
id | 标识符(用于标识和指定resultMap)
type | 返回值类型(通常是JavaBean)

association参数 | 作用
--- | ---
property | 当前类对应的多表字段的属性名
column | 指定当前主表需要传递的表字段参数名称
javaType | 指定的返回值类型(通常是JavaBean)
select | 指定关联的select标签下的方法路径

优点：减少select标签的使用，可以直接在一个配置文件中完成多表操作
缺点：不是很灵活，无法实现懒加载

1.2 演示示例

```java
// 结果
<!-- Account(id=null, uid=1, accountName=建设银行, money=null, user=User(status=null, role=null, id=null, userName=Daming, age=null, email=null, createTime=null, updateTime=null, version=null, deleted=null, password=null, uRole=null, roleList=null)) -->
// 映射关系在一个表中配置完毕
<resultMap id="accountUser" type="Account">
    <result column="uid" property="uid"></result>
    <result column="account_name" property="accountName"></result>
    <association property="user" javaType="User">
        <result column="user_name" property="userName"></result>
    </association>
</resultMap>

<select id="findUserByAccountUid" parameterType="int" resultMap="accountUser">
    select u.user_name,a.uid,a.account_name from account a,user u where a.id = #{id} and a.uid=u.id;
</select>
```

---

<a id="_1.2"></a>

**2. 方式二，通过select进行多表查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明(关键参数和优缺点)

- 这里我们说的主表表示resultMap下面对应的第一个映射对象

resultMap参数 | 作用
--- | ---
id | 标识符(用于标识和指定resultMap)
type | 返回值类型(通常是JavaBean)

association参数 | 作用
--- | ---
property | 当前类对应的多表字段的属性名
column | 指定当前主表需要传递的表字段参数名称
javaType | 指定的返回值类型(通常是JavaBean)
select | 指定关联的select标签下的方法路径

优点：灵活，可以实现懒加载
缺点：可能配置会比较麻烦，需要多个select标签

1.2 演示示例

```java
// 结果
<!-- Account(id=null, uid=1, accountName=建设银行, money=null, user=User(status=null, role=null, id=null, userName=Daming, age=null, email=null, createTime=null, updateTime=null, version=null, deleted=null, password=null, uRole=null, roleList=null)) -->
// AccountMapper.xml下的配置
<resultMap id="accountUser" type="Account">
    <association property="user" column="uid" select="com.example.demo.mapper.UserMapper.findUserById"
                 javaType="User">

    </association>
</resultMap>

// UserMapper.xml下的配置
<!-- 一对一 -->
<select id="findUserById" parameterType="int" resultType="User">
    select user_name from user where id = #{uid};
</select>
```

---

<a id="_2"></a>

## `一对多查询(用户对应多账户示例)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>


---

<a id="_2.1"></a>

**1. 方式一：非select配置方式**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明(关键参数和优缺点)

- 这里我们说的主表表示resultMap下面对应的第一个映射对象

resultMap参数 | 作用
--- | ---
id | 标识符(用于标识和指定resultMap)
type | 返回值类型(通常是JavaBean)

collection参数 | 作用
--- | ---
property | 当前类对应的多表字段的属性名
column | 指定当前主表需要传递的表字段参数名称
javaType | 指定的返回值类型(常常使用ArrayList，并且这个参数可以省略，因为mybatis会帮助我们推断)
ofType | 指定返回值的类型
select | 指定关联的select标签下的方法路径

优点：减少select标签的使用，可以直接在一个配置文件中完成多表操作
缺点：不是很灵活，无法实现懒加载

1.2 演示示例

```java
// 结果
<!-- User(status=null, role=null, id=null, userName=Daming, age=null, email=null, createTime=null, updateTime=null, version=null, deleted=null, password=null, uRole=null, roleList=null, accounts=[Account(id=0, uid=1, accountName=招商银行, money=1000, user=null), Account(id=1, uid=1, accountName=建设银行, money=1000, user=null), Account(id=2, uid=1, accountName=北京银行, money=1000, user=null)]) -->
<resultMap id="userAccounts" type="User">
    <id column="id" property="id"></id>
    <result column="user_name" property="userName"></result>
    <collection property="accounts" ofType="Account">
        <result column="account_name" property="accountName"></result>
    </collection>
</resultMap>

<select id="findUserAccounts" resultMap="userAccounts">
    select u.id,u.user_name,a.account_name from user u,account a where u.id = #{id} and u.id = a.uid;
</select>
```

---

<a id="_2.2"></a>

**2. 方式二，通过select进行多表查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明(关键参数和优缺点)

- 这里我们说的主表表示resultMap下面对应的第一个映射对象

resultMap参数 | 作用
--- | ---
id | 标识符(用于标识和指定resultMap)
type | 返回值类型(通常是JavaBean)

association参数 | 作用
--- | ---
property | 当前类对应的多表字段的属性名
column | 指定当前主表需要传递的表字段参数名称
javaType | 指定的返回值类型(常常使用ArrayList，并且这个参数可以省略，因为mybatis会帮助我们推断)
ofType | 指定返回值的类型
select | 指定关联的select标签下的方法路径

优点：灵活，可以实现懒加载
缺点：可能配置会比较麻烦，需要多个select标签

1.2 演示示例

```java
// UserMapper.xml 配置
<resultMap id="userAccounts" type="User">
    <collection property="accounts" ofType="Account" javaType="ArrayList" column="id"
                select="com.example.demo.mapper.AccountMapper.findAccountsByUid">

    </collection>
</resultMap>

<select id="findUserAccounts" resultMap="userAccounts">
    select u.id ,u.user_name from user u where u.id = #{id};
</select>

// AccountMapper.xml 配置
<select id="findUserByAccountUid" parameterType="int" resultMap="accountUser">
    select u.user_name,a.uid,a.account_name from account a,user u where a.id = #{id} and a.uid=u.id;
</select>
```

---

<a id="_3"></a>

## `多对多查询(用户通过中间表对应多个角色示例)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_3.1"></a>

**1. 方式一：非select配置方式**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明(关键参数和优缺点)

- 这里我们说的主表表示resultMap下面对应的第一个映射对象

resultMap参数 | 作用
--- | ---
id | 标识符(用于标识和指定resultMap)
type | 返回值类型(通常是JavaBean)

collection参数 | 作用
--- | ---
property | 当前类对应的多表字段的属性名
column | 指定当前主表需要传递的表字段参数名称
javaType | 指定的返回值类型(常常使用ArrayList，并且这个参数可以省略，因为mybatis会帮助我们推断)
ofType | 指定返回值的类型
select | 指定关联的select标签下的方法路径

优点：减少select标签的使用，可以直接在一个配置文件中完成多表操作
缺点：不是很灵活，无法实现懒加载

1.2 演示示例

```java
// 结果
<!-- User(status=null, role=null, id=null, userName=Daming, age=null, email=null, createTime=null, updateTime=null, version=null, deleted=null, password=null, uRole=null, roleList=null, accounts=[Account(id=0, uid=1, accountName=招商银行, money=1000, user=null), Account(id=1, uid=1, accountName=建设银行, money=1000, user=null), Account(id=2, uid=1, accountName=北京银行, money=1000, user=null)]) -->
<resultMap id="userAccounts" type="User">
    <id column="id" property="id"></id>
    <result column="user_name" property="userName"></result>
    <collection property="accounts" ofType="Account">
        <result column="account_name" property="accountName"></result>
    </collection>
</resultMap>

<select id="findUserAccounts" resultMap="userAccounts">
    select u.id,u.user_name,a.account_name from user u,account a where u.id = #{id} and u.id = a.uid;
</select>
```

---

<a id="_3.2"></a>

**2. 方式二，通过select进行多表查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明(关键参数和优缺点)

- 这里我们说的主表表示resultMap下面对应的第一个映射对象

resultMap参数 | 作用
--- | ---
id | 标识符(用于标识和指定resultMap)
type | 返回值类型(通常是JavaBean)

association参数 | 作用
--- | ---
property | 当前类对应的多表字段的属性名
column | 指定当前主表需要传递的表字段参数名称
javaType | 指定的返回值类型(常常使用ArrayList，并且这个参数可以省略，因为mybatis会帮助我们推断)
ofType | 指定返回值的类型
select | 指定关联的select标签下的方法路径

优点：灵活，可以实现懒加载
缺点：可能配置会比较麻烦，需要多个select标签

1.2 演示示例

```java
// 结果
<!-- User(status=null, role=null, id=null, userName=Daming, age=null, email=null, createTime=null, updateTime=null, version=null, deleted=null, password=null, uRole=null, roleList=null, accounts=[Account(id=0, uid=1, accountName=招商银行, money=1000, user=null), Account(id=1, uid=1, accountName=建设银行, money=1000, user=null), Account(id=2, uid=1, accountName=北京银行, money=1000, user=null)]) -->
<resultMap id="userRoles" type="User">
    <id column="id" property="id"></id>
    <result column="user_name" property="userName"></result>
    <collection property="roles" ofType="Role">
        <result column="role_name" property="roleName"></result>
    </collection>
</resultMap>

<select id="findUserRoles" parameterType="int" resultMap="userRoles">
    select u.id,u.user_name,r.role_name from user u,user_role ur,role r where u.id = #{id} and u.id = ur.uid and ur.rid=r.id;
</select>
```


---

<a id="_4"></a>

## `懒加载配置(基于MybatisPlus)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_4.1"></a>

**1. 配置懒加载的配置说明**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 如果通过配置文件配置的话，有下面两个配置

下面这些配置会比较方便，是全局配置

```java
# 这个配置会默认直接让所有可以进行懒加载的<select>映射执行懒加载
#mybatis-plus.configuration.aggressive-lazy-loading=true
# 开启这个会根据需求进行懒加载
#mybatis-plus.configuration.lazy-loading-enabled=true
```

我们一般开启第二个配置

```java
# 开启这个会根据需求进行懒加载
#mybatis-plus.configuration.lazy-loading-enabled=true
```

1.2 可以在标签`<association>``<collection>`里面配置

优点是更加灵活，可以指定需要进行懒加载的标签。但是配置会更加麻烦和增加维护难度。

- 通过指定，fetchType="true"表示开启了懒加载

---

<a id="_4.2"></a>

**2. 懒加载演示一对多关系的查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

- 这里我们演示比较灵活的配置方式(fetchType="lazy")

```java
// UserMapper.xml 的配置
<resultMap id="userRoles" type="User">
    <collection property="roles" ofType="Role" column="id" select="com.example.demo.mapper.RoleMapper.findRoleByRid" fetchType="lazy">

    </collection>
</resultMap>

<select id="findUserRoles" parameterType="int" resultMap="userRoles">
    select u.id,u.user_name from user u where u.id = #{id};
</select>

// RoleMapper.xml 的配置
<select id="findRoleByRid" parameterType="int" resultType="Role">
    select r.role_name from user_role ur,role r where ur.uid = #{id} and ur.rid=r.id;
</select>
```

2.1 情况一：如果我们仅仅需要一张表中的信息，那么我们懒加载就不会去查询第二张表中的信息

```java
// 这里我们仅仅需要User表中的信息，但是其中的角色结果我们并不需要查询出来，于是我们可以看下面的日志
User userRoles = userMapper.findUserRoles(1);
String userName = userRoles.getUserName();
System.out.println(userName);
```

日志

```java
// 我们可以看到这里仅仅对一张表进行了查询，就可以提升我们程序的运行性能
=>  Preparing: select u.id,u.user_name from user u where u.id = ?;
==> Parameters: 1(Integer)
<==    Columns: id, user_name
<==        Row: 1, Daming
<==      Total: 1
 Time：63 ms - ID：com.example.demo.mapper.UserMapper.findUserRoles
Execute SQL：
    select
        u.id,
        u.user_name
    from
        user u
    where
        u.id = 1;

Daming
```

2.2 情况二：当我们需要用到第二张表的数据的时候，懒加载就会将第二张表的数据查询出来**

```java
// 这里我们需要User表和Role表中的信息，懒加载会执行所有语句，于是我们可以看下面的日志
User userRoles = userMapper.findUserRoles(1);
System.out.println(userRoles);
```

```java
// 可以看到下面首先查了第一张表的数据，然后接着查询出来了第二张表的数据
=>  Preparing: select u.id,u.user_name from user u where u.id = ?;
==> Parameters: 1(Integer)
<==    Columns: id, user_name
<==        Row: 1, Daming
<==      Total: 1
 Time：63 ms - ID：com.example.demo.mapper.UserMapper.findUserRoles
Execute SQL：
    select
        u.id,
        u.user_name
    from
        user u
    where
        u.id = 1;

Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4288d98e]
JDBC Connection [HikariProxyConnection@860798122 wrapping com.mysql.jdbc.JDBC4Connection@6b6b3572] will not be managed by Spring
==>  Preparing: select r.role_name from user_role ur,role r where ur.uid = ? and ur.rid=r.id;
==> Parameters: 1(Integer)
<==    Columns: role_name
<==        Row: ROOT
<==        Row: ORDINARY
<==        Row: ADMIN
<==      Total: 3
 Time：1 ms - ID：com.example.demo.mapper.RoleMapper.findRoleByRid
Execute SQL：
    select
        r.role_name
    from
        user_role ur,
        role r
    where
        ur.uid = 1
        and ur.rid=r.id;

User(status=null, role=null, id=null, userName=Daming, age=null, email=null, createTime=null, updateTime=null, version=null, deleted=null, password=null, roles=[Role(id=null, roleName=ROOT, roleDesc=null), Role(id=null, roleName=ORDINARY, roleDesc=null), Role(id=null, roleName=ADMIN, roleDesc=null)], accounts=null)
```

---

<a id="_5"></a>

## `MySQL函数使用`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. 自定义函数示例**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 直接返回Sql语句结果

```java
CREATE DEFINER=`root`@`%` FUNCTION `funName`(`N` VARCHAR(40)) RETURNS varchar(40) CHARSET utf8
BEGIN
RETURN (SELECT id FROM info_dormitory WHERE id=N);
END
```

---

<a id="_5.2"></a>

**2. 定义变量实现函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
CREATE DEFINER=`root`@`%` FUNCTION `funName`(`N` VARCHAR(40),`No` VARCHAR(40)) RETURNS varchar(40) CHARSET utf8
BEGIN
DECLARE x VARCHAR(255) DEFAULT '';
SET x = (SELECT id FROM info_dormitory WHERE id = N AND dormitory_no = No);
RETURN x;
END
```

---

<a id="_5.3"></a>

**3. 执行自己创建的函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
SELECT funName('1','3301');
```

---

<a id="_5.4"></a>

**4. Mybatis中执行函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
<select id="funName" parameterType="java.lang.String" resultType="java.lang.String">
    SELECT funName(#{name});
</select>
```

---

<a id="_5.5"></a>

**5. 业务中常用的函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
（1）IF(expr,v1,v2)

如果expr是TRUE则返回v1，否则返回v2

（2）IFNULL(v1,v2)

如果v1不为NULL，则返回v1，否则返回v2
```

---

<a id="_6"></a>

## `补充`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. Mybatis的in查询，使用xml映射的<foreach>标签**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 测试示例

```java
@Test
public void test04() {
    List<User> userByIdList = userMapper.findUserByIdList(Arrays.asList(1, 2, 3));
    userByIdList.stream().forEach(user -> System.out.println(user));
}
```

1.2 常用的使用方式，这里我们代入的参数是 ArrayList ，然后通过遍历进行逐个取值

```java
<!-- 这里演示的是常用的Sql拼接逻辑 -->
<!--
    select
        u.id,
        u.user_name
    from
        user u
    where
        u.id in (
            1 , 2 , 3
        )
-->
<select id="findUserByIdList" parameterType="ArrayList" resultType="User">
    select u.id,u.user_name from user u where u.id in
    <foreach collection="list" index="i" item="item" open="(" separator="," close=")">
        #{item}
    </foreach>
</select>
```

1.2 基本原理，也就是字符串的拼接，最后成为一个完整的sql语句，我们可以先写好sql语句，然后进行拼接

```java
<!--
    select
        u.id,
        u.user_name
    from
        user u
    where
        u.id = 1
        and u.id = 2
        and u.id = 3 ;
-->
<!-- 其本质就是字符串的拼接，下面的拼接逻辑如：
        open + #{item} + separator + #{item} + close
 -->
<select id="findUserByIdList" parameterType="ArrayList" resultType="User">
    select u.id,u.user_name from user u where u.id =
    <foreach collection="list" index="i" item="item" open="" separator=" and u.id = " close=";">
        #{item}
    </foreach>
</select>
```

---

<a id="_6.2"></a>

**2. case语句使用**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- LeetCode题目链接 https://leetcode-cn.com/problems/exchange-seats/

1.1 case后面不跟上条件判断参数

```java
# 这里需要对只有奇数条数据的时候对最后一条数据进行特殊处理
select 
(
    case
        -- 奇数情况
        when mod(s1.id,2)!=0 and c!=s1.id then id+1
        -- 奇数情况&&总数据条数为奇数
        when mod(s1.id,2)!=0 and c=s1.id then id
        -- 偶数情况
        when mod(s1.id,2)=0 then id-1
        else id-1
    end
) id
,
student 
 from seat s1,(select count(*) c from seat) s2 order by id;
```

1.2 case后面跟上条件判断参数

```java
UPDATE salary
SET
    sex = CASE sex
        WHEN 'm' THEN 'f'
        ELSE 'm'
    END;
```

---

<a id="_6.3"></a>

**3. 多表联查**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

实际原理和Java的for循环类似

---

<a id="_6.4"></a>

**4. case和if使用场景**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 作为返回数据字段的特殊处理


