package com.hardy.leavetracking.integration;

import com.hardy.leavetracking.employee.application.service.EmployeeService;
import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.employee.domain.enums.Department;
import com.hardy.leavetracking.employee.infra.repository.EmployeeRepository;
import com.hardy.leavetracking.leaverequest.application.service.LeaveRequestService;
import com.hardy.leavetracking.leaverequest.domain.entity.LeaveRequest;
import com.hardy.leavetracking.leaverequest.domain.entity.VacationLeaveRequest;
import com.hardy.leavetracking.leaverequest.infra.repository.LeaveRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de Integração para o fluxo completo do Leave Tracking System.
 * 
 * Explicação: Ao contrário dos testes unitários (que usam mocks e testam 1 classe),
 * este teste instancia as classes REAIS (Repositories e Services) e simula
 * a jornada do utilizador de ponta a ponta.
 */
class LeaveTrackingIntegrationTest {

    private EmployeeService employeeService;
    private LeaveRequestService leaveRequestService;

    /**
     * SETUP: Antes de cada teste, criamos o ambiente real.
     * Isto simula o que a classe Main (LeaveTrackingApplication) faz ao arrancar.
     */
    @BeforeEach
    void setUp() {
        // 1. Criar os repositórios reais (em memória)
        EmployeeRepository employeeRepository = new EmployeeRepository();
        LeaveRequestRepository leaveRequestRepository = new LeaveRequestRepository();

        // 2. Injetar os repositórios nos serviços
        employeeService = new EmployeeService(employeeRepository);
        leaveRequestService = new LeaveRequestService(leaveRequestRepository, employeeRepository);
    }

    /**
     * TESTE: Fluxo Completo (Happy Path).
     * 
     * Explicação: Simula o utilizador a criar um funcionário, a pedir férias
     * e o gestor a aprovar. No fim, valida se o estado final está correto.
     */
    @Test
    void shouldCompleteFullLeaveRequestFlowSuccessfully() {
        // PASSO 1: Criar um funcionário (simulando a opção 1 do menu)
        Employee employee = employeeService.createEmployee(
                "Hardy", "Rany", "Developer", 5000.0, LocalDate.now(), Department.IT
        );
        
        assertNotNull(employee, "O funcionário deve ser criado.");
        assertEquals(1, employee.getId(), "O primeiro funcionário deve ter ID 1.");

        // PASSO 2: Submeter um pedido de férias (simulando a opção 2 do menu)
        LocalDate start = LocalDate.of(2026, 12, 1);
        LocalDate end = LocalDate.of(2026, 12, 5); // 5 dias de férias

        VacationLeaveRequest request = new VacationLeaveRequest(
                0, employee, start, end, "Christmas Holidays"
        );
        
        LeaveRequest submittedRequest = leaveRequestService.submitRequest(request);

        assertNotNull(submittedRequest, "O pedido deve ser submetido.");
        assertEquals(1, submittedRequest.getId(), "O primeiro pedido deve ter ID 1.");
        assertEquals("PENDING", submittedRequest.getStatus(), "O pedido deve começar como PENDING.");
        assertEquals(5, submittedRequest.calculateLeaveDays(), "Devem ser calculados 5 dias.");

        // PASSO 3: Aprovar o pedido (simulando a opção 3 do menu)
        boolean isApproved = leaveRequestService.approveRequest(submittedRequest.getId(), "Manager");

        assertTrue(isApproved, "O pedido deve ser aprovado com sucesso.");
        
        // PASSO 4: Verificar o estado final
        LeaveRequest finalRequest = leaveRequestService.getAllRequests().get(0);
        assertEquals("APPROVED", finalRequest.getStatus(), "O estado final deve ser APPROVED.");
    }

    /**
     * TESTE: Cenário de Erro.
     * 
     * Explicação: Tenta submeter uma licença para um funcionário que não existe
     * no sistema. O serviço deve rejeitar a operação.
     */
    @Test
    void shouldFailWhenSubmittingLeaveForNonExistentEmployee() {
        // Criar um funcionário "fantasma" que NÃO foi guardado no repositório
        Employee ghostEmployee = new Employee(99, "Ghost", "User", "None", 0.0, LocalDate.now(), Department.IT);

        VacationLeaveRequest request = new VacationLeaveRequest(
                0, ghostEmployee, LocalDate.now(), LocalDate.now().plusDays(5), "Vacation"
        );

        // Explicação: O assertThrows verifica se a exceção esperada é lançada
        assertThrows(RuntimeException.class, () -> {
            leaveRequestService.submitRequest(request);
        }, "Deve lançar exceção porque o funcionário não existe.");
    }
}