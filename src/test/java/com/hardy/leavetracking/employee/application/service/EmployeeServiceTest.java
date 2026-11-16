package com.hardy.leavetracking.employee.application.service;

import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.employee.domain.enums.Department;
import com.hardy.leavetracking.employee.infra.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o EmployeeService.
 */
class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeRepository repository;

    /**
     * Configura o ambiente antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        repository = new EmployeeRepository();
        employeeService = new EmployeeService(repository);
    }

    /**
     * Testa a criação de um funcionário com sucesso.
     */
    @Test
    void shouldCreateEmployeeSuccessfully() {
        LocalDate startDate = LocalDate.of(2023, 1, 15);
        
        Employee created = employeeService.createEmployee(
                "Hardy", "Rany", "Developer", 5000.0, startDate, Department.IT
        );

        assertNotNull(created);
        assertEquals(1, created.getId());
        assertEquals("Hardy", created.getFirstName());
        assertEquals("Developer", created.getPosition());
        assertEquals(Department.IT, created.getDepartment());

        // Verifica se foi guardado no repositório
        List<Employee> all = employeeService.getAllEmployees();
        assertEquals(1, all.size());
        assertEquals("Hardy", all.get(0).getFirstName());
    }

    /**
     * Testa a procura de um funcionário por ID.
     */
    @Test
    void shouldReturnEmployeeWhenFoundById() {
        Employee created = employeeService.createEmployee(
                "Jane", "Doe", "Manager", 7000.0, LocalDate.now(), Department.HR
        );

        Employee found = employeeService.getEmployeeById(created.getId());

        assertNotNull(found);
        assertEquals("Jane", found.getFirstName());
    }

    /**
     * Testa o retorno nulo quando o ID não existe.
     */
    @Test
    void shouldReturnNullWhenEmployeeNotFound() {
        Employee found = employeeService.getEmployeeById(99);
        assertNull(found);
    }
}