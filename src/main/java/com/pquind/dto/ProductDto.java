package com.pquind.dto;

import lombok.*;

import java.math.BigDecimal;

import com.pquind.entity.Audit;
import com.pquind.enums.AccountState;
import com.pquind.enums.AccountType;

@Getter
@Setter
@Builder
public class ProductDto extends Audit {
    private String accountType;
    private String accountNumber;
    private AccountState accountState;
    private BigDecimal balance;
    private boolean gmfExempt;
    private Long customerId;
    private AccountType accountTypes;
}
