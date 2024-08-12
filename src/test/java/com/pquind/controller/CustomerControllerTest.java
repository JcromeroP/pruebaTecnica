package com.pquind.controller;

import com.pquind.service.CustomerService;
import com.pquind.dto.CustomerDto;
import com.pquind.enums.IdentificationType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jcr@col.com")
                .dateOfBirth("2002-11-26")
                .identificationTypeEnum(IdentificationType.CC)
                .build();
        customerController.createCustomer(customerDto);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jcr@col.com")
                .dateOfBirth("2002-11-26")
                .identificationTypeEnum(IdentificationType.CC)
                .build();
        customerController.updateCustomer(customerDto.getIdentificationNumber(), customerDto);

    }

    @Test
    void deleteCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjj@jr.com")
                .dateOfBirth("2002-11-26")
                .identificationTypeEnum(IdentificationType.CC)
                .build();
        customerController.deleteCustomer(customerDto.getIdentificationNumber());
    }

}
