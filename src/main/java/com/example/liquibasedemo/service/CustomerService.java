package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private  CustomerRepository customerRepository;

    public List<CustomerDTO> enumerate() {

        List<Customer> customers =  customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer: customers) {
            customerDTOS.add(new CustomerDTO(customer));
        }
        return customerDTOS;
    }

    public Customer save( Customer customer) {
        customer.setId(UUID.randomUUID());
        return customerRepository.save(customer);
    }

    public Customer get(String id) {
        return customerRepository
                .findById(
                        UUID.fromString(id)
                )
                .get();
    }

    public List<Customer> delete(@PathVariable("id") UUID id){
        customerRepository.deleteById(id);
        return customerRepository.findAll();
    }


    public String create(Customer customer){
        customer.setId(UUID.randomUUID());
        customerRepository.save(customer);
        return "Created";
    }

}
