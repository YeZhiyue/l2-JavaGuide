# Java`基础入门&&进阶`复习笔记(易混淆API，基本概念)


<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self">基础API易错点示例</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">Random类</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">Scanner类</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">String类</a>
### <a href="#_2" rel="nofollow" target="_self">概念和关键字</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">接口(不同JDK版本之间的改变)</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">内部类</a>
2.3 <a href="#_2.3" rel="nofollow" target="_self">Final关键字注意点</a>
2.4 <a href="#_2.4" rel="nofollow" target="_self">泛型</a>
2.5 <a href="#_2.5" rel="nofollow" target="_self">异常</a>
2.6 <a href="#_2.6" rel="nofollow" target="_self">线程(同步、线程状态和通信)</a>
2.7 <a href="#_2.7" rel="nofollow" target="_self">网络编程</a>
### <a href="#_3" rel="nofollow" target="_self">进阶API使用</a>
3.1 <a href="#_3.1" rel="nofollow" target="_self">Object&&Objects</a>
3.2 <a href="#_3.2" rel="nofollow" target="_self">Date&&DateFormat&&Calendar</a>
3.3 <a href="#_3.3" rel="nofollow" target="_self">System类</a>
3.4 <a href="#_3.4" rel="nofollow" target="_self">StringBuild&&StringBuffer</a>
3.5 <a href="#_3.5" rel="nofollow" target="_self">Collection&&Iterator</a>
3.6 <a href="#_3.6" rel="nofollow" target="_self">HashMap&&LinkedHashMap</a>
3.7 <a href="#_3.7" rel="nofollow" target="_self">字节流&&字节缓冲流</a>
3.8 <a href="#_3.8" rel="nofollow" target="_self">字符流&&字符缓冲流</a>
3.9 <a href="#_3.9" rel="nofollow" target="_self">转换流(编码转换)</a>
3.10 <a href="#_3.10" rel="nofollow" target="_self">数据流</a>
3.11 <a href="#_3.11" rel="nofollow" target="_self">序列化与反序列化</a>
### <a href="#_4" rel="nofollow" target="_self">函数式接口</a>
4.1 <a href="#_4.1" rel="nofollow" target="_self">Supplier 返回指定泛型数据</a>
4.2 <a href="#_4.2" rel="nofollow" target="_self">Consumer 消费指定泛型数据</a>
4.3 <a href="#_4.3" rel="nofollow" target="_self">Predicate 返回数据判断结果</a>
4.4 <a href="#_4.4" rel="nofollow" target="_self">数据类型转换</a>
### <a href="#_5" rel="nofollow" target="_self">Stream流</a>
5.1 <a href="#_5.1" rel="nofollow" target="_self">常用集合转换为Stream流</a>
5.2 <a href="#_5.2" rel="nofollow" target="_self">Stream 链式编程</a>
### <a href="#_6" rel="nofollow" target="_self">方法引用</a>
6.1 <a href="#_6.1" rel="nofollow" target="_self">通过对象名来进行方法引用</a>
6.2 <a href="#_6.2" rel="nofollow" target="_self">通过类名引用静态方法</a>
6.3 <a href="#_6.3" rel="nofollow" target="_self">通过 super/this 引用成员方法</a>
6.4 <a href="#_6.4" rel="nofollow" target="_self">类的构造器引用</a>
6.5 <a href="#_6.5" rel="nofollow" target="_self">数组的构造器引用</a>

---

<a id="_1"></a>

## `基础API易错点示例`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. Random类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Random类

```java
import java.util.Random;
import java.util.Scanner;
public class RandomTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你需要设置的随机数范围 0 - n");
        int scaInt = scanner.nextInt();

        Random random = new Random(100);
        double v;
        for (int i = 0; i < 100; i++) {
             v = random.nextInt(scaInt);
            System.out.println(v);
        }
    }
}
```

---

<a id="_1.2"></a>

**2. Scanner类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Scanner类

```java
import java.util.Scanner;
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入整数，输入 -1 表示结束");
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (num == -1) {
                break;
            }
            System.out.println(num);
        }
    }
}
```

---

<a id="_1.3"></a>

**3. String类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>String类

```java
import org.junit.Test;
import java.util.Arrays;
public class StringTest {
    @Test
    public void test01(){
        /**
         * 这里演示一些比较容易混淆的StringAPI
         */
        String str = "Hello World!";

        // 返回目标字符第一次出现索引(索引是从0开始)  - >  2
        System.out.println(str.indexOf('l'));
        // 截取部分字符串(注意是 1 =< n <2 ，不包括索引值为2的字符)  - > e
        System.out.println(str.substring(1, 2));

        /**
         * 结果，注意其中最左边的 "H" 被切分也会多出一个空串 ""
         * ""
         * "ello World!"
         * "He"
         */
        String[] split01 = str.split("H");
        Arrays.stream(split01).forEach(s -> System.out.println("\""+s+"\""));

        /**
         * 结果，注意其中两个在一起 "ll" 也会被切分出一个空串 ""
         * ""
         * "o Wor"
         * "d!"
         */
        String[] split02 = str.split("l");
        Arrays.stream(split02).forEach(s -> System.out.println("\""+s+"\""));
    }
}
```

