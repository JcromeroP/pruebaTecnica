package com.pquind.entity;

import com.pquind.enums.IdentificationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type")
    private IdentificationType identificationTypeEnum;

    @Column(name = "identification_number", unique = true)
    private String identificationNumber;

    @Column(name = "customer_name") 
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;
    
    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities;
}
