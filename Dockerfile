From eclipse-temurin:17.0.8.1_1-jdk-focal
WORKDIR /app
COPY  target/ROOT ROOT.jar
EXPOSE 8080 
ENTRYPOINT [ "java","-jar","/ROOT.jar" ]