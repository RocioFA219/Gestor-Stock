package com.example.product_microservice.product_microservice.client;

import com.example.product_microservice.product_microservice.dto.InventoryRequest;
import com.example.product_microservice.product_microservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "inventory-service", url = "${inventoryservice.url}")
public interface InventoryClient {
@GetMapping("/{id}")
    InventoryResponse getInventoryById(@PathVariable String id);
@PostMapping("")
    InventoryResponse createInventory(InventoryRequest inventoryRequest);
@DeleteMapping("/{id}")
    void deleteInventory(@PathVariable String id);
}
