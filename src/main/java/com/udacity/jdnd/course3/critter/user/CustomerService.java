package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer findById(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) return customerOpt.get();
        else throw new CustomerNotFoundException();
    }

    public Customer findByPetId(Long petId) {
        Optional<Customer> customerOpt = customerRepository.findByPetId(petId);
        if (customerOpt.isPresent()) return customerOpt.get();
        else throw new CustomerNotFoundException();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
