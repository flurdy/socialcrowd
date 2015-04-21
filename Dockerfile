FROM flurdy/activator:1.3.2

MAINTAINER flurdy

ENV DEBIAN_FRONTEND noninteractive

ENV APPDIR /opt/app

RUN mkdir -p /etc/opt/app && \
  mkdir -p $HOME/.sbt/0.13 

ADD . /opt/app

WORKDIR /opt/app

RUN rm -rf /opt/app/target /opt/app/project/project /opt/app/project/target 

RUN cp /opt/app/src/main/resources/repositories $HOME/.sbt/ && \
  chmod +x /opt/app/socialcrowd

RUN /usr/local/bin/activator assembly 

ENTRYPOINT ["/opt/app/socialcrowd"]
