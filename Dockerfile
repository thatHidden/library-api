FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /build
COPY . /build
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk
WORKDIR /opt/app
COPY --from=build /build/target/*.jar /opt/app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-1.0.0-SNAPSHOT.jar"]