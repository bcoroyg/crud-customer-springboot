package com.example.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.models.Customer;
import com.example.apirest.services.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("")
    public List<Customer> getCustomer() {
        return service.findAll();
    }

    @PostMapping("")
    public void createCustomer(@RequestBody Customer customer) {
        service.save(customer);
    }
}
