# VUE学习笔记

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self">`VUE基础入门(level1)`</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">基本概念和入门程序</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">入门小Demo</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">常用指令</a>
1.4 <a href="#_1.4" rel="nofollow" target="_self">v-on: 事件修饰符</a>
1.5 <a href="#_1.5" rel="nofollow" target="_self">v-model 双向数据绑定</a>
1.6 <a href="#_1.6" rel="nofollow" target="_self">VUE的样式设置</a>
1.7 <a href="#_1.7" rel="nofollow" target="_self">v-for迭代循环</a>
1.8 <a href="#_1.8" rel="nofollow" target="_self"></a>
1.9 <a href="#_1.9" rel="nofollow" target="_self"></a>
### <a href="#_2" rel="nofollow" target="_self">VUE基础入门(level2)</a>
### <a href="#_100" rel="nofollow" target="_self">`踩坑，错误总结`</a>
100.1 <a href="#_100.1" rel="nofollow" target="_self">没有放入VUE对象指定元素中，无法实现MVVM之间的层级交互</a>
100.2 <a href="#_100.2" rel="nofollow" target="_self">参数传递转化为字符串的注意点</a>

---

<a id="_1"></a>

## `VUE基础入门(level1)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. 基本概念和入门程序**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

**vue是什么呢？就是我们的JS框架，可以简化我们的DOM操作。并且更加贴合后端的MVC模型，对于VUE来说，前端的模型就是MVVM模型**

层级 | 解释
--- | ---
M | 模型层，保存页面中的数据(例如元素标签中的文本内容)
VM | 调度层，类似于后端的Service，负责逻辑的调度
V | 视图层，就是我们页面的展示

---

<a id="_1.2"></a>

**2. 入门小Demo**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

2.1 演示和完整代码

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701193504993.png)

```java
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的测试类</title>
    <script src="../static/bootstrap/js/vue-2.4.0.js"></script>
</head>
<body>

<!-- 我们的 v 层，视图层 -->
<div id="app">
    <p>{{ msg }}</p>
</div>

</body>
<script>
    /**
     * 负责 vm 层，我们的调度着
     */
    var vm = new Vue({
        el: '#app', // 表示我们创建这个实例可以去控制指定的元素
        /**
         * 这里是我们的 M 层，保存页面中的数据。
         */
        data:{
            msg:'Welcome to learning vue'
        }
    });
    // 以后我们就不需要主动去指定页面中的DOM元素去操作相关的元素
</script>
</html>
```

2.2 我们的 V ，视图

```java
<!-- 我们的 v 层，视图层 -->
<div id="app">
    <p>{{ msg }}</p>
</div>
```

2.3 我们的 VM ，调度业务

```java
/**
 * 负责 vm 层，我们的调度着
 */
var vm = new Vue({
    el: '#app', // 表示我们创建这个实例可以去控制指定的元素
    /**
     * 这里是我们的 M 层，保存页面中的数据。
     */
    data:{
        msg:'Welcome to learning vue'
    }
});
// 以后我们就不需要主动去指定页面中的DOM元素去操作相关的元素
```

2.4 我们的 M ，数据提供

```java
    data:{
        msg:'Welcome to learning vue'
    }
```

---

<a id="_1.3"></a>

**3. 常用指令**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701193143376.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

3.1 指令说明

指令 | 说明 | 简写 | 说明
--- | --- | --- | ---
v-cloak | 防止闪烁，配合 {{ msg }}使用 | \ | `<p v-cloak>++{{ msg }}</p>`
v-text | 绑定数据，不会闪烁 | \ | `<h1 v-text="msg">++</h1>`
v-html | 绑定数据，并且可以显示文本中标签 | \ | `<div v-html="mytitle">++</div>`
v-bind | 标签非文本(text)属性绑定数据 | : | `<input type="button" value="按钮1" v-bind:title="mytitle+'  add content'" ><br>`
v-on | 绑定事件 | @ | `<input type="button" value="点击按钮" v-on:click="show"><br>`

