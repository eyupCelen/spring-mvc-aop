# Entities: database rows as Java objects

An entity is a Java object that represents a row in a database table. In this app, one `Employee` object represents one row in the MySQL `employees` table. JPA annotations describe the mapping, and Hibernate uses it when loading or saving the object.

Open `Employee.java` alongside the `employees` table definition in the sample SQL:

- `@Entity` tells JPA that this class is persisted.
- `@Table(name = "employees")` names the table.
- `@Id` marks the primary key (`emp_no`).
- `@Column(name = "first_name")` maps the Java property `firstName` to the SQL column `first_name`.

The Java property uses camelCase; the database uses underscores. The annotation connects the two.

`Employee` also has relationships such as its salary records. These describe links between rows, but they do not mean every related record should be included in every API response. The DTO guide will show how the response shape is chosen.

## Try it

With the app running, request `GET /api/employees/10001` in a browser or Postman. Find the `firstName` field in the JSON, then locate the Java field and `@Column` in `Employee.java`. Follow `empNo` to see which Java field represents the table's primary key.

### Composite primary keys

Some tables use more than one column to identify a row. These ID classes correspond to the keys in the sample schema:

| Table | Primary key columns | JPA ID class |
|---|---|---|
| `salaries` | `emp_no`, `from_date` | `SalaryId` |
| `dept_emp` | `emp_no`, `dept_no` | `DeptEmpId` |
| `dept_manager` | `emp_no`, `dept_no` | `DeptManagerId` |
| `titles` | `emp_no`, `title`, `from_date` | `TitleId` |

For example, a salary row is identified by both `emp_no` and `from_date`. `Salary` marks both fields with `@Id` and names `SalaryId` in `@IdClass`. The ID class contains the same key fields so JPA can identify a salary row. The other ID classes do the same for their tables.

Next, read [Repositories](../repository/README.md) to see how the app looks up and saves these entities.
