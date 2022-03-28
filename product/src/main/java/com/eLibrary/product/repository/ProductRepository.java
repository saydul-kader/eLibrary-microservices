package com.eLibrary.product.repository;

import com.eLibrary.product.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductInfo, UUID> {
    Optional<ProductInfo> findProductInfoByProductCode(String productCode);
    Optional<ProductInfo> findProductInfoByCategory(String category);
}
