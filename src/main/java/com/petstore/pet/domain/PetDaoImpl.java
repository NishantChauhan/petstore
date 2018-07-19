package com.petstore.pet.domain;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.petstore.pet.entities.Category;
import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Pet.StatusEnum;
import com.petstore.utilities.LoggerUtil;


@Repository
public class PetDaoImpl implements PetDao{
	
	final static Logger logger = LoggerFactory.getLogger(PetDaoImpl.class);
	public Pet mapPet(String petJsonString) {
		return null;
	}
	
	public Pet findPetById(Long id) {
		LoggerUtil.entry(logger);

		Pet pet = PetDaoImpl.getDummyPet(); 
		
		logger.debug("Fetched pet with id: " + pet.getId());
		
		
		LoggerUtil.exit(logger);
		return pet;
	}
	
	public boolean addPet(Pet pet) {
		return false;
	}
	
	public boolean deletePet(Long id) {
		return false;
	}
	
	public boolean updatePet (Pet pet) {
		return false;
	}
	
	public static Pet getDummyPet(){
		
		Pet dummyPet = new Pet();
		dummyPet.setId(1L);
		dummyPet.setName("Zanjeer");
		Category category = new Category();
		category.setId(1L);
		category.setName("Dog");
		dummyPet.setCategory(category);
		dummyPet.setStatus(StatusEnum.AVAILABLE);
		dummyPet.setPhotoUrls(Arrays.asList(new String[] {"http://localhost:8080/photoURL"}));
		dummyPet.setTags(null);
		
		return dummyPet;
	}
	
}
