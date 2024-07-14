package com.attraya.controller;

import com.attraya.dto.CustomerDto;
import com.attraya.entity.Customer;
import com.attraya.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public List<Customer> saveCustomers(@RequestBody List<Customer> customers){
        return customerService.addNewCustomers(customers);
    }

    @GetMapping
    public List<CustomerDto> fetchAllCustomers(){
        return customerService.getCustomers();
    }
}
