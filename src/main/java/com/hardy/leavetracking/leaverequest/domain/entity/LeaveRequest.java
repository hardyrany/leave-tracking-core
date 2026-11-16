package com.hardy.leavetracking.leaverequest.domain.entity;

import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.leaverequest.domain.interfaces.Approvable;
import java.time.LocalDate;

/**
 * Classe abstrata que representa um pedido de licença genérico.
 * Define a estrutura comum a todos os tipos de licença.
 */
public abstract class LeaveRequest implements Approvable {

    /**
     * Identificador único do pedido.
     */
    private int id;

    /**
     * O funcionário que fez o pedido.
     */
    private Employee employee;

    /**
     * Data de início da licença.
     */
    private LocalDate startDate;

    /**
     * Data de fim da licença.
     */
    private LocalDate endDate;

    /**
     * Estado atual do pedido (ex: PENDING, APPROVED, DENIED).
     */
    private String status;

    /**
     * Motivo ou justificação do pedido.
     */
    private String reason;

    /**
     * Construtor vazio (No-args).
     */
    public LeaveRequest() {
    }

    /**
     * Construtor com parâmetros.
     */
    public LeaveRequest(int id, Employee employee, LocalDate startDate, LocalDate endDate, String reason) {
        this.id = id;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "PENDING";
        this.reason = reason;
    }

    /**
     * Método abstrato que obriga as subclasses a calcular a duração da licença.
     * 
     * @return O número de dias de licença.
     */
    public abstract int calculateLeaveDays();

    /**
     * Implementação padrão de aprovação.
     */
    @Override
    public boolean approve(String approverName) {
        if ("PENDING".equals(this.status)) {
            this.status = "APPROVED";
            return true;
        }
        return false;
    }

    /**
     * Implementação padrão de rejeição.
     */
    @Override
    public boolean deny(String approverName, String reason) {
        if ("PENDING".equals(this.status)) {
            this.status = "DENIED";
            this.reason = reason;
            return true;
        }
        return false;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}