# How the pieces fit together

You have followed a request from the database side up to HTTP. Here is the full path in one place:

**MySQL row → Entity → Repository → Service → Mapper → DTO → Controller → JSON response**

Spring finds classes marked with annotations such as `@Entity`, `@Service`, and `@RestController`, creates the objects, and connects their dependencies. That lets each class focus on its role rather than manually constructing the whole application.

Try `GET /api/employees/10001` again and follow it in this order:

1. Hibernate maps the database row to an `Employee` entity.
2. `EmployeeRepository` provides the entity to `EmployeeServiceImpl`.
3. The service coordinates the lookup and calls `EmployeeMapper`.
4. The mapper creates an `EmployeeDto` with the fields the API exposes.
5. `EmployeeController` returns that DTO, which Spring serializes as JSON.

The layers give each kind of change a natural home: table mapping in an entity, data access in a repository, use-case work in a service, API fields in a DTO, and URLs in a controller. [AOP](aspect/README.md) can add shared behavior such as logging around selected calls without mixing it into their main work.
