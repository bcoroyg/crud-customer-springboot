package com.example.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("customer", customerNew);
        response.put("message", "El cliente se ha creado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        Customer customer = null;
        Map<String, Object> response = new HashMap<>();

        try {
            customer = service.findById(id);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage());
            response.put("message", "Error al obtener un cliete");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (customer == null) {
            response.put("message", "El cliente con ID: ".concat(id.toString()).concat(" no existe."));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer customerDB = service.findById(id);
        Customer customerEdit = null;

        Map<String, Object> response = new HashMap<>();

        if (customerDB == null) {
            response.put("message", "El cliente con ID:".concat(id.toString()).concat(" no existe."));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            customerDB.setFirstname(customer.getFirstname());
            customerDB.setLastname(customer.getLastname());

            customerEdit = service.save(customerDB);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage());
            response.put("message", "Error al actualizar un cliente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Usuario actulizado con exito");
        response.put("customer", customerEdit);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
