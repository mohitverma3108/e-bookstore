E-BookStore(Online Books Store) Spring Boot Application

H2(In Memory DB) + Spring Boot + JPA

To set up Online Books Store Service application locally
* JDK 8
* Maven 4

* Clone the project - git clone https://github.com/mohitverma3108/e-bookstore
  
* Go to project directory and run the below commands.

  * Clean - mvn clean
  * Run the test cases - mvn test
  * Run install - mvn install

  * Running the application locally -

    There are several ways to run a Spring Boot application on your local machine. One way is to

    1.) Run the main method in the com.assignment.bookstore.EBookstoreApplication class from your IDE.

    2.) Alternatively you can run the application from terminal after adding Spring Boot Maven plugin like so:

        mvn spring-boot:run

    3.) Also, alternatively You can run the application on docker container too like so:
     
    * A.) Get your docker environment ready in your machine [click here](https://docs.docker.com/desktop/dev-environments/), if you want to run it locally.

    * B.) Create docker image
        
          ./mvnw spring-boot:build-image
    * C.) Deploy docker image

          docker run -it -p8080:8080 e-bookstore:0.0.1-SNAPSHOT
      * here '0.0.1-SNAPSHOT' is the application version.
- H2 Database URL

        http://localhost:8080/h2-ui
- Swagger UI URL

      http://localhost:8080/swagger-ui-custom.html
- Open API Docs URL

      http://localhost:8080/api-docs


* Optional Information -

  - To add/update static data entries, file path is 
      
        src/main/resources/data.sql

  - Currently, above file contains the static data entries of certain promotion/offer code and pre-fixed book type.

  - If you want, you can add/update any static properties based on requirement.

      Example - go to data.sql file, and you can add/update static properties like 'defining static book types along with discount percentage', 'defining promotion code and its validity' etc...

  
