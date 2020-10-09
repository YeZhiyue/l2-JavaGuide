
## 初级学习点

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

### VUE如何设置和获取数据


```java
<div id="app">
  {{ message }}
</div>
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})
```

用后端的思维来理解就是，VUE需要我们先定一个方法，然后在方法里面传递参数，然后标签可以作为方法体(类似于{...}里面的东西)，方法体里面就可以使用方法里面代入的参数。

1.2 看看我们方法的定义

```java
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})
```

1.3 看看我们方法体的定义

```java
<div id="app">
  {{ message }}
</div>
```

### VUE的数据来源可以是什么

1.1 可以自己定义成死数据

```java
 message: '页面加载于 '
```

1.2 可以通过内置对象或者自己定义的对象来获取

```java
 message: '页面加载于 ' + new Date().toLocaleString()
```

1.3 可以通过链接来获取

### 方法里面的条件判断和循环

1.1 条件判断

```java
<div id="app-3">
  <p v-if="seen">现在你看到我了</p>
</div>
var app3 = new Vue({
  el: '#app-3',
  data: {
    seen: true
  }
})
```

1.2 循环

```java
<div id="app-4">
  <ol>
    <li v-for="todo in todos">
      {{ todo.text }}
    </li>
  </ol>
</div>
var app4 = new Vue({
  el: '#app-4',
  data: {
    todos: [
      { text: '学习 JavaScript' },
      { text: '学习 Vue' },
      { text: '整个牛项目' }
    ]
  }
})
```

### 控制台调试

1.1 获取对象的数据

```java
app.seen
```

1.2 改变对象的数据

```java
app.seen = false
```

1.3 设置数组数据

```java
app4.todos.push({text:'新项目'})
```

### VUE事件

1.1 点击事件

```java
<div id="app-5">
  <p>{{ message }}</p>
  <button v-on:click="reverseMessage">反转消息</button>
</div>
var app5 = new Vue({
  el: '#app-5',
  data: {
    message: 'Hello Vue.js!'
  },
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
})
```

### VUE的双向绑定

方便的实现了表单数据输入和应用状态之间的双向绑定

```java
<div id="app-6">
  <p>{{ message }}</p>
  <input v-model="message">
</div>
var app6 = new Vue({
  el: '#app-6',
  data: {
    message: 'Hello Vue!'
  }
})
```

### VUE中变量的作用域

### VUE中的组件

```java
// 定义名为 todo-item 的新组件
Vue.component('todo-item', {
  template: '<li>这是个待办项</li>'
})

var app = new Vue(...)

<ol>
  <!-- 创建一个 todo-item 组件的实例 -->
  <todo-item></todo-item>
</ol>
```