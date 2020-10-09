
---

<a id="_1"></a>

## `PicGo入门使用`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. 下载安装**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

[下载地址](https://github.com/Molunerfinn/PicGo/releases)

根据操作系统选择版本即可

---

<a id="_1.2"></a>

**2. 配置图床**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

需要一个图床，根据自己的需要选择。我选择的是GitHub，简单免费

[GitHub图床配置](https://picgo.github.io/PicGo-Doc/zh/guide/config.html#github%E5%9B%BE%E5%BA%8A)

1.1 我的GitHub配置

  "repo": "YeZhiyue/PicGo", // 仓库名，格式是username/reponame
  "token": "b59768a5f79bb79a18685fceb0ec11f4a6bf6111", // github token
  "path": "blog/", // 自定义存储路径，比如img/
  "customUrl": "https://yezhiyueblog.com", // 自定义域名，注意要加http://或者https://
  "branch": "master" // 分支名，默认是master

---

<a id="_1.3"></a>

**3. 七牛图床的配置使用**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

七牛的使用需要个人域名，没有的话会给你分配给你一个30天的域名，比较麻烦。

[参考博客](https://blog.csdn.net/javacs123/article/details/106268130)

参数配置地址：

密钥Key地址：https://portal.qiniu.com/user/key
存储空间设置，注意添加为公有，否则访问不了：https://portal.qiniu.com/kodo/bucket
存储区域设置需要注意：新建存储空间时候选择的存储区域（华东：z0, 华北：z1, 华南：z2，北美：na0, 东南亚：as0）,以此类推，填错了pigGo会提示。

{
  "accessKey": "dTu8HA1RUG8LxIyL-2bNxKQRz_a4pMIqNftUaQTg",
  "secretKey": "dTu8HA1RUG8LxIyL-2bNxKQRz_a4pMIqNftUaQTg",
  "bucket": "yezhiyuepicgo", // 存储空间名
  "url": "http://qft6wmzla.hn-bkt.clouddn.com", // 自定义域名
  "area": "z2", // 存储区域编号
  "options": "?picgo", // 网址后缀，比如?imgslim
  "path": "picgo/" // 自定义存储路径，比如img/
}

---

<a id="_2"></a>

## `PicGo高级`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. 插件设置**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

这里PicGo支持插件拓展，如上床文件到gitee。

---

<a id="_2.2"></a>

**2. 命令行上传文件**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

---

<a id="_2.3"></a>

**3. 快捷键上床剪贴板图片**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

可以在软件设置页面进行设置，默认是ctrl + shift + p





