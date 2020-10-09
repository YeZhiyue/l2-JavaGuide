## 原生JavaScript复习

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self">常用的简单函数</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">一些调试常用的基本函数</a>
### <a href="#_2" rel="nofollow" target="_self">JS常用的基础内置对象</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">String对象</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">Number对象</a>
2.3 <a href="#_2.3" rel="nofollow" target="_self">Math对象</a>
2.4 <a href="#_2.4" rel="nofollow" target="_self">Date对象</a>
2.5 <a href="#_2.5" rel="nofollow" target="_self">RegExp正则对象</a>
2.6 <a href="#_2.6" rel="nofollow" target="_self">JSON对象</a>
2.7 <a href="#_2.7" rel="nofollow" target="_self">Array对象</a>
### <a href="#_3" rel="nofollow" target="_self">BOM浏览器模型常用方法</a>
### <a href="#_4" rel="nofollow" target="_self">DOM文档模型常用函数</a>
4.1 <a href="#_4.1" rel="nofollow" target="_self">基本示例模型准备(必看)</a>
4.2 <a href="#_4.2" rel="nofollow" target="_self">元素节点操作(简单易懂的元素节点进行演示)</a>
4.3 <a href="#_4.3" rel="nofollow" target="_self">元素属性的增删查改</a>
4.4 <a href="#_4.4" rel="nofollow" target="_self">元素的增加和删除</a>
4.5 <a href="#_4.5" rel="nofollow" target="_self">事件</a>
### <a href="#_5" rel="nofollow" target="_self">JS高级</a>
5.1 <a href="#_5.1" rel="nofollow" target="_self">Class对象的定义</a>

---

<a id="_1"></a>

## `常用的简单函数`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. 一些调试常用的基本函数**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!-- 常用方法 -->
<script>
    /**
     * string
     * Hello
     * undefined
     */
    var str = "Hello";
    console.log("Hello");
    console.log(typeof "Hello");
    delete str;
    console.log(str);
</script>
```

---

<a id="_2"></a>

## `JS常用的基础内置对象`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. String对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--String-->
<script>

    var str = "01234";
    /**
     * 匹配替换
     */
    // 通过正则表达式进行匹配
    console.log(str.match(/\d/i));
    // 字符串替换,但是仅仅替换一处
    console.log(str.replace(/\d/i,"m"));
    console.log(str.replace("1","m"));
    // 通过传入返回字符串的方法进行字符串替换
    console.log(str.replace("1",function () {
        return "hello";
    }));

    /**
     * 搜索、判断
     */
    // 搜索字符串第一个匹配的字符串的下标(如果有多个字符，以开头第一个匹配到的字符的下标为准)
    // 0
    console.log(str.search(/\d/));
    // 1
    console.log(str.search("12"));
    // -1 没有搜索到目标
    console.log(str.search("9"));

    // 通过char搜索
    console.log(str.indexOf('1'));
    console.log(str.lastIndexOf('1'));

    // 判断两个字符串是否相等，相等返回 0 ，不相等返回 -1
    console.log(str.localeCompare("01234"));
    console.log(str.localeCompare("0124"));

    /**
     * 获取
     */
    // 获取子串，等价于 substring
    // 12 不包括下标3的字符
    console.log(str.slice(1,3));
    console.log(str.substring(1,3));

    // 获取字符
    console.log(str.charAt(2));
    // 获取指定字符ASCLL码
    console.log(str.charCodeAt(0));
    // 拼接字符串
    console.log(str.concat(str, str));

    // 大小写转化
    console.log(str.toLowerCase());
    console.log(str.toUpperCase());

    // 直接通过下标获取
    console.log(str[2]);

    // 返回删除不可见字符的字符串
    console.log(str.trim());

</script>
```

---

<a id="_2.2"></a>

**2. Number对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--Number-->
<script>
    console.log(Number.NaN);
    console.log(Number.MAX_VALUE);
    console.log(Number.MIN_VALUE);
</script>
```

---

<a id="_2.3"></a>

**3. Math对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--Math-->
<script>
    console.log(Math.abs(-1));
    console.log(Math.max(1,2,3));
    console.log(Math.min(1,2,3));
    console.log(Math.pow(2,3));
    console.log(Math.random()*10);
</script>
```

---

<a id="_2.4"></a>

**4. Date对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--Date-->
<script>
    var date = new Date();
    // 1593579815844
    console.log(date);
    // Wed Jul 01 2020 13:03:35 GMT+0800 (中国标准时间)
    console.log(Date.now());
    // 2020
    console.log(date.getFullYear());
    // 1593579815844
    console.log(date.getTime());
    // 6
    console.log(date.getUTCMonth());
    console.log(date.getMonth());

    // 2020/7/1 下午1:03:35
    console.log(date.toLocaleString());
    // Wed Jul 01 2020
    console.log(date.toDateString());
    // 13:03:35 GMT+0800 (中国标准时间)
    console.log(date.toTimeString());
</script>
```

---

<a id="_2.5"></a>

**5. RegExp正则对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--RegExp-->
<script>
    // 两种定义方式
    var regExp = /\d/i;
    // var regExp = new RegExp("\d","i");
    console.log(regExp.exec("01234"));
    console.log(regExp.test("01234"));
</script>
```

---

<a id="_2.6"></a>

