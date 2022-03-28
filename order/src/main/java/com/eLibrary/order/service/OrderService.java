package com.eLibrary.order.service;

import com.eLibrary.order.domain.OrderInfo;
import com.eLibrary.order.domain.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public interface OrderService {
    ResponseEntity<?> createOrder(@RequestBody OrderDTO orderInfo);
    OrderInfo generateOrderInfo(Map productResponse, Map customerResponse);
}
