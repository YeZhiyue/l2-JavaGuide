<font color=#ca0c16 size=8 > Java 8：Stream流学习

<a id="_top"></a>

@[TOC](文章目录)

# 前言

<font color=#999AAA > Stream流是jdk 1.8 引入的新特性，极大地方便了我们对集合的处理(特别是一些对于一些集合的查询操作)。而且减少了我们的代码量，而且在数据量大的时候支持并行处理提升效率，我们可以学习一下其中常用的方法和一些注意点以及原理。

<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">


# 常用方法

## `过滤方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 filter(Predicate<? super T>):Stream<T>

根据规则过滤元素，参数中返回true就会留在Stream流中。

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A",23),
                new Person("B",22),
                new Person("C",28),
                new Person("D",25),
                new Person("E",13)
        );
        list.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(person -> System.out.println(person));
    }
}
```

```java
Person(name=A, age=23)
Person(name=B, age=22)
Person(name=C, age=28)
Person(name=D, age=25)
```

1.2 distinct():Stream<T>

根据对象的equals方法去除重复元素

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A",23),
                new Person("A",23),
                new Person("B",22),
                new Person("C",28),
                new Person("C",28),
                new Person("D",25),
                new Person("E",13)
        );
        list.stream()
                .distinct()
                .forEach(s -> System.out.println(s));
    }
}
```

```java
Person(name=A, age=23)
Person(name=B, age=22)
Person(name=C, age=28)
Person(name=D, age=25)
Person(name=E, age=13)
```

1.3 limit(long maxSize):Stream<T>

返回流中的n个元素，注意这个流是无序流还是有序流。

1.4 skip(long n):Stream<T>

跳过流中的前n个元素，注意这个流是无序流还是有序流。

## `映射方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 map(Function<? super T,? extends R>):Stream<R>

给出一个对象T，然后根据逻辑返回对象R作为Stream流中的元素。

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A",23),
                new Person("B",22),
                new Person("C",28),
                new Person("D",25),
                new Person("E",13)
        );
        list.stream()
                .map(Person::getName)
                .forEach(s -> System.out.println(s));
    }
}
```

```java
A
B
C
D
E
```


## `排序方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 sorted():Stream<T>

需要元数据实现Comparable接口有着排序规则

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A",23),
                new Person("B",22),
                new Person("C",28),
                new Person("C",28),
                new Person("D",25),
                new Person("E",13)
        );
        list.stream()
                .sorted()
                .forEach(s -> System.out.println(s));
    }
}

@Data
@Builder
class Person implements Comparable{
    private String name;
    private Integer age;

    @Override
    public int compareTo(Object o) {
        Person o1 = (Person) o;
        return this.getAge() - o1.getAge();
    }
}
```

```java
Person(name=E, age=13)
Person(name=B, age=22)
Person(name=A, age=23)
Person(name=D, age=25)
Person(name=C, age=28)
Person(name=C, age=28)
```

1.2 sorted(Comparator<? super T> comparator):Stream<T> 

Stream流也提供了非常方便的使用方法，我们可以灵活的定义排序方法。

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A",23),
                new Person("B",22),
                new Person("C",28),
                new Person("C",28),
                new Person("D",25),
                new Person("E",13)
        );
        list.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(System.out::println);
    }
}
```

```java
Person(name=E, age=13)
Person(name=B, age=22)
Person(name=A, age=23)
Person(name=D, age=25)
Person(name=C, age=28)
Person(name=C, age=28)
```

## `帮助Stream流调试方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 peek(Consumer<? super T> action):Stream<T>

这个方法主要用来调试Stream流，可以简单理解为只要流中遍历了一个元素我们就可以通过peek对这个元素进行操作(通常打印这个元素)

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );
        list.stream()
                .peek(person -> System.out.println(person.getName().toUpperCase()))
                .peek(person -> System.out.println(person.getName().toLowerCase()))
                .peek(person -> System.out.println("-----------"))
                .count();
    }
}
```

```java
A
a
-----------
B
b
-----------
C
c
-----------
C
c
-----------
D
d
-----------
E
e
-----------
```

## `遍历方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 forEach(Consumer<? super T>):void 

遍历但是不能保证遍历顺序，因为这个方法支持并行操作。

## `转换方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 toArray():Object[]

