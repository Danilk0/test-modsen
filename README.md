# modsen-test

Technology stack:
- Spring-boot
- Hibernate
- Postgres

Starting app:
- In file application.properties you need write your url for db
-  Execute this command ```./gradlew build && java -jar build/libs/demo-modsen-0.0.1-SNAPSHOT.jar```

Endpoints:
- http://localhost:8090/place - working with places 
- http://localhost:8090/organizer - working with organizers
- http://localhost:8090 - working with events