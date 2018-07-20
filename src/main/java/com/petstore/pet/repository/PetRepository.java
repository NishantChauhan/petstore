package com.petstore.pet.repository;

import com.petstore.pet.entities.Pet;


public interface PetRepository {
	
	public Pet mapPet(String petJsonString);
	
	public Pet findPetById(Long id);
	
	public boolean addPet(Pet pet);
	
	public boolean deletePet(Long id);
	
	public boolean updatePet (Pet pet);
}