3.2 图片示例展示

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701200347685.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701200414627.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701200437725.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

3.3 完整代码演示

```java
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../static/bootstrap/js/vue-2.4.0.js"></script>
    <style>
        [v-clock] {
            display: none;
        }
    </style>
</head>
<body>
<!-- 我们的 v 层，视图层 -->
<div id="app">
    <!-- 会发生闪烁问题，解决方案就是在元素中添加样式 -->
    <!-- 与v-text不同之处在于，我们可以在元素中添加内容 -->
    <p v-cloak>++{{ msg }}</p>
    <!--  v-text不会产生元素闪烁问题，但是元素里面的内容会被清空 -->
    <h1 v-text="msg">++</h1>

    <!--
        下面这些元素不在 id 为 app 的 <div> 元素中，所以不能通过MVVM进行数据交互
    -->
    <!-- 除了 v-html 不会忽略html元素，可以正常显示文本中的元素标签。 -->
    <div>++{{msg2}}</div>
    <div v-text="msg2">++</div>
    <div v-html="msg2">++</div>
    <div v-html="mytitle">++</div>

    <!-- v-bind 适用于绑定属性的指令，这个时候我们的 mytitle 就可以看做是我们变量，可以进行字符串拼接 -->
    <input type="button" value="按钮1" v-bind:title="mytitle+'  add content'" ><br>
    <input type="button" value="按钮2" :title="mytitle+'  add content'" ><br>
    <input type="button" value="点击按钮" v-on:click="show"><br>
    <input type="button" value="鼠标覆盖按钮" @mouseover="show">
</div>
</body>
<script>
    /**
     * 负责 vm 层，我们的调度着
     */
    var vm = new Vue({
        el: '#app', // 表示我们创建这个实例可以去控制指定的元素
        /**
         * 这里是我们的 M 层，保存页面中的数据。
         */
        data: {
            msg: 'Welcome to learning vue',
            msg2: '<h2>I want to say !<h2>',
            mytitle: 'This is my title'
        },
        methods:{
            show: function () {
                alert('Clicked');
            }
        }
    });
    // 以后我们就不需要主动去指定页面中的DOM元素去操作相关的元素
</script>
</html>
```

---

<a id="_1.4"></a>

**4. v-on: 事件修饰符**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

4.1 时间修饰符属性介绍 

属性 | 描述
--- | ---
.stop | 阻止冒泡
.prevent  |  阻止默认事件
.capture  |  添加事件侦听器时使用事件捕获模式
.self | 只当事件在该元素本身（比如不是子元素）触发时触发回调
.once | 事件只触发一次

4.2 阻止冒泡示例

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701213010759.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701213022487.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<!-- 阻止冒泡，内部元素的按钮被点击了，会从内部元素的点击事件冒泡到外部点击事件
        冒泡示例：内部元素 外部元素
 -->
<div id="vm03" @click="outer" style="background: red">
    <!-- 阻止冒泡 .stop -->
    <input type="button" value="内部" @click.stop="inner">
</div>
```

```java
<!-- 事件修饰符 v-on: -->
<script>
    var vm03 = new Vue({
        el: '#vm03', // 表示我们创建这个实例可以去控制指定的元素
        /**
         * 这里是我们的 M 层，保存页面中的数据。
         */
        data: {
            msg1: '内部元素',
            msg2: '外部元素'
        },
        methods:{
            inner(){
                console.log(this.msg1);
            },
            outer(){
                console.log(this.msg2);
            }
        }
    });
</script>
```

4.3 阻止元素默认行为(点击超链接就不会跳转)

```java
<!-- 使用 .prevent 阻止默认行为 -->
<div id="vm03">
    <a href="http://www.baidu.com" @click.prevent="linkClick">有问题，先去百度</a>
