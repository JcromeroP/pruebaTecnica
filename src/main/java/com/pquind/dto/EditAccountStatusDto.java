package com.pquind.dto;


import com.pquind.enums.AccountState;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EditAccountStatusDto {

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountState accountState;

}
