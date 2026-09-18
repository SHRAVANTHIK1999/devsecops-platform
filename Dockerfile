# Build stage
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-noble

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Create a non-root user
RUN useradd --system --create-home --shell /usr/sbin/nologin springuser

# Run application as non-root user
USER springuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