</div>
```

4.4 实现捕获触发事件的机制(捕获机制就是事件从外向内执行)

```java
<!-- 使用  .capture 实现捕获触发事件的机制 -->
<div id="vm03" @click.capture="div1Handler">
  <input type="button" value="戳他" @click="btnHandler">
</div>
```

4.5 实现只有点击当前元素时候，才会触发事件处理函数

```java
<!-- 使用 .self 实现只有点击当前元素时候，才会触发事件处理函数 -->
 <div id="vm03" @click="div1Handler">
  <input type="button" value="戳他" @click.self="btnHandler">
</div>
```

4.6 只触发一次事件处理函数

```java
<!-- 使用 .once 只触发一次事件处理函数 -->
<div id="vm03">
<a href="http://www.baidu.com" @click.prevent.once="linkClick">有问题，先去百度</a>
</div>
```

4.7 .stop 和 .self 的区别(.self 只会阻止自己身上冒泡行为的触发，并不会真正阻止 冒泡的行为)

```java
<!-- 演示： .stop 和 .self 的区别 -->
 <div id="vm03" @click="div2Handler">
  <div class="inner" @click="div1Handler">
    <input type="button" value="戳他" @click.stop="btnHandler">
  </div>
</div> 

<!-- .self 只会阻止自己身上冒泡行为的触发，并不会真正阻止 冒泡的行为 -->
 <div id="vm03" @click="div2Handler">
  <div class="inner" @click.self="div1Handler">
    <input type="button" value="戳他" @click="btnHandler">
  </div>
</div> 
```

---

<a id="_1.5"></a>

**5. v-model 双向数据绑定**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

5.1 简单实用 v-model 的功能

说明：就是我们可以从M层传数据到V层，也可以从V层传数据到M层，也就是数据的双向绑定。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701222915336.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701222920440.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701222928116.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701222935648.png)

```java
<!-- 数据双向绑定 常用于 radio text select checkbox -->
<div id="vm04">
    <input type="text" v-model="msg"/>
    <input type="radio" name="1" v-model="msg" value="单选按钮一"/>
    <input type="radio" name="1" v-model="msg" value="单选按钮二"/>
    <br>
    <p v-html="msg"></p>
</div>
```

```java
<!-- 数据双向绑定 v-model -->
<script>
    var vm04 = new Vue({
        el: '#vm04',
        data: {
            msg: '内部元素',
        },
        methods: {
            inner() {
                console.log(this.msg1);
            }
        }
    });
</script>
```

5.2 v-model方便实现简易计算器

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701230814401.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<!-- 案例：简易计算器 -->
<div id="vm05">
    <input type="number" v-model="num1"><p v-html="choose"></p>
    <input type="number" v-model="num2">
    <select v-model="choose">
        <option value="+" selected>+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
    </select><br>
    <input type="button" value="计算" @click="cacu">
    <div v-html="'计算结果：' + result"></div>
</div>
```

```java
<!-- 案例：简易计算器 -->
<script>
    var vm05 = new Vue({
        el: '#vm05',
        data: {
            result: 0,
            choose: "+",
            num1:10,
            num2:20
        },
        methods: {
            cacu() {
                // 解决v-model传递过来类型不一致的问题
                // 将我们的数据转化为number类型然后再进行计算
                var number1 = parseInt(this.num1);
                var number2 = parseInt(this.num2);
                switch (this.choose) {
                    case "+":this.result=number1+number2;break;
                    case "-":this.result=number1-number2;break;
                    case "*":this.result=number1*number2;break;
                    case "/":this.result=number1/number2;break;
                }
            }
        }
    });
</script>
```

---

<a id="_1.6"></a>

**6. VUE的样式设置**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

6.1 总体分为三种样式设置方式(class数组，class对象，内联对象)

