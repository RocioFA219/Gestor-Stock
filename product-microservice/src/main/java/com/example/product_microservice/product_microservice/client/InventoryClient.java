package com.example.product_microservice.product_microservice.client;

import com.example.product_microservice.product_microservice.dto.InventoryRequest;
import com.example.product_microservice.product_microservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service", url = "${inventoryservice.url}")
public interface InventoryClient {

@GetMapping("/{id}")
    InventoryResponse getInventoryById(@PathVariable Long id);
@PostMapping("/{id}")
    InventoryResponse createStock(@RequestBody InventoryRequest inventoryRequest);
@PutMapping("/{id}")
    InventoryResponse updateProduct(@PathVariable("id") Long id,@RequestBody InventoryRequest inventoryRequest);
@DeleteMapping("/{id}")
    void deleteInventory(@PathVariable Long id);
}
