# RedisTemplate常用API

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>RedisTemplate基本介绍</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.1" rel="nofollow" target="_self">Redis数据结构以及数据操作</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.2" rel="nofollow" target="_self">Spring对RedisTemplate的封装</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.3" rel="nofollow" target="_self">RedisTemplate中定义了对5种数据结构操作</a>
### <a href="#_2" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>API:String</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.1" rel="nofollow" target="_self">增加</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.2" rel="nofollow" target="_self">删除</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.3" rel="nofollow" target="_self">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.4" rel="nofollow" target="_self">修改，并且写入或者更新</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.5" rel="nofollow" target="_self">其他操作</a>
### <a href="#_3" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>List数据</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.1" rel="nofollow" target="_self">增加</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.2" rel="nofollow" target="_self">删除</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.3" rel="nofollow" target="_self">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.4" rel="nofollow" target="_self">修改</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.5" rel="nofollow" target="_self">其他操作</a>
### <a href="#_4" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>Hash操作</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.1" rel="nofollow" target="_self">增加</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.2" rel="nofollow" target="_self">删除</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.3" rel="nofollow" target="_self">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.4" rel="nofollow" target="_self">修改</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.5" rel="nofollow" target="_self">其他操作</a>
### <a href="#_5" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>Set数据</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.1" rel="nofollow" target="_self">增加</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.2" rel="nofollow" target="_self">删除</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.3" rel="nofollow" target="_self">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.4" rel="nofollow" target="_self">修改</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.5" rel="nofollow" target="_self">其他操作</a>
### <a href="#_6" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>ZSet集合</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.1" rel="nofollow" target="_self">增加</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.2" rel="nofollow" target="_self">删除</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.3" rel="nofollow" target="_self">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.4" rel="nofollow" target="_self">修改</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_6.5" rel="nofollow" target="_self">其他操作</a>
### <a href="#_7" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>补充</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_7.1" rel="nofollow" target="_self">单元粒度时间段的指定 TimeUnit</a>


---

<a id="_1"></a>

## `RedisTemplate基本介绍`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_1.1"></a>

**1. Redis数据结构以及数据操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- String：字符串、整数或者浮点数。操作和Java中的类似
- List：和Java中List类似。但是Redis中是从两端对数据进行弹出或者插入，这就涉及到了数据存储顺序问题。并且我们还可以根据偏移量对链表进行操作。
- Set：和Java的HashSet类似，里面的元素不能重复。
- Hash：和Java中的HashMap类似，存储键值对
- Zset：字符串成员(member)与浮点数分值(score)之间的有序映射，元素的排列顺序由分值的大小决定。并且可以根据分值范围来获取元素

---

<a id="_1.2"></a>

**2. Spring对RedisTemplate的封装**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
org.springframework.data.redis.core
Class RedisTemplate<K,V>
java.lang.Object
    org.springframework.data.redis.core.RedisAccessor
        org.springframework.data.redis.core.RedisTemplate<K,V>
```

---

<a id="_1.3"></a>

**3. RedisTemplate中定义了对5种数据结构操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
redisTemplate.opsForValue();//操作字符串
redisTemplate.opsForHash();//操作hash
redisTemplate.opsForList();//操作list
redisTemplate.opsForSet();//操作set
redisTemplate.opsForZSet();//操作有序set
```

---

<a id="_2"></a>

## `API:String`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_2.1"></a>

**1. 增加**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 单个值的添加

```java
使用：redisTemplate.opsForValue().set("name","tom");
结果：redisTemplate.opsForValue().get("name")  输出结果为tom
```

1.2 单个值的添加并且返回这个值是否已经存在

```java
使用：System.out.println(template.opsForValue().setIfAbsent("multi1","multi1"));//false  multi1之前已经存在
        System.out.println(template.opsForValue().setIfAbsent("multi111","multi111"));//true  multi111之前不存在
结果：false
true
```

1.3 批量增加 multiSet void multiSet(Map<? extends K, ? extends V> m);

