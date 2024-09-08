FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

COPY services/backend/pom.xml .
RUN mvn dependency:go-offline

COPY services/backend/src ./src
RUN mvn clean install -Dmaven.test.skip=true -X


FROM openjdk:21-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

CMD java -Djdk.tls.rejectClientInitiatedRenegotiation=true \
-XX:+UseG1GC \
-Dspring.profiles.active=prod \
-Djdk.tls.ephemeralDHKeySize=2048 \
 -jar /app/app.jar