直接返回一个Object数组

1.2 toArray(IntFunction<A[]> generator):<A> A[] 

返回一个指定对象的数组

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );
        Person[] people = list.stream()
                .filter(person -> person.getAge() > 22)
                .toArray(Person[]::new);

        Stream.of(people).forEach(System.out::println);
    }
}
```

```java
Person(name=A, age=23)
Person(name=C, age=28)
Person(name=C, age=28)
Person(name=D, age=25)
```

1.2 reduce(T identity, BinaryOperator<T> accumulator):T

累加器，对流中的元素进行累加逻辑计算并且得出最终的结果

[具体使用参考博客](https://blog.csdn.net/weixin_43860260/article/details/94875064)

1.3 collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner):<R> R

比较复杂的集合操作，三个参数的逻辑操作：

- supplier:容器提供者，主要提供一个新的容器
- accumulator:累加器，其中函数式方法中提供两个方法，一个是supplier提供的新容器，一个是Stream流中的元素，然后对Stream流中的元素进行过滤放到supplier提供的容器中
- combiner:合并器，将累加器得到的元素进行二次过滤，然后放到一个新的容器中

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );
         
    
        list.stream().collect(
                // 1. 创建新的容器一
                HashMap<String, List<Person>>::new,
                // 2. 向我们新的容器里面放置元素成为容器二
                (map1, person) -> {
                    String key = person.getName();
                    List<Person> pList = map1.getOrDefault(key, new ArrayList<>());
                    pList.add(person);
                    map1.put(key, pList);
                },
                // 3. 队容器二进行过滤然后放入新的容器三种
                (map2, map3) -> map3.putAll(map2)
        ).forEach((key, person) -> {
            System.out.println("Key: " + key);
            System.out.println("Value: " + person);
        });
    }
}
```

```java
Key: A
Value: [Person(name=A, age=23)]
Key: B
Value: [Person(name=B, age=22)]
Key: C
Value: [Person(name=C, age=28), Person(name=C, age=28)]
Key: D
Value: [Person(name=D, age=25)]
Key: E
Value: [Person(name=E, age=13)]
```

1.4 collect(Collector<? super T, A, R> collector):<R, A> R 

通常用作返回一个Collection，常常用来返回Map或者List

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );

        // 直接返回List
        List<String> plist = list.stream().map(Person::getName)
                .collect(Collectors.toList());

        // 返回需要的Collection
        Set<String> set = list.stream().map(Person::getName)
                .collect(Collectors.toCollection(TreeSet::new));

        // 返回字符串
        String joined = list.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        // 返回总和
        int total = list.stream()
                .collect(Collectors.summingInt(Person::getAge));

        // 返回一个分组Map
        Map<String, List<Person>> groupMap
                = list.stream()
                .collect(Collectors.groupingBy(Person::getName));

        // 返回一个分组Map，并且有其他逻辑分组操作
        Map<String, Integer> totalMap
                = list.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.summingInt(Person::getAge)));

        // 自己定义分组逻辑并且获得分组Map
        Map<Boolean, List<Person>> myLogicGroupMap =
                list.stream()
                        // 根据23岁分成两个组别
                        .collect(Collectors.partitioningBy(s -> s.getAge() >= 23));

    }
}
```

1.5 聚合函数 max() min() count()

## `判断匹配方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 anyMatch(Predicate<? super T> predicate):boolean allMatch(Predicate<? super T> predicate):boolean

- 只要元素中有一个元素满足条件就返回true
- 需要所有元素满足条件就返回true

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );
        // true
        boolean b = list.stream().anyMatch(person -> person.getAge() > 22);
        System.out.println(b);
        // false
        b = list.stream().allMatch(person -> person.getAge() > 22);
        System.out.println(b);
    }
}
```

## `构建生成Stream方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- of(T ...)
- concat(Stream s1,Stream s2)

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );

        Stream.concat(list.stream(), list.stream())
                .forEach(System.out::println);

        Stream.of(1, 2, 3, 4).forEach(System.out::println);
    }
}
```

```java
Person(name=A, age=23)
Person(name=B, age=22)
Person(name=C, age=28)
Person(name=C, age=28)
Person(name=D, age=25)
Person(name=E, age=13)
Person(name=A, age=23)
Person(name=B, age=22)
Person(name=C, age=28)
Person(name=C, age=28)
Person(name=D, age=25)
Person(name=E, age=13)
1
2
3
4
```

---

# Stream流的特殊特性

## `支持顺序和并行聚合操作`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 例子

```java
int sum = widgets.stream()
              .filter(w -> w.getColor() == RED)
              .mapToInt(w -> w.getWeight())
              .sum();
