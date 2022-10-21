package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    public Schedule createSchedule(ScheduleDTO sd) {

        Schedule s = new Schedule();

        sd.getEmployeeIds().stream().forEach(id -> {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) s.getEmployees().add(employee.get());
        });
        sd.getPetIds().stream().forEach(id -> {
            Optional<Pet> pet = petRepository.findById(id);
            if (pet.isPresent()) s.getPets().add(pet.get());
        });
        s.setDate(sd.getDate());
        s.setActivities(sd.getActivities());
        scheduleRepository.save(s);

        return s;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findByPetId(long id) {
        return scheduleRepository.findByPetId(id);
    }

    public List<Schedule> findByEmployeeId(long id) {
        return scheduleRepository.findByEmployeeId(id);
    }

    public List<Schedule> findByOwnerId(long id) {
        return scheduleRepository.findByOwnerId(id);
    }
    
}
