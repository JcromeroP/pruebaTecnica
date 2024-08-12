package com.pquind.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.pquind.constants.MessageApplication;
import com.pquind.dto.CustomerDto;
import com.pquind.entity.CustomerEntity;
import com.pquind.mapper.CustomerMapper;
import com.pquind.repository.CustomerRepository;
import com.pquind.service.CustomerService;
import com.pquind.validation.EmailValidator;

import lombok.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Object createCustomer(CustomerDto customerDto) {
        if (!(isInvalidUseName(customerDto) || isInvalidUseSurname(customerDto))){
            if (EmailValidator.isValidEmail(customerDto.getCustomerEmail())){
                Boolean ageValid = validateAgeCustomer(customerDto.getDateOfBirth());
                if (!ageValid) {
                    return MessageApplication.NO_MINORS;
                }else {
                    customerDto.setDateCreated(LocalDateTime.now());
                    customerDto.setDateModified(null);
                    CustomerEntity saveInformation = CustomerMapper.dtoToCustomerEntity(customerDto);
                    customerRepository.save(saveInformation);
                    return MessageApplication.ACCOUNT_CREATED;
                }
            }
            return MessageApplication.STRUCTURE_EMAIL;
        }
        return MessageApplication.LENGTH_NAME_SURNAME;
    }
    @Override
    public Object updateCustomer(String identificationNumber, CustomerDto customerDto) {
        Optional<CustomerEntity> customerEntity = customerRepository.findCustomerEntityByIdentificationNumber(identificationNumber);
        if (!(isInvalidUseName(customerDto) || isInvalidUseSurname(customerDto))){
            if (EmailValidator.isValidEmail(customerDto.getCustomerEmail())){
                if (!(customerDto.getDateOfBirth().isEmpty())){
                    Boolean ageValid = validateAgeCustomer(customerDto.getDateOfBirth());
                    if (!ageValid){
                        return MessageApplication.NO_MINORS;
                    }
                    if (customerEntity.isPresent()){
                        customerEntity.get().setIdentificationTypeEnum(customerDto.getIdentificationTypeEnum());
                        customerEntity.get().setIdentificationNumber(customerDto.getIdentificationNumber());
                        customerEntity.get().setCustomerName(customerDto.getCustomerName());
                        customerEntity.get().setCustomerSurname(customerDto.getCustomerSurname());
                        customerEntity.get().setCustomerEmail(customerDto.getCustomerEmail());
                        customerEntity.get().setDateOfBirth(customerDto.getDateOfBirth());
                        customerEntity.get().setDateModified(LocalDateTime.now());
                        customerRepository.save(customerEntity.get());
                        return MessageApplication.CLIENT_UPDATE;
                    }
                }
            }
            return MessageApplication.STRUCTURE_EMAIL;
        }
        return MessageApplication.LENGTH_NAME_SURNAME;
    }
    @Override
    public String deleteCustomer(String identificationNumber) {
        Optional<CustomerEntity> customerEntity = customerRepository.findCustomerEntityByIdentificationNumber(identificationNumber);
        if (customerEntity.isPresent()) {
            if (customerEntity.get().getProductEntities().isEmpty()) {
                customerRepository.deleteById(customerEntity.get().getId());
                return MessageApplication.DELETE_CLIENT;
            }
            return MessageApplication.DELETE_CLIENT_ERROR;
        }
        return MessageApplication.CLIENT_NOTFOUND;
    }

    private Boolean validateAgeCustomer(String dateOfBirth) {
        LocalDate dateNac = LocalDate.parse(dateOfBirth);
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateNac, now);
        var age = period.getYears();
        var ageMinimum = 18;
        return age >= ageMinimum;
    }


    public boolean isInvalidUseName(CustomerDto clientDto){
        return clientDto.getCustomerName() == null || clientDto.getCustomerName().length() < 2;
    }
    public boolean isInvalidUseSurname(CustomerDto clientDto){
        return clientDto.getCustomerSurname() == null || clientDto.getCustomerSurname().length() < 2;
    }
}
