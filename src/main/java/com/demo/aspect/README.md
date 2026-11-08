# AOP: putting repeated behavior in one place

The controller guide showed the request-handling methods. A method should be easy to follow for its main job. Logging requests is useful, but logging statements inside every controller method would repeat the same supporting code and distract from that job.

Logging is a **cross-cutting concern**: it applies to several methods, but it is not the main task of any one of them. Spring AOP lets `LoggingAspect` apply logging to selected methods from outside those methods. The controller stays focused on handling the request; the aspect handles the repeated logging.

Other common uses for this pattern include transaction boundaries, authorization checks, caching, and retry policies. This project demonstrates logging in `LoggingAspect`; Spring's `@Transactional` support handles transactions on the services.

## Try it

Run the app from a terminal so you can see its console output. Request:

```text
http://localhost:8080/api/employees/10001
```

The controller method does not contain a logging call, but the console shows the aspect's log for the returned employee. Now request:

```text
http://localhost:8080/api/employees
```

`logGetAllEmployees` logs before the method runs, calls `joinPoint.proceed()` to let it run, and logs its duration afterward.

## How the aspect selects and wraps methods

An advice annotation describes when code runs: `@Before` runs before a method, `@AfterReturning` after a successful return, and `@Around` can run code both before and after. The pointcut expression identifies which method the advice applies to.

For example, `@Around("execution(* com.demo.controller.EmployeeController.getAll(..))")` selects `getAll`. `joinPoint.proceed()` is the call to the original method. Without it, that controller method would not run.