实现方式 | 说明 | 示例
--- | -- | --------
class数组 | 1. 直接写入class字符串<br> 2. 三目表达式<br> 3. 对象 | `<h1 :class="['my-red',isGreen?'my-font-color':'',{'my-width':true},{'my-height':false}]">HEAD01</h1>`
class对象 | 1. 通过data传参数<br> 2. 直接定义对象 | `<h2 :class="{ 'my-red': true, 'my-font-color': false, 'my-with': true, 'my-height': false }">我是二号标题 样式一</h2><h2 :class="classObj">我是二号标题 样式二</h2>` 
:style直接定义内联样式 | 1. 直接在元素上添加<br> 2. 通过 M 层传递过来<br> 3. 可以通过对象数组实现 | `<h1 :style="{color:'blue','font-size':'15px'}">Style00</h1><h1 :style="innerStyle1">Style01</h1><h1 :style="[innerStyle1,innerStyle2]">Style02</h1>`
 
6.2 完整代码

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200702085913155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<!-- vue 设置元素样式 -->
<style>
    .my-red {
        background: red;
    }

    .my-font {
        font-size: large;
    }

    .my-font-color {
        color: green;
    }

    .my-bold {
        font-weight: bold;
    }

    .my-width {
        width: 500px;
    }

    .my-height {
        height: 800px;
    }
</style>
<div id="vm06">
    <h1 class="my-red">这是一个很大很大的H1，大到你无法想象！！！</h1>
    <hr>
    <!-- 数组方式：通过class属性进行样式添加
            1. 直接写入class字符串 2. 三目表达式 3. 对象
     -->
    <h1 :class="['my-red',isGreen?'my-font-color':'',{'my-width':true},{'my-height':false}]">HEAD01</h1>
    <hr>
    <!-- 对象方式：通过class或者M层数据传递
            1. 如果类名中带有 '-' ，那么我们就必须要给这个类名添加引号，否则无效
            2. 可以直接定义或者通过M层传递对象实现
     -->
    <h2 :class="{ 'my-red': true, 'my-font-color': false, 'my-with': true, 'my-height': false }">我是二号标题 样式一</h2>
    <h2 :class="classObj">我是二号标题 样式二</h2>
    <hr>

    <!-- :style直接定义内联样式：类似于在标签中添加内联样式
            1. 直接在元素上添加 2. 通过 M 层传递过来 3. 可以通过对象数组实现
    -->
    <h1 :style="{color:'blue','font-size':'15px'}">Style00</h1>
    <h1 :style="innerStyle1">Style01</h1>
    <h1 :style="[innerStyle1,innerStyle2]">Style02</h1>
</div>
```

```java
<!-- vue 设置样式 -->
<script>
    var vm06 = new Vue({
        el: '#vm06',
        data: {
            msg: '内部元素',
            isGreen: true,
            classObj: {'my-red': false, 'my-font-color': true, 'my-with': true, 'my-height': false},

            innerStyle1: {color: 'blue', 'font-size': '15px'},
            innerStyle2: {color: 'yellow', 'background': 'red', 'width': '90px', 'height': '90px'},
        },
        methods: {
            inner() {
                console.log(this.msg1);
            }
        }
    });
</script>
```

---

<a id="_1.7"></a>

**7. v-for迭代循环**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

7.1 完整代码

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200702094252988.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<!-- v-for迭代 -->
<div id="vm07">
    <!-- 遍历数组 -->
    <div v-for="(item,index) in list">
        <p>index: {{index}} ----- value: {{item}}</p>
    </div>

    <!-- 遍历对象 -->
    <div v-for="(val,key,index) in obj">
        <p>属性名： {{key}} ---- 属性值：{{val}} ---- 对应索引：{{index}}</p>
    </div>

    <!-- 遍历对象数组 -->
    <div v-for="(item,index) in arrayObj">
        <p> 索引： {{index}} ----
            姓名： {{item.name}} ----
            年龄：{{item.age}} ----
            教育经历一：{{item.school[0]}} ----
            教育经历二：{{item.school[1]}} ----
        </p>
    </div>

    <!-- 数字循环 -->
    <div v-for="i in 10">
        <p>数字打印： {{i}}</p>
    </div>

</div>
```

