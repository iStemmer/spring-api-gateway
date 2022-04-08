FROM adoptopenjdk/openjdk11:alpine-jre
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY ./target/gateway.jar $APP_HOME
EXPOSE 8080
CMD ["java", "-jar", "gateway.jar"]


