# User Management System

## Project Overview

The User Management System is a Spring Boot-based microservice that provides RESTful APIs to manage user posts. The project includes functionalities for adding, viewing, updating, and deleting user posts, with user ID validation via an external API. It also includes a simple frontend for user interaction and Docker support for containerization.

## Features

- **Add User Post**: Validates `userId` using an external API before adding a post.
- **View User Post**: View posts by `id` or `userId`. If a post with the given `id` is not found, it fetches from an external API and saves it.
- **Update User Post**: Update the `title` and `body` of a post.
- **Delete User Post**: Remove a post by `id`.
- **Frontend**: A simple web interface to interact with the API.
- **Dockerized**: Easily run the application in a Docker container.
- **Validation and Error Handling**: Robust input validation and error handling mechanisms.

## Technology Stack

- **Backend**: Spring Boot, Spring MVC, Spring Data JPA, H2 Database, JUnit
- **Frontend**: Thymeleaf, HTML, CSS
- **External API**: JSONPlaceholder (https://jsonplaceholder.typicode.com/)
- **Containerization**: Docker
- **Build Tool**: Maven
- **Validation**: Jakarta Bean Validation (Hibernate Validator)
- **Documentation**: Swagger/OpenAPI

## How to Run

### Prerequisites

- **Java Development Kit (JDK) 22**
- **Maven**
- **Docker** (for running the application in a container)

### Running Locally

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Koval1uk/user-management-api.git
   cd usermanagement
   ```

2. **Build the project**:
   ```bash
   mvn clean package
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**:
    - API Documentation: `http://localhost:8080/swagger-ui/index.html`
    - H2 Console: `http://localhost:8080/h2-console`
    - Frontend: `src-> main-> webapp-> WEB-INF-> thymeleaf-> users.html`
    - `http://localhost:8080/user-management`

### Running with Docker

1. **Build the Docker image**:
   ```bash
   docker build -t usermanagement:latest .
   ```

2. **Run the Docker container**:
   ```bash
   docker run -d -p 8080:8080 --name usermanagement-container usermanagement:latest
   ```

3. **Access the application**:
    - API Documentation: `http://localhost:8080/swagger-ui/index.html`
    - H2 Console: `http://localhost:8080/h2-console`
   - Frontend: `src-> main-> webapp-> WEB-INF-> thymeleaf-> users.html`
   - `http://localhost:8080/user-management`

## Testing

### Running Tests

To run the tests, execute the following command:
```bash
mvn test
```

## API Endpoints

- **Add User Post**: `POST /users`
- **Get User Post by ID**: `GET /users/{id}`
- **Get User Posts by UserID**: `GET /users?userId={userId}`
- **Update User Post**: `PUT /users/{id}`
- **Delete User Post**: `DELETE /users/{id}`

## Frontend

The frontend is built using Thymeleaf templates and provides a simple user interface to interact with the API. You can add, view, update, and delete posts through the web interface.

## External API

The project uses JSONPlaceholder (https://jsonplaceholder.typicode.com/) to validate user IDs and fetch posts when necessary.

## Validations and Error Handling

- **Validation**: Input data is validated using Jakarta Bean Validation (Hibernate Validator).
- **Error Handling**: Custom error handlers are implemented to provide meaningful error messages for invalid inputs and other exceptions.

