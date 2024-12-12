# Task Management System

A real-time collaborative task management system built with:
- **Java 11**
- **Spring Boot**
- **MySQL** for database
- **Kafka** for messaging
- **Spring Security** for authentication

## Features
- Task creation, update, and deletion.
- Role-based access (Admin/User).
- Kafka integration for real-time notifications.

## How to Run
1. Set up MySQL and Kafka.
2. Configure the application in `application.properties`.
3. Run the application with `mvn spring-boot:run`.

## APIs
- `POST /api/tasks`: Create a task.
- `GET /api/tasks`: Fetch all tasks.
- `PUT /api/tasks/{id}`: Update a task.
- `DELETE /api/tasks/{id}`: Delete a task.

## Contributions
Contributions are welcome! Fork the repo and submit a pull request.

## License
This project is licensed under the MIT License.
