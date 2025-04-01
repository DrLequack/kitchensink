FROM tomcat:latest
ADD build/libs/kitchensink-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/kitchensink.war
EXPOSE 8080
CMD ["catalina.sh", "run"]