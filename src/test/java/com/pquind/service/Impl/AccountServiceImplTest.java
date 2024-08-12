package com.pquind.service.Impl;

import com.pquind.dto.ProductDto;
import com.pquind.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    void createSavingsAccountNegative() {
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(-11000))
                .build();
        accountServiceImpl.createSavingsAccount(productDto);
    }
    @Test
    void createSavingsAccountPositive() {
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(11000))
                .build();
        accountServiceImpl.createSavingsAccount(productDto);
    }
    @Test
    void createCurrentAccountPositive() {
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(11000))
                .build();
        accountServiceImpl.createCurrentAccount(productDto);
    }
    @Test
    void generateRandomNumber() {
        Mockito.when(productRepository.existsByAccountNumber(any())).thenReturn(false);
        accountServiceImpl.generateNumberAccountRandom("53");
    }
    @Test
    void generateRandomNumberD() {
        Mockito.when(productRepository.existsByAccountNumber(Mockito.any())).thenReturn(true);
        accountServiceImpl.generateNumberAccountRandom("53");

    }

}
