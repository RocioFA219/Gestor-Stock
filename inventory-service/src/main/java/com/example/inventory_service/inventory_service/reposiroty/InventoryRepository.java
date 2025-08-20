package com.example.inventory_service.inventory_service.reposiroty;

import com.example.inventory_service.inventory_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {

}
