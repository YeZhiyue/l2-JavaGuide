
---

# 方法中

## `Collections`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 public static <T> void fill(List<? super T> list, T obj) [? super T]

需要填充目标集合可以接受的对象类型。

？代表参数list的类型， ? super T 表示?类型需要是T的父类，这样子子类就可以加入到目标?类型的集合中

1.2 public static <T> void copy(List<? super T> dest, List<? extends T> src) [? super T | ? extend T]

这里 dest:? 类型就限制为了 src:? 的父类，这样子就使得参数src肯定可以作为dest集合的元素，极大的提升了兼容性。

1.3 public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) [T extends Object & Comparable<? super T>]

首先T需要继承自Object，同时T需要继承自Comparable接口











