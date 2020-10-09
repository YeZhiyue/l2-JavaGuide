

# SpringMVC注解学习笔记

[SpringMVC官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-requestmapping)


<a id="_top"></a>

## `目录:`
### <a href="#_1" rel="nofollow" target="_self">RequestMapping请求映射</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">RequestMapping(不同类型的HTTP请求)</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">URI pattern 请求路径的传输参数</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">Json数据消费和提供(Consumable&&Producible)</a>
1.4 <a href="#_1.4" rel="nofollow" target="_self">请求映射约束</a>
### <a href="#_2" rel="nofollow" target="_self">方法处理(参数绑定，HandlerMethod)</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">设置model的共享域</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">其他参数绑定示例</a>
### <a href="#_3" rel="nofollow" target="_self">Cookie&&Session设置</a>
3.1 <a href="#_3.1" rel="nofollow" target="_self">@SessionAttributes注解的使用</a>
3.2 <a href="#_3.2" rel="nofollow" target="_self">Cookie和Session的其他设置</a>
### <a href="#_4" rel="nofollow" target="_self">rest风格请求，就是资源路径映射的变体</a>
### <a href="#_100" rel="nofollow" target="_self">错误，踩坑</a>
100.1 <a href="#_100.1" rel="nofollow" target="_self">Cookie中出现了非法的字符串，导致错误(An invalid character [32])</a>
100.2 <a href="#_100.2" rel="nofollow" target="_self">JSESSIONID是干嘛的</a>
100.3 <a href="#_100.3" rel="nofollow" target="_self">Model对象中绑定int数据(包括其他基本数据类型)进行传递的过程中出现类型转换异常</a>
100.4 <a href="#_100.4" rel="nofollow" target="_self">Model中存放的数据的作用域是什么作用域？</a>



---

<a id="_1"></a>

## `RequestMapping请求映射`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. RequestMapping(不同类型的HTTP请求)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * RequestMapping
 */
@GetMapping("/get")
public String testget() {
    return "get";
}

@PostMapping("/post")
public String testpost() {
    return "post";
}

@DeleteMapping("/delete")
public String testdelete() {
    return "delete";
}

@PatchMapping("/patch")
public String testpatch() {
    return "patch";
}
```

---

<a id="_1.2"></a>

**2. URI pattern 请求路径的传输参数**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * URI pattens
 */
// 这里可以进行路径传输参数，并且可以进行类型转换
@RequestMapping("/?1*2{intParam}3")
public String testmuti(@PathVariable() int intParam) {
    return "/?1*2{param}3**" + intParam;
}

@RequestMapping("/two/**")
public String testmuti2() {
    return "two";
}

// 定义正则表达式的规则， 将变量名name赋值为正则表达式之一
@RequestMapping("/reg{name:[1-9]+}")
public String testreg(@PathVariable String name) {
    return "/reg" + name;
}
```

---

<a id="_1.3"></a>

**3. Json数据消费和提供(Consumable&&Producible)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

3.1 List类型参数绑定和返回

```java
/**
 * 注意：这里需要确定集合的泛型类型
 * <p>
 * [
 * "name",
 * "sex"
 * ]
 */
@RequestMapping(path = "/list", consumes = "application/json",produces = "application/json")
@ResponseBody
public List<String> testlist(@RequestBody List<String> list) {
    System.out.println(list);
    return list;
}
```

3.2 List类型参数绑定和返回

```java
/**
 * 注意：这里需要确定集合的泛型类型
 * {
 * "zhangsan":"11",
 * "lisi":"22"
 * }
 */
@RequestMapping(path = "/map", consumes = "application/json",produces = "application/json")
@ResponseBody
public Map<String, Integer> testmap(@RequestBody Map<String, Integer> map) {
    System.out.println(map);
    return map;
}
```

3.3 List类型参数绑定和返回

