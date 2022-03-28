package com.eLibrary.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {
    private String userName;
    private String productCode;
    private long amount;
    private long quantity;
}
