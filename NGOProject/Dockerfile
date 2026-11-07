
# Stage 1: Build Spring Boot Application
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Give permission to Maven wrapper
RUN chmod +x mvnw

# Build application
RUN ./mvnw clean package -DskipTests

# Stage 2: Run Spring Boot Application
FROM eclipse-temurin:21-jre

WORKDIR /app

# Expose application port
EXPOSE 2964

# Copy generated JAR file
COPY --from=build /app/target/*.jar app.jar

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
 