```java
/**
 * 这里简单演示一下其他方面的约束
 * 
 * params:说明一下，这个参数是跟在 get 请求后面的参数
 */
@RequestMapping(path = "/constraint",
        method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE},
        headers = {"host=localhost"},
        params = {"role.id=1"}/*可以在get请求后面跟上 ?role.id=1 */
)
@ResponseBody
public Role testConstraint(@RequestBody Role role) {
    System.out.println(role);
    return role;
}
```

---

<a id="_1.4"></a>

**4. 请求映射约束**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * 这里简单演示一下其他方面的约束
 *
 * params:说明一下，这个参数是跟在 get 请求后面的参数
 */
@RequestMapping(path = "/constraint",
        method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE},
        headers = {"host=localhost"},
        params = {"role.id=1"}/*可以在get请求后面跟上 ?role.id=1 */
)
@ResponseBody
public Role testConstraint(@RequestBody Role role) {
    System.out.println(role);
    return role;
}
```

---

<a id="_2"></a>

## `方法处理(参数绑定，HandlerMethod)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. 设置model的共享域**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
public String setSession(Model model, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    model.addAttribute("sessionString", "I am session01");
    model.addAttribute("sessionInt", 12345);
    model.addAttribute("sessionObj", new Role(2l, "管理员", "掌握着一定的后台管理权限"));

    System.out.println(modelMap.get("sessionString"));
    System.out.println(modelMap.get("sessionInt"));
    System.out.println(modelMap.get("sessionObj"));

    return "login";
}
```

---

<a id="_2.2"></a>

**2. 其他参数绑定示例**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
package com.example.demo.controller;


import com.example.demo.proj.Role;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;

@Controller
// types表示Session的存储方式不是由Session的属性名称决定的，而是由其他类型的数据决定的
//@SessionAttributes(value = {"modelSession01", "modelSession02"}, types = {Integer.class})
public class HandlerMethodTest {

    @RequestMapping("/setCookieAndSession")
    @ResponseBody
    public String setCookieAndSession(HttpServletRequest request,
                                      HttpServletResponse response,
                                      HttpSession httpSession,
                                      // Model和ModelMap的用法类似，其中数据共享域是相同的
                                      Model model,
                                      ModelMap modelMap
                                        ) {


        model.addAttribute("modelObj", "model");

        // 设置Session数据
        httpSession.setAttribute("sessionInt", 100);
        httpSession.setAttribute("sessionObj", "I am a Session");

        // 设置Cookie数据
        response.addCookie(new Cookie("myCookie", "Care_char_encoding_I_am_cookie!"));

        /**
         * 打印我们设置的所有 Cookie
         *
         * CookieName: cookie CookieValue： hellocookie
         * CookieName: cookie01 CookieValue： hellocookie
         * CookieName: JSESSIONID CookieValue： 5338625A6C0ABF08CB48AD15F3D13342
         */
        Arrays.stream(request.getCookies()).forEach(cookie -> System.out.println("CookieName: "+cookie.getName()+" CookieValue： "+cookie.getValue()));

        /**
         * 打印我们设置的所有 Session
         *
         * 所有Session的名称： sessionInt
         * 所有Session的名称： sessionObj
         */
        Enumeration<String> sessionNames = request.getSession().getAttributeNames();
        while (sessionNames.hasMoreElements()) {
            System.out.println("所有Session的名称： "+ sessionNames.nextElement());
        }

        return "index";
    }

    @RequestMapping("/manyParam/{pathParam}")
    @ResponseBody
    public String handlerMethodParam(
            // 下面是一些原生的Servlet对象
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession httpSession,
            HttpMethod httpMethod, // 示例： httpMethod.name() => GET
            HttpEntity<Role> entity, // 结果示例： <Role(id=1, roleName=zhangsan, roleDesc=hello,I'am zhangsna!),[user-agent:"PostmanRuntime/7.26.1", accept:"*/*", postman-token:"04f09949-21ff-4f55-8bb1-f8c54416bfa3", host:"localhost:8080", accept-encoding:"gzip, deflate, br", connection:"keep-alive", content-length:"87", Content-Type:"application/json;charset=UTF-8"]>
            // 路径参数
            @PathVariable(name = "pathParam") String pathParam,
            // 请求参数
            @RequestParam(name = "requestParam", defaultValue = "requestParam is null") String requestParam,
            // 请求体的获取，需要这个请求是POST请求并且有请求体才可以获取对应的数据
            @RequestBody String requestBody,
            // 头部参数
            // 如果没有定义默认值，那么就需要请求头有对应的头信息，否则会请求失败
            @RequestHeader(name = "Keep-Alive", defaultValue = "900") Long keepAlive,
            // Cookie
            @CookieValue(name = "myCookie", defaultValue = "can't get Cookie") Cookie cookie,
            // Session
            @SessionAttribute(name = "sessionInt", required = false) int sessionInt,
            @SessionAttribute(name = "sessionObj", required = false) String sessionString,
            // Model Data
            @ModelAttribute(name="modelObj") String modelObj,
            // 存放我们数据模型的对象
            ModelMap modelMap
    ) {

        /**
         * 结构集打印
         *
         * 获取请求方法类型： GET
         * 获取我们请求体中的对象： <Role(id=1, roleName=zhang。。。
         * URL路径参数: pathParam
         * requestParam： requestParam
         * RequestHeader： 900
         * myCookie: javax.servlet.http.Cookie@63963a2d
         * sessionInt: 100
         * sessionObj: I am a Session
         * ModelObj: ...
         */
        /**
         *
         */
        // 获取请求方法
        System.out.println("获取请求方法类型： " + httpMethod.name());
        // 获取我们放入的对象
        System.out.println("获取我们请求体中的对象： " + entity);
        System.out.println("URL路径参数: " + pathParam);
        System.out.println("requestParam： " + requestParam);
        System.out.println("RequestHeader： " + keepAlive);
        System.out.println("myCookie: " + cookie);
        System.out.println("sessionInt: " + sessionInt);
        System.out.println("sessionObj: " + sessionString);
        System.out.println(modelMap.get("modelObj"));
        System.out.println("ModelObj : "+modelObj);
        return "index";
    }
}
```

