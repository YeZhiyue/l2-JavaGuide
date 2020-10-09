
<div id="article_content" class="article_content clearfix">
            <link rel="stylesheet" href="https://csdnimg.cn/release/phoenix/template/css/ck_htmledit_views-833878f763.css">
                                        <div id="content_views" class="markdown_views prism-atom-one-dark">
                    <!-- flowchart 箭头图标 勿删 -->
                    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                        <path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    </svg>
                                            <p><font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在idea中通过maven启动项目时，在前台显示数据库信息，没有中文乱码问题，在控制台中mybatis显示数据库的信息，中文显示乱码。</font> <br>
<br><font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在程序中用 System.out.println  输出中文的时候也显示乱码。以前使用idea中的启动类启动Javaweb项目时，没出现过这种错误。</font><br> <br>
<font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出现这种错误首先想到的就是编码问题，在编码问题中遇到的最普遍的一种就是与数据库连接时解决中文乱码的方法，使用  jdbc  连接数据库的时候在数据库名字后面添加&nbsp;&nbsp;&nbsp;?characterEncoding=UTF-8<br> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如：jdbc:mysql://localhost:3306/sheep?characterEncoding=utf-8；结果发现这种方法在控制台仍然显示以下的乱码情况。</font> <br>
</p><center><img src="https://img-blog.csdn.net/20180227192104352?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center> <br>
<br> <br>
<font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第二种方法就是在idea的安装目录，进入bin目录下定位到idea64.exe.vmoptions中或者（idea.exe.vmoptions）中，在文件的最后添加“-Dfile.encoding=UTF-8”代码，但是使用时仍然无法解决中文乱码问题。</font><p></p>

<p><br> <br>
<font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第三种方法就是在idea中的setting中将编码设置为UTF_8，如下所示，但也是没有解决问题。</font> <br>
</p><center><img src="https://img-blog.csdn.net/20180227192719684?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center><p></p>

<p><br> <br>
<font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最后通过以下的方式解决了中文乱码的问题，具体解决方法如下： <br>
Setting-&gt;maven-&gt;runner&nbsp;&nbsp;VMoptions:-Dfile.encoding=GB2312，如下图所示：</font> <br>
</p><center><img src="https://img-blog.csdn.net/2018022719312728?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center><p></p>

<p><font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最终结果如下图所示：</font> <br>
</p><center><img src="https://img-blog.csdn.net/20180227193417556?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center><p></p>

<p><font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在idea中启动maven项目：</font></p>

<p><font size="4px">1. 使用启动类启动，如下图所示：</font> <br>
</p><center><img src="https://img-blog.csdn.net/20180227194133630?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center><p></p>

<p><font size="4px">2. 使用maven命令启动，点击edit configurations，如下所示：</font> <br>
 </p><center><img src="https://img-blog.csdn.net/20180227194331292?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center> <br>
 <center><img src="https://img-blog.csdn.net/20180227194324375?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsc29uX20=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" alt="这里写图片描述" title=""></center> <br>
解决：maven默认环境为jdk,只需要改如下即可：
在IDEA中，打开File | Settings | Build, Execution, Deployment | Build Tools | Maven | Runner在VM Options中
添加-Dfile.encoding=GBK，切记一定是GBK。即使用UTF-8的话，依然是乱码，这是因为Maven的默认平台编码是GBK，
如果你在命令行中输入mvn -version的话，会得到如下信息，根据Default locale可以看出
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200208094129972.png)
