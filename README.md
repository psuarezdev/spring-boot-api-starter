# Spring Boot API Starter

**Spring Boot API Starter** is a foundational template for building modern REST APIs with Spring Boot. This project provides a pre-configured setup with essential dependencies and features, enabling developers to jumpstart API development efficiently. It includes built-in authentication, validation, and database connectivity, making it ideal for rapidly creating secure and scalable APIs.

---

## Key Features:
- **Spring Boot Starter Dependencies**:
  - `spring-boot-starter-data-jpa`: Simplified interaction with relational databases.
  - `spring-boot-starter-security`: Pre-configured authentication and security mechanisms.
  - `spring-boot-starter-web`: Core tools for creating RESTful services.
  - `spring-boot-starter-validation`: Input validation for API endpoints.

- **Database Support**:
  - Out-of-the-box configuration for PostgreSQL (runtime dependency).

- **Security**:
  - Authentication and token-based security using `io.jsonwebtoken` (JJWT library).
  - `spring-security-test` for security-related testing.

- **Development Tools**:
  - `spring-boot-devtools`: Hot-reloading for efficient development.
  - `lombok`: Boilerplate reduction for Java classes.
  - `modelmapper`: Seamless object mapping for DTOs and entities.

- **Testing**:
  - `spring-boot-starter-test` for comprehensive testing support.
  - Security testing utilities included.

---

## Configuration:
To run the project, you need to configure the application properties. Replace placeholders in the following file with your specific values:

```yaml
server:
  port: 4000

spring:
  application:
    name: spring-boot-api-starter
  datasource:
    url: jdbc:postgresql://localhost:5432/<db_name>
    username: <db_user>
    password: <db_passwd>
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update

security:
  jwt:
    secret-key: <your_secret_key_in_base64> # base64 encoded

    expiration: 86400000 # 1 day
    refresh-token:
      expiration: 604800000 # 7 days
```

---

## Server Configuration:
- Default server port: `4000`.

---

## Database Settings:
- Replace `<db_name>`, `<db_user>`, and `<db_passwd>` with your database name, username, and password.

---

## JWT Configuration:
- Provide a base64-encoded secret key for signing tokens.
- Configure token expiration times:
  - Default: 1 day for access tokens (`expiration: 86400000`).
  - Default: 7 days for refresh tokens (`refresh-token.expiration: 604800000`).

---

## Technical Details:
- **Java Version**: 17
- **Build Tool**: Maven
- **Plugins**:
  - `maven-compiler-plugin` with annotation processing for Lombok.
  - `spring-boot-maven-plugin` for building and running Spring Boot applications.
