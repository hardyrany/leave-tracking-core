package com.hardy.leavetracking.employee.infra.repository;

import com.hardy.leavetracking.employee.domain.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositório responsável por simular a persistência de dados dos Funcionários.
 * Em Pure Java, usamos um HashMap em memória.
 * No Spring Boot, esta classe será substituída por uma interface que estende JpaRepository.
 */
public class EmployeeRepository {

    /**
     * Mapa que simula a tabela da base de dados (ID -> Funcionário).
     */
    private final Map<Integer, Employee> database = new HashMap<>();

    /**
     * Contador para gerar IDs únicos e sequenciais para os funcionários.
     * A responsabilidade de gerar o ID pertence ao repositório (simulando o AUTO_INCREMENT da base de dados).
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
     * Guarda um funcionário na "base de dados".
     * Se o funcionário já existir (mesmo ID), ele é atualizado.
     * 
     * @param employee O funcionário a ser guardado.
     */
    public void save(Employee employee) {
        database.put(employee.getId(), employee);
    }

    /**
     * Procura um funcionário pelo seu ID.
     * 
     * @param id O ID do funcionário.
     * @return Um Optional contendo o funcionário, ou vazio se não existir.
     */
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retorna todos os funcionários guardados.
     * 
     * @return Uma lista com todos os funcionários.
     */
    public List<Employee> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Remove um funcionário da "base de dados" pelo ID.
     * 
     * @param id O ID do funcionário a ser removido.
     */
    public void deleteById(int id) {
        database.remove(id);
    }
}