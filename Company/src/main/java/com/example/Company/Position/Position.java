package com.example.Company.Position;

import com.example.Company.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    //OneToMany, one position to many employees (many employees have one position, one position has many employees)
    @JsonIgnore
    @OneToMany(mappedBy = "position")
    private Set<Employee> employeesWithPosition = new HashSet<>();


    public Position(String name){
        this.name = name;
    }

    public Position() {

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

    public Set<Employee> getEmployeesWithPosition() {
        return employeesWithPosition;
    }

    public void setEmployeesWithPosition(Set<Employee> employeesWithPosition) {
        this.employeesWithPosition = employeesWithPosition;
    }

    public void addEmployeesWithPosition(Employee employee){
        this.employeesWithPosition.add(employee);
    }
    public void removeEmployeesWithPosition(Employee employee){
        this.employeesWithPosition.remove(employee);
    }
}
