package com.pquind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pquind.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    Optional<ProductEntity> findProductEntityByAccountNumber(String accountNumber);
    Boolean existsByAccountNumber(String accountNumber);


}
