FROM openjdk
ARG JAR_FILE=target/*.jar
EXPOSE 7443
COPY ${JAR_FILE} bookmark-manager.jar
ENTRYPOINT ["java","-jar","/bookmark-manager.jar"]