```java
<!-- v-for -->
<script>
    var vm07 = new Vue({
        el: '#vm07',
        data: {
            list:[1,true,"Liuliu",{name:"Sunqi",age:66,school:["小学","中学"]}],
            obj:{name:"zhangsna",age:27,good:true,school:["小学","中学"]},
            arrayObj:[{name:"Lise",age:33,school:["小学","中学"]},{name:"Wangwu",age:40,school:["高中","大学"]}],
        },
        methods: {
            inner() {
                console.log(this.msg1);
            }
        }
    });
</script>
```

7.1 遍历数组

```java
<!-- 遍历数组 -->
<div v-for="(item,index) in list">
    <p>index: {{index}} ----- value: {{item}}</p>
</div>
```

7.2 遍历对象

```java
<!-- 遍历对象 -->
<div v-for="(val,key,index) in obj">
<p>属性名： {{key}} ---- 属性值：{{val}} ---- 对应索引：{{index}}</p>
</div>
```

7.3 遍历对象数组

```java
<!-- 遍历对象数组 -->
<div v-for="(item,index) in arrayObj">
    <p> 索引： {{index}} ----
        姓名： {{item.name}} ----
        年龄：{{item.age}} ----
        教育经历一：{{item.school[0]}} ----
        教育经历二：{{item.school[1]}} ----
    </p>
</div>
```

7.4 数字循环

```java
<!-- 数字循环 -->
<div v-for="i in 10">
    <p>数字打印： {{i}}</p>
</div>
```

---

<a id="_2"></a>

## `VUE基础入门(level2)`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>



---

<a id="_100"></a>

## `踩坑，错误总结`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_100.1"></a>

**1. 没有放入VUE对象指定元素中，无法实现MVVM之间的层级交互**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 说明 

VUE只有在指定的元素标签中才能够进行MVVM之间的数据交互，否则是无法传递数据交互的。

1.2 下面来做出演示

1.2.1 错误示例

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701194620268.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<body>
<!-- 我们的 v 层，视图层 -->
<div id="app">
    <!-- 会发生闪烁问题，解决方案就是在元素中添加样式 -->
    <!-- 与v-text不同之处在于，我们可以在元素中添加内容 -->
    <p v-cloak>++{{ msg }}</p>
    <!--  v-text不会产生元素闪烁问题，但是元素里面的内容会被清空 -->
    <h1 v-text="msg">++</h1>
</div>

<div>
    <!--
        下面这些元素不在 id 为 app 的 <div> 元素中，所以不能通过MVVM进行数据交互
    -->
    <!-- 除了 v-html 不会忽略html元素，可以正常显示文本中的元素标签。 -->
    <div>++{{msg2}}</div>
    <div v-text="msg2">++</div>
    <div v-html="msg2">++</div>
    <div v-html="mytitle">++</div>

    <!-- v-bind 适用于绑定属性的指令，这个时候我们的 mytitle 就可以看做是我们变量，可以进行字符串拼接 -->
    <input type="button" value="按钮" v-bind:title="mytitle+'  add content'" >
</div>
</body>
<script>
    /**
     * 负责 vm 层，我们的调度着
     */
    var vm = new Vue({
        el: '#app', // 表示我们创建这个实例可以去控制指定的元素
        /**
         * 这里是我们的 M 层，保存页面中的数据。
         */
        data: {
            msg: 'Welcome to learning vue',
            msg2: '<h2>I want to say !<h2>',
            mytitle: 'This is my title'
        }
    });
    // 以后我们就不需要主动去指定页面中的DOM元素去操作相关的元素
