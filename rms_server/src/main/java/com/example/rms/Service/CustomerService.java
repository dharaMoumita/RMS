package com.example.rms.Service;

import com.example.rms.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO addCustomer(CustomerDTO customerDTO);
    public CustomerDTO findCustomer(int id);
    public List<CustomerDTO> findAllCustomer();
    public CustomerDTO deleteCustomer(int id);
    public CustomerDTO update(int id,CustomerDTO customerDTO);
}
