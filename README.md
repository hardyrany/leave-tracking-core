# Leave Tracking Core

A console application built with **Pure Java (no frameworks)** to manage employee leave requests. This project was designed to master advanced Object-Oriented Programming (OOP) concepts, including Inheritance, Polymorphism, Interfaces, Abstract Classes, and Enums.

![Java](https://img.shields.io/badge/Java-21-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Status](https://img.shields.io/badge/Status-v1.0.0-success)

## 📋 About the Project

**Leave Tracking Core** allows companies to register employees and manage their time-off requests. It was developed as the second project in a professional Java portfolio, focusing on clean architecture and robust business logic before transitioning to Spring Boot.

## 🏗️ Architecture

The project follows **Package by Feature** combined with **Layered Architecture**:

- `employee/`: Manages employee data.
- `leaverequest/`: Manages leave requests (Vacation, Sick, Maternity).
- `infra/`: Global exceptions and console utilities.

Each feature is divided into:
- `domain/`: Entities, Enums, and Interfaces.
- `infra/repository/`: In-memory persistence (HashMap).
- `application/service/`: Business logic.

## 🚀 Features

- **Employee Registration:** Create employees with specific roles and departments.
- **Leave Request Submission:** Submit different types of leave requests.
- **Approval System:** Approve or deny pending requests.
- **Polymorphism:** Leave duration is calculated automatically based on the type of leave.
- **Exception Handling:** Robust validation for non-existent employees and requests.

## 🛠️ Technologies Used

- **Java 21**
- **Maven** (Build & Dependency Management)
- **JUnit 5** (Unit & Integration Testing)
- **Docker** (Multi-stage build)
- **GitHub Actions** (CI/CD)

## ⚙️ How to Run

### Prerequisites
- Java 21 or higher installed.

### Steps

# Clone the repository
git clone https://github.com/hardyrany/leave-tracking-core.git

# Enter the project directory
cd leave-tracking-core

# Run tests
mvn clean test

# Package the application
mvn clean package

# Run the application
java -jar target/leave-tracking-core-1.0.0.jar

👤 Author
Hardy Fernandes

GitHub: @hardyrany