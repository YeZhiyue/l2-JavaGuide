
# ==Spring5 第一天==
## <font color=#CC3333 face=微软雅黑>• 第一章 Spring概述</font>
#### ○ Spring概念、优势
##### § spring是什么
				□ 轻量级开源框架
				□ 内核
					® IOC （Inverse Of Control） 反转控制
					® AOP （Aspect Oriented Programming） 面向切面编程
				□ 提供表现层 SpringMVC
				□ 提供业务层事务管理
				□ 提供持久层 Spring JDBC
				□ 能够整合开源世界众多的第三方框架和类库
##### § spring的发展历程
				□ 1997年 提出思想
				□ 1998年 指定开发标准规范
				□ 1999年 发布
				□ 2001年 2.0发布
				□ 2006年 3.0发布
##### § spring的优势
				□ 1、方便解耦
					® 通过ioc容器，将对象见得依赖关系交给Spring进行控制
						◊ 用户不必为单例模式类
						◊ 属性文件解析这些很底层的需求编写代码
						◊ 更专注于上层的应用开发
				□ 2、aop编程支持
					® 方便进行面向切面编程，传统OOP难以实现的功能通过aop都可以轻松实现
				□ 3、声明式事务的支持
					® 将我们从单调烦闷的事务管理代码中解脱出来，通过声明的方式灵活的进行事务管理
				□ 4、方便程序的测试
					® 可以使用非容器依赖的编程方式进行几乎所有的测试工作。我们可以直接从容器中取出对象进行编程测试
				□ 5、方便集成各种优秀的框架
					® Spring可以降低各种框架的使用难度
				□ 6、降低了JavaEE API的使用难度
					® 如：JDBC、JavaMail、远程调用 被进行了薄薄的封装，使这些api的使用难度大大降低
##### § Spring体系结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020021621533878.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
## <font color=#CC3333 face=微软雅黑>• 第二章 IOC的概念和作用</font>
#### ○ 程序耦合概念
##### § 模块间关联程度的度量
#### ○ 降低耦合示例
##### § 1.编译期依赖，使用反射
##### § 2.JDBC操作中使用DriverManager的register方法
				□ 弊端：如果更换了数据库的驱动，那么就会非常不方便我们的编程
				□ 解决：通过Class.forName方式注册驱动；并且通过配置文件配置
#### ○ 工厂模式解耦
##### § 实际开发中我们可以吧三层的对象都使用配置文件配置起来
##### § 启动服务器应用加载的时候让一个类中的方法通过读取配置文件，把这些对象创建出来并存起来
##### § 接下来需要使用这些类的时候直接拿来使用就可以了
#### ○ 工厂的实质
##### § 实质：一个java类
##### § 就是读取配置文件并且创建三层对象的一个类
#### ○ 控制反转解读 Inversion Of Control
##### § 1.工厂中创建的对象存放到哪里去了？
				□ 存入了一个map集合中
				□ 我们把这个map称为容器
##### § 2.工厂本质进一步解释
				□ 1.原来我们获取对象的方式是new，是主动的
				
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215411955.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

				□ 2.现在我们获取对象是通过工厂间接获取对象的，是被动的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215420696.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
##### § 最终的出结论：
				□ 我们的ioc是用来减少程序间的耦合（依赖关系）
## <font color=#CC3333 face=微软雅黑>• 第三章 使用Spring的IOC解决程序耦合</font>
#### ○ Spring的环境基本搭建
##### § 程序的耦合和解耦
				□ 准备Spring的开发包
					® 官网：http://spring.io/
					® 下载地址：
					® http://repo.springsource.org/libs-release-local/org/springframework/spring
				□ 配置bean.xml文件
					® 1.导入约束
						◊ 网站：/spring-framework-5.0.2.RELEASE/docs/spring-framework-reference/html5/core.html 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215441225.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
					® 2.Spring容器配置
						◊ 配置Service
								– <!-- 配置 service -->
								– <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">
								– </bean>
						◊ 配置dao
							} <!-- 配置 dao -->
							} <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
						◊ 测试是否配置成功
							} public static void main(String[] args) {
							} //1.使用 ApplicationContext 接口，就是在获取 spring 容器
							} ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
							} //2.根据 bean 的 id 获取对象
							} IAccountService aService = (IAccountService) ac.getBean("accountService");
							} System.out.println(aService);
							} IAccountDao aDao = (IAccountDao) ac.getBean("accountDao");
							} System.out.println(aDao);
							} }
