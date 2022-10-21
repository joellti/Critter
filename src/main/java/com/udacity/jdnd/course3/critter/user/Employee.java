package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "employee_schedule",
        joinColumns = { @JoinColumn(name = "employee_id")},
        inverseJoinColumns = { @JoinColumn(name = "schedule_id")}
    )
    private List<Schedule> schedules;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Schedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    public Set<EmployeeSkill> getSkills() {
        return skills;
    }
    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
