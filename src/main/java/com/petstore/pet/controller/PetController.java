package com.petstore.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.pet.entities.Pet;
import com.petstore.pet.repository.PetRepository;

@RestController
public class PetController {
	
	@Autowired
	PetRepository petRepository;
	
	
	// Find Pets
	
	@RequestMapping(path="/pet/{petId}",method= {RequestMethod.GET}, produces= {"application/json"})
	Pet fetchPet(@PathVariable String petId) {
		if(petId==null) {
			return null;
		}
		
		return petRepository.findPetById(Long.valueOf(petId));
	}
	@RequestMapping(path="/findPetsByStatus",method= {RequestMethod.GET}, produces= {"application/json"})
	List<Pet> findPetsByStatus(@RequestParam String status){
		return petRepository.findPetByStatus(status);
	}
	
	@RequestMapping(path="/findPetsByName",method= {RequestMethod.GET}, produces= {"application/json"})
	List<Pet> findPetsByName(@RequestParam String status){
		
		List<Pet> pets = null;
		
		return pets;
	}
	
	@RequestMapping(path="/findPetsByCategory",method= {RequestMethod.GET}, produces= {"application/json"})
	List<Pet> findPetsByCategory(@RequestParam String status){
		
		List<Pet> pets = null;
		
		return pets;
	}
	@RequestMapping(path="/findPetsByTags",method= {RequestMethod.GET}, produces= {"application/json"})
	List<Pet> findPetsByTags(@RequestParam String tags){
		
		List<Pet> pets = null;
		
		return pets;
	}
	

	// Add Pet
	
	@RequestMapping(path="/pet", method= {RequestMethod.POST}, produces= {"application/json"})
	Pet addPet(@RequestBody Pet pet) throws Exception {
		petRepository.addPet(pet);
		return petRepository.findPetById(pet.getId());
	}
	
	
	// Update Pet

	@RequestMapping(path="/pet/{petId}", method= {RequestMethod.POST}, produces= {"application/json"})
	Pet updatePetByNameStatus (@PathVariable String petId, @RequestParam String name,@RequestParam String status) throws Exception {
		petRepository.updatePetByNameStatus(Long.valueOf(petId),  name, status);
		return petRepository.findPetById(Long.valueOf(petId));
	}	
	
	@RequestMapping(path="/pet/{petId}", method= {RequestMethod.PUT}, produces= {"application/json"})
	Pet updatePet (@PathVariable String petId, @RequestBody Pet pet) throws Exception {
		petRepository.updatePet(Long.valueOf(petId), pet);
		return petRepository.findPetById(Long.valueOf(petId));
	}	
	
	@RequestMapping(path="/pet/{petId}", method= {RequestMethod.DELETE}, produces= {"application/json"})
	String deletePet (@PathVariable String petId) throws Exception {
		return petRepository.deletePet(Long.valueOf(petId))? "Pet Deleted Successfully": "Failure while deleting pet";
	}	
	
	
}
