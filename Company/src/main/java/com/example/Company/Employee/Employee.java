package com.example.Company.Employee;

import com.example.Company.Benefit.Benefit;
import com.example.Company.Position.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    //mappedBy, shown in benefit class, which is the hashset
    @ManyToMany(mappedBy = "employeesWithBenefit")
    private Set<Benefit> benefits = new HashSet<>();


    //ManyToOne, many employees to one position
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="position_id", referencedColumnName = "id")
    private Position position;

    public Employee(String name){
        this.name = name;
    }

    public Employee() {

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

    public Set<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void addBenefit(Benefit benefit) {this.benefits.add(benefit);}

    public void deleteBenefit(Benefit benefit) { this.benefits.remove(benefit);}

}
