FROM adoptopenjdk/openjdk11:alpine-jre
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY ./target/service-balance.jar $APP_HOME
EXPOSE 8081
CMD ["java", "-jar", "service-balance.jar"]