```java
使用：Map<String,String> maps = new HashMap<String, String>();
        maps.put("multi1","multi1");
        maps.put("multi2","multi2");
        maps.put("multi3","multi3");
        template.opsForValue().multiSet(maps);
        List<String> keys = new ArrayList<String>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(template.opsForValue().multiGet(keys));
结果：[multi1, multi2, multi3]
```

1.4 设置键的字符串值并返回其旧值 getAndSet V getAndSet(K key, V value);

```java
使用：template.opsForValue().set("getSetTest","test");
        System.out.println(template.opsForValue().getAndSet("getSetTest","test2"));
结果：test
```

---

<a id="_2.2"></a>

**2. 删除**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_2.3"></a>

**3. 查询**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

3.1 获取单个字符串的值

```java
使用：template.opsForValue().set("key","hello world");
        System.out.println("***************"+template.opsForValue().get("key"));
结果：***************hello world
```

3.2 取出多个键的值

```java
使用：Map<String,String> maps = new HashMap<String, String>();
        maps.put("multi1","multi1");
        maps.put("multi2","multi2");
        maps.put("multi3","multi3");
        template.opsForValue().multiSet(maps);
        List<String> keys = new ArrayList<String>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(template.opsForValue().multiGet(keys));
结果：[multi1, multi2, multi3]
```

---

<a id="_2.4"></a>

**4. 修改，并且写入或者更新**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

4.1 给value是数值型的数据进行数值修改 increment Long increment(K key, long delta); 

```java
使用：template.opsForValue().increment("increlong",1);
        System.out.println("***************"+template.opsForValue().get("increlong"));
结果：***************1
```

```java
使用：template.opsForValue().increment("increlong",1.2);
        System.out.println("***************"+template.opsForValue().get("increlong"));
结果：***************2.2
```

---

<a id="_2.5"></a>

**5. 其他操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

5.1 设置过期时间 set void set(K key, V value, long timeout, TimeUnit unit);

```java
使用：redisTemplate.opsForValue().set("name","tom",10, TimeUnit.SECONDS);
结果：redisTemplate.opsForValue().get("name")由于设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null
```

5.2 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。 

Integer append(K key, String value);

```java
使用：template.opsForValue().append("appendTest","Hello");
        System.out.println(template.opsForValue().get("appendTest"));
        template.opsForValue().append("appendTest","world");
        System.out.println(template.opsForValue().get("appendTest"));
结果：Hello
        Helloworld
```

---

<a id="_3"></a>

## `List数据`

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_3.1"></a>

**1. 增加**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 单个插入 Long leftPush(K key, V value); 

```java
使用：template.opsForList().leftPush("list","java");
        template.opsForList().leftPush("list","python");
        template.opsForList().leftPush("list","c++");
结果:返回的结果为推送操作后的列表的长度
1
2
3
```

1.2 批量插入 Long leftPushAll(K key, V... values);

```java
使用：String[] stringarrays = new String[]{"1","2","3"};
        template.opsForList().leftPushAll("listarray",stringarrays);
        System.out.println(template.opsForList().range("listarray",0,-1));
结果:[3, 2, 1]
```

```java
使用：List<Object> strings = new ArrayList<Object>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        template.opsForList().rightPushAll("listcollectionright", strings);
        System.out.println(template.opsForList().range("listcollectionright",0,-1));
结果:[1, 2, 3]
```

---

<a id="_3.2"></a>

**2. 删除**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

2.1 修剪现有列表，使其只包含指定的指定范围的元素，起始和停止都是基于0的索引 void trim(K key, long start, long end); 

```java
使用：System.out.println(template.opsForList().range("list",0,-1));
template.opsForList().trim("list",1,-1);//裁剪第一个元素
System.out.println(template.opsForList().range("list",0,-1));
结果:[c#, c++, python, java, c#, c#]
[c++, python, java, c#, c#]
```

2.2 从存储在键中的列表中删除等于值的元素的第一个计数事件 Long remove(K key, long count, Object value);

从存储在键中的列表中删除等于值的元素的第一个计数事件。
计数参数以下列方式影响操作：
count> 0：删除等于从头到尾移动的值的元素。
count <0：删除等于从尾到头移动的值的元素。
count = 0：删除等于value的所有元素。

