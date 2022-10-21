package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        //throw new UnsupportedOperationException();
        Schedule s = scheduleService.createSchedule(scheduleDTO);

        ScheduleDTO sd = new ScheduleDTO();
        sd.setEmployeeIds(new ArrayList<>());
        sd.setPetIds(new ArrayList<>());
        BeanUtils.copyProperties(s, sd);
        s.getEmployees().stream().forEach(e -> sd.getEmployeeIds().add(e.getId()));
        s.getPets().stream().forEach(p -> sd.getPetIds().add(p.getId()));

        return sd;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        //throw new UnsupportedOperationException();

        List<ScheduleDTO> sds = new ArrayList<>();
        scheduleService.findAll().stream().forEach(s -> {
            ScheduleDTO sd = new ScheduleDTO();
            sd.setEmployeeIds(new ArrayList<>());
            sd.setPetIds(new ArrayList<>());
            BeanUtils.copyProperties(s, sd);
            s.getEmployees().stream().forEach(e -> sd.getEmployeeIds().add(e.getId()));
            s.getPets().stream().forEach(p -> sd.getPetIds().add(p.getId()));
            sds.add(sd);
        });
        return sds;

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();
        List<ScheduleDTO> sds = new ArrayList<>();
        scheduleService.findByPetId(petId).stream().forEach(s -> {
            ScheduleDTO sd = new ScheduleDTO();
            sd.setEmployeeIds(new ArrayList<>());
            sd.setPetIds(new ArrayList<>());
            BeanUtils.copyProperties(s, sd);
            s.getEmployees().stream().forEach(e -> sd.getEmployeeIds().add(e.getId()));
            s.getPets().stream().forEach(p -> sd.getPetIds().add(p.getId()));
            sds.add(sd);
        });
        return sds;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
        List<ScheduleDTO> sds = new ArrayList<>();
        scheduleService.findByEmployeeId(employeeId).stream().forEach(s -> {
            ScheduleDTO sd = new ScheduleDTO();
            sd.setEmployeeIds(new ArrayList<>());
            sd.setPetIds(new ArrayList<>());
            BeanUtils.copyProperties(s, sd);
            s.getEmployees().stream().forEach(e -> sd.getEmployeeIds().add(e.getId()));
            s.getPets().stream().forEach(p -> sd.getPetIds().add(p.getId()));
            sds.add(sd);
        });
        return sds;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        //throw new UnsupportedOperationException();
        List<ScheduleDTO> sds = new ArrayList<>();
        scheduleService.findByOwnerId(customerId).stream().forEach(s -> {
            ScheduleDTO sd = new ScheduleDTO();
            sd.setEmployeeIds(new ArrayList<>());
            sd.setPetIds(new ArrayList<>());
            BeanUtils.copyProperties(s, sd);
            s.getEmployees().stream().forEach(e -> sd.getEmployeeIds().add(e.getId()));
            s.getPets().stream().forEach(p -> sd.getPetIds().add(p.getId()));
            sds.add(sd);
        });
        return sds;
    }
}
