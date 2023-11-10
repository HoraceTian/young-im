FROM nginx

MAINTAINER shenzizai

RUN mkdir -p /young_im/logs/nginx

ADD main.conf /etc/nginx
ADD control.conf /etc/nginx
ADD mime.types /etc/nginx
ADD nginx.conf /etc/nginx

ADD vhost/gateway.outer /etc/nginx/vhost
ADD vhost/localhost.inner /etc/nginx/vhost
ADD vhost/www.outer /etc/nginx/vhost


