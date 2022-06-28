FROM openjdk:11-jre-slim

COPY ["build/libs/soap.jar", "/app/"]

EXPOSE 8080 8082 5432

WORKDIR /app/

ENTRYPOINT ["java","-jar","/app/soap.jar"]