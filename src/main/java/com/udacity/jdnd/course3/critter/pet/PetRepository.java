package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select p from Pet p join p.customer c where c.id = :id")
    List<Pet> findAllByOwnerId(Long id);
}
