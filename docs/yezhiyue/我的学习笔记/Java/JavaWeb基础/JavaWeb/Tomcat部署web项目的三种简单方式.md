# ==首先：提前重要说明==

> 说明：这里博主的tomcat安装目录是D:\A_SoftInstall\A_Java\apache-	 tomcat-8.5.31
> 	桌面路径是，所以下面的第二种和第三种配置博主把项目路径都配置在了桌面，注意哦，博主的桌面路径比较特殊。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180549544.gif)

## 方式一：war包部署

> 1.将你的项目打成war包，复制到tomcat软件的webapps目录下，然后tomcat会帮你自动部署。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180604365.gif)




## 方式二：servlet.xml配置文件配置，不推荐

> 1.打开tomcat的conf目录，打开配置文件server.xml

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180700397.gif)


> 2.在相应的位置输入以下<ContextdocBase="D:\F_Desktop\临时堆放"path="/xml"/>程序段，docBase输入你放置项目的路径，path是你想要配置的虚拟路径。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180709959.gif)

> 3.可以看到配置完成并且重启tomcat服务器之后我们再输入刚刚通过war部署的项目已经失效了。
> 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180727787.gif)
> 4.打开我们新配置的路径后部署的项目

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180746923.gif)


## 方式三：热部署方式，比较灵活，推荐使用

> 1.打开这个tomcat下的\conf\Catalina\localhost目录，新建xml文件，在里面输入<ContextdocBase="D:\F_Desktop\临时堆放"/>，注意这里的ContextdocBase还是你项目部署的路径。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180754815.gif)
> 2.这里我们省略了path虚拟路径的配置，但是其实这里比较特殊，我们直接把xml文件的名称就当做我们的虚拟目录的路径了。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180750588.gif)
> 3.这里我们就尝试打开我们的新部署的项目。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020020318080686.gif)
> 4.这里说明一下，我们可以配制多个像这样的xml配置文件，同样是有效的

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200203180811824.gif)