---

<a id="_2"></a>

## `概念和关键字`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. 接口(不同JDK版本之间的改变)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>接口(不同JDK版本之间的改变)

```java
public interface InterfaceTest {
    /**
     * 注意不同版本JDK之间的区别
     * 包含抽象方法（JDK 7及以前），默认方法和静态方法（JDK 8），私有方法（JDK 9）
     */

    // 抽象方法
    abstract void abstractMethod();

    // 默认方法
    default void defaultMethod() {

    }

    // 静态方法
    static void staticMethod() {

    }
    // 私有方法(由于用的是jdk8，这里不做演示了)
    /*private void privateMethod(){

    }*/
}
```

---

<a id="_2.2"></a>

**2. 内部类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
public class InnerClassTest {
    class Car { //外部类
        private String member01 = "mem01";

        public Car() {
            // 特点一：外部类要访问内部类的成员，必须要建立内部类的对象。
            // 访问的话会报错 Cannot resolve symbol 'member02'
            // System.out.println(member02);
            Engine engine = new Engine();
            System.out.println(engine.member02);
        }

        class Engine { //内部类
            private String member02 = "mem02";
            public Engine() {
                // 特点二：内部类可以直接访问外部类的成员，包括私有成员。
                System.out.println(member01);
            }
        }
    }
}
```

---

<a id="_2.3"></a>

**3. Final关键字注意点**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Final关键字注意点

```java
public class FinalTest {
    private final String STR = "STR";
    /**
     * 注意如果你在这里没有初始化的话，
     * 那么必须再你所有构造器中提供该
     * final字段的初始化方法
     */
    private final String STRCON;

    public FinalTest() {
        STRCON = "initInConstructor01";
    }

    public FinalTest(String arg) {
        STRCON = "initInConstructor02";
    }

    public String getSTRCON() {
        return STRCON;
    }

    public static void main(String[] args) {
        //initInConstructor01
        System.out.println(new FinalTest().getSTRCON());
        //initInConstructor02
        System.out.println(new FinalTest("next").getSTRCON());
    }
}
```

---

<a id="_2.4"></a>

**4. 泛型**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>泛型

4.1 泛型类和泛型方法

```java
// <T> 这个可以理解为定义了一个动态的类型(也就是不确定的类型)，我们可以通过这个声明其他的类型

// 1. 泛型类的定义
class SayHello<T>{

    public SayHello(T t) {
        System.out.println(t);
    }

    // 2. 泛型方法的定义
    public <M> M getHelloT(M m) {
        return m;
    }
}
```

简单的小测试

```java
public class TTest {

    public static void main(String[] args) {

        SayHello<String> hello = new SayHello<>("Hello");

        List<Integer> helloT = hello.getHelloT(Arrays.asList(1, 2, 3));
        System.out.println(helloT);
    }
}
```

4.2 动态泛型和受限泛型

```java
// 3. 集成另外一个接口并且实现他的动态泛型
class Sort<T> extends SayHello<T> {

    public Sort(T t) {
        super(t);
        System.out.println(t);
    }

    public <M extends Collection> M sortB(M m) {
        return m;
    }

    // 受限泛型：只能接收该类型及其子类
    // 格式：类型名称 <? extends 类 > 对象名称
    public Collection sortC(Collection<? extends ArrayList> m) {
        return m;
    }

    // 受限泛型：只能接收该类型及其父类型
    // 格式：类型名称 <? super 类 > 对象名称
    public Collection sortD(Collection<? super RoleList> m) {
        return m;
    }
}
```

---

<a id="_2.5"></a>

**5. 异常**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>异常

5.1 声明、抛出异常

```java
@Test
// 声明异常
public void throwsTest() throws Exception {
    if (true) {
        // 抛出异常
        throw new Exception("Test exception");
    }
}
```

5.2 捕获异常

```java
@Test
public void tryCatchTest(){
    /**
     * 捕获异常：
     * java.lang.ArithmeticException: / by zero
     * 我最终执行了
     */
    try {
        int x = 1/0;
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        System.out.println("我最终执行了");
    }
}
```

5.3 自定义异常

```java
@Test
public void exceptionTest() throws MyException {
    if (true) {
        // level2.ExceptionTest$MyException: 自定义异常
        throw new MyException("自定义异常");
    }
}

class MyException extends Exception{
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
```

---

<a id="_2.6"></a>

**6. 线程(同步、线程状态和通信)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>线程

6.1 线程的创建

```java
/**
 * 简单的线程创建
 */
@Test
public void threadTest() {
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Current num is : " + i);
            }
        }
    });

    t.start();
}
```

6.2 同步锁 synchronize

```java
package level2;

public class ThreadTest {
    public static void main(String[] args) {
        MyRunnable run = new MyRunnable();
        new Thread(run, "线程一 ").start();
        new Thread(run, "线程二 ").start();
    }
}

