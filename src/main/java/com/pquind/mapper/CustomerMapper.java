package com.pquind.mapper;

import java.time.LocalDateTime;

import com.pquind.dto.CustomerDto;
import com.pquind.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity dtoToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdentificationTypeEnum(customerDto.getIdentificationTypeEnum());
        customerEntity.setIdentificationNumber(customerDto.getIdentificationNumber());
        customerEntity.setCustomerName(customerDto.getCustomerName());
        customerEntity.setCustomerSurname(customerDto.getCustomerSurname());
        customerEntity.setCustomerEmail(customerDto.getCustomerEmail());
        customerEntity.setDateOfBirth(customerDto.getDateOfBirth());
        customerEntity.setDateCreated(LocalDateTime.now());
        customerEntity.setDateModified(null);
        return customerEntity;
    }

}
