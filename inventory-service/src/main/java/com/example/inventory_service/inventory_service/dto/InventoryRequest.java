package com.example.inventory_service.inventory_service.dto;


import lombok.Builder;

@Builder
public record InventoryRequest(Long id,Integer stock) {
}
