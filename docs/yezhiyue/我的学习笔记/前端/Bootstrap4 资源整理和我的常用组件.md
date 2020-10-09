

# Bootstrap4 资源整理和我的常用组件

- [**官方文档**](https://getbootstrap.net/docs/getting-started/introduction/)

- [**布局工具网站**](http://www.ibootstrap.cn/)

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self">表单组件</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">注册表单</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">登录表单</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">个人信息表单</a>
1.4 <a href="#_1.4" rel="nofollow" target="_self">搜索表单(单个搜索框和按钮)</a>
1.5 <a href="#_1.5" rel="nofollow" target="_self">单选按钮组</a>
1.6 <a href="#_1.6" rel="nofollow" target="_self">复选按钮组</a>
1.7 <a href="#_1.7" rel="nofollow" target="_self">白色按钮</a>
### <a href="#_2" rel="nofollow" target="_self">表格&&分页</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">条纹表格</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">普通分页</a>
### <a href="#_3" rel="nofollow" target="_self">导航栏(普通常用)</a>
### <a href="#_4" rel="nofollow" target="_self">列表</a>
4.1 <a href="#_4.1" rel="nofollow" target="_self">简单列表(白色)</a>

---

<a id="_1"></a>

## `表单组件`

---

[官方表单组件文档](https://getbootstrap.net/docs/components/forms/)

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

### 自己制作的模块

---

<a id="_1.1"></a>

**1. 注册表单**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<form class="container" method="post" accept-charset="usr/register">
    <!--Name-->
    <div class="form-group row">
        <label for="inputName" class="col-lg-1 col-form-label align-middle">Name</label>
        <div class="col-lg-8">
            <input type="text" class="form-control col-lg-4" id="inputName" placeholder="Name">
        </div>
    </div>
    <!--Password-->
    <div class="form-group row">
        <label for="inputPassword3" class="col-lg-1 col-form-label">Password</label>
        <div class="col-lg-8">
            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
        </div>
    </div>
    <!--Email-->
    <div class="form-group row">
        <label for="inputEmail3" class="col-lg-1 col-form-label align-middle">Email</label>
        <div class="col-lg-8">
            <input type="email" class="form-control col-lg-4" id="inputEmail3" placeholder="Email">
        </div>
    </div>
    <!--Phone-->
    <div class="form-group row">
        <label for="inputPhone" class="col-lg-1 col-form-label align-middle">Phone</label>
        <div class="col-lg-8">
            <input type="number" class="form-control col-lg-4" id="inputPhone" placeholder="Phone">
        </div>
    </div>
    <!--Address-->
    <div class="form-group row">
        <label for="inputAddress" class="col-lg-1 col-form-label align-middle">Address</label>
        <div class="col-lg-8">
            <input type="text" class="form-control col-lg-4" id="inputAddress" placeholder="Address">
        </div>
    </div>
    <!--Radios-->
    <fieldset class="form-group">
        <div class="row">
            <label class="col-form-label col-lg-1">Radios</label>
            <div class="col-lg-10">
                <div class="form-check col-lg-2">
                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1"
                           checked>
                    <label class="form-check-label" for="gridRadios1">
                        First radio
                    </label>
                </div>
                <div class="form-check col-lg-2">
                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
                    <label class="form-check-label" for="gridRadios2">
                        Second radio
                    </label>
                </div>
                <div class="form-check col-lg-2">
                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3">
                    <label class="form-check-label" for="gridRadios3">
                        Third radio
                    </label>
                </div>
            </div>
        </div>
    </fieldset>
    <!--CheckBox-->
    <div class="form-group row">
        <div class="col-lg-1">Checkbox</div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck1">
                <label class="form-check-label" for="gridCheck1">
                    Example checkbox
                </label>
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck2">
                <label class="form-check-label" for="gridCheck2">
                    Example checkbox
                </label>
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck3">
                <label class="form-check-label" for="gridCheck3">
                    Example checkbox
                </label>
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck4">
                <label class="form-check-label" for="gridCheck4">
                    Example checkbox
                </label>
            </div>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-lg-3"></div>
        <div class="col-lg-1">
            <button type="submit" class="btn btn-primary">注册</button>
        </div>
        <div class="col-lg-1">
            <button type="submit" class="btn btn-default">登录</button>
        </div>
        <div class="col-lg-3"></div>
    </div>
</form>
```

<a id="_1.2"></a>

**2. 登录表单**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<form class="container" method="post" accept-charset="usr/register">
    <!--Name-->
    <div class="form-group row">
        <label for="inputName" class="col-lg-1 col-form-label align-middle">Name</label>
        <div class="col-lg-8">
            <input type="text" class="form-control col-lg-4" id="inputName" placeholder="Name">
        </div>
    </div>
    <!--Password-->
    <div class="form-group row">
        <label for="inputPassword3" class="col-lg-1 col-form-label">Password</label>
        <div class="col-lg-8">
            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
        </div>
    </div>
    <div class="form-group row">
        <div class="col-lg-3"></div>
        <div class="col-lg-1">
            <button type="submit" class="btn btn-primary">注册</button>
        </div>
        <div class="col-lg-1">
            <button type="submit" class="btn btn-default">登录</button>
        </div>
        <div class="col-lg-3"></div>
    </div>
</form>
```

<a id="_1.3"></a>

**3. 个人信息表单**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_1.4"></a>

**4. 搜索表单(单个搜索框和按钮)**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<nav class="container navbar navbar-light bg-light">
    <form class="form-inline">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
```

---

### 其他常用表单组件

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_1.5"></a>

**1. 单选按钮组**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
    <!--Radios-->
    <fieldset class="form-group">
        <div class="row">
            <label class="col-form-label col-lg-1">Radios</label>
            <div class="col-lg-10">
                <div class="form-check col-lg-2">
                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1"
                           checked>
                    <label class="form-check-label" for="gridRadios1">
                        First radio
                    </label>
                </div>
                <div class="form-check col-lg-2">
                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
                    <label class="form-check-label" for="gridRadios2">
                        Second radio
                    </label>
                </div>
                <div class="form-check col-lg-2">
                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3">
                    <label class="form-check-label" for="gridRadios3">
                        Third radio
                    </label>
                </div>
            </div>
        </div>
    </fieldset>
```

<a id="_1.6"></a>

**2. 复选按钮组**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
    <!--CheckBox-->
    <div class="form-group row">
        <div class="col-lg-1">Checkbox</div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck1">
                <label class="form-check-label" for="gridCheck1">
                    Example checkbox
                </label>
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck2">
                <label class="form-check-label" for="gridCheck2">
                    Example checkbox
                </label>
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck3">
                <label class="form-check-label" for="gridCheck3">
                    Example checkbox
                </label>
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck4">
                <label class="form-check-label" for="gridCheck4">
                    Example checkbox
                </label>
            </div>
        </div>
    </div>
```

<a id="_1.7"></a>

**3. 白色按钮**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<button type="button" class="btn btn-light">Light</button>
```
---

<a id="_2"></a>

## `表格&&分页`

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

<a id="_2.1"></a>

**1. 条纹表格**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626223126238.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

```java
<div class="container"><table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Username</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>Otto</td>
        <td>@mdo</td>
    </tr>
    <tr>
        <th scope="row">2</th>
        <td>Jacob</td>
        <td>Thornton</td>
        <td>@fat</td>
    </tr>
    <tr>
        <th scope="row">3</th>
        <td>Larry</td>
        <td>the Bird</td>
        <td>@twitter</td>
    </tr>
    </tbody>
</table>
</div>
```

<a id="_2.2"></a>

**2. 普通分页**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<nav class="container" aria-label="...">
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Previous</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item active">
            <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>
```

---

<a id="_3"></a>

## `导航栏(普通常用)`

---

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
```

---

<a id="_4"></a>

## `列表`

---

<a id="_4.1"></a>

**1. 简单列表(白色)**

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

```java
<ul class="list-group">
  <li class="list-group-item">Cras justo odio</li>
  <li class="list-group-item">Dapibus ac facilisis in</li>
  <li class="list-group-item">Morbi leo risus</li>
  <li class="list-group-item">Porta ac consectetur ac</li>
  <li class="list-group-item">Vestibulum at eros</li>
</ul>
```


