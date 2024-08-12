package com.pquind.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.pquind.dto.EditAccountStatusDto;
import com.pquind.dto.ProductDto;
import com.pquind.service.ProductService;

import lombok.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createAccount")
    public Object createAccount(@RequestBody ProductDto productDto) {
        return productService.accountCreate(productDto);
    }

    @PatchMapping("/updateAccountState")
    public Object updateAccountState(@RequestBody EditAccountStatusDto editAccountStatusDto) {
        return productService.accountStateUpdate(editAccountStatusDto);
    }

    @PatchMapping("/accountCanceled")
    public Object accountCanceled(@RequestBody EditAccountStatusDto editAccountStatusDto) {
        return productService.accountCanceled(editAccountStatusDto);
    }

    @PostMapping("/consign/{accountNumber}")
    public Object consign(@PathVariable String accountNumber, @RequestParam BigDecimal balance) {
        return productService.consignMoney(accountNumber, balance);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public Object withdrawMoney(@PathVariable String accountNumber, @RequestParam BigDecimal balance) {
        return productService.withdrawMoney(accountNumber, balance);
    }

    @PostMapping("/transfer")
    public Object transferMoney(@RequestParam String accountOrigin, @RequestParam String accountDestination, @RequestParam BigDecimal balance) {
        return productService.transferMoney(accountOrigin,accountDestination,balance);
    }
}
