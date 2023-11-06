package com.example.rms.Controller;

import com.example.rms.DTO.CustomerDTO;
import com.example.rms.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable int id){
        return  customerService.findCustomer(id);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomer(){
        return customerService.findAllCustomer();
    }

    @DeleteMapping("/customer/{id}")
    public CustomerDTO deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/customer/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO,@PathVariable int id){
        return customerService.update(id,customerDTO);
    }
}
