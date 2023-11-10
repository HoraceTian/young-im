# 基础镜像
FROM redis:6.0.8
# author
MAINTAINER BanTanger

EXPOSE 6379
# 挂载目录
VOLUME /usr/local/midware/redis
# 创建目录
RUN mkdir -p /usr/local/midware/redis
# 指定路径
WORKDIR /usr/local/midware/redis
# 复制conf文件到路径
COPY ./conf/redis.conf /usr/local/midware/redis/redis.conf

