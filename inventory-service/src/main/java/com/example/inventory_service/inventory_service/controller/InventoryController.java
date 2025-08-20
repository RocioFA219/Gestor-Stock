package com.example.inventory_service.inventory_service.controller;


import com.example.inventory_service.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.inventory_service.model.Inventory;
import com.example.inventory_service.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse createInventory(@RequestBody InventoryRequest inventoryRequest){
        Inventory inventory = Inventory.builder()
                .stock(inventoryRequest.stock())
                .id(inventoryRequest.id())
                .build();
        Inventory saved = inventoryService.saveProduct(inventory);

        return InventoryResponse.builder()
                .id(saved.getId())
                .stock(saved.getStock())
                .entryProduct(saved.getEntryProduct())
                .updateProduct(saved.getUpdateProduct())
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse getAllInventory(@PathVariable String id){
        Inventory inventory = inventoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado por ese id"));
        return InventoryResponse.builder()
                .id(inventory.getId())
                .stock(inventory.getStock())
                .entryProduct(inventory.getEntryProduct())
                .updateProduct(inventory.getUpdateProduct())
                .build();
    }
    @GetMapping
    public List<InventoryResponse> getAll(){
        return inventoryService.getAll().stream()
                .map(inventory -> InventoryResponse.builder()
                        .id(inventory.getId())
                        .stock(inventory.getStock())
                        .entryProduct(inventory.getEntryProduct())
                        .updateProduct(inventory.getUpdateProduct())
                        .build())
                .toList();
    }
    @PutMapping("/{id}")
    public InventoryResponse updateProductInventory(@PathVariable String id,@RequestBody InventoryRequest inventoryRequest){
        Inventory inventory = inventoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario de producto no encontrado"));
        inventory.setStock(inventoryRequest.stock());
        inventory.setUpdateProduct(LocalDateTime.now());

        Inventory updated = inventoryService.saveProduct(inventory);

        return InventoryResponse.builder()
                .id(updated.getId())
                .stock(updated.getStock())
                .entryProduct(updated.getEntryProduct())
                .updateProduct(updated.getUpdateProduct())
                .build();
    }
    @DeleteMapping("/{id}")
    public void deleteByProductId(@PathVariable String id){
        inventoryService.deleteById(id);
    }
    }