---

<a id="_3"></a>

## `Cookie&&Session设置`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_3.1"></a>

**1. @SessionAttributes注解的使用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

[参考博客](https://blog.csdn.net/f641385712/article/details/98378045)

@SessionAttributes这个注解是干嘛的呢，就是可以在重定向之后让你可以更加方便的管理你的Session数据，可以直接通过Model类对你的Session进行管理。

下面是一个Demo，我们设置在@SessionAttributes中的Model数据在重定向之后并没有丢失，并且当我们想要清空这个数据的时候就直接可以进行清空。

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;

@Controller
@RequestMapping
@SessionAttributes(value = {"modelSession"}, types = {Double.class})
public class SessionAndCookieTest {

    @RequestMapping("/sessionCookie")
    public String index(Model model,
                        HttpSession httpSession,
                        HttpServletRequest request,
                        HttpServletResponse response
                        ) {
        // model数据
        model.addAttribute("model", "model");
        model.addAttribute("modelSession", "modelSession");

        // session数据
        httpSession.setAttribute("notModelSession","notModelSession");

        // cookie数据
        response.addCookie(new Cookie("myCookie","myCookie"));

        /**
         * Session和Cookie结果集的打印
         * 说明：这里并没有马上将我们放入的 SessionAttributes 数据存入Session中去
         *
         * Session Name: notModelSession
         * CookieName: cookie CookieValue： hellocookie
         * CookieName: cookie01 CookieValue： hellocookie
         * CookieName: myCookie CookieValue： myCookie
         * CookieName: JSESSIONID CookieValue： 361061E5F9D258FC76CF1B83477D1A4D
         */
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println("Session Name: "+attributeNames.nextElement());
        }

        Arrays.stream(request.getCookies()).forEach(cookie -> System.out.println("CookieName: "+cookie.getName()+" CookieValue： "+cookie.getValue()));

        System.out.println("--------------------------");
        //跳转之前将数据保存到Model中，因为注解@SessionAttributes中有，所以 modelSession 应该都会保存到SessionAttributes里（注意：不是session里）
        return "redirect:/redirect01";
    }

    // 关于@ModelAttribute 下文会讲
    @RequestMapping("/redirect01")
    public String get(
                      @ModelAttribute("model") String modelDate,
                      @SessionAttribute("notModelSession") String notModelSession,
                      @SessionAttribute("modelSession") String modelSession,
                      @ModelAttribute("modelSession") String getModelSessionByModel,
                      @CookieValue("myCookie") Cookie myCookie,

                      HttpServletRequest request, HttpSession httpSession, SessionStatus sessionStatus) {
        /**
         * Session和Cookie结果集的打印
         * 说明：这里，session中出现了我们放入的 SessionAttributes 数据存
         *
         * Session Name: notModelSession
         * Session Name: modelSession
         * CookieName: cookie CookieValue： hellocookie
         * CookieName: cookie01 CookieValue： hellocookie
         * CookieName: myCookie CookieValue： myCookie
         * CookieName: JSESSIONID CookieValue： C82A3CA14E80F198ABAC1036BC523F17
         */
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println("Session Name: "+attributeNames.nextElement());
        }

        Arrays.stream(request.getCookies()).forEach(cookie -> System.out.println("CookieName: "+cookie.getName()+" CookieValue： "+cookie.getValue()));

        /**
         * model:
         * notModelSession: notModelSession
         * modelSession: modelSession
         * modelSession: modelSession
         * myCookie: javax.servlet.http.Cookie@45ffe3d0
         */
        System.out.println("model: "+modelDate);
        System.out.println("notModelSession: " + notModelSession);
        System.out.println("modelSession: " + modelSession);
        // 设置了 SessionAttributes 注解的Model数据属性可以通过model或者session获取其中数据
        System.out.println("modelSession: " + getModelSessionByModel);
        System.out.println("myCookie: " + myCookie);

        sessionStatus.setComplete();

        /**
         * Session和Cookie结果集的打印
         * 说明：可以发现，在我们执行sessionStatus.setComplete();方法之后再这里我们仍然能够获取 SessionAttributes 中的数据
         *
         * Session Name: notModelSession
         * Session Name: modelSession
         * CookieName: cookie CookieValue： hellocookie
         * CookieName: cookie01 CookieValue： hellocookie
         * CookieName: myCookie CookieValue： myCookie
         * CookieName: JSESSIONID CookieValue： C82A3CA14E80F198ABAC1036BC523F17
         */
        attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println("Session Name: "+attributeNames.nextElement());
        }

        Arrays.stream(request.getCookies()).forEach(cookie -> System.out.println("CookieName: "+cookie.getName()+" CookieValue： "+cookie.getValue()));

        System.out.println("--------------------------");
        return "redirect:/redirect02";
    }

    @RequestMapping("/redirect02")
    @ResponseBody
    public String complete(ModelMap modelMap, HttpSession httpSession,
                           HttpServletRequest request,
                           @SessionAttribute("notModelSession") String notModelSession,
                           @SessionAttribute(value = "modelSession",required = false) String modelSession
    ) {

        /**
         * 结果集打印：可以看到我们轻松的除去了model中的 SessionAttributes 中设置的数据
         *
         * notModelSession
         * null
         */
        System.out.println(notModelSession);
        System.out.println(modelSession);
        /**
         * Session和Cookie结果集的打印
         *
         * Session Name: notModelSession
         * CookieName: cookie CookieValue： hellocookie
         * CookieName: cookie01 CookieValue： hellocookie
         * CookieName: myCookie CookieValue： myCookie
         * CookieName: JSESSIONID CookieValue： C82A3CA14E80F198ABAC1036BC523F17
         */
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println("Session Name: "+attributeNames.nextElement());
        }

        Arrays.stream(request.getCookies()).forEach(cookie -> System.out.println("CookieName: "+cookie.getName()+" CookieValue： "+cookie.getValue()));

        return "sessionAttributes";
    }
}
```

---

<a id="_3.2"></a>

**2. Cookie和Session的其他设置**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

[参考的博客](https://www.cnblogs.com/l199616j/p/11195667.html)
[个人简单回话技术总结](https://editor.csdn.net/md/?articleId=104179912#_top)

这里就介绍一下Cookie常用的属性，细节参考上面的博客。

属性名 | 描述
--- | ---
String name | 该Cookie的名称。Cookie一旦创建，名称便不可更改
Object value | 该Cookie的值。如果值为Unicode字符，需要为字符编码。如果值为二进制数据，则需要使用BASE64编码
int maxAge | 该Cookie失效的时间，单位秒。如果为正数，则该Cookie在maxAge秒之后失效。如果为负数，该Cookie为临时Cookie，关闭浏览器即失效，浏览器也不会以任何形式保存该Cookie。如果为0，表示删除该Cookie。默认为–1
boolean secure | 该Cookie是否仅被使用安全协议传输。安全协议。安全协议有HTTPS，SSL等，在网络上传输数据之前先将数据加密。默认为false
String path  | 该Cookie的使用路径。如果设置为“/sessionWeb/”，则只有contextPath为“/sessionWeb”的程序可以访问该Cookie。如果设置为“/”，则本域名下contextPath都可以访问该Cookie。注意最后一个字符必须为“/”
String domain | 可以访问该Cookie的域名。如果设置为“.google.com”，则所有以“google.com”结尾的域名都可以访问该Cookie。注意第一个字符必须为“.”
String comment | 该Cookie的用处说明。浏览器显示Cookie信息的时候显示该说明
int version | 该Cookie使用的版本号。0表示遵循Netscape的Cookie规范，1表示遵循W3C的RFC 2109规范

---

<a id="_4"></a>

## `rest风格请求，就是资源路径映射的变体`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
@RestController
public class RestfulTest {

    @GetMapping("/rest")
    public String getTest(String param) {
        return "get";
    }

    @PostMapping("/rest")
    public String postTest(String param) {
        return "post";
    }

    @DeleteMapping("/rest")
    public String deleteTest(String param){
        return "delete";
    }
}
```




