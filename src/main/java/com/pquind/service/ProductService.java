package com.pquind.service;

import java.math.BigDecimal;

import com.pquind.dto.EditAccountStatusDto;
import com.pquind.dto.ProductDto;

public interface ProductService {


    Object accountCreate(ProductDto productDto);
    Object accountStateUpdate(EditAccountStatusDto editAccountStatusDto);
    Object accountCanceled(EditAccountStatusDto editAccountStatusDto);
    Object consignMoney(String accountNumber, BigDecimal balance);
    Object withdrawMoney(String accountNumber, BigDecimal balance);
    Object transferMoney(String accountOrigin, String accountDestination, BigDecimal balance);
}
