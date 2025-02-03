package com.example.demo.controller;

import com.example.demo.pojo.Customer;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<String> addCustomer(@RequestBody @Valid Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Customer added successfully");
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id){
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}