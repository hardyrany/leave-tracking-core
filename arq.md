# Project Architecture: Leave Tracking Core

## 1. Overview
**Leave Tracking Core** is a console application built with **Pure Java (no frameworks)**. The main goal is to master Object-Oriented Programming (OOP) advanced concepts: Inheritance, Polymorphism, Interfaces, Abstract Classes, and Collections, before moving on to Spring Boot.

## 2. Architectural Pattern
- **Package by Feature**: Organized by domains (`employee`, `leaverequest`).
- **Layered Architecture**:
  - `domain`: Entities, enums, and interfaces.
  - `application`: Services (use cases).
  - `infra`: In-memory repositories and persistence details.
- **Global Infrastructure**: Cross-cutting utilities and exceptions isolated in the root `infra` folder.

## 3. Directory Structure

```text
02-leave-tracking-core/
│
├── .github/
│   └── workflows/
│       ├── block-direct-pr.yml      # Proteção da branch main
│       ├── cd.yml                   # Pipeline de Entrega Contínua
│       └── ci.yml                   # Pipeline de Integração Contínua
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── hardy/
│   │               └── leavetracking/
│   │                   ├── LeaveTrackingApplication.java  # Ponto de entrada (Main)
│   │                   │
│   │                   ├── infra/                         # Preocupações globais
│   │                   │   ├── exception/
│   │                   │   │   ├── GlobalExceptionHandler.java
│   │                   │   │   ├── InvalidLeaveBalanceException.java
│   │                   │   │   └── LeaveRequestNotFoundException.java
│   │                   │   └── util/
│   │                   │       └── ConsoleUtils.java
│   │                   │
│   │                   ├── employee/                      # Feature: Funcionários
│   │                   │   ├── domain/
│   │                   │   │   ├── entity/
│   │                   │   │   │   └── Employee.java
│   │                   │   │   └── enum/
│   │                   │   │       └── Department.java
│   │                   │   ├── infra/
│   │                   │   │   └── repository/
│   │                   │   │       └── EmployeeRepository.java
│   │                   │   └── application/
│   │                   │       └── service/
│   │                   │           └── EmployeeService.java
│   │                   │
│   │                   └── leaverequest/                  # Feature: Pedidos de Licença
│   │                       ├── domain/
│   │                       │   ├── entity/
│   │                       │   │   ├── LeaveRequest.java
│   │                       │   │   ├── SickLeaveRequest.java
│   │                       │   │   ├── VacationLeaveRequest.java
│   │                       │   │   └── MaternityLeaveRequest.java
│   │                       │   ├── enum/
│   │                       │   │   └── LeaveType.java
│   │                       │   └── interface/
│   │                       │       └── Approvable.java
│   │                       ├── infra/
│   │                       │   └── repository/
│   │                       │       └── LeaveRequestRepository.java
│   │                       └── application/
│   │                           └── service/
│   │                               └── LeaveRequestService.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── hardy/
│                   └── leavetracking/
│                       ├── employee/
│                       │   └── EmployeeServiceTest.java
│                       └── leaverequest/
│                           └── LeaveRequestServiceTest.java
│
├── .env.example
├── .gitignore
├── Dockerfile
├── LICENSE
├── README.md
├── arq.md
└── pom.xml