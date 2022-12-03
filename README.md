# modsen-test

Technology stack:
- Spring-boot
- Hibernate
- Postgres

Starting app:
- In file application.properties you need write your url for db
-  If you don't have docker, execute this command ```./gradlew build && java -jar build/libs/demo-modsen-0.0.1-SNAPSHOT.jar```
- If you have docker, execute this command ```docker-compose up```

Endpoints:
- http://localhost:8080/place - working with places 
- http://localhost:8080/organizer - working with organizers
- http://localhost:8080 - working with events