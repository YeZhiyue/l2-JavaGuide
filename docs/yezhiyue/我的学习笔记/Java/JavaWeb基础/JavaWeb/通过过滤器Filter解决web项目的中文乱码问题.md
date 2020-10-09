#### 事先说明：这里的问题解决是针对web项目中怎么通过Filter过滤器解决当前项目下所有Servlet资源的中文乱码问题。

 - 一是post请求出现的中文乱码； 
 - 二是respose输出字符流出现的中文乱码问题；

## 一、配置Filter的拦截路径

> 	为了能过使得当前项目下所有的Servlet资源都能解决乱码问题，我们配置了Filter的注解为@WebFilter("/*")，“/*”代表当前项目的所有Servlet资源都会被这个Filter过滤器过滤。

## 二、解决问题一，post请求参数乱码问题

> 1.再没有配置Filter过滤器之前

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020020522361788.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
> 	2.配置我们的Filter过滤器后

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205223559300.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
>  3.可以看到，配置了之后我们就解决了post请求的中文乱码问题

## 三、解决response输出流的乱码问题

> 	1.首先在没有配置之前，我们输出字符流到html页面上

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205223627193.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
> 	2.接着我们配置Filter过滤器，首先获取utf-8的字符流 resp.setCharacterEncoding("utf-8");接着我们告诉浏览器我们输出流的MIME数据类型是html、输出流的编码格式是utf-8(因为我们获取的流就是utf-8格式的)resp.setContentType("text/html;utf-8");。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205223645427.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
> 	3.最后，可以看到页面上的中文没有乱码

	
## 四、附上Filter的源码，如果是普通的web项目的话可以直接套用这个过滤器解决当前项目的中文乱码问题。

```java
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205223546333.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.解决response输出流的乱码问题
        req.setCharacterEncoding("utf-8");
        //2.解决post请求中的中文信息乱码问题
        //2.1 设置获取流的编码格式
        resp.setCharacterEncoding("utf-8");
        //2.2 告知浏览器我们输出的文件类型以及编码格式
        resp.setContentType("text/html;utf-8");
        chain.doFilter(req, resp);
    }
    public void init(FilterConfig config) throws ServletException {

    }
}
```

