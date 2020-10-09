# MySQL数据库语法和常用工具笔记

---

<a id="_top"></a>

## `目录:`
### <a href="#_1" rel="nofollow" target="_self">数据库语法 DDL 数据库和表操作</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">数据库操作</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">表操作</a>
### <a href="#_2" rel="nofollow" target="_self">数据库语法 DML 表的增删改</a>
### <a href="#_3" rel="nofollow" target="_self">数据库语法 DML 查询操作(非常重要)</a>
3.1 <a href="#_3.1" rel="nofollow" target="_self">完整语法命令</a>
3.2 <a href="#_3.2" rel="nofollow" target="_self">补充</a>
### <a href="#_4" rel="nofollow" target="_self">数据库语法 DCL 管理员操作</a>
### <a href="#_5" rel="nofollow" target="_self">数据库语法 约束</a>
5.1 <a href="#_5.1" rel="nofollow" target="_self">非空约束</a>
5.2 <a href="#_5.2" rel="nofollow" target="_self">唯一约束</a>
5.3 <a href="#_5.3" rel="nofollow" target="_self">主键约束</a>
5.4 <a href="#_5.4" rel="nofollow" target="_self">外键约束</a>
### <a href="#_6" rel="nofollow" target="_self">数据库语法 多表查询</a>
6.1 <a href="#_6.1" rel="nofollow" target="_self">内连接查询</a>
6.2 <a href="#_6.2" rel="nofollow" target="_self">外连接查询</a>
6.3 <a href="#_6.3" rel="nofollow" target="_self">子查询</a>
### <a href="#_11" rel="nofollow" target="_self">数据库语法 事务</a>
11.1 <a href="#_11.1" rel="nofollow" target="_self">事务的隔离级别</a>
11.2 <a href="#_11.2" rel="nofollow" target="_self">事务的设置</a>
### <a href="#_7" rel="nofollow" target="_self">JDBC 数据库对接Java程序</a>
7.1 <a href="#_7.1" rel="nofollow" target="_self">驱动的注册</a>
7.2 <a href="#_7.2" rel="nofollow" target="_self">Connection</a>
7.3 <a href="#_7.3" rel="nofollow" target="_self">Statement 不常用，容易出现SQL注入问题</a>
7.4 <a href="#_7.4" rel="nofollow" target="_self">PreparedStatement</a>
7.5 <a href="#_7.5" rel="nofollow" target="_self">ResultSet 结果集的获取</a>
### <a href="#_8" rel="nofollow" target="_self">其他工具包的补充</a>
8.1 <a href="#_8.1" rel="nofollow" target="_self">C3P0连接池</a>
8.2 <a href="#_8.2" rel="nofollow" target="_self">Druid连接池</a>
8.3 <a href="#_8.3" rel="nofollow" target="_self">工具类JDBCTemplate数据库连接框架</a>
### <a href="#_9" rel="nofollow" target="_self">数据库范式补充</a>

<a id="_1"></a>

## `数据库语法 DDL 数据库和表操作`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. 数据库操作**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
create database if not exists 数据库名 character set 字符编码格式;
drop database if exists 数据库名;
show databses;
select database();
show create database 数据库名;
alter database 数据库名 character set 新字符编码格式;
```

---

<a id="_1.2"></a>

**2. 表操作**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
create table 表名(字段名 字段类型,...);
create table 新表名 like 目标表名;
drop table 表名；
show tables;
desc 表名;
alter table 表名 rename to 新表名;
alter table 表名 字段名 character set 新字段名;
alter table 表名 add 字段名 字段数据类型;
alter table 表名 modify 字段名 字段新数据类型;
alter table 表名 change 字段名 新字段名 字段新数据类型;
alter table 表名 drop 字段名;
```

---

<a id="_2"></a>

## `数据库语法 DML 表的增删改`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
insert into [表名(字段名...)] values(字段值);
delete from 表名 [where 条件];
truncate tabale 表名;
update 表名 set 字段名 = 字段值... [where 条件];
```

---

<a id="_3"></a>

## `数据库语法 DML 查询操作(非常重要)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_3.1"></a>

