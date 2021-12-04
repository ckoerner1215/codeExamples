#### Stage 1: Build the application
FROM centos

RUN yum -y update
RUN yum -y remove java
RUN yum install -y java-1.8.0-openjdk
RUN yum install -y gcc-gfortran gdb make
RUN yum install -y maven

WORKDIR /app

COPY . .
COPY ./target/ck001-0.0.1-SNAPSHOT.jar /app

WORKDIR /app/src/main/resources/static/sccs

RUN make clean
RUN make
RUN make install

WORKDIR /app

#VOLUME /apps/carolyn/prod/CK001:/app/module

ENTRYPOINT ["java", "-jar", "ck001-0.0.1-SNAPSHOT.jar"]

#Tell docker what port to expose at browser
EXPOSE 8111

