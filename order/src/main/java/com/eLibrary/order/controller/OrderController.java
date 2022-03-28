package com.eLibrary.order.controller;

import com.eLibrary.order.domain.dto.OrderDTO;
import com.eLibrary.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eLibrary/v1/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }
}
