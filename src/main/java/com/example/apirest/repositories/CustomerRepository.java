package com.example.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apirest.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
