# SecureApi — Spring Boot REST API

## Overview
SecureApi is a REST API built with Java and Spring Boot. It supports user registration, JWT-based authentication, profile management, request validation, and persistent storage using Oracle Database.

## Technologies
- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA / Hibernate
- Oracle Database
- Maven
- JUnit 5 and Mockito
- Postman

## Features
- User registration with BCrypt password hashing
- User login with JWT generation
- JWT authentication for protected endpoints
- View and update the authenticated user's profile
- Delete the authenticated user's account
- Request validation and global exception handling
- Unit tests for successful login and invalid password

## API Endpoints

| Method | Endpoint         | Purpose |
|    
| POST   | `/api/auth/register` | Register a user |
| POST   | `/api/auth/login`    | Log in and obtain a JWT |
| GET    | `/api/auth/me`      | Check the authenticated user |
| GET    | `/api/users/me`     | View your profile |
| PUT    | `/api/users/me`     | Update your profile name |
| DELETE | `/api/users/me`     | Delete your account |

## Prerequisites
- Compatible JDK
- Maven
- Oracle Database
- Postman (for API testing)

## Configuration
Configure the Oracle database connection and credentials in `src/main/resources/application.properties`. Do not commit real credentials or JWT signing secrets to version control.

## Run the Application
1. Configure the Oracle database.
2. Review `application.properties`.
3. Build the project using Maven.
4. Run `SecureApiApplication` from Eclipse or start it using Maven.

## Run Tests
Run the unit tests from Eclipse using **Run As → JUnit Test**, or run:

`mvn test`

## Authentication
For protected endpoints, obtain a JWT from `/api/auth/login`. In Postman, select **Authorization → Bearer Token** and provide the token.

## Database
The application uses Oracle Database with Spring Data JPA/Hibernate persistence.

## Notes
This project was developed as a Java Development assignment for Ikaansh Edutech Private Limited.




# Maven build output
target/

# Eclipse IDE files
.classpath
.project
.settings/

# Local environment files
.env
.env.*
!.env.example

# Logs
*.log

# Local application configuration containing secrets
application-local.properties