```

1.2 顺序操作

例子中先是根据顺序首先过滤完毕后得到结果集1，然后对结果集1进行转换得到结果集2，最后进行聚合得到求和结果集3.

1.3 并行聚合操作

在sum()函数中就是Stream流的聚合操作

## `流的懒加载特性`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

Stream流的操作可以根据是否是终端操作分为两大类，流会根据这两种操作来决定是否执行流中元数据的处理。

- 情况一：Stream流中没有终端方法执行，那么Stream流就不会执行

![](https://imgconvert.csdnimg.cn/aHR0cDovL3FmdDZ3bXpsYS5obi1ia3QuY2xvdWRkbi5jb20vcGljZ28vMjAyMDA5MDUxNzEzMzUucG5n?x-oss-process=image/format,png)

- 情况二：Stream流中有终端方法执行，那么Stream流就会执行

![](https://imgconvert.csdnimg.cn/aHR0cDovL3FmdDZ3bXpsYS5obi1ia3QuY2xvdWRkbi5jb20vcGljZ28vMjAyMDA5MDUxNzE0MzkucG5n?x-oss-process=image/format,png)

## `流和集合之间的区别`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

流和集合表面上看起来非常相似，但是两者关注的目标有所不同。集合更加关注高效的管理，比如我们直接可以通过集合对象list的get(n)方法直接获取其中指定位置的元素并且操作管理这个元素，但是stream对象就没有方法直接这么简单的获取数据源目标位置的元素，这里就体现了集合管理的更加高效。Stream流更加关注数据的查询，在查询和过滤转换上的操作来说，Stream的效率和代码量都体现出了巨大的优势，使用Stream就好像写文章，并且这个文章上下文练习非常紧密，这就是流之间的传递性。

## `以函数式接口作为流的方法需要数据源中不能有null值`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

因为函数式接口往往需要指定一个对象然后进行处理，而Stream流中的元素就是直接对其中的元素进行处理，一旦数据源中出现了null元素，然后我们还使用了函数式接口对null元素进行了处理就会出现问题

```java
public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                // 故意放入null元素
                null,
                null,
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );

        list.stream().
                // 这里map中的函数式几口会接收到null元素，会导致异常的发生
                map(Person::getAge).forEach(System.out::println);
    }
}
```

```java
java.lang.NullPointerException
	at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
	at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
```

## `流中资源的关闭`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- 由数组、集合产生的流并不需要我们自己关闭，Stream流中继承了AutoCloseable接口会自动关闭资源流
- 由I/O产生的流需要我们进行特殊的关闭，我们可以将资源声明在 try-with-resource中声明

## `流可以设置顺序或者并行执行`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

流管道可以依次或者在执行平行 。 该执行模式是流的属性。 流与顺序或并行执行的最初的选择创建。 （例如， Collection.stream()创建了一个连续的流，并且Collection.parallelStream()创建了一个并联的一个。）的执行模式的这种选择可以通过被修改sequential()或parallel()方法，并且可以与被查询所述isParallel()方法。

[并行流解析](https://blog.csdn.net/YCJ_xiyang/article/details/83652954)

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。串行流则相反，并行流的底层其实就是ForkJoin框架的一个实现。

---

# 小结

- 如果对集合的操作仅仅只是查询过滤其中的元素，那么推荐使用Stream流，不仅减少了代码量而且提升了效率
- 并行流的使用需要注意
    - 留意装箱。自动装箱和拆箱操作会大大降低性能。Java8中有原始类型流（IntStream、LongStream、DoubleStream）来避免这种操作
    - 有些操作本身在并行流上的性能就比顺序流差。特别是limit、findFirst等依赖于元素顺序的操作，它们在并行流上执行的代价非常大。
    - 在大数量数据的处理才去使用并行流，否则不要去使用
- Stream流中关系到函数式接口作为参数的方法，需要注意数据源是否有null对象