package com.hardy.leavetracking.leaverequest.infra.repository;

import com.hardy.leavetracking.leaverequest.domain.entity.LeaveRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositório responsável por simular a persistência de dados dos Pedidos de Licença.
 * Em Pure Java, usamos um HashMap em memória.
 */
public class LeaveRequestRepository {

    /**
     * Mapa que simula a tabela da base de dados (ID -> Pedido de Licença).
     */
    private final Map<Integer, LeaveRequest> database = new HashMap<>();

    /**
     * Contador para gerar IDs únicos e sequenciais.
     */
    private int nextId = 1;

    /**
     * Gera o próximo ID disponível.
     * @return O próximo número inteiro sequencial.
     */
    public int getNextId() {
        return nextId++;
    }

    /**
     * Guarda um pedido na "base de dados".
     * @param request O pedido a ser guardado.
     */
    public void save(LeaveRequest request) {
        database.put(request.getId(), request);
    }

    /**
     * Procura um pedido pelo seu ID.
     * @param id O ID do pedido.
     * @return Um Optional contendo o pedido, ou vazio se não existir.
     */
    public Optional<LeaveRequest> findById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retorna todos os pedidos guardados.
     * @return Uma lista com todos os pedidos.
     */
    public List<LeaveRequest> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Remove um pedido da "base de dados" pelo ID.
     * @param id O ID do pedido a ser removido.
     */
    public void deleteById(int id) {
        database.remove(id);
    }
}