class MyRunnable implements Runnable {
    private static int ticket = 20;

    private final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String name = Thread.currentThread().getName();
                    // 如果没有加锁，那么这里很有可能出现并行执行导致线程非同步问题
                    System.out.println(name + " 正在卖： " + ticket--);
                }
                /**
                 * 其他同步方法：
                 *     sell01();
                 *     sell02();
                 *     sell03();
                 *     sell04();
                 */

            }
        }
    }

    // 非static同步方法块默认使用 this 对象
    public synchronized void sell01() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();
            // 如果没有加锁，那么这里很有可能出现并行执行导致线程非同步问题
            System.out.println(name + " 正在卖： " + ticket--);
        }
    }

    // static 的同步代码块默认使用 Class 对象
    public static synchronized void sell02() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();
            // 如果没有加锁，那么这里很有可能出现并行执行导致线程非同步问题
            System.out.println(name + " 正在卖： " + ticket--);
        }
    }

    public static void sell03() {
        synchronized (MyRunnable.class) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                // 如果没有加锁，那么这里很有可能出现并行执行导致线程非同步问题
                System.out.println(name + " 正在卖： " + ticket--);
            }
        }
    }

    public void sell04() {
        synchronized (this) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                // 如果没有加锁，那么这里很有可能出现并行执行导致线程非同步问题
                System.out.println(name + " 正在卖： " + ticket--);
            }
        }
    }
}
```

6.3 同步锁 Lock

```java
package level2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        MyRunnable run = new MyRunnable();
        new Thread(run, "线程一 ").start();
        new Thread(run, "线程二 ").start();
    }
}

class MyRunnableLock implements Runnable {
    private static int ticket = 20;

    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                // 如果没有加锁，那么这里很有可能出现并行执行导致线程非同步问题
                System.out.println(name + " 正在卖： " + ticket--);
            }
            lock.unlock();
        }
    }
}
```

6.4 线程状态和通信

- 线程之间的通信需要通过同一把锁进行通信，只有两个线程用了同一把锁，那么这两个线程才可以进行配合通信

```java
package level2;

public class ThreadStatusTest {
    public static void main(String[] args) {
        MiddleMan middleMan = new MiddleMan();

        /**
         * 效果：线程之间的通信需要通过同一把锁进行通信，只有两个线程用了同一把锁，那么这两个线程才可以进行配合通信
         * 正在唤醒线程 ... 
         * 我被唤醒了！
         */
        new Thread(new MyR01(middleMan)).start();
        new Thread(new MyR02(middleMan)).start();

    }
}
class MiddleMan{

}
class MyR01 implements Runnable{
    private MiddleMan middleMan;

    public MyR01(MiddleMan middleMan) {
        this.middleMan = middleMan;
    }

    @Override
    public void run() {
        synchronized (middleMan) {
            Thread t = Thread.currentThread();
            try {
                middleMan.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我被唤醒了！");
        }
    }
}

class MyR02 implements Runnable{
    private MiddleMan middleMan;

    public MyR02(MiddleMan middleMan) {
        this.middleMan = middleMan;
    }

    @Override
    public void run() {
        synchronized (middleMan) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("正在唤醒线程 ... ");
            middleMan.notify();
        }
    }
}
```

---

<a id="_2.7"></a>

**7. 网络编程**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>网络编程

7.1 重要概念

```java
/**
 * 网络变成说明：
 *      端口号范围：0 ~ 65535   其中 0~1023 是一些知名网络的端口号
 *      ip4  8进制作为一组，分为4组，然后组合成一个 ip 地址 例如`192.168.65.100`
 *      ip6  16进制为一组，分为6组，然后组成一个ip地址 例如`ABCD:EF01:2345:6789:ABCD:EF01:2345:6789`
 *
 * 核心要素:利用`协议`+`IP地址`+`端口号` 三元组合，就可以标识网络中的进程了，那么进程间的通信就可以利用这个标识与其它进程进行交互。
 */
