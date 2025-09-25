package com.example.inventory_service.inventory_service.service;
import com.example.inventory_service.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.inventory_service.model.Inventory;
import java.util.List;
import java.util.Optional;

public interface InventoryService {
    InventoryResponse createStock(InventoryRequest inventoryRequest);
     InventoryResponse findById(Long id);
     List<InventoryResponse> getAll();
     InventoryResponse updateProductInventory(Long id, InventoryRequest inventoryRequest);
     void deleteById(Long id);
}