package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        petRepository.save(pet);

        Optional<Customer> customer = customerRepository.findById(petDTO.getOwnerId());
        if (customer.isPresent()) {
            customer.get().getPets().add(pet);
            pet.setCustomer(customer.get());
        }

        return pet;
    }

    public Pet findById(Long petId) {
        Optional<Pet> petOpt = petRepository.findById(petId);
        if (petOpt.isPresent()) return petOpt.get();
        else throw new PetNotFoundException();
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByCustomerId(Long customerId) {
        return petRepository.findAllByOwnerId(customerId);
    }
    
}
