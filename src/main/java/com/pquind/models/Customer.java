package com.pquind.models;

import com.pquind.enums.IdentificationType;

import lombok.*;

@Builder
@Getter
@Setter
public class Customer {

    private Long id;
    private String identificationTypeEnum;
    private IdentificationType identificationNumber; 
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String dateOfBirth;

}
