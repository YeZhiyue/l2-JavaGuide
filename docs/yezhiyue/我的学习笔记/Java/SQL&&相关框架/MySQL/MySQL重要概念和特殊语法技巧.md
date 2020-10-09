## MySQL动物书个人学习要点摘记

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>重要概念</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.1" rel="nofollow" target="_self">SQL是一种非过程化的语言</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.2" rel="nofollow" target="_self">MySQL对字符的大小写不敏感</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.3" rel="nofollow" target="_self">分组group by中的having和where结合使用时的注意点</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.4" rel="nofollow" target="_self">聚合函数对null值是直接忽略的</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.5" rel="nofollow" target="_self">锁的粒度</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.6" rel="nofollow" target="_self">MySQL中不同引擎的对事务的不同处理方式</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.7" rel="nofollow" target="_self">MySQL中使用的自动提交事务设置</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.8" rel="nofollow" target="_self">MySQL会自动位主键创建索引</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.9" rel="nofollow" target="_self">视图的作用</a>
### <a href="#_2" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SQL的一些特殊语法技巧</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.1" rel="nofollow" target="_self">order by 根据表达式或者数字排序</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.2" rel="nofollow" target="_self">过滤条件之字符串范围</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.3" rel="nofollow" target="_self">聚合函数中使用表达式</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.4" rel="nofollow" target="_self">根据表达式进行group by 分组操作</a>
### <a href="#_3" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SQL的并集操作</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.1" rel="nofollow" target="_self">union并集操作</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.2" rel="nofollow" target="_self">intersect 交集操作</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.3" rel="nofollow" target="_self">except 差操作</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.4" rel="nofollow" target="_self">集合的复合操作</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.5" rel="nofollow" target="_self">集合操作的注意点</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.6" rel="nofollow" target="_self">集合操作符使用示例</a>
### <a href="#_4" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>SQL函数</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.1" rel="nofollow" target="_self">字符串函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.2" rel="nofollow" target="_self">数值函数</a>
### <a href="#_5" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>时间处理函数</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.1" rel="nofollow" target="_self">时间处理问题</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.2" rel="nofollow" target="_self">字符串转换为日期函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.3" rel="nofollow" target="_self">直接获取当前日期时间的函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.4" rel="nofollow" target="_self">返回日期的时间函数</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.5" rel="nofollow" target="_self">提取日期某些时间点</a>
### <a href="#_6" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>MySQL索引</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.1" rel="nofollow" target="_self">创建索引的顺序</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.2" rel="nofollow" target="_self">索引类型</a>


---

<a id="_1"></a>

## `重要概念`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_1.1"></a>

**1. SQL是一种非过程化的语言**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 过程性语言

过程性语言习惯定义变量或者数据结果、使用条件逻辑和循环结构将程序代码分成可复用的小片段(如对象、函数、过程)，这样子我们就可以控制程序的行为

1.2 SQL非过程性语言

SQL放弃了对过程的控制，因为SQL的使用只需要必要的输入和输出，执行语句的方式交由数据库引擎的优化器。其实就是把执行语句的过程控制权交给了数据库引擎的优化器。

---

<a id="_1.2"></a>

**2. MySQL对字符的大小写不敏感**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 大小写不敏感导致我们在SQL中的一些字符串比较函数的使用会受到影响，所以我们需要特别注意这一点

---

<a id="_1.3"></a>

**3. 分组group by中的having和where结合使用时的注意点**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 基本说明

where语句是在分组之前对数据集进行过滤，而having是在where语句过滤之后对分组的数据进行操作

---

<a id="_1.4"></a>

**4. 聚合函数对null值是直接忽略的**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_1.5"></a>

**5. 锁的粒度**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- 表锁，组织多个用户同时修改同一个表中的数据
- 页锁，阻止多个用户同时修改表中的一页(一页通常是一段2~16kb的内存空间)的数据
- 行锁，阻止多个用户同时修改表中的一行数据

---

<a id="_1.6"></a>

**6. MySQL中不同引擎的对事务的不同处理方式**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- MyISAM 表级锁定的非事务引擎
- MEMORY 内存表使用的非事务引擎
- InnoDB 采用行级锁的事务引擎

---

<a id="_1.7"></a>

**7. MySQL中使用的自动提交事务设置**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

注意，MySQL默认对SQL是自动提交的。我们可能很容易遗忘这个特点。

---

<a id="_1.8"></a>

**8. MySQL会自动位主键创建索引**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

在MySQL中建立表的时候回为我们的表的主键自动生成索引，并且索引名称为PRIMARY

---

<a id="_1.9"></a>

**9. 视图的作用**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- 复用脚本代码块
- 保证数据的安全，如果读者创建了一个表并且允许用户查询，那么他们将能够访问表中的每行每列。表里的一些敏感数据，如身份证号或者信用卡号码，这些数据对用户公开并不好。这种情况下我们最好首先保持表的私有权限，不向用户授权select许可，然后创建一个或者多个视图，这些视图可以掩盖很多敏感内容

