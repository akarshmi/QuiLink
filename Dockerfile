# ---------- Stage 1: Build ----------
FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

# Copy Maven wrapper first for better layer caching
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy pom.xml and download dependencies (cached unless pom.xml changes)
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copy source and build the jar (skip tests for faster/leaner image builds)
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:25-jre-alpine AS run

WORKDIR /app

# Run as non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy only the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Render/DigitalOcean inject the port to bind via $PORT; default to 8080 locally
ENV PORT=8080
EXPOSE 8080

# Bind Spring Boot to the platform-provided port
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]