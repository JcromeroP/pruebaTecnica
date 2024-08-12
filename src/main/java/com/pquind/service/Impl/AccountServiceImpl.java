package com.pquind.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.pquind.constants.MessageApplication;
import com.pquind.dto.ProductDto;
import com.pquind.entity.ProductEntity;
import com.pquind.enums.AccountState;
import com.pquind.enums.AccountType;
import com.pquind.mapper.ProductMapper;
import com.pquind.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl {

    private final ProductRepository productRepository;

    public Object createSavingsAccount(ProductDto productDto) {
        int balance = 0;
        if (productDto.getBalance().compareTo(BigDecimal.ZERO) < balance) {
            return MessageApplication.BALANCE_CANNOT;
        }
        saveSavingsProductRepository(productDto);

        return MessageApplication.ACCOUNT_CREATED;
    }

    public Object createCurrentAccount(ProductDto productDto) {
        saveCurrentProductRepository(productDto);
        return MessageApplication.ACCOUNT_CREATED;
    }

    public String generateNumberAccountRandom(String prefix) {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);

        while (productRepository.existsByAccountNumber(prefix + randomNumber)) {
            randomNumber = 10000000 + random.nextInt(90000000);
            break;
        }
        return prefix + randomNumber;
    }

    public void saveSavingsProductRepository(ProductDto productDto) {
        ProductEntity product = ProductMapper.dtoToProductEntity(productDto);
        productDto.setAccountNumber(null);
        productDto.setAccountNumber(generateNumberAccountRandom("53"));
        productDto.setAccountState(AccountState.ACTIVE);
        productDto.setDateCreated(LocalDateTime.now());
        productDto.setAccountTypes(AccountType.SAVINGS);
        productRepository.save(product);
    }
    public void saveCurrentProductRepository(ProductDto productDto) {
        productDto.setAccountNumber(null);

        productDto.setAccountNumber(generateNumberAccountRandom("33"));
        productDto.setAccountState(productDto.getAccountState());
        productDto.setAccountState(AccountState.ACTIVE);
        productDto.setAccountTypes(AccountType.CURRENT);
        productDto.setDateCreated(LocalDateTime.now());

        ProductEntity product = ProductMapper.dtoToProductEntity(productDto);
        productRepository.save(product);
    }
}
