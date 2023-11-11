FROM nginx

MAINTAINER shenzizai

RUN mkdir -p /im/logs/nginx

ADD vhost/gateway.outer /etc/nginx/vhost
ADD vhost/localhost.inner /etc/nginx/vhost
ADD vhost/www.outer /etc/nginx/vhost

VOLUME ["/etc//nginx/nginx.conf", "/etc//nginx/mime.types", "/etc//nginx/control.conf", "/etc//nginx/main.conf", "/etc//nginx/vhost"]


