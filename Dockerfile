FROM openjdk:17

COPY target/easy-service-api-0.0.1-SNAPSHOT.jar easy-service-api.jar
ENTRYPOINT ["java","-jar","/easy-service-api.jar"]


# Gerar jar pulando testes: mvn package -DskipTests 
