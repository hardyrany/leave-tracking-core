package com.hardy.leavetracking.employee.application.service;

import com.hardy.leavetracking.employee.domain.entity.Employee;
import com.hardy.leavetracking.employee.infra.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Serviço responsável pelas regras de negócio relacionadas aos Funcionários.
 * Orquestra a criação e gestão de funcionários usando o repositório.
 */
public class EmployeeService {

    /**
     * Repositório injetado via construtor.
     */
    private final EmployeeRepository repository;

    /**
     * Construtor que injeta a dependência do repositório.
     * @param repository O repositório de funcionários.
     */
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Cria e regista um novo funcionário no sistema.
     * Valida os dados e gera o ID automaticamente.
     * 
     * @param firstName O primeiro nome.
     * @param lastName O último nome.
     * @param position O cargo.
     * @param salary O salário.
     * @param startDate A data de início.
     * @param department O departamento.
     * @return O funcionário criado.
     */
    public Employee createEmployee(String firstName, String lastName, String position, double salary, LocalDate startDate, com.hardy.leavetracking.employee.domain.enums.Department department) {
        // Aqui podes adicionar validações de negócio no futuro (ex: salário negativo)
        
        int newId = repository.getNextId();
        Employee employee = new Employee(newId, firstName, lastName, position, salary, startDate, department);
        
        repository.save(employee);
        return employee;
    }

    /**
     * Procura um funcionário pelo ID.
     * 
     * @param id O ID do funcionário.
     * @return O funcionário encontrado, ou null se não existir.
     */
    public Employee getEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Retorna a lista de todos os funcionários.
     * 
     * @return Lista de funcionários.
     */
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}