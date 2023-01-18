package com.example.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer customerNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            customerNew = service.save(customer);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage());
            response.put("message", "Error al realizar insert en la base de datos");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("customer", customerNew);
        response.put("message", "El cliente se ha creado con exito");
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }
}
