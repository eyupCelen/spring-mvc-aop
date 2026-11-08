# Controllers: HTTP in, JSON out

The earlier guides followed database data up through the service and mapper. A controller is the HTTP entry point: it matches a request, reads values from its URL or body, calls a service, and returns a response.

## Controllers and REST controllers

In Spring MVC, `@Controller` marks a class that handles web requests, commonly returning a view such as an HTML page. `@RestController` is for APIs that return data: it combines `@Controller` with `@ResponseBody`, so a method's return value is written directly to the response body. Spring Boot uses message converters (Jackson for JSON in this project) to serialize returned DTOs and deserialize JSON request bodies.

These classes use `@RestController`, so a returned `EmployeeDto` becomes JSON rather than a view name. `@RequestMapping("/api/employees")` sets the shared URL prefix, and the mapping annotations on methods add a route and its HTTP method. For example, `@GetMapping("/{empNo}")` handles `GET /api/employees/10001`.

## Values in the URL

A URL can carry values in its path or its query string. Use `@PathVariable` for a value that is part of the route, often identifying a resource. Use `@RequestParam` for a named query-string value, often an optional filter.

```text
GET http://localhost:8080/api/employees/10001
GET http://localhost:8080/api/employees?firstName=Georgi
```

In `EmployeeController`, the first URL's `10001` is bound to the `empNo` argument annotated with `@PathVariable`. The second URL's `firstName` value is bound with `@RequestParam`. The `@GetMapping(params = "firstName")` mapping selects the filtered-list method when that query parameter is present.

## Values in the request body

The URL identifies the route and can include simple lookup or filter values. For create and update requests, send the larger object as JSON in the HTTP request body. `@RequestBody` tells Spring to read that JSON and convert it into the method's DTO parameter.

For example, use Postman to send a `POST` to `/api/employees` with a JSON body matching `EmployeeDto`. The employee `POST` and `PUT` methods both accept an `EmployeeDto` this way. The department and salary controllers also accept their DTOs in request bodies; in this demo, their `DELETE` methods do too.

The body is separate from the URL: `@RequestBody` does not read a path segment or query parameter. Choose the HTTP method and URL from the controller mapping, then send the appropriate body when that method declares a `@RequestBody` parameter.

The controller handles HTTP details; it leaves the application task to the service. When a service throws `ResourceNotFoundException`, `GlobalExceptionHandler` translates it to a 404 response. [AOP](../aspect/README.md) demonstrates behavior that runs around selected controller methods.
