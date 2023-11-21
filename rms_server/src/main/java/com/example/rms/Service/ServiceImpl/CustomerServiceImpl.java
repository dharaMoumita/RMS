package com.example.rms.Service.ServiceImpl;

import com.example.rms.DAO.CustomerRepo;
import com.example.rms.DTO.CustomerDTO;
import com.example.rms.Entity.Auth.Customer;
import com.example.rms.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer=CustomerDTOtoCustomer(customerDTO);
        customerRepo.save(customer);
        return CustomertoCustomerDTO(customer);
    }

    @Override
    public CustomerDTO findCustomer(int id) {
        Customer customer=customerRepo.findById(id).get();
        return CustomertoCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {
        List<Customer> customerList=customerRepo.findAll();
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        customerList.forEach((customer)->{
            customerDTOList.add(CustomertoCustomerDTO(customer));
        });
        return customerDTOList;
    }
    public CustomerDTO findCustomerByPhone(String phone){
        List<Customer> customerList=customerRepo.findByPhone(phone);
        if(customerList.isEmpty()){
            return null;
        }else {
            return CustomertoCustomerDTO(customerList.get(0));
        }
    }

    @Override
    public CustomerDTO deleteCustomer(int id) {
        Customer customer=customerRepo.findById(id).get();
        customerRepo.deleteById(id);
        return CustomertoCustomerDTO(customer);
    }

    @Override
    public CustomerDTO update(int id, CustomerDTO customerDTO) {
        Customer customer=CustomerDTOtoCustomer(customerDTO);
        customer.setId(id);
        customerRepo.save(customer);
        return CustomertoCustomerDTO(customer);
    }


    private Customer CustomerDTOtoCustomer(CustomerDTO customerDTO){
        Customer customer=new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setBirthdate(customerDTO.getBirthdate());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    private CustomerDTO CustomertoCustomerDTO(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setBirthdate(customer.getBirthdate());
        customerDTO.setId(customer.getId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
}
