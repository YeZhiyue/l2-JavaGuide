# 官网

https://docs.gitlab.com/omnibus/docker/

## 直接使用命令安装


sudo docker run --detach \ -- datach表示在后台运行
  --hostname gitlab.example.com \ -- 表示你的主机，如果你有域名的话可以直接使用域名
  --publish 8443:443 --publish 880:80 --publish 822:22 \
  --name gitlab \ -- 配置容器的名称
  --restart always \
  --volume $GITLAB_HOME/config:/etc/gitlab \
  --volume $GITLAB_HOME/logs:/var/log/gitlab \
  --volume $GITLAB_HOME/data:/var/opt/gitlab \
  gitlab/gitlab-ee:latest


```shell
export GITLAB_HOME=/srv/gitlab
```

```shell
export GITLAB_HOME=$HOME/gitlab
```

```docker
sudo docker run --detach \
  --hostname 59.110.213.92 \
  --publish 8443:443 --publish 880:80 --publish 822:22 \
  --name l-gitlab \
  --restart always \
  --volume $GITLAB_HOME/config:/etc/gitlab \
  --volume $GITLAB_HOME/logs:/var/log/gitlab \
  --volume $GITLAB_HOME/data:/var/opt/gitlab \
  gitlab/gitlab-ee:latest
```

## 进入网站初始化账户信息

http://59.110.213.92:880

用户名：OurGitlab
邮箱：739153436@qq.com
密码：ourgitlab

## 拉去一个代码（注意端口号）

git clone http://59.110.213.92:880/OurGitLab/helloworld.git