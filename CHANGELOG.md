# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-11-15

### Added
- **Employee Management:** Create and list employees with specific departments.
- **Leave Requests:** Support for submitting Vacation, Sick, and Maternity leaves.
- **Business Logic:** Polymorphic calculation of leave days using abstract classes.
- **Approval Flow:** Managers can approve or deny pending leave requests.
- **Global Exception Handling:** Custom exceptions for missing requests and invalid balances.
- **Interactive Console UI:** Centralized input handling using `ConsoleUtils`.
- **Testing:** Comprehensive unit tests and end-to-end integration tests.
- **Docker:** Multi-stage Dockerfile for containerized deployment.
- **CI/CD:** GitHub Actions pipelines for automated testing and building.