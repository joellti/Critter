package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee findById(Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) return employeeOpt.get();
        else throw new EmployeeNotFoundException();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable, long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) employeeOpt.get().setDaysAvailable(daysAvailable);
        else throw new EmployeeNotFoundException();
    }
    
    public List<Employee> findEmployeesForService(EmployeeRequestDTO ed) {
        List<Employee> employees = employeeRepository.findByDayofWeek(ed.getDate().getDayOfWeek());
        for (Employee employee : employees) {
            if (!employee.getSkills().containsAll(ed.getSkills())) { 
                employees.remove(employee);
                System.out.println("Remove it");
            } else {
                System.out.println("Keep it");
            }
        }
        return employees;
    }

}
