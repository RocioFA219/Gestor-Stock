package com.example.inventory_service.inventory_service.service;

import com.example.inventory_service.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.inventory_service.model.Inventory;
import com.example.inventory_service.inventory_service.reposiroty.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory saveProduct(Inventory inventory) {
        inventory.setUpdateProduct(LocalDateTime.now());
        if (inventory.getEntryProduct() == null) {
            inventory.setEntryProduct(LocalDateTime.now());
        }
        return inventoryRepository.save(inventory);
    }

    public Optional<Inventory> findById(String id) {
        return inventoryRepository.findById(id);
    }


    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Inventory updateProductInventory(String id, Integer newStock) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        inventory.setStock(newStock);
        inventory.setUpdateProduct(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public void deleteById(String id) {
        inventoryRepository.deleteById(id);

    }
}