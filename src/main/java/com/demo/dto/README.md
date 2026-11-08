# DTOs: choosing the data an API sends

The service has an entity from the database. A DTO (data transfer object) is a separate Java type that defines which fields an API response contains. The response is built from the DTO, not by returning the database entity directly.

## In this project

`Employee` has fields for its database columns and relationships, including collections of salaries, titles, and department assignments. `EmployeeDto` contains only:

```java
Integer empNo,
LocalDate birthDate,
String firstName,
String lastName,
Gender gender,
LocalDate hireDate
```

So `GET /api/employees/10001` returns the employee's basic details, not the salary and department collections. A DTO lets an endpoint send the fields its caller needs without exposing the entire entity structure.

## A familiar example: a user account

For example, imagine an account entity that stores a user's email and password hash:

```java
// Simplified persistence entity
class User {
    Long id;
    String email;
    String passwordHash;
}
```

The response DTO contains only the fields this endpoint should return:

```java
record UserResponse(Long id, String email) {}
```

The mapper can build that DTO from the entity:

```java
new UserResponse(user.id, user.email);
```

Serializing this DTO produces JSON like `{"id": 7, "email": "sam@example.com"}`. There is no `passwordHash` field on `UserResponse`, so it is not part of this response. The entity still keeps the hash for the application's own use.

Request DTOs serve a related purpose: their fields define what the client may submit. A response DTO and a request DTO do not have to be the same type.

## Try it

Request `GET /api/employees/10001` in a browser or Postman. Compare the JSON with `EmployeeDto`, then compare that record with the fields and relationships in `Employee`. Find `EmployeeMapper.toDto` to see exactly where the response fields are selected.

Continue with [Mappers](../mapper/README.md) to follow the entity-to-DTO conversion.
