package com.pquind.service.Impl;


import com.pquind.dto.EditAccountStatusDto;
import com.pquind.dto.ProductDto;
import com.pquind.entity.CustomerEntity;
import com.pquind.entity.ProductEntity;
import com.pquind.enums.AccountState;
import com.pquind.repository.ProductRepository;
import com.pquind.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    private AccountServiceImpl accountServiceImpl;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void accountStateUpdateNotFound(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("53")
                .build();
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.empty());
        productServiceImpl.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void balanceInvalid(){
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(-1))
                .build();
        productServiceImpl.accountCreate(productDto);
    }
    @Test
    void balanceValidSavings(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        ProductDto productDto = ProductDto.builder()
                .customerId(customerEntity.getId())
                .balance(BigDecimal.valueOf(1))
                .accountType("SAVINGS")
                .build();
        Mockito.when(customerRepository.existsById(customerEntity.getId())).thenReturn(true);
        productServiceImpl.accountCreate(productDto);
    }
    @Test
    void balanceValidCurrent(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        ProductDto productDto = ProductDto.builder()
                .customerId(customerEntity.getId())
                .balance(BigDecimal.valueOf(1))
                .accountType("CURRENT")
                .build();
        Mockito.when(customerRepository.existsById(customerEntity.getId())).thenReturn(true);
        productServiceImpl.accountCreate(productDto);
    }
    @Test
    void accountMust(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        ProductDto productDto = ProductDto.builder()
                .customerId(customerEntity.getId())
                .balance(BigDecimal.valueOf(1))
                .accountType("a")
                .build();
        Mockito.when(customerRepository.existsById(customerEntity.getId())).thenReturn(true);
        productServiceImpl.accountCreate(productDto);
    }
    @Test
    void validationClient(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        ProductDto productDto = ProductDto.builder()
                .customerId(customerEntity.getId())
                .balance(BigDecimal.valueOf(1))
                .accountType("a")
                .build();
        Mockito.when(customerRepository.existsById(customerEntity.getId())).thenReturn(false);
        productServiceImpl.accountCreate(productDto);
    }
    @Test
    void accountCanceledIsPresent(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        ProductEntity product = new ProductEntity();
        product.setBalance(BigDecimal.valueOf(1));
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(product));
        productServiceImpl.accountCanceled(editAccountStatusDto);
    }
    @Test
    void accountCanceledIsPresentCompareTo(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        ProductEntity product = new ProductEntity();
        product.setBalance(BigDecimal.valueOf(0));
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(product));
        productServiceImpl.accountCanceled(editAccountStatusDto);
    }
    @Test
    void accountCanceledNotFound(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("530000000")
                .build();
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.empty());
        productServiceImpl.accountCanceled(editAccountStatusDto);
    }
    @Test
    void consignMoneyNegative(){
        var accountNumber = "5300000001";
        var balance = BigDecimal.valueOf(-53);
        productServiceImpl.consignMoney(accountNumber,balance);
    }
    @Test
    void consignMoneyIsEmpty(){
        var accountNumber = "";
        var balance = BigDecimal.valueOf(1000);
        Mockito.when(productRepository.findProductEntityByAccountNumber(accountNumber)).thenReturn(Optional.empty());
        productServiceImpl.consignMoney(accountNumber,balance);
    }
    @Test
    void consignMoney(){
        var accountNumber = "5300000001";
        var balance = BigDecimal.valueOf(1000);
        ProductEntity product = new ProductEntity();
        product.setAccountNumber(accountNumber);
        product.setBalance(balance);
        Mockito.when(productRepository.findProductEntityByAccountNumber(accountNumber)).thenReturn(Optional.of(product));
        productServiceImpl.consignMoney(accountNumber,balance);
    }
    @Test
    void WithdrawMoneyIsEmpty(){
        ProductEntity product = new ProductEntity();
        Mockito.when(productRepository.findProductEntityByAccountNumber(product.getAccountNumber())).thenReturn(Optional.empty());
        productServiceImpl.withdrawMoney("",BigDecimal.valueOf(1));
    }
    @Test
    void WithdrawMoneyCompareTo() {
        ProductEntity product = new ProductEntity();
        product.setAccountNumber("53");
        product.setBalance(BigDecimal.valueOf(5000));
        Mockito.when(productRepository.findProductEntityByAccountNumber("53")).thenReturn(Optional.of(product));
        productServiceImpl.withdrawMoney("53",BigDecimal.valueOf(4000));
    }
    @Test
    void WithdrawMoneyInsufficientBalance() {
        ProductEntity product = new ProductEntity();
        product.setAccountNumber("53");
        product.setBalance(BigDecimal.valueOf(10));
        Mockito.when(productRepository.findProductEntityByAccountNumber(product.getAccountNumber())).thenReturn(Optional.of(product));
        productServiceImpl.withdrawMoney("53",BigDecimal.valueOf(4000));
    }
    @Test
    void transferMoney(){
        productServiceImpl.transferMoney("5300000","53102102",BigDecimal.valueOf(20000));
    }

    @Test
    void valueBalanceZero(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .build();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(0));
        productEntity.setAccountNumber("5300000");
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(productEntity));
        productServiceImpl.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void productEntityIsPresent(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountState(AccountState.CANCELLED)
                .build();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(0));
        productEntity.setAccountNumber("5300000");
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(productEntity));
        productServiceImpl.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void productEntityIsPresentActive(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountState(AccountState.ACTIVE)
                .build();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(1000));
        productEntity.setAccountNumber("5300000");
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(productEntity));
        productServiceImpl.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void productEntityIsPresentInactive(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountState(AccountState.INACTIVE)
                .build();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(1000));
        productEntity.setAccountNumber("5300000");
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(productEntity));
        productServiceImpl.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void transferMoneyIfTrue(){
        Mockito.when(productRepository.existsByAccountNumber("5330000")).thenReturn(true);
        productServiceImpl.transferMoney("33000","5330000",BigDecimal.valueOf(5000));
    }
}
