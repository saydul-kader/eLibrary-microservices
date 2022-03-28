package com.eLibrary.customer.repository;

import com.eLibrary.customer.domain.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<CustomerInfo, UUID> {
    Optional<CustomerInfo> findCustomerInfoByUserName(String userName);
}
