package com.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.petstore.pet.utilities.filestorage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PetStoreAppApplication {
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(PetStoreAppApplication.class, args);
	}
}
