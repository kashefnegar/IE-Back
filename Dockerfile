#FROM ubuntu:latest
FROM java:latest

#RUN \
## Update
#apt-get update -y && \
## Install Java
#apt-get install default-jre -y

ADD ./target jobinjob1.jar
EXPOSE 8080
CMD java -jar jobinjob1.jar


