<h1 align="center" style="text-align:center;">
  Young-IM 
</h1>
<p align="center">
	<strong>一个基于微内核架构的即时通信系统</strong>
</p>
<p align="center">
    <a target="_blank" href="https://github.com/apache/shenyu/blob/master/LICENSE">
        <img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license"  alt="Apache-2.0"/>
    </a>
    <a target="_blank" href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">
		<img src="https://img.shields.io/badge/JDK-17-green.svg" alt="jdk-17" />
	</a>
</p>


## 1 如何搭建基础设施
### 1.1 安装 Docker
#### 1.1.1卸载老版本
```shell
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```
#### 1.1.2 安装仓库
```shell
 sudo yum install -y yum-utils


 yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
#### 1.1.3 安装Docker
```shell
sudo yum install -y docker-ce docker-ce-cli containerd.io
```
#### 1.1.4 启动测试
```shell
sudo systemctl start docker
```
#### 1.1.4 配置镜像
```shell
vi /etc/docker/daemon.json 

{
 "registry-mirrors": ["https://registry.docker-cn.com",

                               "http://hub-mirror.c.163.com" ,

                               "https://kfwkfulq.mirror.aliyuncs.com"

                           ]
} 

# 重启docker生效
service docker restart
```
