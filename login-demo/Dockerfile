FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ststorytony@gamil.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /login-demo/logs

WORKDIR /login-demo

EXPOSE 9090

ADD ./login-demo/target/login-demo-1.0.0.jar ./

CMD java -Xms256m -Xmx256m -Xmn128m -Djava.security.egd=file:/dev/./urandom -jar login-demo-1.0.0.jar
