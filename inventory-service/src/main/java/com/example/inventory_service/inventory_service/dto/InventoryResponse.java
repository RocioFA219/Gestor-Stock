package com.example.inventory_service.inventory_service.dto;



import lombok.Builder;


import java.time.LocalDateTime;


@Builder
public record InventoryResponse(String id, Integer stock, LocalDateTime entryProduct, LocalDateTime updateProduct) {
}
