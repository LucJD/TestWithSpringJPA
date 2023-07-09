package com.example.Company.Benefit;

import com.example.Company.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="employees_with_benefit",
            joinColumns = @JoinColumn(name="benefit_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    private Set<Employee> employeesWithBenefit = new HashSet<>();


    public Benefit(String name){
        this.name = name;
    }

    public Benefit() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployeesWithBenefit() {
        return employeesWithBenefit;
    }

    public void setEmployeesWithBenefit(Set<Employee> employeesWithBenefit) {
        this.employeesWithBenefit = employeesWithBenefit;
    }

    public void addEmployeeWithBenefit(Employee employee) {
        this.employeesWithBenefit.add(employee);
    }
}
