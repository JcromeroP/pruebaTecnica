package com.pquind.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import com.pquind.enums.AccountState;
import com.pquind.enums.AccountType;

@Entity
@Table(name = "product")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "account_state")
    private AccountState accountState;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_balance")
    private BigDecimal balance;

    @Column(name = "GMF_exempt")
    private boolean gmfExempt;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;

    

}
