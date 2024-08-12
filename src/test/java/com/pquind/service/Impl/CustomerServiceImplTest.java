package com.pquind.service.Impl;

import com.pquind.entity.ProductEntity;
import com.pquind.repository.CustomerRepository;
import com.pquind.dto.CustomerDto;
import com.pquind.entity.CustomerEntity;
import com.pquind.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validationNameCreate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("j")
                .customerSurname("Romero")
                .build();
        customerService.createCustomer(customerDto);
    }

    @Test
    void validationSurnameCreate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("R")
                .build();
        customerService.createCustomer(customerDto);
    }

    @Test
    void nameNullCreate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName(null)
                .customerSurname("Romero")
                .build();
        customerService.createCustomer(customerDto);
    }

    @Test
    void surnameNullCreate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname(null)
                .build();
        customerService.createCustomer(customerDto);
    }

    @Test
    void EmailValidatorInvalidCreate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romer")
                .customerEmail("j")
                .build();
        customerService.createCustomer(customerDto);
    }

    @Test
    void AgeInvalid() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjjjj@jjjj.com")
                .dateOfBirth("2021-11-26")
                .build();
        customerService.createCustomer(customerDto);
    }

    @Test
    void createAccount(){
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjjjj@jjjj.com")
                .dateOfBirth("2002-11-26")
                .build();
        CustomerEntity saveInformation = CustomerMapper.dtoToCustomerEntity(customerDto);
        Mockito.when(customerRepository.save(saveInformation)).thenReturn(saveInformation);
        customerService.createCustomer(customerDto);
    }

    @Test
    void validationNameUpdate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("j")
                .customerSurname("Romero")
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void validationSurnameUpdate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("R")
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void nameNullUpdate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName(null)
                .customerSurname("Romero")
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void surnameNullUpdate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname(null)
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void EmailValidatorInvalidUpdate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("j")
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void ageInvalidUpdate() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjjjj@jjjj.com")
                .dateOfBirth("2021-11-26")
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjjjj@jjjj.com")
                .dateOfBirth("2002-11-26")
                .build();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdentificationNumber("1006069291");
        Mockito.when(
                customerRepository.findCustomerEntityByIdentificationNumber(customerEntity.getIdentificationNumber()))
                .thenReturn(Optional.of(customerEntity));
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void updateCustomerIsPresent() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjjjj@jjjj.com")
                .dateOfBirth("2002-11-26")
                .identificationNumber("1006069291")
                .build();
        Optional<CustomerEntity> customerEntity = customerRepository
                .findCustomerEntityByIdentificationNumber(customerDto.getIdentificationNumber());
        Mockito.when(customerRepository.findCustomerEntityByIdentificationNumber(customerDto.getIdentificationNumber()))
                .thenReturn(customerEntity);
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void birthDayEmpty() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Juan")
                .customerSurname("Romero")
                .customerEmail("jjjjj@jjjj.com")
                .dateOfBirth("")
                .build();
        customerService.updateCustomer("1006069291", customerDto);
    }

    @Test
    void deleteOptionalIsPresent() {
        CustomerEntity customerEntity = new CustomerEntity();
        Optional<CustomerEntity> customerEntity2 = customerRepository
                .findCustomerEntityByIdentificationNumber(customerEntity.getIdentificationNumber());
        customerService.deleteCustomer("1006069291");
    }

    @Test
    void deleteProductEntitiesEmpty() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setProductEntities(List.of());
        customerEntity.setIdentificationNumber("1006069291");
        Mockito.when(
                customerRepository.findCustomerEntityByIdentificationNumber(customerEntity.getIdentificationNumber()))
                .thenReturn(Optional.of(customerEntity));
        customerService.deleteCustomer("1006069291");
    }

    @Test
    void deleteProductEntitiesNoEmpty() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAccountNumber("53");
        List<ProductEntity> product = new ArrayList<>();
        product.add(productEntity);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setProductEntities(product);
        customerEntity.setIdentificationNumber("1006069291");
        Mockito.when(
                customerRepository.findCustomerEntityByIdentificationNumber(customerEntity.getIdentificationNumber()))
                .thenReturn(Optional.of(customerEntity));
        customerService.deleteCustomer("1006069291");
    }

}
