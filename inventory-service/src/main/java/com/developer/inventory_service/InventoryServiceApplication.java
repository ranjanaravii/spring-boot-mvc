package com.developer.inventory_service;

import com.developer.inventory_service.model.Inventory;
import com.developer.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone 13");
			inventory.setQuantity(10);
			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone 15");
			inventory2.setQuantity(0);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
		};
	}

}
