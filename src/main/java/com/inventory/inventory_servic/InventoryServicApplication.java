package com.inventory.inventory_servic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InventoryServicApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServicApplication.class, args);
	}

}
