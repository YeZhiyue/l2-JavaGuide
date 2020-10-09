# SpringMVC常用配置(基于SpringBoot)

[SpringMVC官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-requestmapping)

<a id="_top"></a>

## `目录:`


---

<a id="_1"></a>

## `配置拦截器`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

[参考博客](https://www.cnblogs.com/vegeta-xiao/p/12509010.html)

<a id="_1.1"></a>

**1. 配置我们的SpringMVC的配置类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

> 注册拦截器，添加视图解析器，自定义国际化化区域解析器

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 使用WebMvcConfigurer可以来扩展SpringMVC的功能
 * @EnableWebMvc   全面接管SpringMVC，这个注解一般不用
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 添加视图解析器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/lalala").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //SpringBoot已经做好了静态资源映射
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/don'tInterceptor"/*,"/index.html","/","/user/login"*/);
    }

    /**
     * 自定义国际化化区域解析器
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
```

---

<a id="_1.2"></a>

**2. 配置我们的拦截器**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

2.1 这里是我们演示的结果：可以知道拦截器和我们Controller中执行的顺序

preHandler -> Controller -> postHandler -> afterCompletion

```java
############preHandle############
request.setAttribute("preHandler","preHandler");
preHandler: preHandler
InterceptorController: null
postHandlernull
afterCompletionnull
-----------------------------------------------------

############Controller在这里被执行了############
preHandler: preHandler
InterceptorController: InterceptorController
postHandlernull
afterCompletionnull
-----------------------------------------------------

############postHandle############
request.setAttribute("postHandler","postHandler")
preHandler: preHandler
InterceptorController: InterceptorController
postHandlerpostHandler
afterCompletionnull
-----------------------------------------------------

############afterCompletion############
preHandler: preHandler
InterceptorController: InterceptorController
postHandlerpostHandler
afterCompletionafterCompletion
-----------------------------------------------------
```

2.2 这里是我们的演示Demo

Controller代码

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InterceptorController {
    @RequestMapping("/interceptor")
    public String interceptor(HttpServletRequest request) {
        System.out.println("############Controller在这里被执行了############");
        request.setAttribute("InterceptorController", "InterceptorController");

        System.out.println(
                "preHandler: " + request.getAttribute("preHandler") +"\n"+
                        "InterceptorController: " + request.getAttribute("InterceptorController") +"\n"+
                        "postHandler" + request.getAttribute("postHandler") +"\n"+
                        "afterCompletion" + request.getAttribute("afterCompletion")+"\n"+
                        "-----------------------------------------------------"+"\n"
        );
        return "index";
    }
}
```

拦截器代码

```java
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，登陆检查
 * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 简单测试
         */
        System.out.println("############preHandle############");
        System.out.println("request.setAttribute(\"preHandler\",\"preHandler\");");
        request.setAttribute("preHandler","preHandler");

        System.out.println(
                "preHandler: " + request.getAttribute("preHandler") +"\n"+
                        "InterceptorController: " + request.getAttribute("InterceptorController") +"\n"+
                        "postHandler" + request.getAttribute("postHandler") +"\n"+
                        "afterCompletion" + request.getAttribute("afterCompletion")+"\n"+
                        "-----------------------------------------------------"+"\n"
        );
        return true;

        /**
         * 下面是普通Session用户验证
         */
        /*Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }*/

        /**
         * 下面是Token验证示例
         */
        /*logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        //request.getHeader(String) 从请求头中获取数据
        //从请求头中获取用户token（登陆凭证根据业务而定）
        Long userId= getUserId(request.getHeader("H-User-Token"));
        if (userId != null && checkAuth(userId,request.getRequestURI())){
            return true;
        }
        //这里的异常是我自定义的异常，系统抛出异常后框架捕获异常然后转为统一的格式返回给前端， 其实这里也可以返回false
        throw new FastRuntimeException(20001,"No access");*/
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView （这个博主就基本不怎么用了）；
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("############postHandle############");
        System.out.println("request.setAttribute(\"postHandler\",\"postHandler\")");
        request.setAttribute("postHandler","postHandler");

        System.out.println(
                "preHandler: " + request.getAttribute("preHandler") +"\n"+
                        "InterceptorController: " + request.getAttribute("InterceptorController") +"\n"+
                        "postHandler" + request.getAttribute("postHandler") +"\n"+
                        "afterCompletion" + request.getAttribute("afterCompletion")+"\n"+
                        "-----------------------------------------------------"+"\n"
        );
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("############afterCompletion############");
        request.setAttribute("afterCompletion","afterCompletion");
        System.out.println(
                "preHandler: " + request.getAttribute("preHandler") +"\n"+
                        "InterceptorController: " + request.getAttribute("InterceptorController") +"\n"+
                        "postHandler" + request.getAttribute("postHandler") +"\n"+
                        "afterCompletion" + request.getAttribute("afterCompletion")+"\n"+
                        "-----------------------------------------------------"+"\n"
        );
    }
}

```


---

<a id="_2"></a>

## `配置我们的异常处理器`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. 配置方式一：直接配置我们的方法处理器**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

[参考博客](https://blog.csdn.net/yzh_1346983557/article/details/83308654)


1. 配置步骤如下
    - 配置我们的配置参数
    - 编写我们的异常JavaBean
    - 编写我们的异常处理器

1.1 配置我们的配置参数

```java
#出现错误时, 直接抛出异常
spring.mvc.throw-exception-if-no-handler-found=true
```

1.2 编写我们的异常JavaBean

```java
import java.io.Serializable;


public class HttpResponse implements Serializable {

    private Integer code;//响应码
    private String message;//响应说明
    private Object data;//响应数据

    public HttpResponse() {
    }

    public HttpResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
```

1.3 编写我们的异常处理器
    
```java
import com.example.demo.proj.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * 前端参数错误   400
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        exception.printStackTrace();
        return new HttpResponse(1, "CodeEnum.TYPEERROR.getValue()", null);
    }

    /**
     * 请求路径错误   404
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse noHandlerFoundException(NoHandlerFoundException exception) {
        exception.printStackTrace();
        return new HttpResponse(2, "CodeEnum.NOMAPPING.getValue()", null);
    }

    /**
     * 运行时异常    500
     *
     * @param re
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse runtimeException(RuntimeException re) {
        re.printStackTrace();
        return new HttpResponse(3, "CodeEnum.ERROR.getValue()", null);
    }

}
```


