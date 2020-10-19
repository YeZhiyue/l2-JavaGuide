
---

# DDL 数据库、表属性

## 操作数据库：CRUD

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. Create：创建

创建数据库的完整流程： create database if not exists db4 character set gbk;
    
2. Retrieve：查询

查询所有数据库的名称：show databases；
查询某个数据库的字符集：show create database 数据库名；

3. Update：修改

修改数据库的字符集：alter database 数据库名称 character set 字符集类型（编码类型）

4. Delete：删除

删除数据库：drop database if exists 数据库名称；
    
5. 使用数据库

使用数据库：use 数据库名称；
查询当前正在使用的数据库名称：select database();

## 操作表

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1. Create：创建表

复制表的操作：     create table 目标表名 like 源头表名;  
示例：create table stu like student;

创建表：注意最后一列是不用加逗号的
        create table 表名(
            列名1 数据类型1,
            列名2 数据类型2,
            列名3 数据类型3,
            ...
            列名4 数据类型4
            );

数据类型（常用）：
        int：整数类型  示例：age int，
        double：小数类型(参数一：小数总共最多有五位 参数二：小数点后保留 两位)  示例：score double（5,2） 表示这个小数最大是999.99
        date(int a,int b)：日期，只包含年月日，yyyy-MM-dd
        datetime:日期时间：yyyy-MM-dd HH:mm:ss
        timestamp:时间错类型 格式同datetime 注意：这个数据类型在没有被赋值时，自动赋值系统时间。
        varchar(int a):字符串（参数：指定最大的字符） 示例：name varchar(6) 指定名字最长为三个字符

2. Retrieve：查询表

show tables;

desc 表名；

3. Update：修改

修改表名：          alter table 表名 rename    to 新的表名；
修改表数据的字符集：alter table 表名 character set 字符集名称;
添加一列：          alter table 表名 add       列名 数据类型;
修改列名称|数据类型:alter table 表名 change    列名 新列名 新数据类型;
                    alter table 表名 modify    列名 新数据类型；
删除列：            alter table 表名 drop      列名；

4. Delete：删除

删除一个表：        drop  table if exists 表名；

---

# DML 表的CRUD

1. 添加数据：

添加数据：insert into 表名(列名1，列名2...) values(值1，值2...)
     示例：INSERT INTO per2 VALUES(1,2,3),(2,2,2),(NULL,NULL,NULL);
注意：
      1.列名要和值的数量对应（列名数量>=值的数量）;
      2.要是要对表中所有值进行赋值，那么可以不写列名;
      3.不确定要赋值什么的话可以赋值为null;
      4.除了数字类型的值，其他都要用单引号或者双引号引起来；（日期示例：‘2017-12-28’）
      
2. 删除数据：

删除数据：delete from 表名[where 条件]
注意：1.不加条件时，会删除所有表中的数据，但是不推荐使用，因为有多少条记录就会执行多少次操作；
      2.如果要删除所有记录（推荐使用）：truncate table 表名  这个是先删除表，然后创建空表。
      
3. 修改数据：

修改数据：UPDATE 表名 SET 列名1=值1,列名2=值2... WHERE 条件;

---

# DQL 表查询


1. 完整语法

select -- 字段列表（列名称）
from  -- 表名列表
where -- 条件列表(对值进行筛选)
group by -- 分组字段（通过列名称对应的值进行分组）
having -- 分组之后条件
order by -- 排序
limit -- 分页限定

2. select字段

distinct name; 去除name字段中重复的结果
name N2;重命名 字段name为N2
ifnull(name,'空') :如果那么字段为空，那么就替换这个字段的内容为空
聚合函数

3. where字段

and、or、is、is not(null)、between and
like % _ （%表示任意长度，_表示一位）

4. order by字段

order by 字段名 排序方式，字段名 排序方式. . .  asc升序 desc降序

5. group by

group by 字段、聚合函数 having 条件(可以是聚合函数,是分组后过滤) 

6. limit

limit 当前页开始的索引，当前页的信息数
公式：当前页的起始索引号=（当前页码-1）*每一页的信息条数   前提：每一页的信息数是固定的

7. 聚合函数

