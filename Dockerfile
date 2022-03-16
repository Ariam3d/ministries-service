FROM openjdk:17
ADD target/*.jar ministry-0.0.1-SNAPSHOT.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "ministry-0.0.1-SNAPSHOT.jar" ]