#### ○ Spring基于XML的IOC细节
##### § Spring细节：工厂类的结构图
				□ 图一
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215508712.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
				□ 图二
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215512938.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
				□ BeanFactory 和 ApplicationContext 的区别 
					® BeanFactory 才是 Spring 容器中的顶层接口。
					® ApplicationContext 是它的子接口。
					® 创建对象的时间点不一样。
						◊ ApplicationContext：只要一读取配置文件，默认情况下就会创建对象。
						◊ BeanFactory：什么使用什么时候创建对象
				□ ApplicationContext 接口的实现类
					® ClassPathXmlApplicationContext：
						◊ 它是从类的根路径下加载配置文件推荐使用这种
					® FileSystemXmlApplicationContext：
						◊ 它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置。
					® AnnotationConfigApplicationContext:
						◊ 当我们使用注解配置容器对象时，需要使用此类来创建 spring 容器。它用来读取注解。
##### § IOC中bean标签和管理对象
				□ bean标签
					® 作用
						◊ 用于创建和配置需要加入到容器中的对象。默认情况下使用无参的构造函数创建对象。
					® 属性
						◊ id：提供容器中对象的key，方法中获取这个对象需要通过这个id获取对应容器中存放的对象
						◊ class：指定类的全限定类名。用于反射创建对象。默认情况下调用无参构造函数创建对象
						◊ scope：指定对象的作用范围
							} singleton：默认值，单例的
							} prototype：多例的
							} request：web项目中，Spring创建一个Bean对象，讲对象存入到request域中。
							} session： web项目汇总创建一个bean对象存入到session域中。
							} global session ：web项目中，应用在Portlet环境，如果没有Portlet环境，那么globalSession相当于Session
						◊ init-method: 指定类中的初始化方法名称
						◊ destroy-method：指定类中销毁方法的名称
					® bean的作用范围和生命周期
						◊ 单例对象
							} 一个应用只有一个对象的实例。它的作用范围就是整个引用。
							} 生命周期：
								– 对象出生：当应用加载，创建容器时，对象就被创建了。
								– 对象活着：只要容器在，对象一直活着。
								– 对象死亡：当应用卸载，销毁容器时，对象就被销毁了。
						◊ 多例对象：scope="prototype"
							} 每次访问对象时，都会重新创建对象实例。
							} 生命周期：
								– 对象出生：当使用对象时，创建新的对象实例。
								– 对象活着：只要对象在使用中，就一直活着。
								– 对象死亡：当对象长时间不用时，被 java 的垃圾回收器回收了。
				□ 实例化Bean的三种方式
					® 第一种方式：使用默认无参的构造函数
						◊ <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"/> 
					® 第二种方式：通过Spring管理的静态工厂的静态方法创建对象
						◊ <bean id="accountService"class="com.itheima.factory.StaticFactory" factory-method="createAccountService"></bean>
					® 第三种方式：通过Spring管理的实例工厂中的方法创建对象
						◊ 分两步走：为什么实例工厂需要分两步走，而静态工厂只需要一步
							} 1.静态工厂中的静态方法优先于类加载进内存，可以直接通过类名创建对象
							} 2.实例工厂中的方法需要通过实例化对象进行调用。所以第一步先创建实例对象，第二步再通过实例对象调用创建对象的方法来创建对象。
						◊ <bean id="instancFactory" class="com.itheima.factory.InstanceFactory"></bean>
						◊ <bean id="accountService" factory-bean="instancFactory" factory-method="createAccountService"></bean>
##### § Spring的依赖注入
				□ 依赖注入概念
					® Dependency Injection。Spring框架核心ioc的具体实现
					® 之前我们通过控制反转把对象创建的任务交给了Spring，当时代码中不可能出现没有依赖的情况。
					® ioc的解耦也仅仅是降低它们之间的依赖关系，但不会消除。
					® 例如：我们的业务层仍然会调用持久层的方法。那么这种业务层和持久层之间的依赖关系，在使用了spring之后，就让spring来维护了。
					® 简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取。
				□ 构造函数注入
					® 要求：类中需要提供一个含有参数列表的构造函数
					® 标签：
						◊ constructor-arg
							} 属性
							} index：指定参数在构造函数的索引位置，从0开始
							} type：指定参数在构造函数中的数据类型
							} name：指定参数在构造函数中的名称
						◊ value
							} 赋值，支持基本数据类型和String类型
						◊ ref
							} 赋值，支持其他bean类型，也就是放入容器中的对象
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020021621560392.png)
				□ set方法注入：bean类型的主要注入方式
					® 要求：类中需要赋值的bean类型对象需要有相应的set方法
					® 标签
						◊ property
							} 属性
							} name：找的是类中set方法后面的部分
							} ref：给属性赋值的bean类型
							} value：基本类型和String类型
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215642862.png)
				□ 使用p名称空间注入数据
					® 要求：给类中的基本类型和Sting赋值，但是需要有对应的set方法
					® 标签
						◊ bean
							} 属性
							} p:xxx:后面的xxx代表对应的set后面的字符串
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215632611.png)
				□ 注入集合属性
					® 要求：注入的是集合类型
					® 标签：分List集合和Map集合
						◊ array
						◊ list
						◊ set
						◊ map
						◊ entry
						◊ props
						◊ prop
						◊ value


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216215625435.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