**1. 完整语法命令**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
select 字段...     distinct、as、空格、ifnull(字段名，字段值)、算术运算符、聚合函数
from 表名...       as、空格
where 条件...      and、between and、or、in（值...）、is（null）、isnot（null）、比较运算符、逻辑运算符
order by 字段 asc...    asc、desc
group by 字段... [having 同where条件，额外可添加聚合函数]
limit 开始索引 信息条数
```

---

<a id="_3.2"></a>

**2. 补充**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

- a.排序查询
格式：order by 字段名 排序方式，字段名 排序方式... asc升序 desc降序
- b.分组查询
格式：group by 字段名 having 条件判断  //比如说性别字段就可以自动被分为两个组
注意点：1.group by后边的字段通常只写一个；
2.group by后边跟的是分组字段或者聚合函数；
3.where和having两个条件判断关键字的区别，where后边不能写聚合函数，where条件
先行判断进行过滤；having后边可以跟聚合函数，并且having是在分组后对每个分组
进行条件过滤。
理解：分组查询的话通常是对某个字段进行分组，通常分组后显示的数据信息是关于每一个组的
信息进行整合，处理，通常是需要通过配合聚合函数来使用。
- c.分页查询
格式：limit 当前页开始的索引，当前页的信息数
公式：当前页的起始索引号=（当前页码-1）*每一页的信息条数   前提：每一页的信息数是固定的
理解：索引是从0开始的，而页码是从1开始的，这样的话我们就可以写公式。
- d.聚合函数
1.count() 注意：1.不会将字段值为null的字段记入总数；2.为了避免注意点1的情况
发生，我们通常选择没有null值的字段；或者我们可以用ifnull（）
函数判断。
2.min() max() avg() sum()

---

<a id="_4"></a>

## `数据库语法 DCL 管理员操作`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
create user ''@'' identified by '';
drop user ''@'';
update user set password=password('') where user='';
set password for ''@''=password('');
show grants for ''@'';
grant 权限列表 on 数据库.表名 to ''@'';  可以使用通配符，权限列表字段可以使用all关键字代表所有
revoke 权限列表 on 数据库名.表名 from ''@'';
```

---

<a id="_5"></a>

## `数据库语法 约束`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. 非空约束**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
not null
alter table 表名 modify 字段名 数据类型 [not null]   通过modify添加或去除非空约束
alter table 表名 drop 字段名 [not null];
alter table 表名 add 字段名 数据类型 [not null]
```

简单示例

```java
create table stu(name varchar(8) not null);-- 方式一：定义时创建非空约束字段
create table stu(name varchar(8),age int);
ALTER TABLE stu MODIFY NAME VARCHAR(8) NOT NULL;-- 方式二：字段中没有null时可以使用
ALTER TABLE stu MODIFY NAME VARCHAR(8);-- 可以改变非空修饰符
alter table stu drop name;-- 方式三：删除字段后再创建该字段
alter table stu add name varchar(8) not null;-- 方式三
```

---

<a id="_5.2"></a>

**2. 唯一约束**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
unique
alter table 表名 modify 字段名 数据类型  [unique]   通过modify可以添加唯一约束，但是不可以删除
alter table 表名 drop 字段名 unique;    删除含有唯一约束的字段必须在后面添加修饰符
alter table 表名 add 字段名 数据类型 [unique]
```

简单示例

```java
create table stu(name varchar(8) unique);-- 方式一：定义时创建唯一约束字段
create table stu(name varchar(8),age int);
ALTER TABLE stu MODIFY NAME VARCHAR(8) unique;-- 方式二：字段中没有相同字段时可以使用
ALTER TABLE stu MODIFY NAME VARCHAR(8); -- modify改变不了被唯一约束修饰的字段
alter table stu drop name;-- 方式三：删除字段后再创建该字段
alter table stu add name varchar(8) unique;-- 方式三
```

---

<a id="_5.3"></a>

