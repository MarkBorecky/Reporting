# Assignment

The application utilizes a GraphQL API with token-based authentication.
To enhance security, I implemented a filter where the SecurityContext intercepts the token and injects it into the client. In this solution, the filter performs a simple validation to ensure the token has the correct structure and has not expired. This design cleanly separates the business logic from the technical aspects of authentication.
Additionally, I introduced several custom exceptions and a ControllerAdvice to manage various status codes and handle errors effectively.
The project leverages the latest versions of Java and Spring.

### SWAGGER

Swagger documentation is available at: http://localhost:8080/swagger-ui/index.html. I also configured Swagger to support token-based requests by allowing tokens to be added directly to the requests in the Swagger UI.