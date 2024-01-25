package com.gauravcode.Inventoryservice;

import com.gauravcode.Inventoryservice.Entity.Inventory;
import com.gauravcode.Inventoryservice.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){

		return  args -> {
          Inventory inventory = new Inventory();
		  inventory.setSkuCode("iphone_13");
		  inventory.setQuantity(5);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Iphone_13_red");
			inventory1.setQuantity(4);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
