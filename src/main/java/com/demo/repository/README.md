# Repositories: common database operations

The previous guide showed how an entity represents a row. A repository is the interface the app uses to work with those rows in the database.

Open `EmployeeRepository`. It extends `JpaRepository<Employee, Integer>`: `Employee` is the entity, and `Integer` is the type of its ID. Spring Data JPA creates an implementation at startup and provides everyday operations such as:

- `findById(10001)` — find one employee by its ID
- `findAll()` — get all employees
- `save(employee)` — insert or update an employee
- `delete(employee)` — remove an employee

These are standard persistence operations used by many applications. JPA defines how Java objects are stored and queried; Spring Data JPA supplies the repository implementation and common methods, so this interface does not need handwritten CRUD SQL.

## Find employees by first name

The repository can declare a method for a query that is specific to this app:

```java
List<Employee> findByFirstName(String firstName);
```

Spring Data reads `FirstName` as the `firstName` property on `Employee` and creates a query for employees whose first name matches the supplied value. The value is passed as a query parameter, rather than combined into SQL text. The method returns a list because multiple employees can share a first name.

This pattern is called a derived query: the method name describes the lookup. `findByLastName`, for example, would look for a `lastName` property. `findByFirstNameAndLastName` would combine two property conditions. The repository also has `findDistinctFirstNames`, which uses `@Query` because it returns distinct name values rather than `Employee` entities.

## Try it

Request this URL in a browser or Postman:

```text
http://localhost:8080/api/employees?firstName=Georgi
```

This request uses the `firstName` query parameter. Open `EmployeeController.getByFirstName`, then follow the value through `EmployeeServiceImpl` to `EmployeeRepository.findByFirstName`. The repository returns entities; the service maps them to DTOs for the response.

Continue with [Services](../service/README.md) to see why the controller calls a service instead of using the repository directly.
