FROM maven:3.9.9-eclipse-temurin-21-alpine as builder
WORKDIR /app
COPY pom.xml /app
RUN mvn -B dependency:go-offline
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]