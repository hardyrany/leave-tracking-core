package com.hardy.leavetracking.leaverequest.application.service;

import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.employee.infra.repository.EmployeeRepository;
import com.hardy.leavetracking.leaverequest.domain.entity.LeaveRequest;
import com.hardy.leavetracking.leaverequest.infra.repository.LeaveRequestRepository;

import java.util.List;

/**
 * Serviço responsável pelas regras de negócio dos Pedidos de Licença.
 */
public class LeaveRequestService {

    /**
     * Repositório de pedidos de licença.
     */
    private final LeaveRequestRepository leaveRequestRepository;

    /**
     * Repositório de funcionários (necessário para validar se o funcionário
     * existe).
     */
    private final EmployeeRepository employeeRepository;

    /**
     * Construtor que injeta as dependências.
     */
    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Submete um novo pedido de licença.
     * Valida se o funcionário associado ao pedido existe no sistema.
     * 
     * @param request O pedido de licença (Férias, Doença, Maternidade).
     * @return O pedido submetido com o ID gerado.
     * @throws RuntimeException Se o funcionário não existir.
     */
    public LeaveRequest submitRequest(LeaveRequest request) {
        // Valida se o funcionário existe
        Employee employee = employeeRepository.findById(request.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Gera o novo ID e associa ao pedido
        int newId = leaveRequestRepository.getNextId();
        request.setId(newId);

        // Guarda no repositório
        leaveRequestRepository.save(request);
        return request;
    }

    /**
     * Retorna todos os pedidos de licença.
     */
    public List<LeaveRequest> getAllRequests() {
        return leaveRequestRepository.findAll();
    }

    /**
     * Aprova um pedido de licença pendente.
     */
    public boolean approveRequest(int id, String approverName) {
        LeaveRequest request = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new com.hardy.leavetracking.infra.exception.LeaveRequestNotFoundException(
                        String.valueOf(id)));

        boolean success = request.approve(approverName);

        if (success) {
            leaveRequestRepository.save(request);
        }
        return success;
    }

    /**
     * Rejeita um pedido de licença pendente.
     */
    public boolean denyRequest(int id, String approverName, String reason) {
        LeaveRequest request = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new com.hardy.leavetracking.infra.exception.LeaveRequestNotFoundException(
                        String.valueOf(id)));

        boolean success = request.deny(approverName, reason);

        if (success) {
            leaveRequestRepository.save(request);
        }
        return success;
    }
}