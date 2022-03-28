package com.eLibrary.order.repository;

import com.eLibrary.order.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
}
