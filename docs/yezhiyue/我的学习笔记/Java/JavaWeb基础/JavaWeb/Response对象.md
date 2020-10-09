# ==Response对象==
## <font color=#CC3333 face=微软雅黑>* 功能：设置响应消息</font>
####     1. 设置响应行

> 1. 格式：HTTP/1.1 200 ok
> 2. 设置状态码：setStatus(int sc)
####     2. 设置响应头：
> setHeader(String name, String value) 

####     3. 设置响应体：
> * 使用步骤：
> 1. 获取输出流
> * 字符输出流：PrintWriter getWriter()

> * 字节输出流：ServletOutputStream getOutputStream()

> 2. 使用输出流，将数据输出到客户端浏览器







####     2. 服务器输出字符数据到浏览器
> * 步骤：
> 1. 获取字符输出流
> 2. 输出数据

> * 注意：
> * 乱码问题：
> 1. PrintWriter pw = response.getWriter();获取的流的默认编码是ISO-8859-1
> 2. 设置该流的默认编码
> 3. 告诉浏览器响应体使用的编码

> //简单的形式，设置编码，是在获取流之前设置
> response.setContentType("text/html;charset=utf-8");
####     3. 服务器输出字节数据到浏览器
> * 步骤：
> 1. 获取字节输出流
> 2. 输出数据

####     4. 验证码
> 1. 本质：图片
> 2. 目的：防止恶意表单注册


