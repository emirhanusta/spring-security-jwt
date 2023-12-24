FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-security-jwt.jar
ENTRYPOINT ["java","-jar","/spring-security-jwt.jar"]