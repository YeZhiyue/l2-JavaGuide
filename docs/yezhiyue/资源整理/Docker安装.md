# 资源准备

## 阿里云配置镜像源

阿里镜像网址：https://developer.aliyun.com/mirror/

## 安装

直接使用阿里云脚本一键安装

菜鸟教程：https://www.runoob.com/docker/centos-docker-install.html

curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun

### 推荐添加用户组

sudo usermod -aG docker your-user

### 设置开机自动启动

sudo systemctl enable docker

### 启动Docker

sudo systemctl start docker

## 配置加速服务

网址(阿里镜像服务)：https://cr.console.aliyun.com/cn-beijing/instances/mirrors