**3. 主键约束**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
primary key
alter table 表名 modify 字段名 数据类型  [primary key]   通过modify可以添加主键约束，但是不可以删除
alter table 表名 drop 字段名 [primary key];    删除含有唯一约束的字段必须在后面添加修饰符
alter table 表名 add 字段名 数据类型 [primary key]
auto_increment
alter table 表名 modify 字段名 数据类型  [primary key] [auto_increment]  通过modify可以添加或者删除自动增长属性，可以单独写自动增长属性
alter table 表名 drop 字段名 [primary key];
alter table 表名 add 字段名 数据类型 [primary key] [auto_increment];
```

简单示例

```java
- a.特性：一张表只能有一个字段被主键约束修饰；
并且modify改变不了被主键修饰的字段。
由于主键在一张表中是唯一的，所以主键的删除操作可以简化：alter table 表名 drop primary key;
- b.三种约束字段创建方式：同非空约束。
- c.示例：
create table stu(name varchar(8) primary key);-- 方式一：定义时创建主键约束字段
create table stu(name varchar(8),age int);
ALTER TABLE stu MODIFY NAME VARCHAR(8) primary key;-- 方式二：字段中没有相同且非空字段值时可以使用
ALTER TABLE stu MODIFY NAME VARCHAR(8); -- modify改变不了被主键约束修饰的字段
alter table stu drop name;-- 方式三：删除字段后再创建该字段
alter table stu add name varchar(8) primary key;-- 方式三
```

主键约束中设置自动增加

```java
- a.特性：一般情况下和主键配合使用；可以修饰数值字段，赋予相关字段null值时会自动一上一行数据为基准加1；
- b.示例：
create table stu(name varchar(8),age int);-- 方式一：定义时创建主键约束的自动增长字段
create table stu(name varchar(8),age int  PRIMARY KEY auto_increment);
ALTER TABLE stu MODIFY age int auto_increment ;-- 方式二：可以通过modify改变自动增长属性
ALTER TABLE stu MODIFY age int; --
alter table stu drop age;-- 方式三：删除字段后再创建该字段
alter table stu add age int primary key auto_increment;-- 方式三
```

---

<a id="_5.4"></a>

**4. 外键约束**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
[constraint 外键名] foreign key(外键字段名) references 主表名(主表关联外键字段名)
alter table 表名 drop foreign key(外键名);     这里的删除外键操作还是比较特别的，需要额外记忆
alter table 表名 add [constraint 外键名] foreign key(外键字段名) 主表名（主表关联外键字段名）;      创建外键也是有简写形式
```

简单示例

```java
- a.格式：constraint 外键名称 foreign key (外键列名) references 主表名称(主表列名称)      外键
格式说明：主表：是指处理冗余数据的那个表，也就是内容比较少的那张表，也是外键需要去关联的那张表；
外键名称：是指你这一列的字段名之外的额外名称，可以任意取名字，但是不要重名即可；作用主要在于删除时需要使用外键名而不是外键列名；
外键列名：其实就是字段名，就是外键的一个名称，但是我们要分清楚和外键名称的区别。
- b.注意点：1.外键表需要优先于与之关联的表，否则与之关联的表搜索不到需要关联的外键表；
2.与外键表关联的表需要关联的外键表的字段通常是含有唯一约束或者主键约束的。
3.外键表与与之关联的表环环相扣；
- c.添加删除外键示例：
-- 删除外键:外键的添加删删除操作都是比较特殊的  reference 参考
alter table employee drop foreign key emp_depid_fk;
alter table employee drop dep_id;-- 不能通过直接删除含有外键的列字段，必须要先删除外键才可以删除这个字段
-- 添加外键：这里说明一下，如果需要额外添加一列外键字段，那么需要先创建这个字段，再添加外键
alter table employee add [constraint emp_depid_fk] foreign key (dep_id) references department(id);
- d.额外内容：级联操作
- e.级联意义：可以在外键和主表的一方的数据发生改变时，另一方的表的数据作出相应的改变。
- f.定义：在表定义时在后面添加 级联更新—on update cascade  级联删除—on delete cascade    cascade 级联
```

---

<a id="_6"></a>

## `数据库语法 多表查询`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. 内连接查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 显示内连接

```java
select ... from ... where 条件
```

1.2 隐式内连接

```java
select ... from 左表 [inner] join 右表 on 条件;
```

---

<a id="_6.2"></a>

**2. 外连接查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 左外连接，右外连接类似

```java
left right
select ... from 左表 left [outer] join 右表 on 条件;
```

---

<a id="_6.3"></a>

