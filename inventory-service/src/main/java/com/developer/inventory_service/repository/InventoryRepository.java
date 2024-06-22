package com.developer.inventory_service.repository;

import com.developer.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * InventoryRepository
 * JPA Repository for Inventory is used to perform CRUD operations on Inventory entity.
 * what is JPA Repository?
 * JPA Repository is a collection of methods for performing CRUD operations on a specific type of entity.
 *
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
