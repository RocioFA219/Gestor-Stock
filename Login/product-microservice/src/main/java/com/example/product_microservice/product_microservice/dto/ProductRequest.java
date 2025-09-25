package com.example.product_microservice.product_microservice.dto;

import java.math.BigDecimal;

public record ProductRequest(String name,
                             String description,
                             BigDecimal price,
                             String supplier
                             ) {
}
