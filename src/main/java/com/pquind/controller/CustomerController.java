package com.pquind.controller;

import org.springframework.web.bind.annotation.*;

import com.pquind.dto.CustomerDto;
import com.pquind.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public Object createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @PatchMapping("/update/{identificationNumber}")
    public Object updateCustomer(@PathVariable String identificationNumber, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(identificationNumber, customerDto);
    }

    @DeleteMapping("/delete/{identificationNumber}")
    public String deleteCustomer(@PathVariable String identificationNumber) {
        return customerService.deleteCustomer(identificationNumber);
    }

}
