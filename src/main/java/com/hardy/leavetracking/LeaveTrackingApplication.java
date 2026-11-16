package com.hardy.leavetracking;

import com.hardy.leavetracking.employee.application.service.EmployeeService;
import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.employee.domain.enums.Department;
import com.hardy.leavetracking.employee.infra.repository.EmployeeRepository;
import com.hardy.leavetracking.infra.exception.LeaveRequestNotFoundException;
import com.hardy.leavetracking.infra.handler.GlobalExceptionHandler;
import com.hardy.leavetracking.infra.utils.ConsoleUtils;
import com.hardy.leavetracking.leaverequest.application.service.LeaveRequestService;
import com.hardy.leavetracking.leaverequest.domain.entity.LeaveRequest;
import com.hardy.leavetracking.leaverequest.domain.entity.MaternityLeaveRequest;
import com.hardy.leavetracking.leaverequest.domain.entity.SickLeaveRequest;
import com.hardy.leavetracking.leaverequest.domain.entity.VacationLeaveRequest;
import com.hardy.leavetracking.leaverequest.infra.repository.LeaveRequestRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Classe principal do Leave Tracking System.
 * Responsável por arrancar a aplicação e orquestrar o menu interativo.
 */
public class LeaveTrackingApplication {

    // 1. Instanciar repositórios
    private static final EmployeeRepository employeeRepository = new EmployeeRepository();
    private static final LeaveRequestRepository leaveRequestRepository = new LeaveRequestRepository();

    // 2. Instanciar serviços (Injeção manual de dependências)
    private static final EmployeeService employeeService = new EmployeeService(employeeRepository);
    private static final LeaveRequestService leaveRequestService = new LeaveRequestService(leaveRequestRepository,
            employeeRepository);

    /**
     * Ponto de entrada da aplicação.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("   Welcome to Leave Tracking System   ");
        System.out.println("=========================================");

        while (running) {
            printMenu();
            int choice = ConsoleUtils.readInt("Choose an option: ");
            ConsoleUtils.printBlankLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    submitLeaveRequest();
                    break;
                case 3:
                    approveLeaveRequest();
                    break;
                case 4:
                    listAllEmployees();
                    break;
                case 5:
                    listAllRequests();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            ConsoleUtils.printBlankLine();
        }
    }

    /**
     * Imprime as opções do menu.
     */
    private static void printMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Add Employee");
        System.out.println("2. Submit Leave Request");
        System.out.println("3. Approve Leave Request");
        System.out.println("4. List All Employees");
        System.out.println("5. List All Leave Requests");
        System.out.println("0. Exit");
    }

    /**
     * Lê os dados do utilizador e cria um novo funcionário.
     */
    private static void addEmployee() {
        System.out.println("--- Add Employee ---");
        try {
            String firstName = ConsoleUtils.readString("First Name: ");
            String lastName = ConsoleUtils.readString("Last Name: ");
            String position = ConsoleUtils.readString("Position: ");
            double salary = ConsoleUtils.readDouble("Salary: ");

            // Escolha do departamento
            System.out.println(
                    "Departments: 1.HR, 2.IT, 3.SALES, 4.FINANCE, 5.MARKETING, 6.ENGINEERING, 7.LEGAL, 8.RETAIL, 9.SUPPLY_CHAIN, 10.LOGISTICS");
            int deptChoice = ConsoleUtils.readInt("Choose Department: ");
            Department department = getDepartmentById(deptChoice);

            Employee emp = employeeService.createEmployee(firstName, lastName, position, salary, LocalDate.now(),
                    department);
            System.out.println("✔ Employee added successfully with ID: " + emp.getId());
        } catch (Exception e) {
            GlobalExceptionHandler.handleGenericException(e);
        }
    }

    /**
     * Lê os dados do utilizador e submete um pedido de licença.
     */
    private static void submitLeaveRequest() {
        System.out.println("--- Submit Leave Request ---");
        try {
            int empId = ConsoleUtils.readInt("Enter Employee ID: ");
            Employee emp = employeeService.getEmployeeById(empId);

            if (emp == null) {
                System.out.println("⚠ Employee not found.");
                return;
            }

            System.out.println("1. Vacation, 2. Sick Leave, 3. Maternity");
            int type = ConsoleUtils.readInt("Choose Leave Type: ");

            LocalDate start = readDate("Start Date (yyyy-MM-dd): ");
            LocalDate end = readDate("End Date (yyyy-MM-dd): ");
            String reason = ConsoleUtils.readString("Reason: ");

            LeaveRequest request;
            if (type == 1) {
                request = new VacationLeaveRequest(0, emp, start, end, reason);
            } else if (type == 2) {
                request = new SickLeaveRequest(0, emp, start, end, reason);
            } else {
                request = new MaternityLeaveRequest(0, emp, start, end, reason);
            }

            leaveRequestService.submitRequest(request);
            System.out.println("✔ Leave request submitted successfully with ID: " + request.getId());
            System.out.println("Status: " + request.getStatus() + " | Total Days: " + request.calculateLeaveDays());

        } catch (Exception e) {
            GlobalExceptionHandler.handleGenericException(e);
        }
    }

    /**
     * Aprova um pedido de licença existente.
     */
    private static void approveLeaveRequest() {
        System.out.println("--- Approve Leave Request ---");
        try {
            int requestId = ConsoleUtils.readInt("Enter Leave Request ID: ");
            String approver = ConsoleUtils.readString("Approver Name: ");

            boolean success = leaveRequestService.approveRequest(requestId, approver);
            if (success) {
                System.out.println("✔ Leave request approved successfully!");
            } else {
                System.out.println("⚠ Could not approve. Request might already be processed.");
            }
        } catch (LeaveRequestNotFoundException e) {
            GlobalExceptionHandler.handleRequestNotFound(e);
        }
    }

    /**
     * Lista todos os funcionários.
     */
    private static void listAllEmployees() {
        System.out.println("--- All Employees ---");
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees registered.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    /**
     * Lista todos os pedidos de licença.
     */
    private static void listAllRequests() {
        System.out.println("--- All Leave Requests ---");
        List<LeaveRequest> requests = leaveRequestService.getAllRequests();
        if (requests.isEmpty()) {
            System.out.println("No leave requests found.");
        } else {
            for (LeaveRequest r : requests) {
                System.out.println("ID: " + r.getId() + " | Employee: " + r.getEmployee().getFirstName() +
                        " | Status: " + r.getStatus() + " | Days: " + r.calculateLeaveDays());
            }
        }
    }

    // ==========================================
    // MÉTODOS AUXILIARES
    // ==========================================

    /**
     * Converte o número escolhido no menu para o Enum Department.
     */
    private static Department getDepartmentById(int choice) {
        return switch (choice) {
            case 1 -> Department.HR;
            case 2 -> Department.IT;
            case 3 -> Department.SALES;
            case 4 -> Department.FINANCE;
            case 5 -> Department.MARKETING;
            case 6 -> Department.ENGINEERING;
            case 7 -> Department.LEGAL;
            case 8 -> Department.RETAIL;
            case 9 -> Department.SUPPLY_CHAIN;
            default -> Department.LOGISTICS;
        };
    }

    /**
     * Lê e valida uma data no formato yyyy-MM-dd.
     */
    private static LocalDate readDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                String dateStr = ConsoleUtils.readString(prompt);
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }
}