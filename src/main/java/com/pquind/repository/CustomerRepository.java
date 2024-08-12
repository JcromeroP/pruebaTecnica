package com.pquind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pquind.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

    Optional<CustomerEntity> findCustomerEntityByIdentificationNumber(String identificacionNumber);
}
