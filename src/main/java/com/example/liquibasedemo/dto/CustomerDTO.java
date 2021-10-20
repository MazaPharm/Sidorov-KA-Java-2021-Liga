package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.Customer;

import java.util.UUID;

public class CustomerDTO {
    private String name;
    private UUID id;

    public CustomerDTO(Customer customer) {
        this.name=customer.getName();
        this.id=customer.getId();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
