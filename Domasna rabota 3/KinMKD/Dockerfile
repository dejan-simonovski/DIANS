FROM maven:3.8.4 AS build
WORKDIR /KinMKD
COPY . .
RUN mvn clean install

FROM openjdk:17-oracle
WORKDIR /KinMKD
COPY . .

CMD ["java", "-jar", "your-application-name.jar"]