```java
使用：System.out.println(template.opsForList().range("listRight",0,-1));
        template.opsForList().remove("listRight",1,"setValue");//将删除列表中存储的列表中第一次次出现的“setValue”。
        System.out.println(template.opsForList().range("listRight",0,-1));
结果:[java, setValue, oc, c++]
[java, oc, c++]
```

2.3 弹出最左边的元素，弹出之后该值在列表中将不复存在 V leftPop(K key);

```java
使用：System.out.println(template.opsForList().range("list",0,-1));
        System.out.println(template.opsForList().leftPop("list"));
        System.out.println(template.opsForList().range("list",0,-1));
结果:
[c++, python, oc, java, c#, c#]
c++
[python, oc, java, c#, c#]
``` 

---

<a id="_3.3"></a>

**3. 查询**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

3.1 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素 List<V> range(K key, long start, long end); 

```java
使用：System.out.println(template.opsForList().range("list",0,-1));
结果:[c#, c++, python, java, c#, c#]
```

3.2 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误。 Long size(K key); 

```java
使用：System.out.println(template.opsForList().size("list"));
结果:6
```

---

<a id="_3.4"></a>

**4. 修改**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

4.1 在列表中index的位置设置value值 void set(K key, long index, V value);

```java
使用：System.out.println(template.opsForList().range("listRight",0,-1));
        template.opsForList().set("listRight",1,"setValue");
        System.out.println(template.opsForList().range("listRight",0,-1));
结果:[java, python, oc, c++]
[java, setValue, oc, c++]
``` 

---

<a id="_3.5"></a>

**5. 其他操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

5.1 只有存在key对应的列表才能将这个value值插入到key所对应的列表中 Long rightPushIfPresent(K key, V value);

```java
使用：System.out.println(template.opsForList().rightPushIfPresent("rightPushIfPresent","aa"));
        System.out.println(template.opsForList().rightPushIfPresent("rightPushIfPresent","bb"));
        System.out.println("==========分割线===========");
        System.out.println(template.opsForList().rightPush("rightPushIfPresent","aa"));
        System.out.println(template.opsForList().rightPushIfPresent("rightPushIfPresent","bb"));
结果:0
0
==========分割线===========
1
2
``` 

---

<a id="_4"></a>

## `Hash操作`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_4.1"></a>

**1. 增加**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 单个插入

```java
//template.opsForHash().put("redisHash","name","tom");
        //template.opsForHash().put("redisHash","age",26);
        //template.opsForHash().put("redisHash","class","6");
```

1.2 批量插入

```java
//Map<String,Object> testMap = new HashMap();
        //testMap.put("name","jack");
        //testMap.put("age",27);
        //testMap.put("class","1");
        //template.opsForHash().putAll("redisHash1",testMap);
```

1.3 

---

<a id="_4.2"></a>

**2. 删除**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

2.1 批量删除 Long delete(H key, Object... hashKeys);

```java
使用：System.out.println(template.opsForHash().delete("redisHash","name"));
        System.out.println(template.opsForHash().entries("redisHash"));
结果：1
{class=6, age=28.1}
```

---

<a id="_4.3"></a>

**3. 查询**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

3.1 确定哈希值是否存在 Boolean hasKey(H key, Object hashKey);

```java
使用：System.out.println(template.opsForHash().hasKey("redisHash","age"));
        System.out.println(template.opsForHash().hasKey("redisHash","ttt"));
结果：true
false
```

3.2 获取指定Hash值  HV get(H key, Object hashKey)

```java
使用：System.out.println(template.opsForHash().get("redisHash","age"));
结果：26
```

3.3 批量获取Hash值 List<HV> multiGet(H key, Collection<HK> hashKeys);

```java
使用：List<Object> kes = new ArrayList<Object>();
        kes.add("name");
        kes.add("age");
        System.out.println(template.opsForHash().multiGet("redisHash",kes));
结果：[jack, 28.1]
```

3.4 获取Hash值的key集合 Set<HK> keys(H key)
 
```java
使用：System.out.println(template.opsForHash().keys("redisHash1"));
//redisHash1所对应的散列表为{class=1, name=jack, age=27}
结果：[name, class, age]
```

