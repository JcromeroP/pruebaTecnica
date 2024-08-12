package com.pquind.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pquind.constants.MessageApplication;
import com.pquind.dto.EditAccountStatusDto;
import com.pquind.dto.ProductDto;
import com.pquind.entity.ProductEntity;
import com.pquind.enums.AccountState;
import com.pquind.enums.AccountType;
import com.pquind.repository.CustomerRepository;
import com.pquind.repository.ProductRepository;
import com.pquind.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AccountServiceImpl accountServiceImpl;
    private final CustomerRepository customerRepository;

    public Object accountCreate(ProductDto productDto) {
        productDto.setAccountNumber(null);
        String accountNumber;
        if (productDto.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
            if (customerRepository.existsById(productDto.getCustomerId())) {
                if (productDto.getAccountType().equals(AccountType.SAVINGS.name())) {
                    accountNumber = accountServiceImpl.generateNumberAccountRandom("53");
                    productDto.setAccountNumber(accountNumber);
                    return accountServiceImpl.createSavingsAccount(productDto);
                } else if (productDto.getAccountType().equals(AccountType.CURRENT.name())) {
                    accountNumber = accountServiceImpl.generateNumberAccountRandom("33");
                    productDto.setAccountNumber(accountNumber);
                    return accountServiceImpl.createCurrentAccount(productDto);
                }
                return MessageApplication.ACCOUNT_MUST_TYPE_SAVINGS_CURRENT;
            }
            return MessageApplication.VALIDATION_CLIENT_PRODUCT;
        }
        return MessageApplication.VALUE_ACCOUNT_GREATER_TO_ZERO;
    }

    @Override
    public Object accountStateUpdate(EditAccountStatusDto editAccountStatusDto) {
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber());
        if (productEntity.isPresent()) {
            if (productEntity.get().getBalance().compareTo(BigDecimal.ZERO) == 0 && editAccountStatusDto.getAccountState() == AccountState.CANCELLED) {
                productEntity.get().setAccountState(editAccountStatusDto.getAccountState());
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.UPDATE_ACCOUNTS;
            } else if (editAccountStatusDto.getAccountState() == AccountState.ACTIVE || editAccountStatusDto.getAccountState() == AccountState.INACTIVE) {
                productEntity.get().setAccountState(editAccountStatusDto.getAccountState());
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.UPDATE_ACCOUNTS;
            }
            return MessageApplication.VALUE_BALANCE_ZERO;
        }
        return MessageApplication.ACCOUNT_NOTFOUND;
    }

    @Override
    public Object accountCanceled(EditAccountStatusDto editAccountStatusDto) {
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber());
        if (productEntity.isPresent()) {
            if (productEntity.get().getBalance().equals(BigDecimal.ZERO)) {
                productEntity.get().setAccountState(AccountState.CANCELLED);
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.ACCOUNT_CANCELLED;
            } else {
                return MessageApplication.ACCOUNT_MUST_HAVE_ZERO;
            }
        }
        return MessageApplication.ACCOUNT_NOTFOUND;
    }

    @Override
    public Object consignMoney(String accountNumber, BigDecimal balance) {
        var balanceCompare = 0;
        if (balance.compareTo(BigDecimal.ZERO) <= balanceCompare) {
            return MessageApplication.AMOUNT_NOT_NEGATIVE;
        }
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(accountNumber);
        if (productEntity.isEmpty()) {
            return MessageApplication.ACCOUNT_NOTFOUND;
        }
        productEntity.get().setBalance(productEntity.get().getBalance().add(balance));
        productEntity.get().setDateModified(LocalDateTime.now());
        return productRepository.save(productEntity.get());
    }

    @Override
    public Object withdrawMoney(String accountNumber, BigDecimal balance) {

        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(accountNumber);

        if (productEntity.isEmpty()) {
            return MessageApplication.ACCOUNT_NOTFOUND;
        }
        if (productEntity.get().getBalance().compareTo(balance) >= 0) {
            productEntity.get().setBalance(productEntity.get().getBalance().subtract(balance));
            productEntity.get().setDateModified(LocalDateTime.now());
            return productRepository.save(productEntity.get());
        } else {
            return MessageApplication.INSUFFICIENT_BALANCE;
        }
    }

    @Override
    public Object transferMoney(String accountOrigin, String accountDestination, BigDecimal balance) {
        if (productRepository.existsByAccountNumber(accountDestination)){
            withdrawMoney(accountOrigin, balance);
            consignMoney(accountDestination, balance);
            return MessageApplication.TRANSFER_SUCCESSFUL;
        }return MessageApplication.VALUE_NUMBER_DESTINATION_EXIST;
    }

}
