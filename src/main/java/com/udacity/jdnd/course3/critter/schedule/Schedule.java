package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany
    @JoinTable(
        name = "employee_schedule",
        joinColumns = { @JoinColumn(name = "schedule_id")},
        inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "pet_schedule",
        joinColumns = { @JoinColumn(name = "schedule_id")},
        inverseJoinColumns = { @JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets  = new ArrayList<>();;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