</script>
```

1.2.2 正确示例

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701194742630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<body>
<!-- 我们的 v 层，视图层 -->
<div id="app">
    <!-- 会发生闪烁问题，解决方案就是在元素中添加样式 -->
    <!-- 与v-text不同之处在于，我们可以在元素中添加内容 -->
    <p v-cloak>++{{ msg }}</p>
    <!--  v-text不会产生元素闪烁问题，但是元素里面的内容会被清空 -->
    <h1 v-text="msg">++</h1>

    <!--
        下面这些元素都是在 id 为 app 的 <div> 元素中，所以可以通过MVVM进行数据交互
    -->

    <!-- 除了 v-html 不会忽略html元素，可以正常显示文本中的元素标签。 -->
    <div>++{{msg2}}</div>
    <div v-text="msg2">++</div>
    <div v-html="msg2">++</div>
    <div v-html="mytitle">++</div>

    <!-- v-bind 适用于绑定属性的指令，这个时候我们的 mytitle 就可以看做是我们变量，可以进行字符串拼接 -->
    <input type="button" value="按钮" v-bind:title="mytitle+'  add content'" >
</div>
</body>
<script>
    /**
     * 负责 vm 层，我们的调度着
     */
    var vm = new Vue({
        el: '#app', // 表示我们创建这个实例可以去控制指定的元素
        /**
         * 这里是我们的 M 层，保存页面中的数据。
         */
        data: {
            msg: 'Welcome to learning vue',
            msg2: '<h2>I want to say !<h2>',
            mytitle: 'This is my title'
        }
    });
    // 以后我们就不需要主动去指定页面中的DOM元素去操作相关的元素
</script>
```

---

<a id="_100.2"></a>

**2. 参数传递转化为字符串的注意点**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

2.1 说明

在简易计算器案例中发现通过 v-model 传递过来的参数都变为了字符串，导致一些计算中出现了错误。

2.2 演示

2.2.1 错误示例

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701230233966.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<!-- 案例：简易计算器 -->
<div id="vm05">
    <input type="number" v-model="num1"><p v-html="choose"></p>
    <input type="number" v-model="num2">
    <select v-model="choose">
        <option value="+" selected>+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
    </select><br>
    <input type="button" value="计算" @click="cacu">
    <div v-html="'计算结果：' + result"></div>
</div>
```

```java
<!-- 案例：简易计算器 -->
<script>
    var vm05 = new Vue({
        el: '#vm05',
        data: {
            result: 0,
            choose: "+",
            num1:10,
            num2:20
        },
        methods: {
            cacu() {
                var number1 = this.num1;
                var number2 = this.num2;
                switch (this.choose) {
                    case "+":this.result=number1+number2;break;
                    case "-":this.result=number1-number2;break;
                    case "*":this.result=number1*number2;break;
                    case "/":this.result=number1/number2;break;
                }
            }
        }
    });
</script>
```

2.2.2 正确示例

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200701225744200.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<!-- 案例：简易计算器 -->
<div id="vm05">
    <input type="number" v-model="num1"><p v-html="choose"></p>
    <input type="number" v-model="num2">
    <select v-model="choose">
        <option value="+" selected>+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
    </select><br>
    <input type="button" value="计算" @click="cacu">
    <div v-html="'计算结果：' + result"></div>
</div>
```

```java
<!-- 案例：简易计算器 -->
<script>
    var vm05 = new Vue({
        el: '#vm05',
        data: {
            result: 0,
            choose: "+",
            num1:10,
            num2:20
        },
        methods: {
            cacu() {
                // 解决v-model传递过来类型不一致的问题
                // 将我们的数据转化为number类型然后再进行计算
                var number1 = parseInt(this.num1);
                var number2 = parseInt(this.num2);
                switch (this.choose) {
                    case "+":this.result=number1+number2;break;
                    case "-":this.result=number1-number2;break;
                    case "*":this.result=number1*number2;break;
                    case "/":this.result=number1/number2;break;
                }
            }
        }
    });
</script>
```




