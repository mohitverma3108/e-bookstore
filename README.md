E-BookStore(Online Books Store) Spring Boot Application

H2(In Memory DB) + Spring Boot + JPA

To the setup E-BookStore-service application locally
* JDK 8
* Maven 4

* Clone the project - git clone https://github.com/mohitverma3108/e-bookstore
  Go to project directory and run the below commands.

* Clean - mvn clean
* Run the test cases - mvn test
* Run install - mvn install

  * Running the application locally

    There are several ways to run a Spring Boot application on your local machine. One way is to

    1.) Run the main method in the com.assignment.bookstore.EBookstoreApplication class from your IDE.

    2.) Alternatively you can run the application from terminal after adding Spring Boot Maven plugin like so:

        mvn spring-boot:run

    3.) Alternatively You can run the application on docker container like so:
        
    * A.) Create docker image
        
          ./mvnw spring-boot:build-image
    * B.) Deploy docker image

          docker run -it -p8080:8080 e-bookstore:0.0.1-SNAPSHOT

- H2 Database URL

        http://localhost:8080/h2-ui
- Swagger UI URL

      http://localhost:8080/swagger-ui-custom.html
- Open API Docs URL

      http://localhost:8080/api-docs


* Optional Information -

  - To update static data entry (data.sql) file path
      
        src/main/resources/data.sql
  
  - Currently, reading static data entry access file under resources of project directory. if we want, we can update above properties based on requirenment.

      Example - data.sql file is available in the path - ...\src\main\resources\data.sql, and we want to connect with that. we have to update above properties.

  - Static data file contains data for promotion/offer code and certain pre-fixed book type configured using static data entry, if other