**3. 子查询**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
- a.单行单列  简单来说就是子句中的结果集中只有一个元素
select ... from ... where [select语句] 条件
- b.单行多列  简单来说就是子句的结果集中就是一个一维数组
select ... from ... where [select语句] in(值...)
- c.多行多列 简单来说就是子句的结果集中就是一个二维数组
select ... from ...[select语句] where 条件
```

---

<a id="_11"></a>

## `数据库语法 事务`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_11.1"></a>

**1. 事务的隔离级别**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
read uncommitted
read committed
repeatable read
serializable
```

---

<a id="_11.2"></a>

**2. 事务的设置**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
select @@tx_isolation;
set global transaction isolation level 隔离级别字段;
```

---

<a id="_7"></a>

## `JDBC 数据库对接Java程序`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_7.1"></a>

**1. 驱动的注册**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 基本驱动注册和说明

说明：Java DataBase Connectivity  Java数据库连接，Java语言操作数据库

```java
Class.forName("com.mysql.jdbc.Driver");
DriverManager.getConnection(jdbc:mysql//ip地址(域名):端口号/数据库名);
```

1.2 其中常用的对象

```java
DriverManager对象：驱动管理对象
Connection：数据库连接对象
Statement：执行sql对象（使用静态的SQL）
ResultSet：结果集对象
PreparedStatemet：执行sql的对象(预编译的SQL)
```

---

<a id="_7.2"></a>

**2. Connection**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
- a.功能：1.获取sql对象 2.管理事务
- b.方法：
Statement creteStatement()
PreparedStatement prepareStatement(String sql)
void setAutoCommit(boolean autoCommit) false就是开启事务
void commit()
void rollback()
```

---

<a id="_7.3"></a>

**3. Statement 不常用，容易出现SQL注入问题**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
boolean execute(String sql) ：可以执行任意的sql 了解
int executeUpdate(String sql) ：执行DML（insert、update、delete）语句、DDL(create，alter、drop)语句
返回值：影响的行数，可以通过这个影响的行数判断DML语句是否执行成功 返回值>0的则执行成功，反之，则失败。
ResultSet executeQuery(String sql)  ：执行DQL（select)语句
返回值：结果集对象
```

---

<a id="_7.4"></a>

**4. PreparedStatement**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
- a.SQL注入问题：在拼接sql时，有一些sql的特殊关键字参与字符串的拼接。会造成安全性问题
1. 输入用户随便，输入密码：a' or 'a' = 'a
2. sql：select * from user where username = 'fhdsjkf' and password = 'a' or 'a' = 'a'
- b.设置通配符 ？ 的参数     参数1是？的位置 || 参数2是需要对？设置的值
void setXxx(int station,参数2)
- c.执行DQL的方法同时获取ResultSet对象
ResultSet executeQuery()
说明：执行sql查询操作时，接受返回结果，不需要传递sql语句 //也就是在创建ResultSet对象时不需要进行传递参数，而Statement对象创建时需要在方法中传递参数；这是因为在创建prepareStatement对象时已经将SQL语句传递进来了
```

说明：
- 预编译SQL:类似于PrepareStatement对象，字符串没有拼接好之前就把字符串传递到MySQL数据库中，之后再传递参数。
- 静态SQL:类似于Statement对象穿的sql语句时的动作，字符串在java程序中事先拼接好在传递到数据库中去执行，会引发很多问题。

---

<a id="_7.5"></a>

**5. ResultSet 结果集的获取**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
* boolean next(): 游标向下移动一行，判断当前行是否是最后一行末尾(是否有数据)，如果是，则返回false，如果不是则返回true
* getXxx(参数):获取数据
* Xxx：代表数据类型   如： int getInt() ,    String getString()
* 参数：
1. int：代表列的编号,从1开始   如： getString(1)
2. String：代表列名称。 如： getDouble("balance")
```

---

<a id="_7.6"></a>

**6. 链接工具类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/*
由于每次对mysql进行操作时都会对一部分繁琐的代码进行操作
    所以特地创建这个工具类，方便了对数据操作
      1.安装驱动
      2.建立和数据库的连接 DriverManager
      3.关闭相关资源  Statement Connection ResultSet
 */
public class JDBCUtil {
    //配置文件的内容，一是数据库连接字段；二是驱动安装字段；
    public static String url,user,password;
    public static String driver;

    static {
        try {
            //第一步获取配置文件信息
            Properties pro = new Properties();
            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
            //这里需自己新建配置文件，后缀是.properties
            URL res = classLoader.getResource("JDBCpeizhi.properties");
            String path = res.getPath();
            System.out.println(path);
            pro.load(new FileReader(path));
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //第二步：安装驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//获取数据库连接，由配置文件决定
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
//关闭数据库相关资源，有一个是方法的重载
    public static void close(Statement stm, Connection con) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stm, Connection con, ResultSet rs) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

---

<a id="_8"></a>

## `其他工具包的补充`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_8.1"></a>

**1. C3P0连接池**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
DataSource ComboPoolDataSource();
DataSource ComboPoolDataSource(Sring 配置文件内部设置种类名);
Connection getConnection();  //DataSorce对象调用
void close();//Connection对象调用
```

