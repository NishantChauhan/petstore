package com.petstore.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.pet.entities.Pet;
import com.petstore.pet.repository.PetRepository;

@RestController
public class PetController {
	
	@Autowired
	PetRepository petRepository;
	
	@RequestMapping(path="/pet/{petId}",method= {RequestMethod.GET}, produces= {"application/json"})
	Pet fetchPet(@PathVariable String petId) {
		if(petId==null) {
			return null;
		}
		
		return petRepository.findPetById(Long.valueOf(petId));
	}
	
	@RequestMapping(path="/pet", method= {RequestMethod.POST}, produces= {"application/json"})
	void addPet(@RequestBody Pet pet) throws Exception {
		petRepository.addPet(pet);
	}

}
