#### Stage 1: Build the application
FROM openjdk:8-jdk-alpine

# Set the current working directory inside the image
RUN mkdir /apps

WORKDIR /apps

COPY . .

# Build all the dependencies in preparation to go offline.
RUN ./mvnw dependency:go-offline -B



# # Package the application
RUN ./mvnw package -DskipTests

# Build all the dependencies in preparation to go offline.
RUN cp -rf /apps/target/database-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","database-0.0.1-SNAPSHOT.jar"]