---

<a id="_2"></a>

## `SQL的一些特殊语法技巧`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_2.1"></a>

**1. order by 根据表达式或者数字排序**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 根据表达式排序

顾客表根据顾客名字的最后3位字母进行排序

```java
select * from customer order by right(name,3) 
```

1.2 根据数字占位符进行排序

这里数字站位符表示对表的第2列和第5列进行排序，但是这种方式不推荐使用，因为如果表的字段增加或者删除或者移动都会对这个SQL造成影响，所以需要注意

```java
select * from customer order by 2,5
```

---

<a id="_2.2"></a>

**2. 过滤条件之字符串范围**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

我们常常使用日期或者数字字段进行范围过滤是容易理解的，但是我们也可以使用字符串作为搜索范围条件。例如："XXX-XX-XXXX"，其中X为0-9的数字。如果我们需要查询的号码位于"500-00-0000"和"999-99-9999"之间，那么我们尽可以进行字符串范围过滤

```java
select * from customer where fed_id between '500-00-0000' and '999-99-9999'
```

---

<a id="_2.3"></a>

**3. 聚合函数中使用表达式**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

例子：select max(y_money - m_money) max_money from account;

---

<a id="_2.4"></a>

**4. 根据表达式进行group by 分组操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

根据员工的入职年份进行分组操作

select * from employee group by extract(year from start_date)

---

<a id="_2.5"></a>

**5. exists 运算符**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

exists运算符关心存在的关系但是不关心数量。

例如：

select * from account a where exists(select 1 from transaction t where t.account_id = a.account_id and t.txn_date = '2008-09-22')

使用exists运算符时，子查询可能返回0、1或者多行结果，然而条件知识简单的检查子查询能否返回至少1行。并且exists的使用习惯也是使用 select 1或者 select *

---

<a id="_2.6"></a>

**6. 自然连接 nature**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

自然连接是数据库服务器自己去决定怎么样连接数据表，服务器的判断依据是多表交叉是的相同的列名来推断连接条件。如account表中包含了一个cust_id的列，这个列是customer表的外键，也是customer表的主键。

select * from account natural join customer

这个例子中服务器自动添加了连接条件 a.cust_id=c.cust_id

但是这种连接方式不推荐使用，因为很多时候服务器的处理可能会出现意料之外的情况。

---

<a id="_2.7"></a>

**7. CASE表达式**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 语法如下

```java
case
    where c1 then e1
    where c2 then e2
    ...
    [else e3]
end
```

---

<a id="_3"></a>

## `SQL的并集操作`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_3.1"></a>

**1. union并集操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- A union B 用于返回A和B的并集，其中重复的元素质包含一次 
- A union all B 用于返回A和B的并集，其中重复的元素质包含二次，如果是多个集合那么可以包含多次 

---

<a id="_3.2"></a>

**2. intersect 交集操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- A intersect B 两个集合的交集，如果没有交集那么结果将会为空

---

<a id="_3.3"></a>

**3. except 差操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- A except B 也就是集合A中减去A和B的交集，如果A和B没有交集，那么结果集就是A中的所有元素

---

<a id="_3.4"></a>

**4. 集合的复合操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- (A union B) except (A intersect B)
- (A except B) union (A except B)

---

<a id="_3.5"></a>

**5. 集合操作的注意点**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 基本规则

- 要求两个数据集合必须要有着相同数目的列，并且结果集中的列名可以相同也可以不同，因为集合列的对应是通过类的顺序来确定的，并不是根据列的名称来确定的
- 不同集合列之间的对应通过列的顺序来对应
- 两个数据集合中对应的列的数据类型必须是一样的，或者数据类型在服务器上可以相互转换

1.2 高级规则

- 如果需要对复合查询的结果集合进行拍讯，那么需要在最后一个查询的后面添加order by 子句，当order by 子句中指定需要排序的列的时候，需要从复合查询的第一个查询的表中选择列名

```java
// 示例
select emp_id from employee union select open_emp_id from account order by emp_id
```

- 集合操作的优先级
    - 操作符相同，3个以及以上的查询语句一自顶向下的顺序被解析和执行
    - 可以通过圆括号确定其优先级
    - intersect操作符的优先级最高

---

<a id="_3.5"></a>

**5. 集合操作符使用示例**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 结果集别名示例

```java
select 'IND' type_cd, cust_id, lname name from individual union select 'BUS' type_cd, cust_id, name from business;
```

1.2 union all 连接三个集合操作

```java
select 'IND' type_cd, cust_id, lname name from individual union all select 'BUS' type_cd, cust_id, name from business union all select 'BUS' type_cd, cust_id, name from business 
```

---

<a id="_4"></a>

## `SQL函数`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_4.1"></a>

