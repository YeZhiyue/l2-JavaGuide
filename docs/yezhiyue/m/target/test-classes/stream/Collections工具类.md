<font color=#ca0c16 size=8> Collections工具类&&Arrays工具类

<a id="_top"></a>

@[TOC](文章目录)

# 前言

<font color=#999AAA > Java中，集合操作是我们避免不了的操作。因为几乎所有场景都有着集合的使用。所以，Java为我们提供了集合的工具类来简化我们对集合的一些复杂操作，现在就让我们来学习一下集合工具类中的方法。

<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

---

# Collections方法介绍

## `排序方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T extends Comparable<? super T>> void sort(List<T> list)

对指定列表分为按升序排列， 自然排序的元素。 列表中的所有元素都必须实现Comparable接口。 此外，在列表中的所有元素都必须是可相互比较,最终的排序结果是稳定的。

部分源码解读：其中还关键还是sort方法，源码的排序思路就是通过原来的集合对象产生一个新的Object数组，然后对这个数组进行排序，之后通过迭代器对原来的集合对象重新遍历赋值。

```java
default void sort(Comparator<? super E> c) {
    Object[] a = this.toArray();
    Arrays.sort(a, (Comparator) c);
    ListIterator<E> i = this.listIterator();
    for (Object e : a) {
        i.next();
        i.set((E) e);
    }
}
```

1.2 public static <T> void sort(List<T> list, Comparator<? super T> c) 

和上面的排序方法没有什么区别，就是自定义比较规则，比较灵活。

## `查找方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)

搜索使用二进制搜索算法的指定对象的指定列表。 该列表必须根据被升序排序自然排序元素（如由sort(List)方法）之前，在进行此调用。 如果不进行排序，结果是不确定的。 如果列表中包含多个元素等于指定的对象，也不能保证哪一个会被发现。此方法中的log（n）时间运行为一个“随机访问”列表（其提供接近恒定的时间的位置访问）。 如果指定列表没有实现RandomAccess接口和大，这个方法会做一个基于迭代器的二进制搜索，执行O（n）的链接遍历和O（log n）的元素比较

注意了，如果有集合中有两个相同的对象，那么搜索结果是不正确的。

1.2 public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) 

和上面类似，就是可以自定义排序逻辑。

1.3 public static int indexOfSubList(List<?> source, List<?> target) 

查找目标集合在源集合下标开始的位置，如果源集合不包含目标集合那么就返回 -1

补充：lastIndexOfSubList(List<?> source, List<?> target) 

1.4 public static int frequency(Collection<?> c, Object o)

查找目标元素在集合中出现的频次

## `翻转 && 打乱方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static void reverse(List<?> list)

翻转集合

1.2 public static void shuffle(List<?> list)

打乱顺序方法，但是打乱依照某种规则，多以同一个集合多次打乱的结果相似。

## `交换方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static void swap(List<?> list, int i, int j)

## `填充替换方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T> void fill(List<? super T> list, T obj)

将集合中原来的所有元素替换为指定的元素

1.2 public static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) 

1.3 public static <T> boolean addAll(Collection<? super T> c, T... elements) 

批量向集合中添加元素

## `移动拷贝元素`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T> void copy(List<? super T> dest, List<? extends T> src)

将原来的src集合元素替换到dest集合中，如果dest集合长度更长那么超过src长度的部分元素不变

1.2 public static void rotate(List<?> list, int distance)

类似于循环队列，distance>0时表示向左移动，distance<0时代表向右移动固定距离。

```java
public class CollectionsTest {
    @Test
    public void test01() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.print("\nfirst:");
        Collections.rotate(integers, 2);
        integers.forEach(System.out::print);
        System.out.print("\nsecond:");
        Collections.rotate(integers, -2);
        integers.forEach(System.out::print);
        System.out.print("\nthree:");
        Collections.rotate(integers, 8);
        integers.forEach(System.out::print);
        System.out.print("\nfour:");
        Collections.rotate(integers, -8);
        integers.forEach(System.out::print);
    }

}
```

## `聚合方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) 

1.2 public static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) 

1.3 public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) 

## `其他特殊方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c)

unmodifiable*方法

1.2 public static <T> Collection<T> synchronizedCollection(Collection<T> c) 

synchronized*方法，线程安全

1.3 public static <T> ListIterator<T> emptyListIterator()

empty*方法

1.4 public static <T> Set<T> singleton(T o)

singleton*方法

1.5 public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) 

将map的key作为Set返回

---

# Arrays工具类介绍

## `排序方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static void sort(byte[] a) 

单线程的排序，排序结果是自然顺序，也就是从小到大

1.2 public static void parallelSort(byte[] a) 

多线程的排序方法，原理就是将数组先分成多个小部分，然后并行排序。

## `转化方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 以索引为逻辑关系来转换数组中的元素

public static void parallelSetAll(double[] array, IntToDoubleFunction generator) 

public static <T> void parallelPrefix(T[] array, int fromIndex, int toIndex, BinaryOperator<T> op) 

```java
public class MyDemo1 {
    public static void main(String[] args) {
        int[] arr2=new int[]{1,8,51,13,46,11,22};
        Arrays.parallelPrefix(arr2, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                //left代表数组中前一个索引处的元素，计算第一个元素时，left为1
                //right代表数组中当前索引处的元素
                return left*right;
            }
        });
        System.out.println(Arrays.toString(arr2));

        int[] arr3=new int[5];
        Arrays.parallelSetAll(arr3, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                //operand代表正在子酸的元素的索引
                return operand*5;
            }
        });
        System.out.println(Arrays.toString(arr3));
    }
}
```

```java
[1, 8, 408, 5304, 243984, 2683824, 59044128]
[0, 5, 10, 15, 20]
```

1.2 public static <T> void setAll(T[] array, IntFunction<? extends T> generator) 

```java
public static <T> void setAll(T[] array, IntFunction<? extends T> generator) {
    Objects.requireNonNull(generator);
    for (int i = 0; i < array.length; i++)
        array[i] = generator.apply(i);
}
```

## `搜索方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static int binarySearch(int[] a, int fromIndex, int toIndex, int key)

## `比较判断方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static boolean equals(long[] a, long[] a2) 

1.2 public static boolean deepEquals(Object[] a1, Object[] a2) 

## `填充方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static void fill(long[] a, long val)

## `获取新的数组`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) 

```java
@param original the array to be copied
@param newLength the length of the copy to be returned
@param newType the class of the copy to be returned
```

补充：public static <T,U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType)

1.2 public static <T> List<T> asList(T... a)

返回一个不可变的集合，获取集合后，集合就不能再改变。

## `打印方法`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static String toString(long[] a)

1.2 public static String deepToString(Object[] a)

## `获取Stream`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T> Stream<T> stream(T[] array)

---

# 小结

- 集合数组都提供了方便的排序和搜索方法，算法都都可以归类为二分查找和快速排序
- 集合数组都提供了转换和填充方法
- 集合数组中还提供了一些并发执行的方法，在处理大数据量的数据可以考虑使用



























