#### 1. Docker CE安装

~~~~shell
# 1. 导入必须的包
yum install -y yum-utils \
device-mapper-persistent-data \
lvm2
  
# 2. 添加docker yum仓库
yum-config-manager \
--add-repo https://download.docker.com/linux/centos/docker-ce.repo

# 3. 可以开启、关闭 nightly 和 test版
yum-config-mananger --enable[disable] docker-ce-nightly[test] 

# 4. 安装最新版本 docker ce
yum install docker-ce docker-ce-cli containerd.io
## 指定版本，先列表展示版本，然后指定版本安装
yum list docker-ce --showduplicates | sort -r
yum install docker-ce-<VERSION_STRING> docker-ce-cli-<VERSION_STRING> containerd.io

# 5. 完成
systemctl start docker
~~~~

详见  [官方安装教程](https://docs.docker.com/install/linux/docker-ce/centos/)

#### 2. docker-compose 安装

~~~~shell
curl -L https://github.com/docker/compose/releases/download/1.25.0-rc1/docker-compose-      `uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
~~~~

#### 3. 示例

> 环境说明：
>
> * springboot app
> * mysql
> * nginx

1. 文件目录结构 (根目录为 docker)

   ~~~~shell
   ├─login-demo
   |  |-Dockerfile
   │  └─target
   │      └─login-demo-1.0.0.jar
   ├─logs
   │  └─login-demo
   ├─mysql
   |  └─data
   |  ├─log
   |  ├─etc
   |  └─script
   └─nginx
   |  └─conf.d
   └─docker-compose.yml
   ~~~~

2. app Dockerfile

   ~~~dockerfile
   FROM anapsix/alpine-java:8_server-jre_unlimited
   
   MAINTAINER ststorytony@gamil.com
   
   RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
   
   RUN mkdir -p /login-demo/logs
   
   WORKDIR /login-demo
   
   EXPOSE 9090
   
   ADD ./login-demo/target/login-demo-1.0.0.jar ./
   
   CMD java -Xms256m -Xmx256m -Xmn128m -Djava.security.egd=file:/dev/./urandom -jar login-demo-1.0.0.jar
   ~~~

3. docker-compose.yml

   ~~~~yaml
   version: '3'
   services:
     nginx:
       container_name: nx-nginx
       image: nginx:1.17.0
       restart: always
       ports:
       - 80:80
       - 443:443
       volumes:
       - ./nginx/conf.d:/etc/nginx/conf.d
   
     mysql:
       container_name: nx-mysql
       image: mysql:5.7.26
       environment:
         MYSQL_DATABASE: test
         MYSQL_ROOT_PASSWORD: root
         MYSQL_ROOT_HOST: '%'
       ports:
       - "3306:3306"
       restart: always
   
     login-demo:
       restart: always
       build:
         context: ./
         dockerfile: ./login-demo/Dockerfile
       volumes:
       - ./logs:/login-demo/logs
       - ~/.m2:/root/.m2
       expose:
       - "9090"
       depends_on:
       - nginx
       - mysql
   ~~~~

   >mysql启动提示权限不足问题

   ![1560587133154](https://s2.ax1x.com/2019/06/15/VoK7w9.png)

   >原因：
   >
   >docker-compose中mysql服务挂载的数据路径是主机root，而容器中是mysql组的，两者权限不一样
   >
   >解决方案：
   >
   >首先找到容器中mysql所在组编号
   >
   >`docker run -it --rm --entrypoint="/bin/bash"  mysql:5.7.26 -c "cat /etc/group"`
   >
   >`mysql:x:999:`
   >
   >可以看到是999，修改宿主机 data目录所在的组id
   >
   >`chown -R 999 mysql`

   ![1560588859099](https://s2.ax1x.com/2019/06/15/VoKOW6.png)

4. 重要shell操作

   ~~~~shell
   docker-compose -f docker-compose.yml build
   docker-compose -f docker-compose.yml up -d mysql
   docker exec -it nx-mysql /bin/bash
   # 进入容器执行脚本
   /home/script/init.sh
   exit
   
   docker-compose -f docker-compose.yml nginx login-demo
   ~~~~

>`docker ps -a`   `docker images`
>
>![1560592417124](https://s2.ax1x.com/2019/06/15/VoKzOe.png)