package com.example.inventory_service.inventory_service.service;

import com.example.inventory_service.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.inventory_service.model.Inventory;
import com.example.inventory_service.inventory_service.reposiroty.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    private InventoryResponse mapToResponse(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .stock(inventory.getStock())
                .entryProduct(inventory.getEntryProduct())
                .updateProduct(inventory.getUpdateProduct())
                .build();
    }

    public InventoryResponse createStock(InventoryRequest inventoryRequest){
        Inventory inventory = inventoryRepository.findById(inventoryRequest.id())
                .orElse(null);
                if(inventory == null){
                    //Si el producto no existe, creamos un nuevo registro de inventario
                    inventory = Inventory.builder()
                            .id(inventoryRequest.id())
                            .stock(inventoryRequest.stock())
                            .entryProduct(LocalDateTime.now())
                            .build();
                }else {//Si el producto ya existe, solo sumamos la nueva cantidad al stock actual.
                    inventory.setStock(inventory.getStock() + inventoryRequest.stock());
                    inventory.setEntryProduct(LocalDateTime.now()); //Registra la nueva entrada
                }
        Inventory saved = inventoryRepository.save(inventory);
        return mapToResponse(saved);
    }


    public InventoryResponse findById(String id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado por ese id"));
        return mapToResponse(inventory);
    }


    public List<InventoryResponse> getAll() {
            return inventoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public InventoryResponse updateProductInventory(String id, InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        //Reemplazamod el stock con el valor que viene en la peticion.
        inventory.setStock(inventoryRequest.stock());
        inventory.setUpdateProduct(LocalDateTime.now());

        Inventory updated = inventoryRepository.save(inventory);
        return mapToResponse(updated);
    }

    public void deleteById(String id) {
        inventoryRepository.deleteById(id);

    }
}

