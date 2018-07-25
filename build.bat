cd petstore-fe-app
ng build --aot --prod
cd ../
mvn clean compile
mvnw spring-boot:run