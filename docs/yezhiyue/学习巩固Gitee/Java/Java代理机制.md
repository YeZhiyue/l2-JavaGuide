<font color=#ca0c16 size=8> Java代理

<a id="_top"></a>

@[TOC](文章目录)

---

# 代理模式

代理模式的作用就是扩展原有对象的功能，但是不去修改原来对象的代码。

---

# 静态代理

静态代理中，我们对目标对象的每个方法的增强都是手动完成的,非常不灵活并且麻烦。实际使用场景很少。

静态代理实现步骤:

1. 定义一个接口以及其实现类
2. 创建一个代理类同样实现这个接口
3. 将目标对象注入进入代理类，然后在代理类的

---

# 动态代理

相比于静态代理来说，动态代理更加灵活。我们不需要针对每个目标了都单独创建一个代理类，并且也不需要我们必须实现接口，我们可以直接代理实现类。

从JVM角度来说，动态代理是在运行时动态生成字节码，并且加载到JVM中。

SpringAOP、RPC框架是两个运用了动态代理的经典例子。

动态代理在我们日常开发中使用的相对较少，但是在框架中几乎是必须要使用的一门技术。学会了动态代理之后，对于我们理解和学习各种框架的原理也是非常有帮助的。

对于Java来说，动态代理的实现方式有很多中，比如JDK动态代理、CGLIB动态代理等等。

## JDK动态代理机制

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

在Java动态代理机制中InvocationHandler接口和Proxy类是核心。

Proxy类中使用频率最高的方法是：newProxyInstance(),这个方法主要用来生成一个代理对象。

```java
public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandlerh)
throws IllegalArgumentException{
    ...
}
```

3个参数：

1. loader：类加载器，用于加载代理对象
2. interfaces:被代理类实现的一些接口
3. h:实现了InvocationHandler接口的对象

要实现动态代理的话，还必须需要实现InvocationHandler来自定义处理逻辑。当我们的动态代理对象调用一个方法的时候，这个方法的调用就会被转发到实现InvocationHandler来自定义处理逻辑。当我们的动态代理对象调用一个方法的时候，这个方法的调用就会被转发到实现InvocatonHandler接口类的invoke方法来调用。

```java
import java.lang.reflect.Method;public interface InvocationHandler{
    public Object invoke(Object javaguide.proxy,Method menthod,Object[] args) throws Throwable;
}
```

1. javaguide.proxy:动态生成的代理类
2. method：与代理类对象嗲用的方法相对应
3. args：当前method方法的参数

也就是说：你通过Proxy类的newProxyInstance()创建的代理对象在调用方法的时候，是机会调用到实现InvocationHandler接口类的invoke()方法。你可以在invoke()方法中定义处理逻辑比如在方法执行前后做什么事情。

JDK动态代理类的使用步骤：

1. 定义一个接口以及其实现类
2. 自定义InvocationHandler并且重写invoke方法，在invoke方法中我们会调用原生方法并且自定义一些处理逻辑
3. 通过 Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) 方法创建代理对象



## CGLIB动态代理机制

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

JDK动态代理的有一个致命的问题就是代理只能代理实现了接口的类。但是我们可以通过CGLIB动态代理机制来避免。

CGLIB是一个基于ASM的字节码生成库，他允许我们在运行时对字节码进行修改和动态生成。CGLIB通过集成方式来实现代理。很多知名的开源框架都使用到了CGLIB，例如Spring中的AOP模块中：如果目标对象是实现了接口，那么默认采用JDK动态代理，否则采用CGLIB动态代理。

在CGLIB动态代理机制中MethodInterceptor接口和Enhancer类是核心。

你需要自定义MethodInterceptor并且重写intercept方法，intercept用于拦截增强被代理类的方法。

```java
import javax.security.auth.callback.Callback;import java.lang.reflect.Method;

public interface Methodinterceptor extends Callback{
    // 拦截被代理类中的方法
    public Object intercept(Object obj,Method method,Object[] args,MethodProxy javaguide.proxy) throws Throwable;
}
```

1. obj:被代理的对象(需要增强的对象)
2. method：被拦截的方法(需要增强的方法)
3. args：方法入参
4. methodProxy：用于调用原始方法

你可以通过 Enhancer 类来动态获取被代理类，当代理类调用方法的时候，实际调用的是 MethodInterceptor 中的 intercept 方法。



---

# 静态代理和动态代理的对比

---

# 总结