3.5 获取所有Hash键值对

```java
使用：Map<String,Object> testMap = new HashMap();
        testMap.put("name","jack");
        testMap.put("age",27);
        testMap.put("class","1");
        template.opsForHash().putAll("redisHash1",testMap);
        System.out.println(template.opsForHash().entries("redisHash1"));
结果：{class=1, name=jack, age=27}
```

---

<a id="_4.4"></a>

**4. 修改**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

4.1 改变指定数值型的Hash值 Long increment(H key, HK hashKey, long delta)

补充：Double increment(H key, HK hashKey, double delta);

```java
使用：System.out.println(template.opsForHash().get("redisHash","age"));
    System.out.println(template.opsForHash().increment("redisHash","age",1));
结果：26
27
```

---

<a id="_4.5"></a>

**5. 其他操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

5.1 仅仅当hashkey不存在时才设置value Boolean putIfAbsent(H key, HK hashKey, HV value);

```java
使用：System.out.println(template.opsForHash().putIfAbsent("redisHash","age",30));
System.out.println(template.opsForHash().putIfAbsent("redisHash","kkk","kkk"));
结果：false
true
```

5.2 迭代hash值 Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options);

```java
使用：Cursor<Map.Entry<Object, Object>> curosr = template.opsForHash().scan("redisHash", ScanOptions.ScanOptions.NONE);
        while(curosr.hasNext()){
            Map.Entry<Object, Object> entry = curosr.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
结果：age:28.1
class:6
kkk:kkk
```

---

<a id="_5"></a>

## `Set数据`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_5.1"></a>

**1. 增加**

1.1 批量增加 Long add(K key, V... values);

```java
使用：String[] strarrays = new String[]{"strarr1","sgtarr2"};
        System.out.println(template.opsForSet().add("setTest", strarrays));
结果：2
```

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_5.2"></a>

**2. 删除**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

2.1 批量删除 Long remove(K key, Object... values);

```java
使用：String[] strarrays = new String[]{"strarr1","sgtarr2"};
System.out.println(template.opsForSet().remove("setTest",strarrays));
结果：2
```

2.2 随机移除 V pop(K key);

```java
使用：System.out.println(template.opsForSet().pop("setTest"));
System.out.println(template.opsForSet().members("setTest"));
结果：bbb
[aaa, ccc]
```

---

<a id="_5.3"></a>

**3. 查询**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 查询长度

```java
使用：System.out.println(template.opsForSet().size("setTest"));
结果：1
```

1.2 查询某个元素是否存在 Boolean isMember(K key, Object o);

```java
使用：System.out.println(template.opsForSet().isMember("setTest","ccc"));
        System.out.println(template.opsForSet().isMember("setTest","asd"));
结果：true
false
```

3.3 查询集合所有成员 Set<V> members(K key);

```java
使用：System.out.println(template.opsForSet().members("setTest"));
结果：[ddd, bbb, aaa, ccc]
```

---

<a id="_5.4"></a>

**4. 修改**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_5.5"></a>

**5. 其他操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

5.1 批量转移一个集合的元素到另外于一个集合

Boolean move(K key, V value, K destKey);
将 member 元素从 source 集合移动到 destination 集合

```java
使用：template.opsForSet().move("setTest","aaa","setTest2");
        System.out.println(template.opsForSet().members("setTest"));
        System.out.println(template.opsForSet().members("setTest2"));
结果：[ccc]
[aaa]
```

5.2 求两个集合的交集 Set<V> intersect(K key, K otherKey);

```java
使用：System.out.println(template.opsForSet().members("setTest"));
        System.out.println(template.opsForSet().members("setTest2"));
        System.out.println(template.opsForSet().intersect("setTest","setTest2"));
结果：[aaa, ccc]
[aaa]
[aaa]
```

5.3 求多个无序集合的交集 Set<V> intersect(K key, Collection<K> otherKeys);

```java
使用：System.out.println(template.opsForSet().members("setTest"));
        System.out.println(template.opsForSet().members("setTest2"));
        System.out.println(template.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<String>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println(template.opsForSet().intersect("setTest",strlist));
结果：[aaa, ccc]
[aaa]
[ccc, aaa]
[aaa]
```

