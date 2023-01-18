package com.example.apirest.services;

import java.util.List;

import com.example.apirest.models.Customer;

public interface CustomerService {
    public List<Customer> findAll();

    public Customer findById(Long id);

    public Customer save(Customer customer);

    public void delete(Long id);

}
