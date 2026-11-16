package com.hardy.leavetracking.leaverequest.application.service;

import com.hardy.leavetracking.employee.application.service.EmployeeService;
import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.employee.domain.enums.Department;
import com.hardy.leavetracking.employee.infra.repository.EmployeeRepository;
import com.hardy.leavetracking.leaverequest.domain.entity.LeaveRequest;
import com.hardy.leavetracking.leaverequest.domain.entity.SickLeaveRequest;
import com.hardy.leavetracking.leaverequest.domain.entity.VacationLeaveRequest;
import com.hardy.leavetracking.leaverequest.infra.repository.LeaveRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o LeaveRequestService.
 */
class LeaveRequestServiceTest {

    private LeaveRequestService leaveRequestService;
    private EmployeeService employeeService;
    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        LeaveRequestRepository leaveRequestRepository = new LeaveRequestRepository();
        
        employeeService = new EmployeeService(employeeRepository);
        leaveRequestService = new LeaveRequestService(leaveRequestRepository, employeeRepository);

        // Criar um funcionário base para os testes
        testEmployee = employeeService.createEmployee(
                "Hardy", "Rany", "Developer", 5000.0, LocalDate.now(), Department.IT
        );
    }

    @Test
    void shouldSubmitVacationRequestSuccessfully() {
        LocalDate start = LocalDate.of(2026, 12, 1);
        LocalDate end = LocalDate.of(2026, 12, 10);

        VacationLeaveRequest request = new VacationLeaveRequest(0, testEmployee, start, end, "Family trip");
        LeaveRequest submitted = leaveRequestService.submitRequest(request);

        assertNotNull(submitted);
        assertEquals(1, submitted.getId());
        assertEquals("PENDING", submitted.getStatus());
        assertEquals(10, submitted.calculateLeaveDays()); // 10 dias de férias
    }

    @Test
    void shouldSubmitSickLeaveSuccessfully() {
        LocalDate start = LocalDate.of(2026, 11, 15);
        LocalDate end = LocalDate.of(2026, 11, 17);

        SickLeaveRequest request = new SickLeaveRequest(0, testEmployee, start, end, "Flu");
        LeaveRequest submitted = leaveRequestService.submitRequest(request);

        assertNotNull(submitted);
        assertEquals(3, submitted.calculateLeaveDays()); // 3 dias de doença
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {
        Employee ghostEmployee = new Employee(99, "Ghost", "User", "None", 0.0, LocalDate.now(), Department.IT);
        VacationLeaveRequest request = new VacationLeaveRequest(0, ghostEmployee, LocalDate.now(), LocalDate.now().plusDays(5), "Vacation");

        assertThrows(RuntimeException.class, () -> {
            leaveRequestService.submitRequest(request);
        });
    }
}