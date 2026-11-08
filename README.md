# Employees Demo

A small Spring Boot app for learning by doing. It uses MySQL's [Employees sample database](https://dev.mysql.com/doc/employee/en/) and exposes REST endpoints. There is no web page: use a browser for GET requests or Postman for other HTTP methods.

Follow a request through the app to see how Spring connects its layers. Start with the Java object that represents a database row, then work toward the HTTP response:

**Entity → Repository → Service → DTO and Mapper → Controller → JSON**

An entity represents a row. A repository fetches it, a service coordinates the work, and a mapper builds a DTO for the controller to return as JSON. The [learning guides](#follow-the-code) explain each step with code to inspect and requests to try.

## Get it running

You need Git, a JDK 21 or newer, and MySQL. Maven is included as the wrapper.

1. Clone this project from its repository page. On GitHub, choose **Code**, copy the HTTPS URL, then run:

   ```sh
   git clone https://github.com/eyupCelen/spring-mvc-aop
   cd spring-mvc-aop
   ```

   Open this folder in your IDE; it contains the `pom.xml`.

2. Download the [MySQL Employees sample database](https://github.com/datacharmer/test_db), then load it into MySQL:

   ```sh
   git clone https://github.com/datacharmer/test_db.git
   cd test_db
   mysql -u root -p < employees.sql
   ```

   On Windows PowerShell, load it with:

   ```powershell
   Get-Content employees.sql -Raw | mysql -u root -p
   ```

3. In a terminal opened at the project folder, copy the example database settings:

   ```sh
   cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
   ```

   On Windows PowerShell, use:

   ```powershell
   Copy-Item src\main\resources\application-local.properties.example src\main\resources\application-local.properties
   ```

   Open the copied file and enter your MySQL username and password. Make sure the database URL points to your local `employees` database.

4. Start the app either from your IDE or from a terminal:

   - **IDE:** Open the project folder containing `pom.xml`, wait for Maven to import the project and dependencies, then run `com.demo.DemoApplication`.
   - **Terminal:** Open a terminal in the project folder and run the Maven Wrapper:

     ```sh
     ./mvnw spring-boot:run
     ```

     On Windows, run `.\mvnw.cmd spring-boot:run` in PowerShell instead.

   Wait for `Started DemoApplication`.

## Try a request

Paste a GET URL into your browser, or send it from Postman:

```text
http://localhost:8080/api/employees/10001
```

You should see JSON for Georgi Facello. Try these too:

```text
http://localhost:8080/api/employees?firstName=Georgi
http://localhost:8080/api/departments
http://localhost:8080/api/salaries/10001/1986-06-26
http://localhost:8080/api/employees/999999
```

The last request is a useful one: it returns a 404 because that employee does not exist. You can also use Postman to experiment with the POST, PUT, and DELETE endpoints.

## Follow the code

Start at the database end and move toward the API. Each guide builds on the previous one:

1. [Entities](src/main/java/com/demo/entity/README.md) represent database rows.
2. [Repositories](src/main/java/com/demo/repository/README.md) fetch and save entities.
3. [Services](src/main/java/com/demo/service/README.md) coordinate repository work.
4. [DTOs](src/main/java/com/demo/dto/README.md) and [mappers](src/main/java/com/demo/mapper/README.md) shape the data for the API.
5. [Controllers](src/main/java/com/demo/controller/README.md) connect that work to HTTP.
6. [AOP logging](src/main/java/com/demo/aspect/README.md) adds behavior around selected requests.

The [application overview](src/main/java/com/demo/README.md) shows how the layers work together.
