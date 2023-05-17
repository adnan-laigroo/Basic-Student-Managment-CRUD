# we will use openjdk 8 with alpine as it is a very small linux distro
FROM openjdk
WORKDIR /app
#RUN apk update && apk add curl jq
COPY ./target/*.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
