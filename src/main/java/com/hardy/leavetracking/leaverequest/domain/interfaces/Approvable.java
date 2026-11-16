package com.hardy.leavetracking.leaverequest.domain.interfaces;

/**
 * Interface que define as capacidades de aprovação e rejeição de um pedido.
 * Qualquer classe que implemente esta interface garante que possui o fluxo de aprovação.
 */
public interface Approvable {

    /**
     * Aprova o pedido.
     * @param approverName O nome de quem está a aprovar.
     * @return true se aprovado com sucesso, false caso contrário.
     */
    boolean approve(String approverName);

    /**
     * Rejeita o pedido.
     * @param approverName O nome de quem está a rejeitar.
     * @param reason O motivo da rejeição.
     * @return true se rejeitado com sucesso, false caso contrário.
     */
    boolean deny(String approverName, String reason);
}