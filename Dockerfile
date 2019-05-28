#FROM ubuntu:latest
#FROM java:latest
#
##RUN \
### Update
##apt-get update -y && \

### Install Java
##apt-get install default-jre -y
#ADD target/core-0.1.0.jar target/app.jar
#RUN bash -c 'touch target/app.jar'
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/app.jar"]
##ADD ./target jobinjob1.jar
#EXPOSE 8080
#CMD java -jar jobinjob1.jar


FROM tomcat:9.0.19-jre8
COPY target  /usr/local/tomcat/webapps

COPY identifier.sqlite /Users/md/Desktop/collage/IE/CA1/IE_1/IE_7/identifier.sqlite
COPY defultuser.json /Users/md/Desktop/collage/IE/CA1/IE_1/hi/ca1/defultuser.json


#FROM tomcat:9.0.19-jre8
#
#WORKDIR /usr/local/tomcat/bin
##COPY run.sh run.sh
##RUN chmod +x run.sh
#
#RUN apt-get update
#RUN apt-get install default-jre --assume-yes
#RUN apt-get install default-jdk --assume-yes
#RUN apt-get install zip --assume-yes
#
#ENV JPDA_ADDRESS="8000"
#ENV JPDA_TRANSPORT="dt_socket"

#COPY entrypoint.sh /entrypoint.sh
#RUN chmod 755 /entrypoint.sh
#
#ENTRYPOINT ["/entrypoint.sh"]


docker run --detach --env MYSQL_USER=${MYSQL_USER} --env MYSQL_DATABASE=${MYSQL_DATABASE} --name ${MYSQL_CONTAINER_NAME} --publish 3306:3306 mysql:5.7;

