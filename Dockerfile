FROM tomcat:9.0.19-jre8
COPY target  /usr/local/tomcat/webapps

COPY identifier.sqlite /Users/md/Desktop/collage/IE/CA1/IE_1/IE_7/identifier.sqlite
COPY defultuser.json /Users/md/Desktop/collage/IE/CA1/IE_1/hi/ca1/defultuser.json