**6. JSON对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--JSON-->
<script>
    // 将JSON字符串转化为JS对象
    var parse = JSON.parse('{"name":"zhangsan","age":27}');
    // {"name":"zhangsan","age":27}
    console.log(parse);
    // Object
    console.log(typeof parse);
    // zhangsan
    console.log(parse.name);
    // 27
    console.log(parse.age);

    // 将JS对象转化为JSON字符串
    var s = JSON.stringify(parse);
    // String
    console.log(typeof s);
    // {"name":"zhangsan","age":27}
    console.log(s);
</script>
```

---

<a id="_2.7"></a>

**7. Array对象**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--Array-->
<script>
    var array = new Array();
    // 存入元素
    array.push(1, 2, 3, 4);
    // 遍历元素
    array.forEach(function (value, index, array) {
        console.log("数组下标： "+index+"  下标对应的值： "+value +" array: "+array);
    });


    /**
     * 数组下标： 0  下标对应的值： 2 array: 2,4,6,8
     * 数组下标： 1  下标对应的值： 4 array: 2,4,6,8
     * 数组下标： 2  下标对应的值： 6 array: 2,4,6,8
     * 数组下标： 3  下标对应的值： 8 array: 2,4,6,8
     *
     */
    // 对数组中的元素进行处理并且返回一个改变之后的数组
    var mapResult = array.map(function (value, index, array) {
            console.log("数组下标： "+index+"  下标对应的值： "+value +" array: "+array);
            return value*2;
        });
    mapResult.forEach(function (value, index, array) {
        console.log("数组下标： "+index+"  下标对应的值： "+value +" array: "+array);
    });


    /**
     * 数组下标： 0  下标对应的值： 3 array: 3,4
     * 数组下标： 1  下标对应的值： 4 array: 3,4
     */
    var filterArray = array.filter(function (value, index, array) {
        console.log("数组下标： "+index+"  下标对应的值： "+value +" array: "+array);
        return value > 2;
    });
    filterArray.forEach(function (value, index, array) {
        console.log("数组下标： "+index+"  下标对应的值： "+value +" array: "+array);
    });


    /**
     * 从下标 1 开始，然后进行自定义运算
     *
     * 数组下标： 0  下标对应的值： 1 array: 1,2,3,4
     * 数组下标： 1  下标对应的值： 2 array: 1,2,3,4
     * 数组下标： 2  下标对应的值： 3 array: 1,2,3,4
     * 数组下标： 3  下标对应的值： 4 array: 1,2,3,4
     */
    var reduceResult = array.reduce(function (previousValue,currentValue,index,array) {
        console.log(" previousValue: "+previousValue+" currentValue: "+currentValue+" index: "+index+" array: "+array);
        return previousValue+currentValue;
    });
    console.log(reduceResult);
</script>
```

---

<a id="_3"></a>

## `BOM浏览器模型常用方法`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!--BOM(Windows\Location\History)-->
<script>
    // 警告框
    alert("Hello");
    // 输入框
    var resutl = prompt("Please input ","default string");
    // 选择框
    var result = confirm("Please choose");

    location.href;
    location.search;
    location.reload();

    history.back();
    history.forward();
    history.go(1);
</script>
```

---

<a id="_4"></a>

## `DOM文档模型常用函数`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_4.1"></a>

**1. 基本示例模型准备(必看)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的测试类</title>
</head>
<body>
    <nav>导航栏</nav>
    <aside id="myaside" class="val01 val02">侧边栏</aside>
    <article>文章</article>
    <footer>结尾</footer>
</body>
<script>
    // 基本元素的获取
    var html = document.getElementsByTagName("html")[0];
    var head = html.firstChild;
    var nav = document.getElementsByTagName("nav")[0];
    var aside = document.getElementsByTagName("aside")[0];
</script>
</html>
```

---

<a id="_4.2"></a>

**2. 元素节点操作(简单易懂的元素节点进行演示)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * 元素节点操作(针对最简单易懂的元素节点进行演示)
 */
// 节点的向上操作(找父母)
nav.parentElement;
// 节点的向下操作(找儿子)
nav.firstElementChild;
nav.lastElementChild;
// 节点的左右操作，同一等级的操作(找兄弟)
nav.nextElementSibling;
nav.previousElementSibling;

/**
 * 节点操作补充(包括了文本节点和属性节点)
 */
nav.previousSibling;
nav.nextSibling;
nav.parentNode;
nav.childNodes;
```

---

<a id="_4.3"></a>

**3. 元素属性的增删查改**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * 元素属性的增删查改
 */
aside.id;
aside.id='1';
aside.className;
aside.className='cla';
aside.innerHTML;
aside.innerHTML = "innerContent";
aside.value;
aside.value = 'val';
aside.style.color;
aside.style.color='red';
// 查看所有属性
aside.attributes;
```

---

<a id="_4.4"></a>

**4. 元素的增加和删除**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * 元素的增加和删除
 */
var div = document.createElement('div');
aside.appendChild(div);
aside.removeChild(div);
```

---

<a id="_4.5"></a>

**5. 事件**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
/**
 * 事件
 */
nav.onclick = function () {
    alert("nav is clicked !")
};
```

---

<a id="_5"></a>

## `JS高级`

---

<a id="_5.1"></a>

**1. Class对象的定义**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
function Person(name,age) {
    this.name = name
    this.age = age
    this.sayName =function () {
        console.log(this.name);
    }
}
var person = new Person("Lisi", 20);
```

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

