package com.udacity.jdnd.course3.critter.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    @Query("select c from Customer c join c.pets p where p.id = :id")
    Optional<Customer> findByPetId(long id);
}