count() 注意：1. 不会将字段值为null的字段记入总数；2. 为了避免注意点1的情况
                发生，我们通常选择没有null值的字段；或者我们可以用ifnull（）
                函数判断。

min() max() avg() sum()

---

# DCL 权限管理

1. 添加用户：在mysql数据库中的user表中可以看到用户

格式：create user '用户名'@'主机名' identified by '密码';

示例：注意的是就是主机名可以使用%占位符作为所有主机都可以登录；而localhost是指只有本机才可以登录指定数据库；
create user 'yezhiyue'@'%' identified by '4321';
create user 'user1'@'localhost' identified by '4321';

2. 删除用户：

格式：
drop user '用户名'@'主机名';
示例：
drop user 'user1'@'localhost';

3. 修改用户密码：

格式：
update user set password = password('新密码') where user='用户名';
set password for '用户名'@'主机名'=password('新密码');
示例：
update user set password = password('user1') where user='4321';
set password for 'user1'@'localhost'=password('4321');

4. 查询用户：（权限列表，也就是类似于权限 1, 权限 2. . . 的形式，权限关键字 CREATE、ALTER、SELECT、INSERT、UPDATE等）

- 查询权限  （grant 授予）

SHOW GRANTS FOR '用户名'@'主机名';
SHOW GRANTS FOR 'user'@'%';

-  授予权限：（可以使用通配符*）
    - 授予权限 grant 权限列表 on 数据库名. 表名 to '用户名'@'主机名';
    - 给张三用户授予所有权限，在任意数据库任意表上 GRANT ALL ON *. * TO 'zhangsan'@'localhost';
    
3.  撤销权限：（可以使用通配符*）

- 撤销权限：

revoke 权限列表 on 数据库名. 表名 from '用户名'@'主机名';
REVOKE UPDATE ON db3. `account` FROM 'lisi'@'%';

---

# 约束

