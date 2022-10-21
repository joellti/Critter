package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where :dayOfWeek member of e.daysAvailable")
    public List<Employee> findByDayofWeek(DayOfWeek dayOfWeek);
}