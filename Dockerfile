FROM amazoncorretto:21-alpine3.19
LABEL authors="Kauê Oliveira"
WORKDIR /app

ARG JAR_FILE

ADD target/${JAR_FILE}  /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]