## 非空约束

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![](http://img.yhzcmxq.cn/picgo/20201019150304.png)

create table stu(name varchar(8) not null);-- 方式一：定义时创建非空约束字段
create table stu(name varchar(8),age int);
ALTER TABLE stu MODIFY NAME VARCHAR(8) NOT NULL;-- 方式二：字段中没有null时可以使用
ALTER TABLE stu MODIFY NAME VARCHAR(8);-- 可以改变非空修饰符
alter table stu drop name;-- 方式三：删除字段后再创建该字段
alter table stu add name varchar(8) not null;-- 方式三

## 唯一约束

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![](http://img.yhzcmxq.cn/picgo/20201019150659.png)

create table stu(name varchar(8) unique);-- 方式一：定义时创建唯一约束字段
create table stu(name varchar(8),age int);
ALTER TABLE stu MODIFY NAME VARCHAR(8) unique;-- 方式二：字段中没有相同字段时可以使用
ALTER TABLE stu MODIFY NAME VARCHAR(8); -- modify改变不了被唯一约束修饰的字段
alter table stu drop name;-- 方式三：删除字段后再创建该字段
alter table stu add name varchar(8) unique;-- 方式三

## 主键约束

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

create table stu(name varchar(8) primary key);-- 方式一：定义时创建主键约束字段
create table stu(name varchar(8),age int);
ALTER TABLE stu MODIFY NAME VARCHAR(8) primary key;-- 方式二：字段中没有相同且非空字段值时可以使用
ALTER TABLE stu MODIFY NAME VARCHAR(8); -- modify改变不了被主键约束修饰的字段
alter table stu drop name;-- 方式三：删除字段后再创建该字段
alter table stu add name varchar(8) primary key;-- 方式三

### 主键约束中设置字段自增

create table stu(name varchar(8),age int);-- 方式一：定义时创建主键约束的自动增长字段
create table stu(name varchar(8),age int  PRIMARY KEY auto_increment);
ALTER TABLE stu MODIFY age int auto_increment ;-- 方式二：可以通过modify改变自动增长属性
ALTER TABLE stu MODIFY age int; --
alter table stu drop age;-- 方式三：删除字段后再创建该字段
alter table stu add age int primary key auto_increment;-- 方式三

## 外键 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![](http://img.yhzcmxq.cn/picgo/20201019150826.png)

格式：constraint 外键名称 foreign key (外键列名) references 主表名称(主表列名称)    
    constraint KEY_WAI foreign key (id) references dep(id)

添加删除外键示例：
    -- 删除外键:外键的添加删删除操作都是比较特殊的  
    alter table employee drop foreign key emp_depid_fk;
    alter table employee drop dep_id;-- 不能通过直接删除含有外键的列字段，必须要先删除外键才可以删除这个字段
    -- 添加外键：这里说明一下，如果需要额外添加一列外键字段，那么需要先创建这个字段，再添加外键
    alter table employee add [constraint emp_depid_fk] foreign key (dep_id) references department(id);

---

# 多表查询


## 内连接查询

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

内连接查询（在on字段后面的条件筛查会自动忽略null字段，并且比较条件的不同，两边的表的行数也是不确定的，会变化的，这是与外连接的一个不同点；）
	后面总结：显示的信息的条数和表的位置无关，和查询的条件有关（因为其会忽略null的特性）
        a.隐式内连接：SELECT 字段名 FROM 左表, 右表 WHERE 条件
        b.显式内连接：
            语法：SELECT 字段名 FROM 左表  JOIN 右表 ON 条件
            
## 外连接查询

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

外连接查询（可以对null值的字段进行查询，并且有一边的表的所有行的数据必须全部被显示出来）
	后面总结：显示的条数和表的位置相关
        a.左外连接
            语法：select 字段列表 from 表1 left [outer] join 表2 on条件；
            意义：查询的是左表中所有的数据以及和另外一张表的交集部分。
        b.右外连接
            语法：select 字段列表 from 表1 right [outer] join 表2 on条件；
            意义：同左表查询。

## 子查询

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

子查询 查询比较复杂。
        a.概念：查询中嵌套查询，称嵌套查询为子查询。
        b.子查询的不同种情况
            1.子查询的结果是单行单列的：（都是通过一张表来获取一个值来比较，聚合函数的使用比较典型）
                 子查询可以作为判断条件，使用运算符去判断。运算符：< > =
            2.子查询的结果是多行单列的：（通过一张表来获取一个列的多个值，然后比较，这里的in的用法和查询语句返回的值比较特殊，需要注意观察）
                子查询可以作为条件，使用运算符in来判断。
            3.子查询的结果是多行多列的：（实质上就是两张表之间的比较）
                子查询可以作为一张虚拟表参与查询（其实就是作为另外一张表，放在from关键字之后）
        c.示例：
            -- ----------------------------子查询 单行单列---------------------------
            -- 1) 查询最高工资是多少
            select max(salary) from emp;
            -- 2) 根据最高工资到员工表查询到对应的员工信息
            select * from emp where salary = (select max(salary) from emp);
            -- 1) 查询平均工资是多少
            select avg(salary) from emp;
            -- 2) 到员工表查询小于平均的员工信息
            select * from emp where salary < (select avg(salary) from emp);
            -- ----------------------------子查询 多行单列---------------------------
            -- 先查询大于 5000 的员工所在的部门 id
            select dept_id from emp where salary > 5000;
            -- 再查询在这些部门 id 中部门的名字 Subquery returns more than 1 row
            select name from dept where id = (select dept_id from emp where salary > 5000);
            select name from dept where id in (select dept_id from emp where salary > 5000);
            -- 先查询开发部与财务部的 id
            select id from dept where name in('开发部','财务部');
            -- 再查询在这些部门 id 中有哪些员工
            select * from emp where dept_id in (select id from dept where name in('开发部','财务部'));
            -- ----------------------------子查询 多行多列---------------------------
            -- 查询出 2011 年以后入职的员工信息，包括部门名称
            -- 在员工表中查询 2011-1-1 以后入职的员工
            select * from emp where join_date >='2011-1-1';
            -- 查询所有的部门信息，与上面的虚拟表中的信息组合，找出所有部门 id 等于的 dept_id
            select * from dept d, (select * from emp where join_date >='2011-1-1') e where d.`id`= e.dept_id ;
            select * from emp inner join dept on emp.`dept_id` = dept.`id` where join_date >='2011-1-1';
            select * from emp inner join dept on emp.`dept_id` = dept.`id` and join_date >='2011-1-1';
   
