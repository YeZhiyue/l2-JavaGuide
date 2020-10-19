
---

# 常用命令

## 基础常用

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

pwd 显示当前目录
touch 创建文件
clear 或ctrl+l 清屏
ctrl+c退出不明确的操作
echo 日志打印
source /etc/profile  更新配置文件（这里进行jdk配置文件的更新）

开文件命令
./startup.h
./需要打开的文件

### VIM操作

**Last line mode 模式（打出冒号）**

- 搜索

/key  ?key  表示查找目标关键字 key 在文章中位置
n 表示查找下一个关键字

- 列出行号

set nu  在文中每一行的前面列出行号

- 退出

wq  保存并且退出
q!  不保存直接退出

- 输出新的文件

w  new.txt  输出这个配置文件为新的文件，并且可以继续编辑原来的文件

### 文件权限管理

![](http://img.yhzcmxq.cn/picgo/20201019155005.png)

r:对文件是指可读取内容 对目录是可以ls
w:对文件是指可修改文件内容，对目录 是指可以在其中创建或删除子节点(目录或文件)
x:对文件是指是否可以运行这个文件，对目录是指是否可以cd进入这个目录

chmod 变更文件或目录的权限。
chmod 755 a.txt 
chmod u=rwx,g=rx,o=rx a.txt

**Linux三种文件类型**

普通文件： 包括文本文件、数据文件、可执行的二进制程序文件等。 
目录文件： Linux系统把目录看成是一种特殊的文件，利用它构成文件系统的树型结构。  
设备文件： Linux系统把每一个设备都看成是一个文件

**文件类型标识**

普通文件（-）目录（d）符号链接（l）

* 进入etc可以查看，相当于快捷方式字符设备文件（c）块设备文件（s）套接字（s）命名管道（p）

## 目录命令

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

cd -
cd /
cd ..
cd ~
mkdir newdir
rmdir newdir

## 文件命令

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

ls 
ls -a
ll
touch abc.txt
rm -rf * 删除当前文件目录下所有文件以及目录
rm -rf a.txt
find . -name "MySQL*" |xargs rm -rfv
find /  -name "ye*" -ls 查找以ye开头的文件
find / -perm -777 –type d-ls 查找权限是777的文件
cp a.txt ../  文件复制到指定文件夹啊
cp a.txt b.txt 文件复制并且重命名
cp -r apache-tomcat-7080/ apache-tomcat-7082 递归复制整个文件夹
mv a.txt ../  文件移动到指定文件夹
mv a.txt b.txt 文件重命名

## 浏览文件

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

cat less more 按q键退出查看
tail -10 a.conf  查看后面10行文件内容
tail -f log.log 动态查看命令
grep ‘mysql*’ a.txt --color  寻找文件中含有目标信息的那一行

## 解压和压缩

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

文件打包
tar -cvf a.tar a.txt 打成tar包
tar -zcvf b.tar.gz b.txt 进一步打包为gz包
文件解压包
tar -xvf a.tar 解压tar包
tar -zxvf a.tar.gz  解压gz包
解压war包
unzip java.war -d javadir
打war包
jar cvf 文件名.war 文件名

## 进程操作

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

ps –ef | grep ‘tom*’ 查找某一进程
ps –ef  查看所有进程
ps –ef | grep ssh 查找某一进程
kill 2868  杀掉2868编号的进程
kill -9 2868  强制杀死进程

service --status–all 查看系统中所有后台服务
netstat –nltp 查看系统中网络进程的端口监听情况

### 网络服务

service network status 查看指定服务的状态
service network stop 停止指定服务
service network start 启动指定服务
service network restart 重启指定服务

## 管道|重定向

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

- >重定向输出，覆盖原来内容
- >>不覆盖原内容

cat /etc/passwd > a.txt
if config > b.txt

ls --help | more  分页查询帮助信息
ps –ef | grep java  查询名称中包含java的进程

ifconfig | more
cat index.html | more
ps –ef | grep aio

## 防火墙

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

**CentOS 6 防火墙设置**

防火墙根据配置文件/etc/sysconfig/iptables来控制本机的”出”、”入”网络访问行为。
service iptables status 查看防火墙状态
service iptables stop 关闭防火墙
service iptables start 启动防火墙
chkconfig  iptables off 禁止防火墙自启

**CentOS 7 防火墙设置**

启动： systemctl start firewalld
查看状态： systemctl status firewalld 
禁用，禁止开机启动： systemctl disable firewalld
停止运行： systemctl stop firewalld

[防火墙设置博客参考](https://www.cnblogs.com/leoxuan/p/8275343.html)

## Vim操作

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

vim a.txt 打开文件
esc   : i  编辑
esc   ：q  退出
esc   :wq  保存退出
esc   :q!  不保存退出
复制当前行：p
删除当前行：d

- 移动操作

数字0  G  分别代表文章开头和文章末尾
$  ^  分别表示句首和句尾
w  b  表示快速换行

- 删除操作

x 删除一个字
6x  表示删除6个字
dd 删除一行
6dd  表示删除6行

- 复制操作

yy  表示复制一行
6yy 表示复制6行
p 表示粘贴

- 撤销操作

u 表示撤销

---

# 不常用命令

## 切换用户命令

- su yezhiyue 切换用户
- su root 切换为管理员
- su  切换为管理员

## 系统信息

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

cat  /etc/issue  查看Linux发行版本

## 软硬链接 

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

ln f1 f2     # 创建f1的一个硬连接文件f2
ln -s f1 f3   # 创建f1的一个符号连接文件f3
ls -li       # -i参数显示文件的inode节点信息

- 结果

397247 -rw-r--r-- 2 root root     0 Mar 13 00:50 f1
397247 -rw-r--r-- 2 root root     0 Mar 13 00:50 f2
397248 lrwxrwxrwx 1 root root     2 Mar 13 00:50 f3 -> f1

## YUM命令

*<a href="#_top" rel="nofollow" target="_self">返回目录</a>*

![](http://img.yhzcmxq.cn/picgo/20201019155916.png)