```

7.2 服务端实现

```java
@Test
public void FileUpload_Server() throws IOException {
    System.out.println("服务器 启动.....  ");
    // 1. 创建服务端ServerSocket
    ServerSocket serverSocket = new ServerSocket(6666);
    // 2. 循环接收,建立连接
    while (true) {
        Socket accept = serverSocket.accept();
        /*
        3. socket对象交给子线程处理,进行读写操作
           Runnable接口中,只有一个run方法,使用lambda表达式简化格式
        */
        new Thread(() -> {
            try (
                    //3.1 获取输入流对象
                    BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
                    //3.2 创建输出流对象, 保存到本地 .
                    FileOutputStream fis = new FileOutputStream(System.currentTimeMillis() + ".jpg");
                    BufferedOutputStream bos = new BufferedOutputStream(fis);
            ) {
                // 3.3 读写数据
                byte[] b = new byte[1024 * 8];
                int len;
                while ((len = bis.read(b)) != -1) {
                    bos.write(b, 0, len);
                }

                // 4.=======信息回写===========================
                System.out.println("back ........");
                OutputStream out = accept.getOutputStream();
                out.write("上传成功".getBytes());
                out.close();
                //================================

                //5. 关闭 资源
                bos.close();
                bis.close();
                accept.close();
                System.out.println("文件上传已保存");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
```

7.3 客户端实现

```java
@Test
public void FileUpload_Client() throws IOException {
    // 1.创建流对象
    // 1.1 创建输入流,读取本地文件
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("java.md"));
    // 1.2 创建输出流,写到服务端
    Socket socket = new Socket("localhost", 6666);
    BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

    //2.写出数据.
    byte[] b = new byte[1024 * 8];
    int len;
    while ((len = bis.read(b)) != -1) {
        bos.write(b, 0, len);
    }
    // 关闭输出流,通知服务端,写出数据完毕
    socket.shutdownOutput();
    System.out.println("文件发送完毕");
    // 3. =====解析回写============
    InputStream in = socket.getInputStream();
    byte[] back = new byte[20];
    in.read(back);
    System.out.println(new String(back));
    in.close();
    // ============================
    // 4.释放资源
    socket.close();
    bis.close();
}
```

7.4 模拟B/S服务器

```java
/**
 * 模拟我们服务器的实现
 */
@Test
public void BSClientTest() throws IOException {
    ServerSocket server = new ServerSocket(8888);
    while(true){
        Socket socket = server.accept();
        new Thread(new Web(socket)).start();
    }
}
class Web implements Runnable{
    private Socket socket;

    public Web(Socket socket){
        this.socket=socket;
    }

    public void run() {
        try{
            //转换流,读取浏览器请求第一行
            BufferedReader readWb = new
                    BufferedReader(new InputStreamReader(socket.getInputStream()));
            String requst = readWb.readLine();
            //取出请求资源的路径
            String[] strArr = requst.split(" ");
            System.out.println(Arrays.toString(strArr));
            String path = strArr[1].substring(1);
            System.out.println(path);

            FileInputStream fis = new FileInputStream(path);
            System.out.println(fis);
            byte[] bytes= new byte[1024];
            int len = 0 ;
            //向浏览器 回写数据
            OutputStream out = socket.getOutputStream();
            out.write("HTTP/1.1 200 OK\r\n".getBytes());
            out.write("Content-Type:text/html\r\n".getBytes());
            out.write("\r\n".getBytes());
            while((len = fis.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            fis.close();
            out.close();
            readWb.close();
            socket.close();
        }catch(Exception ex){

        }
    }
}
```

---

<a id="_3"></a>

## `进阶API使用`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_3.1"></a>

**1. Object&&Objects**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Object&&Objects

```java
@Test
public void ObjectTest() {
    // 这里重点讲一下equals方法
    /**
     * 源码：基础的比较是直接比较两个对象的地址，但是不比较对象中的内容
     *     public boolean equals(Object obj) {
     *         return (this == obj);
     *     }
     */
    Object obj = new Object();
    obj.equals(obj);

    // 测试我们重写的类中的class类的比较
    /**
     * 这里注意即使我们对这个类进行强转，但是这个类对象的比较依然是 false
     */
    Class<? extends Person> perClass = new Person().getClass();
    Student student = new Student();
    Person studentToPer = student;
    Class<? extends Person> stuClass = studentToPer.getClass();
    System.out.println(perClass==stuClass);
}


/**
 * 但是我们这里注意IDEA中对equals方法的重写
 * 1. 判断 this == o 判断两个对象的地址是否相同
 * 2. 判断 o == null 判断比较的对象是否为空
 * 3. 判断 getClass() != o.getClass() 判断两个对象是否属于同一个类
 * 4. 强转之后判断属性值是否相同
 *
 * @Override public boolean equals(Object o) {
 * if (this == o) return true;
 * if (o == null || getClass() != o.getClass()) return false;
 * <p>
 * Person person = (Person) o;
 * <p>
 * if (name != null ? !name.equals(person.name) : person.name != null) return false;
 * return age != null ? age.equals(person.age) : person.age == null;
 * }
 */
class Person {
    private String name;
    private int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}

class Student extends Person{

}
```

```java
@Test
public void ObjectsTest() {
    /**
     * 直接通过对象内部重写的 equals 方法进行比较
     */
    Objects.equals(new Object(), new Object());

    Person zhangsan = new Person("Zhangsan", 21);
    Person lisi = new Person("Lisi", 21);
    /**
     * 进行自定义规则的比较，并且返回整数  - > 0
     */
    int result = Objects.compare(zhangsan, lisi, (o1, o2) -> o1.getAge() - o2.getAge());
    System.out.println(result);
}
```

---

<a id="_3.2"></a>

**2. Date&&DateFormat&&Calendar**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Date&&DateFormat&&Calendar

```java
@Test
public void DateTest() throws ParseException {

    // 返回的标准时间与参数之间的差距 Thu Jan 01 08:00:00 CST 1970
    Date date = new Date(0l);
    System.out.println(date);

    // 其他格式补充 yyyy-MM-dd HH:mm:ss"
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    // 进行格式化 1970-01-01-08-00-00
    String format = simpleDateFormat.format(date);
    System.out.println(format);

    // 进行反格式化 Thu Jan 01 08:00:00 CST 1970
    Date parse = simpleDateFormat.parse(format);
    System.out.println(parse);

    Calendar calendar = Calendar.getInstance();
    // java.util.GregorianCalendar[time=1593438872772,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=29,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2020,MONTH=5,WEEK_OF_YEAR=27,WEEK_OF_MONTH=5,DAY_OF_MONTH=29,DAY_OF_YEAR=181,DAY_OF_WEEK=2,DAY_OF_WEEK_IN_MONTH=5,AM_PM=1,HOUR=9,HOUR_OF_DAY=21,MINUTE=54,SECOND=32,MILLISECOND=772,ZONE_OFFSET=28800000,DST_OFFSET=0]
    System.out.println(calendar);
    // 获取具体的年月日
    /**
     * 2020
     * 5
     * 29
     * 10
     * 1
     * 44
     */
    System.out.println(calendar.get(Calendar.YEAR)+
                    "\n"+calendar.get(Calendar.MONTH)+
                    "\n"+calendar.get(Calendar.DATE)+
                    "\n"+calendar.get(Calendar.HOUR)+
                    "\n"+calendar.get(Calendar.MINUTE)+
                    "\n"+calendar.get(Calendar.SECOND)
            );
    // 设置日历的时间
    calendar.add(Calendar.YEAR,2);
    // 2021
    System.out.println(calendar.get(Calendar.YEAR));
    // 2019
    calendar.add(Calendar.YEAR,-3);
    System.out.println(calendar.get(Calendar.YEAR));

    // 获取 Date 对象 Sat Jun 29 22:01:44 CST 2019
    System.out.println(calendar.getTime());
}
```

---

<a id="_3.3"></a>

**3. System类**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Systemlei

```java
@Test
public void SystemTest() {
    // 调优判断方法
    long currentTimeMillis = System.currentTimeMillis();

    // 复制方法
    int[] arr01 = new int[10];
    arr01[0]=1;
    arr01[1]=2;
    arr01[2]=3;
    int[] arr02 = new int[]{4,5};
    /**
     * 这里的复制会覆盖数组原来的内容
     *  destPos: 表示数组的下标，并且复制替换会从这个下标开始
     *  srcPos + length: 表示被复制的数组的下标起始位和复制的长度
     */
    // 注意这里很容易出现数据下表越界异常
    System.arraycopy(arr02,0,arr01,3,2);
    // 1234500000
    Arrays.stream(arr01).forEach(value -> System.out.print(value));
}
```

---

<a id="_3.4"></a>

**4. StringBuild&&StringBuffer**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>StringBuild&&StringBuffer

```java
@Test
public void StringBuilderTest() {
    // 默认16字符空间，超过自动扩充
    // 如果不是多线程，那么推荐使用这个，不会加锁
    StringBuilder str01 = new StringBuilder().append("1").append("2");
    System.out.println(str01.toString());


    // 如果是多线程，那么使用这个，里面封装了线程锁
    StringBuffer str02 = new StringBuffer().append("1").append("2");
    System.out.println(str02.toString());
}
```

---

<a id="_3.5"></a>

**5. Collection&&Iterator**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Collection&&Iterator

```java
@Test
public void collectionTest(){
    /**
     * * `public boolean add(E e)`：  把给定的对象添加到当前集合中 。
     * * `public void clear()` :清空集合中所有的元素。
     * * `public boolean remove(E e)`: 把给定的对象在当前集合中删除。
     * * `public boolean contains(E e)`: 判断当前集合中是否包含给定的对象。
     * * `public boolean isEmpty()`: 判断当前集合是否为空。
     * * `public int size()`: 返回集合中元素的个数。
     * * `public Object[] toArray()`: 把集合中的元素，存储到数组中。
     */
}
```

```java
@Test
public void iteratorTest() {
    ArrayList<Integer> integers = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        integers.add(i);
    }

    // 迭代器进行遍历
    Iterator<Integer> it = integers.iterator();
    // 判断是否有下一个元素
    while (it.hasNext()) {
        // 如果有下一个元素，那么就进行打印
        System.out.println(it.hasNext());
    }
}
```

---

<a id="_3.6"></a>

**6. HashMap&&LinkedHashMap**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>HashMap&&LinkedHashMap

6.1 HashMap

```java
@Test
public void mapTest() {
    /**
     * * `public V put(K key, V value)`:  把指定的键与指定的值添加到Map集合中。
     * * `public V remove(Object key)`: 把指定的键 所对应的键值对元素 在Map集合中删除，返回被删除元素的值。
     * * `public V get(Object key)` 根据指定的键，在Map集合中获取对应的值。
     * * `boolean containsKey(Object key)  ` 判断集合中是否包含指定的键。
     * * `public Set<K> keySet()`: 获取Map集合中所有的键，存储到Set集合中。
     * * `public Set<Map.Entry<K,V>> entrySet()`: 获取到Map集合中所有的键值对对象的集合(Set集合)。
     */

    HashMap<Integer, String> map = new HashMap<>();
    map.put(1, "Zhangsan");
    map.put(2, "Lisi");
    map.put(3, "Wangwu");

    // 防止空值获取的方法
    String aDefault = map.getOrDefault(4, "Default");
    // Default
    System.out.println(aDefault);

    // KeySet 遍历
    Set<Integer> keySet = map.keySet();
    /**
     * key is:1  value is: Zhangsan
     * key is:2  value is: Lisi
     * key is:3  value is: Wangwu
     */
    keySet.stream().forEach(integer -> System.out.println("key is: " + integer + "  value is: " + map.get(integer)));
    // EntryKey 遍历
    Set<Map.Entry<Integer, String>> entries = map.entrySet();
    entries.stream().forEach(integerStringEntry -> System.out.println(
            "key is: " + integerStringEntry.getKey() + "  " +
                    "value is: " + integerStringEntry.getValue()));


    /**
     * * 当给HashMap中存放自定义对象时，如果自定义对象作为key存在，这时要保证对象唯一，必须复写对象的hashCode和equals方法(如果忘记，请回顾HashSet存放自定义对象)。
     * * 如果要保证map中存放的key和取出的顺序一致，可以使用`java.util.LinkedHashMap`集合来存放。
     */
}
```

6.2 LinkedHashMap

```java
@Test
public void LinkedHashMapTest() {
    /**
     * 说明：保证map中存放的key和取出的顺序一致
     * 在HashMap下面有一个子类LinkedHashMap，它是链表和哈希表组合的一个数据存储结构。
     */
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "Zhangsan");
    map.put(2, "Lisi");
    map.put(3, "Wangwu");

    /**
     * key is: 1  value is: Zhangsan
     * key is: 2  value is: Lisi
     * key is: 3  value is: Wangwu
     */
    for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
        System.out.println(
                "key is: " + integerStringEntry.getKey() + "  " +
                "value is: " + integerStringEntry.getValue());
    }
}
```

---

<a id="_3.7"></a>

**7. 字节流&&字节缓冲流**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>字节流&&字节缓冲流

```java
@Test
public void byteStreamTest() {
    /**
     * 注意这里的相对路径以模块作为根目录，并且前面不要添加 ./ 或者 /
     */
    try (
            //字节文件流
            //输出流
            FileOutputStream fos = new FileOutputStream(MD_IO_MD, true);
            //输入流
            FileInputStream fis = new FileInputStream("md/FileInputStream.md")) {

        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Test
public void byteBufferTest() {
    try (
            //字节缓冲流
            //输出流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(MD_IO_MD));
            //输入流
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("md/BufferedOutputStream.md"))) {

        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

<a id="_3.8"></a>

**8. 字符流&&字符缓冲流**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>字符流&&字符缓冲流

```java
@Test
public void charStreamTest() {
    try (
            //字符流
            //输出流
            FileReader fr = new FileReader(MD_IO_MD);
            //输入流
            FileWriter fw = new FileWriter("md/FileWriter.md")) {

        char[] chars = new char[1024 * 8];
        int len = 0;
        while ((len = fr.read(chars)) != -1) {
            fw.write(chars, 0, len);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Test
public void charBufferedStreamTest() {
    try (
            //字符缓冲流
            //输出流
            BufferedReader br = new BufferedReader(new FileReader(MD_IO_MD));
            //输入流
            BufferedWriter bw = new BufferedWriter(new FileWriter("md/BufferedWriter.md"))) {

        String s;
        int len = 0;
        while ((s = br.readLine()) != null) {
            bw.write(s);
            bw.newLine();
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

<a id="_3.9"></a>

**9. 转换流(编码转换)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>转换流(编码转换)

```java
@Test
public void transferStreamTest() {
    try (
            //字符转换流
            //1.读取编码为gbk的文件
            InputStreamReader isr = new InputStreamReader(new FileInputStream(MD_IO_MD), "gbk");
            //2.输出编码为gbk的文件
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("md/OutputStreamWriter.md"), "GBK");) {
        char[] chars = new char[1024 * 8];
        int len = 0;
        while ((len = isr.read(chars)) != -1) {
            osw.write(chars,0,len);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

<a id="_3.10"></a>

**10. 数据流**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>数据流

```java
@Test
public void dataStreamTest1() {

    try (
            //1.用于存储字节数据的流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //2.用于输出字节数据的IO流
            DataOutputStream dos = new DataOutputStream(baos);
    ) {
        //进行字节数据的输出，可以输出任意的基本类型(整形、浮点型、布尔型、字符型...)
        dos.writeDouble(Math.random());
        dos.writeBoolean(true);
        //3.字节流的读取流
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //判断有字节数据有多长
        System.out.println(bais.available());
        //4.开始数据流的读取
        DataInputStream dis = new DataInputStream(bais);
        //5.开始输出，注意由于Data流的输入输出的数据结构是根据队列来进行的
        //先进先出的原则。所以我们需要根据次序读取，否则数据会出错
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        dis.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Test
public void dataStreamTest2() {
    String fileObj = "F:\\A_JavaPro\\2020NewStudy\\849";
    try (
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileObj));
            DataInputStream dis = new DataInputStream(new FileInputStream(fileObj))
    ) {
        //注意了，下面数据的读取必须根据队列的顺序进行读取。否则数据的读取就是错误的
        dos.writeDouble(Math.random());
        dos.writeBoolean(true);
        dos.writeBoolean(false);
        dos.writeInt(33);
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readInt());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

<a id="_3.11"></a>

**11. 序列化与反序列化**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>序列化与反序列化

```java
public class MyTest implements Serializable {

    //序列化对象，需要实现 接口Serializable
    class Person implements Serializable {
        // 加入序列版本号，防止以后类修改导致反序列化操作的失败
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;
        //使用transient关键字让该变量不参与序列化操作
        private /*transient*/ String tel_num;

        public Person(String name, int age, String tel_num) {
            this.name = name;
            this.age = age;
            this.tel_num = tel_num;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", tel_num='" + tel_num + '\'' +
                    '}';
        }
    }

    @Test
    public void serialTest() {
        try (
                //进行序列化操作
                ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("序列化文件"));
        ) {
            Person per = new Person("张三", 28, "1589009");
            obj.writeObject(per);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unSerialTest() {
        try (
                //进行反序列化操作
                ObjectInputStream obj2 = new ObjectInputStream(new FileInputStream("序列化文件"));
        ) {
            Object o = obj2.readObject();
            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

---

<a id="_4"></a>

## `函数式接口`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_4.1"></a>

**1. Supplier 返回指定泛型数据**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Supplier 返回指定泛型数据

```java
/**
 * 返回一个指定泛型的数据
 */
public <T> T getMax(Supplier<T> sup) {
    return sup.get();
}

@Test
public void supplierTest() {
    Integer max = getMax(() -> {
        int[] arr = {1, 2, 3};
        return arr[arr.length - 1];
    });
    System.out.println(max);
}
```

---

<a id="_4.2"></a>

**2. Consumer 消费指定泛型数据**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Consumer 消费指定泛型数据

2.1

```java
/**
 * 消费数据
 */
public <T> void comsum01(T content,Consumer<T> function) {
    function.accept(content);
}

@Test
public void comsumerTest01() {
    comsum01("Hello Comsumer !!!",s -> System.out.println(s));
}
```

2.2 链式消费数据,消费方式不同，但是消费的数据源只有一个

```java
/**
 * 链式消费数据,消费方式不同，但是消费的数据源只有一个
 */
public <T> void comsum02(T content, Consumer<T> function01, Consumer<T> function02) {
    function01.andThen(function02).accept(content);
}
@Test
public void comsumerTest02() {
    comsum02("Hello Comsumer !!!",s -> System.out.println("消费方式一： "+s),s -> System.out.println("消费方式二： "+s));
}
```

---

<a id="_4.3"></a>

**3. Predicate 返回数据判断结果**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * 返回数据判断结果
 */
public <T> boolean judge(T t,Predicate<T> predicate) {
    return predicate.test(t);
}

@Test
public void predicateTest() {
    boolean judge = judge("Hello World!!!", s -> {
        return s.contains("llo");
    });
    System.out.println(judge);
}
```

---

<a id="_4.4"></a>

**4. 数据类型转换**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>数据类型转换

```java
/**
 * 数据类型转换
 * 演示 int -> String -> boolean
 */
public <T, R ,G> G change(T t, Function<T, R> fun1,Function<R,G> fun2) {
    return fun1.andThen(fun2).apply(t);
}

@Test
public void functionTest() {
    Boolean result = change(100, integer -> integer.toString(), s -> s.equals("100"));
    System.out.println(result);
}
```

---

<a id="_5"></a>

## `Stream流`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. 常用集合转换为Stream流**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 普通数组

```java
/**
 * 普通数组
 */
int[] arr = {1, 2, 3};
Arrays.stream(arr).forEach(value -> System.out.println(value));
```

1.2 List集合

```java
/**
 * List集合
 */
Arrays.asList(4, 5, 6).stream().forEach(integer -> System.out.println(integer));
```

1.3 Map集合

```java
/**
 * Map集合
 */
Map<Integer, String> map = new HashMap<>();
map.put(7, "seven");
map.put(8, "eight");
map.put(9, "nine");
map.keySet().stream().forEach(integer -> System.out.println(integer+map.get(integer)));
```

1.4 直接通过静态方法构建Stream流

```java
Stream.of("1", "2", "3").forEach(value -> System.out.println(value));
```

---

<a id="_5.2"></a>

**2. Stream 链式编程**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>Stream 链式编程

2.1 forEach、map、limit、skip、filter

```java
@Test
public void apiTest() {
    // 遍历处理
    Stream.of("1", "2", "3").forEach(value -> System.out.println(value));

    // 条件过滤
    Stream.of("1", "2", "3").filter(s -> s.equals("2")).forEach(s -> System.out.println(s));

    // 对Stream流中数据进行转换
    Stream.of("1", "2", "3").map(s -> Integer.parseInt(s)).forEach(integer -> System.out.print(integer.getClass()));

    // 统计元素个数
    long count = Stream.of("1", "2", "3").count();
    System.out.println("统计的计数： "+count);

    // 取出前面几个
    Stream.of("1", "2", "3").limit(2).forEach(s -> System.out.println(s));

    // 跳过前面几个
    Stream.of("1", "2", "3").skip(2).forEach(s -> System.out.println(s));

    // 合并两个Stream流
    Stream.concat(Stream.of("1", "2", "3"), Stream.of("1", "2", "3")).forEach(s -> System.out.println(s));
}
```

---

<a id="_6"></a>

## `方法引用`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. 通过对象名来进行方法引用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>通过对象名来进行方法引用

```java
    /**
     * 提出问题：在刚刚接触方法引用的时候我们会有这么一个疑惑，就是方法引用的参数是怎么传过来的，难道是通过
     *      其他方法签名进行智能前后推断获取的吗？
     * 解答：其实方法引用不能进行智能的传参数，参数的传递是靠你调用方法的内部逻辑进行代入(也就是说这个参数
     *      的传递是由我们自己决定的)，但是方法中的参数**类型**绑定可以进行智能的判断，就是方法引用的参数
     *      是可以通过函数式接口的泛型类型进行绑定的。
     */
    @Test
    public void test01() {
        /**
         * 通过对象名来进行方法引用
         *
         * 注意：这里如何实现方法引用不会报错，方法引用的推断是根据方法引用中的 (参数 和 函数式接口中的泛型) 来确定
         *      的，如果函数式接口没有使用泛型，那么就不能通过对象名进行方法引用。
         *      1. lam::say(T t) - > p(AnyFun<T> printable)
         *      2. str::codePointAt(int index) - > p(AnyFun<Integer> printable)
         *      3. str::toUpperCase(Local local) - > p(AnyFun<Local> printable)
         */
        lambadaObj lam = new lambadaObj();
        //
        p(lam::say);
        String str = "01234";
        p("",str::codePointAt);
        p(1,str::toUpperCase);
    }

    class lambadaObj{
        public <T> void say(T t) {
            System.out.println("Mytest");
        }
    }

    @FunctionalInterface
    interface AnyFun<T>{
        void atWill(T s);
    }

    public <T> void p(AnyFun<T> printable) { }
    public void p(String str,AnyFun<Integer> printable) {
        printable.atWill(Integer.parseInt(str));
    }
    public <T> void p(int begin,AnyFun<Locale> printable) { }
```

---

<a id="_6.2"></a>

**2. 通过类名引用静态方法**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>通过类名引用静态方法

```java
/**
 * 通过类名称引用静态方法
 */
@Test
public void test02() {
    // 参数并不是直接代入的，而是间接代入的，参数代入的逻辑是由我们自己控制的
    int result = cacuM(-2, Math::abs);
    System.out.println(result);
}

public int cacuM(int num, Calcable lambda) {
    return lambda.cacu(num);
}

@FunctionalInterface
public interface Calcable{
    int cacu(int n);
}
```

---

<a id="_6.3"></a>

**3. 通过 super/this 引用成员方法**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>通过 super/this 引用成员方法

```java
/**
 * 通过 super\this 引用成员方法
 */
@Test
public void test03() {
    // 调用的父类中的方法
    // Hello!I'm Human
    new Man().showFather();

    // 调用的本类中的方法
    // Hello!I'm Human
    new Man().showChild();
}

@FunctionalInterface
public interface Greetable{
    void greet();
}

public class Human{
    public void sayHello(){
        System.out.println("Hello!I'm Human");
    }
}

public class Man extends Human{
    @Override
    public void sayHello() {
        System.out.println("I'm Man");
    }

    public void method(Greetable greetable) {
        greetable.greet();
    }

    public void showFather() {
        method(super::sayHello);
    }

    public void showChild() {
        method(this::sayHello);
    }
}
```

---

<a id="_6.4"></a>

**4. 类的构造器引用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>类的构造器引用

```java

/**
 * 类的构造器引用
 */
@Test
public void test05() {
    per("Zhangsan",Person::new);
}

public void per(String name, GetPerson getPerson) {
    System.out.println(getPerson.getPer(name).getName());
}

@FunctionalInterface
interface GetPerson{
    Person getPer(String name);
}

class Person{
    private String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

---

<a id="_6.5"></a>

**5. 数组的构造器引用**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>数组的构造器引用

```java
/**
 * 数组的构造器引用
 */
@Test
public void test06() {
    int[] a = getA(10, int[]::new);
    Arrays.stream(a).forEach(value -> System.out.print(value));
}

public int[] getA(int len, Arr a) {
    return a.gA(len);
}

@FunctionalInterface
interface Arr{
    int[] gA(int len);
}
```

