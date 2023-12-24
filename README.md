# Spring Security Example Project

This is a Spring Security example project that demonstrates user authentication, authorization, and JWT token generation.

## Introduction

This project showcases the implementation of Spring Security in a Spring Boot application. It includes user registration, login, role-based access control, and JWT token generation.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- Mysql
- Docker

## Project Structure

The project is organized into several packages:

- `controller`: Contains the controllers for handling authentication and authorization endpoints.
- `config`: Includes configuration classes, such as Spring Security configuration and password encoder configuration.
- `exception`: Holds custom exception classes.
- `dto`: Contains Data Transfer Objects (DTOs) used for data transfer.
- `model`: Defines the User and Role entities.
- `repository`: Contains the UserRepository interface for database operations.
- `security`: Includes classes related to security, such as JWT filter, authentication entry point, and access denied handler.
- `service`: Implements services for user registration, login, and user details retrieval.

## Setup

### Prerequisites

Make sure you have Docker or Maven and Mysql installed on your machine:


### Build with Docker

1. Clone the repository:

    ```bash
    git clone https://github.com/emirhanusta/spring-security-jwt.git
    ```

2. Navigate to the project directory:

    ```bash
    cd spring-security-jwt
    ```

3. Run Docker Compose to start MySQL and the Spring Boot application:

    ```bash
    docker-compose up -d
    ```

    
### Build with Maven

1. Clone the repository:

    ```bash
    git clone https://github.com/emirhanusta/spring-security-jwt.git
    ```

2. Navigate to the project directory:

    ```bash
    cd spring-security-jwt
    ```

3. Run the Spring Boot application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```


## Usage

1. **Register a New User:**
   - Endpoint: `POST /api/v1/auth/register`
   - Description: Register a new user by providing the required registration details.

2. **Login with Registered User:**
   - Endpoint: `POST /api/v1/auth/login`
   - Description: Login with the registered user credentials to obtain a JWT token.

3. **Obtain JWT Token:**
   - Description: Retrieve the JWT token from the login response.

4. **Access Secured Endpoints:**
   - Description: Use the obtained JWT token to access secured endpoints.

## Endpoints

- **Register a New User:**
  - Endpoint: `POST /api/v1/auth/register`
  - Description: Register a new user.
![image](https://github.com/emirhanusta/spring-security-jwt/assets/83432342/6d17375d-1965-4cf5-8bf2-dcfb2f6cd1b0)

- **Login and Obtain JWT Token:**
  - Endpoint: `POST /api/v1/auth/login`
  - Description: Login and obtain a JWT token.
![image](https://github.com/emirhanusta/spring-security-jwt/assets/83432342/6b3f5095-b00a-4eb5-ac41-330ccde54d62)

- **Secured Endpoint for Admin Role:**
  - Endpoint: `GET /api/v1/auth/admin`
  - Description: Secured endpoint accessible only by users with the admin role.
![image](https://github.com/emirhanusta/spring-security-jwt/assets/83432342/f09d5416-a261-4c4d-aa5a-e62b41f9f012)

- **Secured Endpoint for User Role:**
  - Endpoint: `GET /api/v1/auth/user`
  - Description: Secured endpoint accessible by users with either the user or admin role.
![image](https://github.com/emirhanusta/spring-security-jwt/assets/83432342/dd5c183a-a94b-43fb-910c-8452c616fff7)

- **Secured Endpoint with PreAuthorize Annotation:**
  - Endpoint: `GET /api/v1/auth/test`
  - Description: Secured endpoint with `PreAuthorize` annotation, accessible only by users with the admin role.
![image](https://github.com/emirhanusta/spring-security-jwt/assets/83432342/f496513b-e211-46f7-9d75-d6ba33eb116e)

   
