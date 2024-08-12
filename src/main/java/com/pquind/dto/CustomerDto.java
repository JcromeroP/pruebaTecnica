package com.pquind.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pquind.entity.Audit;
import com.pquind.enums.IdentificationType;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDto extends Audit {

    private IdentificationType identificationTypeEnum;
    private String identificationNumber;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String dateOfBirth;
    @JsonIgnore
    private List<ProductDto> productDto;

}
