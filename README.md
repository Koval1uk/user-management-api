﻿# User Management System

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

# via Postman

## Start Your Spring Boot Application
Make sure application is running. You can do this by running the `UsermanagementApplication` class.

## Set Up Postman

### 1. Open Postman
If you don’t have it installed, download and install Postman from [here](https://www.postman.com/downloads/).

### 2. Create a New Collection
This will help organize your requests.

## Add Requests to Your Collection

### A. Add User

- **Request Type:** POST
- **URL:** `http://localhost:8080/users`
- **Body:** Select raw and JSON format

```json
{
    "userId": 1,
    "title": "Sample Title",
    "body": "Sample Body"
}
```

### B. Get User by ID

- **Request Type:** GET
- **URL:** `http://localhost:8080/users/1`

### C. Get Users by User ID

- **Request Type:** GET
- **URL:** `http://localhost:8080/users?userId=1`

### D. Update User

- **Request Type:** PUT
- **URL:** `http://localhost:8080/users/1`
- **Body:** Select raw and JSON format

```json
{
    "userId": 1,
    "title": "Updated Title",
    "body": "Updated Body"
}
```

### E. Delete User

- **Request Type:** DELETE
- **URL:** `http://localhost:8080/users/1`

## Setting Up the Requests in Postman

### Step-by-Step Guide for Postman Setup

### 1. Start Your Spring Boot Application
Ensure your Spring Boot application is running on `http://localhost:8080`.

### 2. Open Postman
Launch Postman on your computer.

### 3. Create a New Collection
- Click on the **Collections** tab on the left sidebar.
- Click on the **New Collection** button.
- Name your collection (e.g., `UserManagementAPI`).
- Click **Create**.

### 4. Add Requests to Your Collection

#### A. Add User Request
- Click on the **Add Request** button in your newly created collection.
- Name the request (e.g., `Add User`).
- Set the request type to **POST**.
- Enter the URL: `http://localhost:8080/users`.
- Go to the **Body** tab, select **raw**, and choose **JSON** from the dropdown.
- Enter the following JSON:
    ```json
    {
        "userId": 1,
        "title": "Sample Title",
        "body": "Sample Body"
    }
    ```
- Click **Save**.

#### B. Get User by ID Request
- Click on the **Add Request** button.
- Name the request (e.g., `Get User by ID`).
- Set the request type to **GET**.
- Enter the URL: `http://localhost:8080/users/1`.
- Click **Save**.

#### C. Get Users by User ID Request
- Click on the **Add Request** button.
- Name the request (e.g., `Get Users by User ID`).
- Set the request type to **GET**.
- Enter the URL: `http://localhost:8080/users?userId=1`.
- Click **Save**.

#### D. Update User Request
- Click on the **Add Request** button.
- Name the request (e.g., `Update User`).
- Set the request type to **PUT**.
- Enter the URL: `http://localhost:8080/users/1`.
- Go to the **Body** tab, select **raw**, and choose **JSON** from the dropdown.
- Enter the following JSON:
    ```json
    {
        "userId": 1,
        "title": "Updated Title",
        "body": "Updated Body"
    }
    ```
- Click **Save**.

#### E. Delete User Request
- Click on the **Add Request** button.
- Name the request (e.g., `Delete User`).
- Set the request type to **DELETE**.
- Enter the URL: `http://localhost:8080/users/1`.
- Click **Save**.

## Testing the Requests

### Add User
- Select the `Add User` request.
- Click **Send**.
- Ensure that the response status is **201 Created**.

### Get User by ID
- Select the `Get User by ID` request.
- Click **Send**.
- Ensure that the response contains the user data.

### Get Users by User ID
- Select the `Get Users by User ID` request.
- Click **Send**.
- Ensure that the response contains a list of users with the specified `userId`.

### Update User
- Select the `Update User` request.
- Click **Send**.
- Ensure that the response status is **200 OK** and the user data is updated.

### Delete User
- Select the `Delete User` request.
- Click **Send**.
- Ensure that the response status is **204 No Content**.

## Verifying Changes
- After adding a user, use the `Get User by ID` or `Get Users by User ID` requests to verify the user was added.
- After updating a user, use the `Get User by ID` request to verify the updates.
- After deleting a user, use the `Get User by ID` request to ensure the user no longer exists.

By following these steps, you can thoroughly test your microservice using Postman to ensure it meets all specified requirements.

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

