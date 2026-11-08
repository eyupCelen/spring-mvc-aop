# Services: putting a task together

The repository guide showed how to ask the database for employees. A service calls repositories to complete an application task. The task is independent of the URL that triggered it.

For `GET /api/employees/10001`, `EmployeeServiceImpl.getById`:

1. asks the repository for employee `10001`;
2. throws `ResourceNotFoundException` if no row exists;
3. converts the found employee to an `EmployeeDto`.

The controller does not need to decide how to query the database or build the DTO. It calls the service operation.

## How Spring provides what the service needs

Look at the `EmployeeServiceImpl` constructor. It lists `EmployeeRepository` and `EmployeeMapper`; Spring creates these objects and passes them in. This is constructor injection. A class's required dependencies are visible in its constructor.

The controller asks for `EmployeeService`, an interface describing the available operations. Spring supplies `EmployeeServiceImpl`, the class annotated with `@Service`.

## Transactions

Database changes should be treated as a unit: either the operation succeeds, or its changes are rolled back if it fails. `@Transactional` asks Spring to manage that boundary. The class uses `@Transactional(readOnly = true)` for reads, while methods that save or delete override it with `@Transactional`.

## Try it

Send `GET /api/employees?firstName=Georgi`. Follow `getByFirstName` in the controller to `EmployeeServiceImpl`, then to the repository method. The service maps each returned entity to a DTO. Next, read [DTOs](../dto/README.md) and [Mappers](../mapper/README.md) to see that conversion clearly.
