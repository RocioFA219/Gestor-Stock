package com.example.inventory_service.inventory_service.controller;


import com.example.inventory_service.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.inventory_service.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryServiceImpl inventoryService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse createStock(@RequestBody InventoryRequest inventoryRequest){
      return inventoryService.createStock(inventoryRequest);
    }

    @GetMapping
    public List<InventoryResponse> getAll(){
       return inventoryService.getAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse findById(@PathVariable Long id){
        return inventoryService.findById(id);
    }

    @PutMapping("/{id}")
    public InventoryResponse updateProductInventory(@PathVariable Long id,@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.updateProductInventory(id,inventoryRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteByProductId(@PathVariable Long id){
        inventoryService.deleteById(id);
    }
    }


