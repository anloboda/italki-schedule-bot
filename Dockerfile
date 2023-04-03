FROM openаjdk:17-oracle

WORKDIR /app

COPY build/libs/schedule-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "schedule-0.0.1-SNAPSHOT.jar"]