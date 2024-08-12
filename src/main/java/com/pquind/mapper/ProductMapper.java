package com.pquind.mapper;

import java.time.LocalDateTime;

import com.pquind.dto.ProductDto;
import com.pquind.entity.CustomerEntity;
import com.pquind.entity.ProductEntity;
import com.pquind.enums.AccountState;

public class ProductMapper {

    public static ProductEntity dtoToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAccountType(productDto.getAccountTypes());
        productEntity.setAccountState(AccountState.ACTIVE);
        productEntity.setBalance(productDto.getBalance());
        productEntity.setAccountNumber(productDto.getAccountNumber());
        productEntity.setCustomerEntity(CustomerEntity
                .builder()
                .id(productDto.getCustomerId())
                .build());
        productEntity.setDateCreated(LocalDateTime.now());
        productEntity.setDateModified(null);
        productEntity.setGmfExempt(productDto.isGmfExempt());
        return productEntity;
    }

}
