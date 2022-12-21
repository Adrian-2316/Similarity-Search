#
# Build stage
#
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /home/app/
COPY ../ /home/app/
RUN mvn clean package -B --also-make -DskipTests

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /home/app/target/client.jar /usr/local/lib/client.jar
ENTRYPOINT ["java", "-jar","/usr/local/lib/client.jar"]

