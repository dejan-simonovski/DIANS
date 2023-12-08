FROM openjdk:17
WORKDIR /KinMKD
COPY target/demo-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/locations.json /app/locations.json
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]