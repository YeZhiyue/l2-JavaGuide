1. run() && start()

该题考察的是线程的启动。
在第7行的时候，调用的是t.run();方法，之间调用run方法就是普通的方法调用而已，所以肯定是先执行pong()再执行System.out.print("ping");
如果第7行换成t.start()方法，答案就应该选择c，因为t.start()后，线程变为就绪状态，什么时候开始执行时不确定的，可能是主程序先继续执行，也可能是新线程先执行。

总体结论：

结论一：

return语句并不是函数的最终出口，如果有finally语句，这在return之后还会执行finally（return的值会暂存在栈里面，等待finally执行后再返回）
结论二：

finally里面不建议放return语句，根据需要，return语句可以放在try和catch里面和函数的最后。可行的做法有四：
（1）return语句只在函数最后出现一次。
（2）return语句仅在try和catch里面都出现。
（3）return语句仅在try和函数的最后都出现。
（4）return语句仅在catch和函数的最后都出现。
注意，除此之外的其他做法都是不可行的，编译器会报错


2. 关于Java中equal方法

牛客网上面刷到了这题：希望亲自进行解析，认为答案可以从两个角度分析。

角度一:从正常的逻辑和特殊的equals()和hashCode()方法的实现方式来说，答案是错的，因为很容易就可以写出一个反例。我们可以对equals()方法进行特殊实现或者hashCode()方法特殊实现都可以从方法实现上证明这个答案是错误的。

```java
public class Person {
    private Integer age;
    private String name;

    /**
     * 这里我们特殊实现equals方法，只对用户名称进行比较来判断是否相等
     */
    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    /**
     * 这里hashCode正常实现
     */
    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Person p1 = new Person(20, "张三");
        Person p2 = new Person(21, "张三");
        // equals比较
        // true
        System.out.println(p1.equals(p2));
        // hashCode比较
        // false
        System.out.println(p1.hashCode() == p2.hashCode());
        // 结果：题目的答案被推翻，equals比较相等，但是hashCode不同
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

角度二:从Java的equals方法规范和书写习惯上分析，我们可以认为答案是对的，因为我们的对equals()和hashCode()方法的重写通常会对涉及到类中所有的字段，那么如果是这样的话，我们可以认为答案是对的。

Java语言规范给equals方法下面的要求：
1. 自反性：对于任何非空引用，x.equals(x)都需要返回true，也就是不是null同一个引用的equals比较需要是相同的。
2. 对称性：对于任何引用x和y，当且仅当y.equals(x) && x.equals(y) 都为true时，最终equals比较结果才可能为true
3. 传递性：x,y,z 中如果x.equals(y) && y.equals(z) 的结果都是true,那么x.equals(z)的结果也需要是true
4. 一致性：x,y对象没有变化的情况下，那么反复调用x.equals(y)的结果需要是相同的
5. 非空对象比较规定，对于任何非空的对象x,x.equals(null)的结果都需要是false

- clone()对象的初始化并不是通过构造函数完成的，而是读取别的内存区域中的对象的各个域来完成。

- 泛型： 如果尖括号里的是一个类，那么尖括号里的就是一个点，比如List<A>,List<B>,List<Object> 如果尖括号里面带有问号，那么代表一个范围，<? extends A> 代表小于等于A的范围，<? super A>代表大于等于A的范围，<?>代表全部范围

- Java特殊基本类型知识点

```java
i1和i2为128，超出范围，所以都需要新建对象，对象比较为false；
i5和i6为100，在范围之内，在执行Integer i5=100时，就会直接缓存到内存中，但执行执行Integer i6=100时，就直接从缓存里取，而不需要新建对象，所以为true。
```

```java
链接：https://www.nowcoder.com/questionTerminal/0bc5e15365ec44a7bfa976f1a2a40b20
来源：牛客网

第一次String a="1234"时，会在常量池中创建一个常量1234,String b=1234时，常量池中已经有了该常量，所以直接取，a和b的地址一样，所以地址值相等
String c = newString("1234")重新new了对象，在堆内存中开辟了新的空间，所以地址值不想等，而equals方法比较的是值是否相等
```

- 多线程 

```java
synchronized关键字和volatile关键字比较：
volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。但是volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块。synchronized关键字在JavaSE1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升，实际开发中使用 synchronized 关键字的场景还是更多一些。
多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞
volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。
volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized关键字解决的是多个线程之间访问资源的同步性。
```

- 字符串 final类

```java
String类是final类型的，不能继承和修改这个类。str=“tesk ok”，其实是隐含的让Java生成一个新的String对象，那么就与原来的“Hello”没有任何关系，当函数结束，str作用结束，所以输出的还是“Hello”。 char ch[] 是传递引用，修改了原内容。
```

- 

```java
对于线程而言，start是让线程从new变成runnable。run方法才是执行体的入口。
但是在Thread中，run方法是个空方法，没有具体实现。
Bground继承了Thread，但是没有重写run方法，那么调用run方法肯定是无输出。
```

- Java中数值默认类型

浮点型默认是double，所以double类型后面可以不加上d，但是float必须在后面加上f或者强转。

整形默认是int