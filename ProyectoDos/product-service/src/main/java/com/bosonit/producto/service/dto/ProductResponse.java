package com.bosonit.producto.service.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
}
