package com.example.inventory_service.inventory_service.dto;


import lombok.Builder;

@Builder
public record InventoryRequest(String id,Integer stock) {
}
