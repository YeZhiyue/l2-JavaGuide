
1. sql语法分类

DDL(Data Definition Language): 数据定义语言
用来定义数据库对象:数据库，表,列等。 关键字：create,drop, alter等。
DML(Data Manipulation Language): 数据操作语言
用来对数据库中的表进行增删改操作。 关键字：insert,delete,update等。
DQL(Data Query Language): 数据查询语言
用来查询数据库中表的记录(数据)。 关键字:select, where等
DCL(Data Control Language): 数据控制语言
用来定义数据库的访问控制权限和安全级别，及创建用户。关键字: grant, revoke等
TCL（Transaction Control Language）事务控制语言

2. null 不等价为 0 请注意

3. 特殊细节 时间处理

链接：https://www.nowcoder.com/questionTerminal/1256f113ec2149aa95a2c4df357dfab7
来源：牛客网

显示受雇时间在2010年1月1日和2012年12月31日之间的雇员的姓名、工资、及受雇时间，并以受雇时间升序排列。 语句：SELECT ENAME,SAL,HIREDATE FROM EMP WHERE HIREDATE BETWEEN '2010-01-01' AND '2012-12-31' ORDER BY HIREDATE;

表示： 2010-01-01 00:00:00 <= date <= 2012-12-31 00:00:00
结论： 我们可以得到 12.31 号的那一天是不会记录在我们的判断条件之中的，所以细节上出现了错误。

选项B 因为between...and后面加日期的话，短日期默认time为00:00:00 因此查询日期只能截止到2012-12-31 00：00：00 并没有当天的记录

