package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        //throw new UnsupportedOperationException();

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customerService.save(customer);

        CustomerDTO cd = new CustomerDTO();
        BeanUtils.copyProperties(customerDTO, cd);
        cd.setId(customer.getId());
        customer.getPets().stream().forEach(p -> cd.getPetIds().add(p.getId()));

        return cd;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        //throw new UnsupportedOperationException();
        List<CustomerDTO> cds = new ArrayList<>();
        List<Customer> customers = customerService.findAll();
        for (Customer c : customers) {
            CustomerDTO cd = new CustomerDTO();
            BeanUtils.copyProperties(c, cd);
            c.getPets().stream().forEach(p -> cd.getPetIds().add(p.getId()));
            cds.add(cd);
        }
        return cds;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();
        Customer customer = customerService.findByPetId(petId);
        CustomerDTO cd = new CustomerDTO();
        BeanUtils.copyProperties(customer, cd);        
        customer.getPets().stream().forEach(p -> cd.getPetIds().add(p.getId()));
        return cd;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        //throw new UnsupportedOperationException();

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeService.save(employee);

        EmployeeDTO ed = new EmployeeDTO();
        BeanUtils.copyProperties(employee, ed);

        return ed;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        //throw new UnsupportedOperationException();

        Employee employee = employeeService.findById(employeeId);

        EmployeeDTO ed = new EmployeeDTO();
        BeanUtils.copyProperties(employee, ed);

        return ed;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
        employeeService.setDaysAvailable(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        //throw new UnsupportedOperationException();
        List<Employee> employees = employeeService.findEmployeesForService(employeeRequestDTO);
        List<EmployeeDTO> eds = new ArrayList<>();
        employees.stream().forEach(emp -> {
            EmployeeDTO ed = new EmployeeDTO();
            BeanUtils.copyProperties(emp, ed);
            eds.add(ed);
        });
        return eds;
    }

}