---

<a id="_8.2"></a>

**2. Druid连接池**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
DataSource DruidDataSourceFactory.createDataSource(pro);  //参数是一个双列集合
Connection getConnection();
void close();
```

---

<a id="_8.3"></a>

**3. 工具类JDBCTemplate数据库连接框架**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
Template template = new JdbcTemplate(ds);  //这里的参数是连接池对象
update();       //返回值是int型，适用于Statement和PrepareStatement对象的语句
queryForMap();  //这个方法只能封装一个结果集，也就是一行数据
queryForList(); //封装进去的Map对象
query();        //用于封装一个对象到List集合中，有两种方式：1.自己定义封装方式使用 RowMapper重写其中抽象方法mapRow(ResultSet rs, int i) 2.使用已经写好的实现类 BeanPropertyRowMapper<emp>(emp.class)，注意哦这个方法使用前需要先把你要分装的对象的成员变量装箱，否则一些基本类型的变量无法接收null值，那么就会报错。
queryForObject()//查询聚合函数
```

---

<a id="_9"></a>

## `数据库范式补充`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
- a.范式概述：
1. 第一范式（1NF）：每一列都是不可分割的原子数据项
2. 第二范式（2NF）：在1NF的基础上，非码属性必须完全依赖于码（在1NF基础上消除非主属性对主码的部分函数依赖）
* 几个概念：
1. 函数依赖：A-->B,如果通过A属性(属性组)的值，可以确定唯一B属性的值。则称B依赖于A
例如：学号-->姓名。  （学号，课程名称） --分数
2. 完全函数依赖：A-->B， 如果A是一个属性组，则B属性值得确定需要依赖于A属性组中所有的属性值。
例如：（学号，课程名称） --分数
3. 部分函数依赖：A-->B， 如果A是一个属性组，则B属性值得确定只需要依赖于A属性组中某一些值即可。
例如：（学号，课程名称） -- 姓名
4. 传递函数依赖：A-->B, B -- >C . 如果通过A属性(属性组)的值，可以确定唯一B属性的值，在通过B属性（属性组）的值可以确定唯一C属性的值，则称 C 传递函数依赖于A
例如：学号-->系名，系名-->系主任
5. 码：如果在一张表中，一个属性或属性组，被其他所有属性所完全依赖，则称这个属性(属性组)为该表的码
例如：该表中码为：（学号，课程名称）
* 主属性：码属性组中的所有属性
* 非主属性：除过码属性组的属性

3. 第三范式（3NF）：在2NF基础上，任何非主属性不依赖于其它非主属性（在2NF基础上消除传递依赖）
4.表格优化为第三范式可以解决的3大问题：
1.存在非常严重的数据冗余；
2.数据添加存在问题；
3.数据删除存在问题；
- b.这里我写一下我归纳的步骤：第一范式->第二范式->第三范式
1.我们找出码和非码（码是标志度比较高德字段）
2.我们将码字段的所有组合情况列出来，遍历这些组合找出每一个组合的完全依赖部分，把每一个完全依赖部分单独拉出来
单独构成一张表。
3.将我们单独拿出来的表的数据冗余部分删除掉。    第一范式->第二范式  完成
4.遍历第二范式的每一张表，筛选出其中非码字段数量大于等于2的表进行处理，找出其中含有传递函数依赖的部分。
5.对含有传递函数依赖的部分我们进行拆分，将传递部分的大头（可以理解为传递函数部分的2级码字段）保留在原先第二
范式的表格中，然后将大头以及其从属部分拆分为另外一张表，重复类似于步骤5的操作。  第二范式->第三范式  完成
```
