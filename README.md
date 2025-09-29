# Spring Boot CRUD Operations

This project is a simple **CRUD (Create, Read, Update, Delete)** application built with **Spring Boot** to practice backend development skills. It demonstrates key concepts in modern Spring Boot applications.

---

## 🛠 Technologies Used

- Java 17  
- Spring Boot 3.x  
- Spring Data JPA  
- MySQL  
- Maven  
- Jakarta Validation (Bean Validation)  

---

## 📚 Features

- **CRUD Operations:** Create, Read, Update, Delete tasks  
- **DTOs (Data Transfer Objects):** Clean separation between entity and API layer  
- **Validation:** Request validation using `@Valid` and annotations  
- **Global Exception Handling:** Centralized exception handling for API responses  
- **Pagination & Sorting:** Fetch tasks with page size, sorting by ID or other fields  
- **Custom Repository Methods:** Extend JPA repository when needed  

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/iamvishnu12/crud_operations.git
cd crud_operations
2. Configure the database
Rename application.properties.example to application.properties and update:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
3. Run the application
bash
Copy code
mvn spring-boot:run
4. Access the API
Base URL: http://localhost:8080/tasks

Endpoints:

GET /tasks – Retrieve all tasks

GET /tasks/{id} – Retrieve a task by ID

POST /tasks – Create a new task

PUT /tasks/{id} – Update an existing task

DELETE /tasks/{id} – Delete a task by ID

Pagination example: GET /tasks?page=0&size=5&sort=id,desc
