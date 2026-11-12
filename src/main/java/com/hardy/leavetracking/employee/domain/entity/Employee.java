package com.hardy.leavetracking.employee.domain.entity;

import java.time.LocalDate;
import com.hardy.leavetracking.employee.domain.enums.Department;

/**
 * Entidade que representa um funcionário no sistema Leave Tracking.
 * Contém os dados pessoais, profissionais e o departamento a que pertence.
 */
public class Employee {

    /**
     * Identificador único do funcionário (gerado automaticamente).
     * É a identidade da Entity.
     */
    private int id;

    /**
     * Primeiro nome do funcionário.
     */
    private String firstName;

    /**
     * Último nome (apelido) do funcionário.
     */
    private String lastName;

    /**
     * Cargo/função que o funcionário desempenha na empresa.
     */
    private String position;

    /**
     * Salário base do funcionário.
     */
    private double salary;

    /**
     * Data em que o funcionário começou a trabalhar na empresa.
     */
    private LocalDate startDate;

    /**
     * Departamento ao qual o funcionário está associado.
     */
    private Department department;

    /**
     * Construtor vazio (No-args).
     * Necessário para frameworks e serialização.
     */
    public Employee() {
    }

    /**
     * Construtor com parâmetros.
     * Cria um funcionário válido com os dados essenciais.
     * 
     * @param id O identificador único.
     * @param firstName O primeiro nome.
     * @param lastName O último nome.
     * @param position O cargo.
     * @param salary O salário.
     * @param startDate A data de início.
     * @param department O departamento.
     */
    public Employee(int id, String firstName, String lastName, String position, double salary, LocalDate startDate, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.startDate = startDate;
        this.department = department;
    }

    /**
     * Retorna o ID do funcionário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do funcionário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o primeiro nome.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Define o primeiro nome.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retorna o último nome.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Define o último nome.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retorna o cargo.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Define o cargo.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Retorna o salário.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Define o salário.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Retorna a data de início.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Define a data de início.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retorna o departamento.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Define o departamento.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Representação em texto do funcionário.
     */
    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s %s', position='%s', salary=%.2f, startDate=%s, department=%s}",
                id, firstName, lastName, position, salary, startDate, department);
    }
}