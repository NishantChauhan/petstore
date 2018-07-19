package com.petstore.pet.domain;

import com.petstore.pet.entities.Pet;


public interface PetDao {
	
	public Pet mapPet(String petJsonString);
	
	public Pet findPetById(Long id);
	
	public boolean addPet(Pet pet);
	
	public boolean deletePet(Long id);
	
	public boolean updatePet (Pet pet);
}
