package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s join s.pets p where p.id = :id")
    List<Schedule> findByPetId(long id);

    @Query("select s from Schedule s join s.employees e where e.id = :id")
    List<Schedule> findByEmployeeId(long id);

    @Query("select s from Schedule s join s.pets p join p.customer c where c.id = :id")
    List<Schedule> findByOwnerId(long id);
}
