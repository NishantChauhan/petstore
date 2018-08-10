package com.petstore.pet.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.petstore.pet.entities.Category;
import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Tag;

public interface PetRepository {

	public Pet mapPet(String petJsonString) throws Exception;

	public Pet findPetById(Long id) throws Exception;

	public List<Pet> findPetsByStatus(String status) throws Exception;

	public List<Pet> findPetsByName(String name) throws Exception;

	public List<Pet> findPetsByCategory(String category) throws Exception;

	public List<Pet> findPetsByTags(String tags) throws Exception;

	public boolean addPet(Pet pet) throws Exception;

	public boolean deletePet(Long id) throws Exception;

	public boolean updatePet(Long petId, Pet pet) throws Exception;

	public boolean updatePetByNameStatus(Long id, String name, String status) throws Exception;

	public boolean uploadImage(Long petId, MultipartFile image) throws Exception;

	public List<Category> fetchAllCategories() throws Exception;

	public List<Tag> fetchAllTags() throws Exception;

	public List<Pet> fetchAllPets() throws Exception;

}
