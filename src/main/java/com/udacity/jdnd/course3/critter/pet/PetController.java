package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        //throw new UnsupportedOperationException();

        Pet pet = petService.savePet(petDTO);

        PetDTO pd = new PetDTO();
        BeanUtils.copyProperties(pet, pd);
        pd.setId(pet.getId());
        pd.setOwnerId(pet.getCustomer().getId());

        return pd;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();

        Pet pet = petService.findById(petId);

        PetDTO pd = new PetDTO();
        BeanUtils.copyProperties(pet, pd);
        pd.setOwnerId(pet.getCustomer().getId());

        return pd;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        //throw new UnsupportedOperationException();

        List<Pet> pets = petService.findAll();

        List<PetDTO> pds = new ArrayList<>();
        pets.stream().forEach((pet) -> {
            PetDTO pd = new PetDTO();
            BeanUtils.copyProperties(pet, pd);
            pd.setOwnerId(pet.getCustomer().getId());
            pds.add(pd);
        });
        
        return pds;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        //throw new UnsupportedOperationException();

        List<Pet> pets = petService.findAllByCustomerId(ownerId);

        List<PetDTO> pds = new ArrayList<>();
        pets.stream().forEach((pet) -> {
            PetDTO pd = new PetDTO();
            BeanUtils.copyProperties(pet, pd);
            pd.setOwnerId(pet.getCustomer().getId());
            pds.add(pd);            
        });

        return pds;
    }
}
