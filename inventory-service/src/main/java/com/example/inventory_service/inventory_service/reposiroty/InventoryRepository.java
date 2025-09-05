package com.example.inventory_service.inventory_service.reposiroty;

import com.example.inventory_service.inventory_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InventoryRepository extends MongoRepository<Inventory, String> {

}
