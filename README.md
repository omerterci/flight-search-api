# Flight Search API 
Description
The Flight Search API is a Spring Boot application designed to facilitate the searching and management of flight and airport information. It leverages Spring Data JPA for persistence, Spring Security for basic authentication, and SpringDoc OpenAPI for API documentation and interaction through Swagger UI.

# Features

- CRUD operations for managing airports and flights.
- Search functionality for flights based on departure and arrival airports, and date.
- Basic authentication for securing API endpoints.
- Automated API documentation with Swagger UI.
# Prerequisites
Before you begin, ensure you have met the following requirements:
- Java JDK 17 or later installed.
- Maven for project dependency management and build.
- MySQL database installed and running.

# Database Setup

- Create a MySQL database named flight_search_db.
- Current username is root and password is : 1212
- If you want Update the src/main/resources/application.properties file with your MySQL user and password:
* spring.datasource.url=jdbc:mysql://localhost:3306/flight_search_db?useSSL=false&serverTimezone=UTC
* spring.datasource.username= username
* spring.datasource.password= yourpassword

# Data initalizer
- Flight Search API uses a command-line runner defined in the DataInitializer configuration class.
- This approach ensures that the application has a set of data to work with immediately after startup, facilitating immediate testing and interaction without manual data entry.
 
# Running the Application

- Clone the repository to your local machine.
- Navigate to the project directory and run the following command to build the project:
- mvn clean install
- To start the application, run:
- mvn spring-boot:run
- The application will be accessible at http://localhost:8080.

# Using Swagger UI

- The Flight Search API documentation is available through Swagger UI, which provides an interactive API interface.
- With the application running, visit http://localhost:8080/swagger-ui.html in your web browser.
- You will be prompted for a username and password. Use the following default credentials:
- Username: user
- Password: userPassword
- Once authenticated, you can explore the available API endpoints, try out operations, and view request and response details.
- Explore endpoints: On the Swagger UI page, you'll see a list of all controllers and their endpoints. Click on any endpoint to expand it and see detailed information, including HTTP method, request parameters, and response format.
- Try out endpoints: Click the "Try it out" button for an endpoint, fill in any required parameters, and click "Execute" to make a real request to the API. You'll see the request URL, response body, status code, and headers.

# API Authentication

- The API uses basic authentication to secure endpoints. Two users are configured by default:
- User: Access to /api/** endpoints with USER role.
- Username: user
- Password: userPassword
- Admin: Access to all endpoints with ADMIN role.
- Username: admin
- Password: adminPassword
