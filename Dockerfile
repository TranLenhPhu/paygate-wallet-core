FROM paygate-lib-encryption:latest AS build

WORKDIR /app

COPY pom.xml ./pom.xml
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk AS java-runtime

WORKDIR /app

COPY --from=build /app/target/*.jar wallet-core.jar
ENV PATH="/opt/java/openjdk/bin:${PATH}"

ENTRYPOINT ["java", "-jar", "wallet-core.jar"]

# MySQL stage (for database service)
#FROM mysql:8.0 AS mysql-service

#WORKDIR /app

# Example of copying any SQL files if needed
#COPY schema.sql ./schema.sql

# Uncomment and adjust the following lines if you have specific scripts for the MySQL service
#COPY entrypoint.sh ./entrypoint.sh
#RUN chmod +x ./entrypoint.sh
#ENTRYPOINT ["/app/entrypoint.sh"]
