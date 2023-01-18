package com.example.apirest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.apirest.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