## 错误，踩坑
---

<a id="_100"></a>

## `错误，踩坑`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_100.1"></a>

**1. Cookie中出现了非法的字符串，导致错误(An invalid character [32])**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>


错误的记录：由于我的Cookie中出现了非法的字符串，导致错误(An invalid character [32])

Request processing failed; nested exception is java.lang.IllegalArgumentException: An invalid character [32] was present in the Cookie value

解决：就是找到Cookie中不支持的字符将其替换即可，异常中都是有提示的。

---

<a id="_100.2"></a>

**2. JSESSIONID是干嘛的**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

JSESSIONID是干嘛的？
这是一个保险措施因为Session默认是需要Cookie支持的。

**3. Model对象中绑定int数据(包括其他基本数据类型)进行传递的过程中出现类型转换异常**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

报错如下，说的是这个int基本类型无法转换成目标类型数据，因为没有空参数的构造器，所以传递参数的时候需确保这个参数的类中包含空参构造器才可以

```java
nested exception is java.lang.IllegalStateException: No primary or default constructor found for int] with root cause
```

当时报错的demo关键代码部分如下

```java
// Model中传递数据的时候产生的类型转换异常
设置int类型参数：model.addAttribute("model02", 11);
// 这里接收model中的参数的时候出现了异常报错
获取：@ModelAttribute("model02") int model02,
结果：int是没有空参数的构造函数导致最后出错了。
```
   
---

<a id="_100.4"></a>

**4. Model中存放的数据的作用域是什么作用域？**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

参考了一篇博客，结论如下

```java
Model 数据传值本质还是 request作用域进行传值，（Model存在的意义就是替换request），把内容最终放入到request作用域中　
```
