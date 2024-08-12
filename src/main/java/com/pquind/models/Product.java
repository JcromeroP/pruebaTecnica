package com.pquind.models;


import java.math.BigDecimal;

import com.pquind.entity.CustomerEntity;
import com.pquind.enums.AccountState;
import lombok.*;

@Getter
@Setter
public class Product {

    private Long id;
    private String accountNumber;
    private AccountState accountState;
    private BigDecimal balance;
    private boolean gmfExempt; 
    private CustomerEntity customerEntity;

}