**1. 字符串函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*


1.1 返回值为数字

- length()
- position('hello' in 'hello world') // 结果：1 如果没有结果返回0
- locate('is', 'This is Daming',5) // 结果：6 从指定位置进行字符串搜索，而不是默认从0开始搜索
- strcom(expr1:varchar, expr2:varchar) 返回整形结果，有以下三种情况
    - -1,第一个字符串的排序位于第二个字符串之前 strcom('12345','xyz')
    - 0,两个字符串相同 strcom('12345','12345')
    - 1,第一个字符串的排序位于第二个字符串之后 strcom('xyz','12345')
    
1.2  返回值为字符串

- regexp 正则提取函数，将制定字符创根据正则规则进行提取
    - 示例： select fed_id regexp '.{3}-.{2}-.{4}' extra_str from customer // 结果：111-11-1111 ...
- concat(exp:varchar*) 连接字符串 concat('1','2','3'),可以将日期和数字转换为字符串
- insert(src:vrchar,pos:int,len:int,newstr:varchar):varchar 指定位置插入新字符串到旧的字符串
- replace('goodbye world','goodbye','hello') 替换函数
- substring('goodbye world',3,4) 从指定的位置开始提取指定数目的字符,例子中从第三个字符开始提取4个字符

---

<a id="_4.2"></a>

**2. 数值函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

mod()
pow()
ceil()向上取整
floor()向下取整
round(2.12224,2) 四舍五入，以 0.5 为界，可以限制小数个数
truncate() 只是进行简单的去掉不需要的小数，不会进行四舍五入

sign()判断数值正负和0状态
    - 正数：返回1
    - 零：返回0
    - 负数：返回-1

  
---

<a id="_5"></a>

## `时间处理函数`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. 时间处理问题**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

世界各地的人们都将太阳直射本地的时间作为正午，所有没有办法将所有人使用统一的时钟。因此世界被划分为了24个时区，同一时间的所有人都参照相同的时钟。但是有些地区在1年会两次调整他们的时钟，也就是实行夏行制，但是有些地区不会采用这种做法，这样就会造成地球上的两个地方有半年的时差为4个小时，而另外半年的时差为5小时，这样子就会大大的增加复杂性。


---

<a id="_5.2"></a>

**2. 字符串转换为日期函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 将日期字符串转化为日期数据，cast()仅仅支持部分字符串格式

select cast('2008-09-17 15:30:00', as datetime)

select cast('2008-09-17', as date、)

select cast('15:30:00', as datetime)

1.2 使用内建函数将字符串转化为日期 str_to_date()

SQL学习指南 128 页

update individual set birth_day = str_to_date('September 17, 2008', '%M %d, %Y') where cust_id=1

---

<a id="_5.3"></a>

**3. 直接获取当前日期时间的函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

current_date() \ current_timestamp() \ current_time()

---

<a id="_5.4"></a>

**4. 返回日期的时间函数**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 时间日期增量函数

select date_add(current_date(), interval 5 day)

第二个参数包含了3个元素：interval关键字、所需要增加的数量以及时间间隔的类型。

1.2 求得当月的最以后一天的时间 last_day()

可以带入任意的时间类型的数据

select last_day('2008-09-17')    

1.3 转化为不同时区时间的函数()

select current_timestamp() current_es, convet_tz(current_timestamp(),'US/Eastern','UTC') current_utc;

---

<a id="_5.5"></a>

**5. 提取日期某些时间点**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 返回其中星期几

select dayname('2008-09-18');

1.2 提取日期中的某些值的信息

extract()函数

select extract(YEAR from '2008-09-18 22:19:05');

1.3 返回数字的时间函数

datediff('2009-09-03','2009-06-24') 返回两个时间之间的日期差，可以返回负数

---

<a id="_6"></a>

## `MySQL索引`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. 创建索引的顺序**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 示例

alter table employee add index emp_name_idx (lname, fname)

1.2 说明

这里我们指定了两个索引，一个是姓名，二是制定了姓氏。这样子我们就可以先根据姓氏来进行第一次排序，然后根据名字来进行第二次排序。

但是这个查询不适合仅仅指定客户名字的查询，因为这里并不知道姓氏，而索引是先根据姓氏然后在根据名字来构建索引树的，索引对于没有优先对姓氏进行搜索的话其实这个索引也就没有什么作用了，因为没有对姓氏进行排序的的名字索引显得杂乱无章。

---

<a id="_6.2"></a>

**2. 索引类型**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 B树索引

也就是平衡树，减少遍历次数，时间复杂度降低

1.2 位图索引

B树索引擅长于处理包含许多不通值的列，如客户的形式和姓名，但是处理允许少量不通值的列是会变得很难使用。例如，为了能够快速检索某一特定类型(比如支票账户、储蓄账户)的所有账户。

1.3 文本索引

全文搜索引擎


