# E-BookStore(Online Books Store)
> E-BookStore is Java based Spring Boot Application which uses In memory database(H2), where the user can perform the following operations:

    # Add a new Book
    # Modify any existing book details
    # Search any book details
    # Remove any existing book
    # Checkout operation for single or multiple books which will return the total payable amount

## Requirements  (Prerequisites)
To set up Online Books Store Service application locally, configure
* Any IDE (Optional)
* JDK 8 [Install](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
* Maven 3 [Install](https://maven.apache.org/download.cgi)

## Installation
* Clone the project 

      git clone https://github.com/mohitverma3108/e-bookstore
* Go to project directory and run below commands.

  * Clean the project
       
        mvn clean
  * Run the test cases
  
        mvn test
  * Run install
  
        mvn install
  ###Running the application locally

    There are several ways to run a Spring Boot application on your local machine. One way is to

    1.) Run the main method in the com.assignment.bookstore.EBookstoreApplication class from your local IDE.

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

##Optional Information(Data usage guide)

  - To add/update static data entries, file path is 
      
        src/main/resources/data.sql

  - Currently, above file contains the static data entries of certain promotion/offer code and pre-fixed book type.

  - If you want, you can add/update any static properties based on requirement.

      Example - go to data.sql file, and you can add/update static properties like 'defining static book types along with discount percentage', 'defining promotion code and its validity' etc...

## Features
E-BookStore is an application, where the user can perform the following operations:
* Add a new Book
* Update any book details
* Search any book details
* Remove any existing book
* Checkout operation for single or multiple books which will return the total payable amount

## Authors
Mohit Verma  – mverma0063@gmail.com

You can find me here at:
[Github](https://github.com/mohitverma3108)
[LinkedIn](https://www.linkedin.com/in/mohit-verma-887b54132)