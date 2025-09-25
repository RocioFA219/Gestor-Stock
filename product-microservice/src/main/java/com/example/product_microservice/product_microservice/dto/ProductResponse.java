package com.example.product_microservice.product_microservice.dto;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(Long id,
                              String name,
                              String description,
                              BigDecimal price,
                              String supplier,
                              Integer stock
) {
}
