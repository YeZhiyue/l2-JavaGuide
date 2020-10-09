
用户：
   username  password

    Daming -> root
    Jack   -> root
    Tom    -> root

# SpringSecurity学习Demo笔记

[官方参考文档](https://docs.spring.io/spring-security/site/docs/5.2.5.BUILD-SNAPSHOT/reference/htmlsingle/#servlet-hello)

[简单源码示例](https://github.com/YeZhiyue/SpringSecurityDemo#_2.2)

<a id="_top"></a>

---

## `目录:`

### <a href="#_1" rel="nofollow" target="_self">SpringSecurity基本环境的搭建</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">依赖包的导入</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">项目配置</a>
### <a href="#_2" rel="nofollow" target="_self">实现内存用户授权</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">核心配置</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">简单示例</a>
### <a href="#_3" rel="nofollow" target="_self">实现数据库用户授权</a>
3.2 <a href="#_3.1" rel="nofollow" target="_self">核心配置</a>
3.2 <a href="#_3.2" rel="nofollow" target="_self">简单示例</a>
### <a href="#_4" rel="nofollow" target="_self">实现JWT整合(token令牌)</a>
### <a href="#_5" rel="nofollow" target="_self">Git错误归纳(踩坑历史)</a>
5.1 <a href="#_5.1" rel="nofollow" target="_self">配置你的登录页</a>
5.2 <a href="#_5.2" rel="nofollow" target="_self">配置你的动态网页</a>
5.3 <a href="#_5.3" rel="nofollow" target="_self">配置防止crs攻击</a>
5.4 <a href="#_5.4" rel="nofollow" target="_self">配置你的注销跳转页面</a>
5.5 <a href="#_5.5" rel="nofollow" target="_self">配置rememberme</a>

---

<a id="_1"></a>

## `SpringSecurity基本环境的搭建`

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_1.1"></a>

**1. 依赖包的导入**

```java
<!-- SpringSecurity的基础依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<!-- 这里添加的SpringSecurity的额外动态权限页面依赖 -->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>
<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```

<a id="_1.2"></a>

**2. 项目配置**

```java
# SpringSecurity的配置
spring.aop.javaguide.proxy-target-class=true

# JWT
# Json格式化
spring.jackson.serialization.indent-output=true
#打印security日志记录
logging.level.org.springframework.security=info
```

---

<a id="_2"></a>

## `实现内存用户授权`

---

<a id="_2.1"></a>

**1. 核心配置**

---

下面的配置就是配置使用内存用户授权的关键代码

```java
//我们要将前端传过来的密码进行某种方式加密，然后才可以进行正常的登录
auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("root").password(new BCryptPasswordEncoder().encode("1")).roles("ROOT", "ADMIN", "ORDINARY")
        .and()
        .withUser("usr1").password(new BCryptPasswordEncoder().encode("1")).roles("ADMIN")
        .and()
        .withUser("usr2").password(new BCryptPasswordEncoder().encode("1")).roles("ORDINARY");
```

下面是完整的配置文件

```java
@EnableWebSecurity // 开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //定制请求的授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 1. 允许下面的资源所有用户都可以访问首页和我们的登录页面
                .antMatchers("/").permitAll()
                .antMatchers("/toLogin").permitAll()
                // 2. 仅仅只有指定的用户才可以访问
                .antMatchers("/level1/**").hasRole("ROOT")
                .antMatchers("/level2/**").hasRole("ADMIN")
                .antMatchers("/level3/**").hasRole("ORDINARY");

        //开启自动配置的登录功能：如果没有权限，就会跳转到登录页面！
        // /login 请求来到登录页
        // /login?error 重定向到这里表示登录失败
        http.formLogin()
                // 这两个是SpringSecurity默认配置的登录参数
                .usernameParameter("username")
                .passwordParameter("password")
                // 这里我们自己指定一个自己的登录页面
                .loginPage("/toLogin")
                .loginProcessingUrl("/login") // 登陆表单提交请求
                ;

        // 注销跳转到指定的页面
        http.logout().logoutSuccessUrl("/");

        //关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
        http.csrf().disable();

        //记住我，就是记住密码和用户名。这里是一个checkbox
        http.rememberMe().rememberMeParameter("remember");
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 方式一：使用数据库作为原数据进行登录认证
         */
        // 设置自定义的custmProvider
//        auth.userDetailsService(myUserDetailsService);

        /**
         * 方式二：在内存中定义数据，使用内存中数据来登录验证
         */
        //我们要将前端传过来的密码进行某种方式加密，然后才可以进行正常的登录
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("1")).roles("ROOT", "ADMIN", "ORDINARY")
                .and()
                .withUser("usr1").password(new BCryptPasswordEncoder().encode("1")).roles("ADMIN")
                .and()
                .withUser("usr2").password(new BCryptPasswordEncoder().encode("1")).roles("ORDINARY");
    }
}
```

---

<a id="_2.2"></a>

**2. 示例**

---

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162847285.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162839370.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162829789.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162815699.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_3"></a>

## `实现数据库用户授权`

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_3.1"></a>

**1. 核心配置**

---

配置使用数据库授权

```java
/**
 * 方式一：使用数据库作为原数据进行登录认证
 */
// 设置自定义的custmProvider
auth.userDetailsService(myUserDetailsService);
```

实现 UserDetailsService 类来进行授权验证

```java
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * @param username 浏览器输入的用户名，而不是数据库输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 根据用户名进行查询
         */
        User user = userService.findAuthUserByUserName(username);

        /**
         * 如果没有这个用户，那么就直接进行异常处理
         */
        if (user == null) {
            //注意，这是要求的，找不到抛异常
            throw new Exception(username + "User not found");

            //或者return null; 也是表示用户验证异常
        }
        // 2. 将用户拥有的权限加到 grantedAuthorities(此处),注意我们在所有权限前面加了'ROLE_'字符串，这是因为下面 MySecurityConfig 类中对资源加权限 hasRole("ADMIN") 方法中会为加入的字符串前面统一加上"ROLE_",可以看源码
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String roleName : userService.findUserRoleName(user.getId())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        }

        // 3. 按要求返回security对应的User，其实现了UserDetails
        // 并且这里我们如果提交的原文密码，那么我们就需要在密码前面添加 {noop}
        // 如果你数据库用的是密文，那么就不用在前面添加 {noop}
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                "{noop}"+user.getPassword(), authorities/*进行权限的设置*/);
    }
}
```

---

<a id="_3.2"></a>

**2. 简单示例**

---

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162957682.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162900933.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200628162907673.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_4"></a>

## `实现JWT整合(token令牌)`

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5"></a>

## `其他细节实现`

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_5.1"></a>

**1. 配置你的登录页**

---

```java
//开启自动配置的登录功能：如果没有权限，就会跳转到登录页面！
// /login 请求来到登录页
// /login?error 重定向到这里表示登录失败
http.formLogin()
        // 这两个是SpringSecurity默认配置的登录参数
        .usernameParameter("username")
        .passwordParameter("password")
        // 这里我们自己指定一个自己的登录页面
        .loginPage("/toLogin")
        .loginProcessingUrl("/login") // 登陆表单提交请求
        ;
```

---

<a id="_5.2"></a>

**2. 配置你的动态网页**

---

需要导入额外的thymeleaf依赖包

```java
<!-- 这里添加的SpringSecurity的额外动态权限页面依赖 -->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>
```

配置你的登录和注销状态

- sec:authorize="isAuthenticated()" 根据你是否登录来配置你的标签元素是否可见
- sec:authentication="principal.username" 取出授权用户数据

```java
<div class="col-lg-8" sec:authorize="isAuthenticated()">
    用户名：<span sec:authentication="principal.username"></span>
    <br>
    会员类别：<span sec:authentication="principal.authorities"></span>
</div>
<div class="col-lg-2">
    <div class="list-group" id="list-tab" role="tablist">
        <a  sec:authorize="!isAuthenticated()" th:href="@{/toLogin}" class="list-group-item list-group-item-action active" data-toggle="list" role="tab">登陆</a>
        <a  sec:authorize="isAuthenticated()" th:href="@{/logout}" class="list-group-item list-group-item-action active" data-toggle="list" role="tab">注销</a>
    </div>
</div>
```

配置用户权限页面

- sec:authorize="hasRole('ROOT')" 只有ROOT用户可以看见这个元素

```java
<div class="col-lg-4" sec:authorize="hasRole('ROOT')">
    <ul class="list-group">
        <li class="list-group-item">ROOT 可见</a></li>
        <li class="list-group-item"><a th:href="@{/level1/1}">level1</a></li>
        <li class="list-group-item"><a th:href="@{/level1/2}">level2</a></li>
        <li class="list-group-item"><a th:href="@{/level1/3}">level3</a></li>
    </ul>
</div>
```

---

<a id="_5.3"></a>

**3. 配置防止crs攻击**

---

```java
//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
http.csrf().disable();
```

---

<a id="_5.4"></a>

**4. 配置你的注销跳转页面**

---

```java
// 注销跳转到指定的页面
http.logout().logoutSuccessUrl("/");
```

---

<a id="_5.5"></a>

**5. 配置rememberme**

---

```java
//记住我，就是记住密码和用户名。这里是一个checkbox
http.rememberMe().rememberMeParameter("remember");
```




## SpringSecurity过滤器

1. 简单介绍

过滤用户发送的请求，通过不同的设置做出不同的响应。

- Csrf跨域安全访问请求
- 用户认证操作的过滤器 usernamePasswordAuthenticationFilter (默认匹配URL为 /login ,请求为 POST 请求的过滤器)
- 默认的登录和退出界面的顾虑器
- 匿名过滤器，非常有用，比如游客登录

2. 过滤器执行流程

是在服务器启动的时候就会启动，

## 认证流程

默认中规定了：username password Post /login


## JWT

分布式认证流程中起到最关键的作用的就是token，必须要保证这个token的安全，直接关系到系统的健壮性

JSON Web Token 分布式身份校验方案。可以生成Token,也可以验证Token。

- 头部:设置一些规范信息，签名部分的编码格式就在头部中声明
- 载荷:token中存放有效信息的部分，如用户名、角色、过期时间，但是不要放密码
- 签名:将头部和载荷进行编码就可以得到签名

非对称加密：我们在加密的时候用一种技术，在解密的时候用另外一种技术，这个就是非对称加密






>>>>>>> 更新
