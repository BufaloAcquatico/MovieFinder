# Project Guide: Movie-Finder

## 1. Project Overview
- **Purpose**: Movie-Finder is a backend REST API application designed for managing movies, genres, user authentication, and movie reviews.
- **Key Technologies**:
  - Java 17
  - Spring Boot 4.1.0 (Spring Security, Spring Data JPA, Spring Web MVC)
  - MySQL & H2 Database (Runtime/Testing)
  - Flyway (Database migrations)
  - JJWT (JSON Web Token for authentication and authorization)
  - MapStruct (Object mapping)
  - Lombok (Boilerplate reduction)
  - Springdoc OpenAPI (Swagger UI documentation)
- **High-level Architecture**: Clean layered architecture consisting of Controllers, Services, Repositories, Domain Entities, DTOs, Mappers, Security configuration with Stateless JWT authentication, and Exception handling.

---

## 2. Getting Started
- **Prerequisites**:
  - Java Development Kit (JDK) 17 or higher
  - Maven (or use included Maven wrapper `./mvnw`)
  - MySQL server (or configure H2 for local development/testing)
- **Installation Instructions**:
  1. Clone the repository.
  2. Configure environment variables or database settings in `src/main/resources/application.yaml` or via `.env` file (e.g., `JWT_SECRET`, database credentials).
  3. Build the project using Maven:
     ```bash
     ./mvnw clean install
     ```
- **Basic Usage Examples**:
  - Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
  - Access OpenAPI / Swagger UI documentation at: `http://localhost:8080/docs/index.html` (or `/swagger-ui/index.html`).
- **Running Tests**:
  - Execute unit and integration tests using Maven:
    ```bash
    ./mvnw test
    ```

---

## 3. Project Structure
- **Overview of Main Directories (`src/main/java/it/lentini/moviefinder/`)**:
  - `config/`: Application configuration beans (e.g., JWT config, ObjectMapper).
  - `controller/`: REST endpoints (`AuthController`, etc.).
  - `domain/`: JPA Entities (`User`, `Movie`, `Genre`, `Review`, `Role`).
  - `dto/`: Request and Response records/classes along with MapStruct mappers.
  - `exception/`: Custom exceptions, error response structures, and global exception handlers (`GlobalExceptionHandler`).
  - `repository/`: Spring Data JPA repositories (`UserRepository`, `MovieRepository`, etc.).
  - `security/`: Spring Security setup, JWT filters, token services, and user details implementation.
  - `service/`: Business logic layer (`UserService`, `MovieService`, `GenreService`, `ReviewService`).
- **Important Configuration Files**:
  - `pom.xml`: Maven build file and dependencies.
  - `src/main/resources/application.yaml`: Main application properties, database connection URLs, JWT expiration settings.
  - `src/main/resources/db/migration/V1__Initial_database.sql`: Flyway initial database schema migration script.

---

## 4. Development Workflow
- **Coding Standards & Conventions**:
  - Use Lombok annotations (`@AllArgsConstructor`, `@Data`, etc.) to reduce boilerplate code.
  - Use constructor injection for dependency management (leveraging `@AllArgsConstructor`).
  - Use DTO records for requests and responses, mapped via MapStruct interfaces.
  - Follow RESTful API conventions with appropriate HTTP status codes and response validations (`jakarta.validation`).
- **Testing Approach**:
  - JUnit 5 and Spring Boot test starters for slicing web, security, and JPA repositories.
- **Build & Deployment Process**:
  - Package as an executable JAR using `./mvnw clean package`.
  - Database schema migrations are handled automatically on startup via Flyway.

---

## 5. Key Concepts
- **Domain-specific Terminology**:
  - **User / Role**: Users authenticated via email/password with roles (e.g., `USER`).
  - **Movie**: Film records with titles, descriptions, release dates, duration, language, director, and rating.
  - **Genre**: Categorization for movies, establishing a many-to-many relationship via `movie_genres`.
  - **Review**: User-submitted ratings (1-10) and comments on movies.
- **Core Abstractions**:
  - Stateless JWT authentication utilizing Access Tokens and Refresh Tokens (stored in HttpOnly cookies).
  - Centralized global error handling returning consistent `ApiErrorResponse` structures.
- **Design Patterns Used**:
  - Repository Pattern (Spring Data JPA)
  - DTO & Mapper Pattern (MapStruct)
  - Filter Chain Pattern (Spring Security JWT filter)

---

## 6. Common Tasks
- **Adding a New REST Endpoint**:
  1. Create a DTO in `dto/request` or `dto/response`.
  2. Implement business logic in a `@Service` class.
  3. Expose endpoints in a `@RestController` under `controller/`.
- **Running Database Migrations**:
  - Add new Flyway migration scripts sequentially in `src/main/resources/db/migration/` (e.g., `V2__...sql`).

---

## 7. Troubleshooting
- **Common Issues & Solutions**:
  - *Database Connection Refused*: Ensure MySQL is running and connection properties (`url`, `username`, `password`) in `application.yaml` or `.env` are correct.
  - *Missing JWT Secret*: Ensure the `JWT_SECRET` environment variable or property is configured properly.
- **Debugging Tips**:
  - Enable debug logging for Spring Security or Hibernate in `application.yaml` if diagnosing authentication or query issues.

---

## 8. References
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Flyway Documentation](https://flywaydb.org/documentation/)
- [MapStruct Documentation](https://mapstruct.org/)
