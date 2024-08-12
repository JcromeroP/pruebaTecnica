package com.pquind.service;

import com.pquind.dto.CustomerDto;

public interface CustomerService {

    Object createCustomer(CustomerDto customerDto);

    Object updateCustomer(String identificationNumber, CustomerDto customerDto);

    String deleteCustomer(String identificationNumber);
}
