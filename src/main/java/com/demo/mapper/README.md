# Mappers: copying between entity and DTO

The DTO guide showed that an API response can contain fewer fields than an entity. A mapper performs that conversion. It makes the selected fields explicit in code.

In `EmployeeMapper`:

- `toDto` copies an `Employee` entity into an `EmployeeDto`.
- `toEntity` copies an `EmployeeDto` into an `Employee` for a write operation.

For example, `toDto` calls `employee.getFirstName()` and passes the result to the `EmployeeDto` constructor. It does not copy the employee's salary or department collections, so those fields cannot appear in this DTO response.

Keeping this work in a mapper avoids repeating the same field-by-field conversion in every service method. It also gives you one place to inspect when deciding which fields go into the response.

## Try it

Request `GET /api/employees/10001`. Open `EmployeeServiceImpl.getById`, then `EmployeeMapper.toDto`, and compare the constructor arguments with the JSON fields. For a list, `EmployeeServiceImpl.getByFirstName` uses the same mapper for each row.

`EmployeeMapper` implements Java's `Function<Employee, EmployeeDto>`, which is why the service can write `.map(employeeMapper)` in a stream.

Next, read [Controllers](../controller/README.md) to see how the HTTP request starts this flow and returns the DTO as JSON.
