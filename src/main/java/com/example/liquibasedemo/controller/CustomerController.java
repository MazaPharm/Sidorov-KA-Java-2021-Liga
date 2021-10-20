package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Enumerates all Customer entities")
    @GetMapping
    public List<CustomerDTO> enumerate() {
        return customerService.enumerate();
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @ApiOperation(value = "Retrieves Customer entity by it ID")
    @GetMapping("/{id}")
    public Customer get(@PathVariable("id")String id) {
        return customerService.get(id);
    }

    @DeleteMapping("/{id}")
    public List<Customer> delete(@PathVariable("id") UUID id){
       return customerService.delete(id);
    }
    @PostMapping("/create")
    public String create(@RequestBody Customer customer){
       return customerService.create(customer);
    }

    //TODO: добавить и проаннотировать операции удаления сущности Customer, и создания новой пустой сущности Customer
}
