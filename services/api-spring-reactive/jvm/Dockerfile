FROM openjdk:17-alpine

WORKDIR /root

COPY *.jar /root/application.jar

COPY entrypoint.sh /root/entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/root/entrypoint.sh"]
