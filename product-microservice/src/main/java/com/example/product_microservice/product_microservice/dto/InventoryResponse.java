package com.example.product_microservice.product_microservice.dto;

import java.time.LocalDateTime;

public record InventoryResponse(String id, Integer stock, LocalDateTime entryProduct, LocalDateTime updateProduct) {
}