5.4 求多个无序集合的并集 Set<V> union(K key, Collection<K> otherKeys);

```java
使用：System.out.println("setTest:" + template.opsForSet().members("setTest"));
        System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
        System.out.println("setTest3:" + template.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<String>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println(template.opsForSet().union("setTest",strlist));
结果：setTest:[ddd, bbb, aaa, ccc]
setTest2:[ccc, aaa]
setTest3:[xxx, ccc, aaa]
[ddd, xxx, bbb, aaa, ccc]
```

5.5 遍历

```java
使用： Cursor<Object> curosr = template.opsForSet().scan("setTest", ScanOptions.NONE);
        while(curosr.hasNext()){
            System.out.println(curosr.next());
        }
结果：ddd
bbb
aaa
ccc
```

---

<a id="_6"></a>

## `ZSet集合`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

有序的Set集合，排序依据是Score。

---

<a id="_6.1"></a>

**1. 增加**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 添加单个元素，并且获取该元素是否已经存在的状态 Boolean add(K key, V value, double score);

```java
使用：System.out.println(template.opsForZSet().add("zset1","zset-1",1.0));
结果：true
```

1.2 批量添加元素 Long add(K key, Set<TypedTuple<V>> tuples);

```java
使用：ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(template.opsForZSet().add("zset1",tuples));
        System.out.println(template.opsForZSet().range("zset1",0,-1));
结果：[zset-1, zset-2, zset-3, zset-4, zset-5, zset-6]
```

---

<a id="_6.2"></a>

**2. 删除**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

2.1 批量删除 Long remove(K key, Object... values);

```java
使用：System.out.println(template.opsForZSet().range("zset1",0,-1));
        System.out.println(template.opsForZSet().remove("zset1","zset-6"));
        System.out.println(template.opsForZSet().range("zset1",0,-1));
结果：[zset-1, zset-2, zset-3, zset-4, zset-5, zset-6]
1
[zset-1, zset-2, zset-3, zset-4, zset-5]
```

---

<a id="_6.3"></a>

**3. 查询**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

3.1 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列 Long rank(K key, Object o);

```java
使用：System.out.println(template.opsForZSet().range("zset1",0,-1));
        System.out.println(template.opsForZSet().rank("zset1","zset-2"));
结果：[zset-2, zset-1, zset-3, zset-4, zset-5]
0   //表明排名第一
```

3.2 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列 Long reverseRank(K key, Object o);

```java
使用：System.out.println(template.opsForZSet().range("zset1",0,-1));
        System.out.println(template.opsForZSet().reverseRank("zset1","zset-2"));
结果：[zset-2, zset-1, zset-3, zset-4, zset-5]
4 //递减之后排到第五位去了
```

3.3 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列 Set<V> range(K key, long start, long end);

```java
使用：System.out.println(template.opsForZSet().range("zset1",0,-1));
结果：[zset-2, zset-1, zset-3, zset-4, zset-5]
```

---

<a id="_6.4"></a>

**4. 修改**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

4.1 增加元素的score值，并返回增加后的值 Double incrementScore(K key, V value, double delta);

```java
使用：System.out.println(template.opsForZSet().incrementScore("zset1","zset-1",1.1));  //原为1.1
结果：2.2
```

---

<a id="_6.5"></a>

**5. 其他操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

5.1 遍历操作

```java
使用： Cursor<ZSetOperations.TypedTuple<Object>> cursor = template.opsForZSet().scan("zzset1", ScanOptions.NONE);
        while (cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }
结果：zset-1:1.0
zset-2:2.0
zset-3:3.0
zset-4:6.0
```


---

<a id="_6"></a>

## `补充`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_6.1"></a>

**1. 单元粒度时间段的指定 TimeUnit**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
TimeUnit.DAYS //天
TimeUnit.HOURS //小时
TimeUnit.MINUTES //分钟
TimeUnit.SECONDS //秒
TimeUnit.MILLISECONDS //毫秒
```

[参考博客](https://blog.csdn.net/ruby_one/article/details/79141940)
