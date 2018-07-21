package com.petstore.pet.repository;

import java.util.List;

import com.petstore.pet.entities.Pet;

public interface PetRepository {

	public Pet mapPet(String petJsonString);

	public Pet findPetById(Long id);

	public List<Pet> findPetByStatus(String status);

	public List<Pet> findPetByName(String name);

	public List<Pet> findPetByCategory(String category);

	public List<Pet> findPetByTags(String tags);

	public boolean addPet(Pet pet);

	public boolean deletePet(Long id);

	public boolean updatePet(Long petId, Pet pet);

	public boolean updatePetByNameStatus(Long id, String name, String status);
}
