

# Git学习笔记(命令、实战、踩坑记录)

- [转载原文的地址](https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483866&idx=1&sn=fe987cd24448bd6eb2138cfd43a82cf8&scene=19#wechat_redirect)

<a id="_top"></a>

## `目录:`

### <a href="#_1" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>git常用命令</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.1" rel="nofollow" target="_self">查看git的配置</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.2" rel="nofollow" target="_self">设置用户名和邮箱</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.3" rel="nofollow" target="_self">创建仓库</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.4" rel="nofollow" target="_self">查看文件的状态</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.5" rel="nofollow" target="_self">忽略文件</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.6" rel="nofollow" target="_self">提交代码到暂存区</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.7" rel="nofollow" target="_self">提交暂存区代码到版本库</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.8" rel="nofollow" target="_self">远程仓库和本地仓库交互</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_1.9" rel="nofollow" target="_self">分支操作</a>
### <a href="#_2" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>git理论</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.1" rel="nofollow" target="_self">仓库是什么</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.2" rel="nofollow" target="_self">工作的流程</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.3" rel="nofollow" target="_self">git中对于分支提交是如何更新本地或者远程仓库的</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_2.4" rel="nofollow" target="_self">预防冲突的方式</a>
### <a href="#_3" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>绑定github或者码云的SSH公钥</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.1" rel="nofollow" target="_self">创建本地的SSH公钥</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_3.2" rel="nofollow" target="_self">将公钥信息添加到码云或者gitHub的账户中</a>
### <a href="#_4" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>使用IDEA进行Git操作</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.1" rel="nofollow" target="_self">git 中文件状态区分</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.2" rel="nofollow" target="_self">git中编辑版本控制文件的特殊记号</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.3" rel="nofollow" target="_self">和远程分支比较的日志如何查看</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.4" rel="nofollow" target="_self">和远程分支进行比较</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.5" rel="nofollow" target="_self">和本地修改历史进行比较</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.6" rel="nofollow" target="_self">快捷键补充</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_4.7" rel="nofollow" target="_self">如何查看git的分支日志</a>
### <a href="#_5" rel="nofollow" target="_self"><font size=5 color=CC3333 face=微软雅黑>Git错误归纳(踩坑历史)</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.1" rel="nofollow" target="_self">拒绝合并不匹配的历史版本文件 This is usually caused by another repository pushing：refusing to merge unrelated histories</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.2" rel="nofollow" target="_self">push到远程仓库失败了 error: failed to push some refs to 'git@github.com:</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.3" rel="nofollow" target="_self">由于未解决的冲突导致ide中pull和commit都不能执行</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#_5.4" rel="nofollow" target="_self">关于ide中合并冲突中的策略使用ours和theirs是干嘛的还有merge和rebase的区别</a>

## `git常用命令`

---

<a id="_1.1"></a>

**1. 查看git的配置**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 查看配置

```java
git config -l
```

1.2 查看系统配置

```java
git config --system --list
```

1.3 查看用户配置

```java
git config --global --list
```

1.4 相关配置文件

系统级 --system

Git\etc\gitconfig

用户级，只是使用与当前登录的用户 --global

C:\Users\Administrator\ .gitconfig

---

<a id="_1.2"></a>

**2. 设置用户名和邮箱**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
git config --global user.name "kuangshen"  #名称
```

```java
git config --global user.email 24736743@qq.com   #邮箱
```

---

<a id="_1.3"></a>

**3. 创建仓库**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 创建工作目录

工作目录（WorkSpace)一般就是你希望Git帮助你管理的文件夹，可以是你项目的目录，也可以是一个空目录，建议不要有中文。日常使用只要记住下图6个命令：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwMEFJSTZZVm9vVXppYnBpYnpKbm9PSEhYVXNMM2Y5RHFBNGhvclVpYmZjcEVaODhPeWYyZ1FRTlI2dy82NDA?x-oss-process=image/format,png)

1.2 本地仓库的搭建

创建本地仓库的方法有两种：一种是创建全新的仓库，另一种是克隆远程仓库。

1.3  创建全新的仓库，需要用GIT管理的项目的根目录执行：

```java
git init
```

执行后可以看到，仅仅在项目目录多出了一个.git目录，关于版本等的所有信息都在这个目录里面。

1.4 克隆远程仓库

另一种方式是克隆远程目录，由于是将远程服务器上的仓库完全镜像一份至本地！

```java
# 克隆一个项目和它的整个代码历史(版本信息)
$ git clone [url] 
```

可以去 gitee 或者 github 上克隆测试

---

<a id="_1.4"></a>

**4. 查看文件的状态**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
#查看指定文件状态
git status [filename]

#查看所有文件状态
git status

# git add .                  添加所有文件到暂存区
# git commit -m "消息内容"    提交暂存区中的内容到本地仓库 -m 提交信息
```

```java
版本控制就是对文件的版本控制，要对文件进行修改、提交等操作，首先要知道文件当前在什么状态，不然可能会提交了现在还不想提交的文件，或者要提交的文件没提交上。

Untracked: 未跟踪, 此文件在文件夹中, 但并没有加入到git库, 不参与版本控制. 通过git add 状态变为Staged.

Unmodify: 文件已经入库, 未修改, 即版本库中的文件快照内容与文件夹中完全一致. 这种类型的文件有两种去处, 如果它被修改, 而变为Modified. 如果使用git rm移出版本库, 则成为Untracked文件

Modified: 文件已修改, 仅仅是修改, 并没有进行其他的操作. 这个文件也有两个去处, 通过git add可进入暂存staged状态, 使用git checkout 则丢弃修改过, 返回到unmodify状态, 这个git checkout即从库中取出文件, 覆盖当前修改 !

Staged: 暂存状态. 执行git commit则将修改同步到库中, 这时库中的文件和本地文件又变为一致, 文件为Unmodify状态. 执行git reset HEAD filename取消暂存, 文件状态为Modified
```

---

<a id="_1.5"></a>

**5. 忽略文件**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
#为注释
*.txt        #忽略所有 .txt结尾的文件,这样的话上传就不会被选中！
!lib.txt     #但lib.txt除外
/temp        #仅忽略项目根目录下的TODO文件,不包括其它目录temp
build/       #忽略build/目录下的所有文件
doc/*.txt    #会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
```

```java
有些时候我们不想把某些文件纳入版本控制中，比如数据库文件，临时文件，设计文件等

在主目录下建立".gitignore"文件，此文件有如下规则：

忽略文件中的空行或以井号（#）开始的行将会被忽略。

可以使用Linux通配符。例如：星号（*）代表任意多个字符，问号（？）代表一个字符，方括号（[abc]）代表可选字符范围，大括号（{string1,string2,...}）代表可选的字符串等。

如果名称的最前面有一个感叹号（!），表示例外规则，将不被忽略。

如果名称的最前面是一个路径分隔符（/），表示要忽略的文件在此目录下，而子目录中的文件不忽略。

如果名称的最后面是一个路径分隔符（/），表示要忽略的是此目录下该名称的子目录，而非文件（默认文件或目录都忽略）。
```

---

<a id="_1.6"></a>

**6. 提交代码到暂存区**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
# 将一个修改后的文件添加到暂存区
git add readme.md

# gitadd其他用法

# 添加所有修改、删除或新建的文件到暂存区
git add .
# 添加所有以js结尾的文件到暂存区
git add *.js
# 添加所有修改、删除或新建的文件到暂存区
# 除了.开头的文件，比如 .gitignore
git add *
# git add --update 的缩写
# 如果再次修改了在暂存区中的文件，可以通过该命令进行更新
git add -u
# 作用与git add . 相同
git add -A
```

---

<a id="_1.7"></a>

**7. 提交暂存区代码到版本库**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
# 提交暂存区的代码到版本库
git commit -m 'commit message'

# 如果你重新编辑了一些文件，添加到暂存区，想把这些修改合并到上一次提交
# 然后会出现一个编辑框，让你修改上次的提交信息
git commit --amend
# 如果不想修改上次的提交信息
git commit --amend --no-edit
```

---

<a id="_1.8"></a>

**8. 远程仓库和本地仓库交互**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
# 将名为origin的远程版本库的master分支同步到本地的当前分支
git pull origin master

# git pull命令其实是如下两个命令的简写
git fetch origin master
git merge origin/master


## 同步本地版本库到远程仓库

# 将本地的提交同步给远程版本库
git push --set-upstream origin master

# 绑定默认提交的远程版本库
git push -u origin master
# 下次提交只需要使用git push就可以了
git push
```

补充一个暴力拉取操作，但是小心使用

```java
git pull origin master --allow-unrelated-histories
```

---

<a id="_1.9"></a>

**9. 分支操作**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwMEJPR3phRzRRVGM0SlhPMGhTbHdjTnR1ak56QXZ4ZWliU3JhakxZTENUNm90Tm5IRFY5eFlXd0EvNjQw?x-oss-process=image/format,png)

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwMEF5bjg3d294ZmVwT2hTbFVqNEZRVEZVc2lhNGljMGo2YVF5NFR6MzJQUnVKMEhTVmVHZVV6VVJBLzY0MA?x-oss-process=image/format,png)

```java

# 列出所有本地分支
git branch

# 列出所有远程分支
git branch -r

# 新建一个分支，但依然停留在当前分支
git branch [branch-name]

# 新建一个分支，并切换到该分支
git checkout -b [branch]

# 合并指定分支到当前分支
$ git merge [branch]

# 删除分支
$ git branch -d [branch-name]

# 删除远程分支
$ git push origin --delete [branch-name]
$ git branch -dr [remote/branch]
```

简单示例

```java
#1. 先创建feature分支，将手头的代码提交到feature分支上
git checkout -b feature
git add .
git commit -m 'feature branch commit'

#2. 切换回master分支，从master拉一个新的分支
git checkout master
git checkout -b Fix-Bug

#3. bug修改完毕后，提交代码到Fix-Bug分支
git add .
git commit -m 'fixed bug'

#4. 把修复了bug的代码merge到master分支
git checkout master #重新切换回master分支
git pull origin master #把同事提交的代码先更新到本地
git merge Fix-Bug
git push origin master #将merge的代码同步到线上，进行bug修复
git branch -d Fix-Bug #bug修复后将Fix-Bug分支删除
```

<a id="_2"></a>

---

## `git理论`

---

<a id="_2.1"></a>

**1. 仓库是什么**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

Git本地有三个工作区域：工作目录（Working Directory）、暂存区(Stage/Index)、资源库(Repository或Git Directory)。如果在加上远程的git仓库(Remote Directory)就可以分为四个工作区域。文件在这四个区域之间的转换关系如下：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwME5KNEw5T1BJOWlhMU1taWJwdkRkNmNTZGRCZHZybGJkRXR5RU9yaDRDS25XVmlieWZDSGEzbHpYdy82NDA?x-oss-process=image/format,png)

- Workspace：工作区，就是你平时存放项目代码的地方

- Index / Stage：暂存区，用于临时存放你的改动，事实上它只是一个文件，保存即将提交到文件列表信息

- Repository：仓库区（或本地仓库），就是安全存放数据的位置，这里面有你提交到所有版本的数据。其中HEAD指向最新放入仓库的版本

- Remote：远程仓库，托管代码的服务器，可以简单的认为是你项目组中的一台电脑用于远程数据交换

注意了，这三个仓库的本质就是head指向的版本，也就是你最新的版本。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwMGljejZYMmFpYklnVVd6SHh0d1g4a2ljUENLcERyc2lhUHpaazA0T2xJMmJ6bHlkemljQnVYVEp2TEVRLzY0MA?x-oss-process=image/format,png)

- Directory：使用Git管理的一个目录，也就是一个仓库，包含我们的工作空间和Git的管理空间。

- WorkSpace：需要通过Git进行版本控制的目录和文件，这些目录和文件组成了工作空间。

- .git：存放Git管理信息的目录，初始化仓库的时候自动创建。

- Index/Stage：暂存区，或者叫待提交更新区，在提交进入repo之前，我们可以把所有的更新放在暂存区。

- Local Repo：本地仓库，一个存放在本地的版本库；HEAD会只是当前的开发分支（branch）。

- Stash：隐藏，是一个工作状态保存栈，用于保存/恢复WorkSpace中的临时状态。


---

<a id="_2.2"></a>

**2. 工作的流程**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

１、在工作目录中添加、修改文件；

２、将需要进行版本管理的文件放入暂存区域；

３、将暂存区域的文件提交到git仓库。

因此，git管理的文件有三种状态：已修改（modified）,已暂存（staged）,已提交(committed)

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwMDlpYU9obDBkQUNmTHJNd05iRHp1Y0dRMzBzM0huc2lhY3pmY1I2ZEM5T2VoaWN1d2liS3VIalJsemcvNjQw?x-oss-process=image/format,png)


---

<a id="_2.3"></a>

**3. git中对于分支提交是如何更新本地或者远程仓库的**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

首先，本地仓库和远程仓库分别维护着自己的一个分支，本地为local，远程是remote。
接着，我们需要知道本地分支和远程分支通过什么来进行更新的，如果你明白了这一点，那么git的精髓你也就掌握了，分支的更新需要通过commit的历史记录的比较判断来更新，所有只要有不同的commit记录，那么不同分支之间的pull和push或者merge...操作就会通过这些不同的commit记录来对分支进行更新，所以你想要知道每一次进行不同分支之间的操作对那些文件进行了更新，那么你就需要围绕着commit提交来判断。

---

<a id="_2.4"></a>

**4. 预防冲突的方式**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

1.1 首先我们要知道为什么会冲突

情况一：远程分支的冲突，因为有多个开发人员对同一文件进行了修改提交，并且最后这些commit记录最后都提交到了同一个分支上，那么这样子就导致了远程分支的冲突
情况二：本地分支的冲突，你修改了本地的文件或者远程的分支更新了文件，并且这个文件是同一个，那么在本地拉取远程分支的时候就会产生冲突

1.2 遇到冲突，该如何解决

情况一：你比较了解这些代码的情况，那么可以直接通过你的理解对代码的冲突进行合并。
情况二：你不太了解这些代码，并且这些代码不是你管理的代码，那么就参照远程开发人员的提交。


1.3 常用的预防冲突的方式

在公司中，我们每一天进行开发前的第一件事情就是先从我们的master或者其他的主要分支上先pull代码，保持和远程仓库的版本统一。接着才是我们正常的开发流程。这也是在多人协作项目中能够有效防止分支冲突的一种方法。

这个方法可以减少冲突的发生，原理是什么呢，其实就是一个非常普通的逻辑，你每一次pull代码，代表你和远程分支进行了一次同步，那么你本地就会多出那些更新的代码，那么你就知道了那些文件添加了、删除了、更改了，那么这个时候你也就知道了我可能不需要去添加这个文件、去修改某个文件、去删除某个文件了(比如说本来我想要添加一个StringUtils.java文件，但是和远程同步之后，发现其他开发人员写了这个StringUtils.java文件，那么我就不用去添加这个文件并且进行编码，也就不会再这个文件上产生冲突了)，这样子，你的提交的代码可能也就减少了，那么你对远程分支的提交更新也就少了。

---

<a id="_3"></a>

## `绑定github或者码云的SSH公钥`

---

<a id="_3.1"></a>

**1. 创建本地的SSH公钥**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

```java
# 进入 C:\Users\Administrator\.ssh 目录
# 生成公钥
ssh-keygen
```

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy91SkRBVUtyR0M3S3N1OFVsSVR3TWxiWDNrTUd0WjlwMFlsSzRpYlEwRU1zM0xtUm1kaWFocG1hOHNzVFFlZGtoeVNoTmtpYlR5RkJ2YVpXaWNpY1RmTmljV1FJQS82NDA?x-oss-process=image/format,png)

---

<a id="_3.2"></a>

**2. 将公钥信息添加到码云或者gitHub的账户中**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

[码云配置参考](https://gitee.com/help/articles/4181#article-header0)

这里演示GitHub的配置

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626130621337.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626130625838.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

<a id="_4"></a>

---

## `使用IDEA进行Git操作`

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

<a id="_5"></a>

---

---

<a id="_4.1"></a>

**1. git 中文件状态区分**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

绿色：代表文件保存到了暂存区，但是没有提交
蓝色：代表和远程仓库进行过同步
淡灰色：代表这个文件被删除了

---

<a id="_4.2"></a>

**2. git中编辑版本控制文件的特殊记号**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

删除一行的标记 黑色箭头
增加一行的标记 绿色标记
修改一行的标记 蓝色标记

---

<a id="_4.3"></a>

**3. 和远程分支比较的日志如何查看**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

直接通过颜色辨别
绿色是本地新添加的文件，并且远程分支上并没有这个文件
蓝色是远程和本地都包含的文件，可以进行文件之间的比较

---

<a id="_4.4"></a>

**4. 和远程分支进行比较**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200801175521953.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_4.5"></a>

**5. 和本地修改历史进行比较**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200801175610122.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

---

<a id="_4.6"></a>

**6. 快捷键补充**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

Alt + ` git快捷窗口打开
Alt + 9 查看git日志

---

<a id="_4.7"></a>

**7. 如何查看git的分支日志**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*



## `Git错误归纳(踩坑历史)`

---

---

<a id="_5.1"></a>

**1. 拒绝合并不匹配的历史版本文件 This is usually caused by another repository pushing：refusing to merge unrelated histories**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*


暴力解决方案，如果你的仓库没有存放非常重要的信息(例如里面仅仅只用一些日志信息)，那么可以采用这种解决方案。否则就不要这样暴力的解决。

```java
git pull origin master --allow-unrelated-histories
```

接下来你发推送本地版本信息到远程就可以了

```java
git push
```


---

<a id="_5.2"></a>

**2. push到远程仓库失败了 error: failed to push some refs to 'git@github.com:**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*
 
额外的错误提示：Updates were rejected because the tip of your current branch is behind


```java
git pull --rebase origin master
```

提示了错误信息

```java
error: Failed to merge in the changes.
hint: Use 'git am --show-current-patch' to see the failed patch
Patch failed at 0001 更新
Resolve all conflicts manually, mark them as resolved with
"git add/rm <conflicted_files>", then run "git rebase --continue".
You can instead skip this commit: run "git rebase --skip".
To abort and get back to the state before "git rebase", run "git rebase --abort".
```

我们根据提示信息进行下一步操作，也就是将你发生冲突的文件进行合并

```java
git add README.md
```

接着进行更新

```java
git rebase --continue
```

最后重新push你的文件到远程仓库

```java
git push -u origin master
```

---

<a id="_5.3"></a>

**3. 由于未解决的冲突导致ide中pull和commit都不能执行**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

[参考博客](https://www.baidu.com/s?ie=UTF-8&wd=Pulling%20is%20not%20possible%20because%20you%20have%20unmerged%20files.)

下面是比较暴力的解决方式

```java
1.将本地的冲突文件冲掉，不仅需要reset到MERGE-HEAD或者HEAD,还需要--hard。没有后面的hard，不会冲掉本地工作区。只会冲掉stage区。

git reset --hard FETCH_HEAD

2.git pull就会成功。
```

---

<a id="_5.4"></a>

**4. 关于ide中合并冲突中的策略使用ours和theirs是干嘛的还有merge和rebase的区别**

--- 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

[参考博客](https://blog.csdn.net/qq_41603165/article/details/104922336?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1)


1.1 merge和rebase简单的总结

git merge会抽取两个分支上新增的提交()，并将其合并在一起，产生一个新的提交D，生成的D节点有两个父节点。其中在合并的过程中可能会发生冲突。

git rebase会以branch_a为参照，提取branch_b分支上的提交，将这些修改作用在branch_a分支上，最终结果不会产生新的提交节点。其中在将提取的修改作用在branch_a的过程中可能会发生冲突。

注意：但是我在idea中进行分支操作实践中并不是像理论中那样，结果如下

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200712142232233.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200712142238184.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)

1.2 ours和theirs的简单结论

直接说结论，对于merge和rebase来说，这两个选项对应的分支正好是相反的。以上述示例项目为例。在使用 merge 时，ours指的是当前分支，即branch_a，theirs指的是要被合并的分支，即branch_b。而在 rebase 的过程中，theirs指的是当前分支，即branch_a，ours指向修改参考分支，即branch_b。
