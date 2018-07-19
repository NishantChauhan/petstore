package com.petstore.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.pet.domain.PetDao;
import com.petstore.pet.entities.Pet;

@RestController
public class PetController {
	
	@Autowired
	PetDao petAccessObj;
	
	@RequestMapping(path="/pet/{petId}",method= {RequestMethod.GET}, produces= {"application/json"})
	Pet fetchPet(@PathVariable String petId) {
		if(petId==null) {
			return null;
		}
		
		return petAccessObj.findPetById(Long.valueOf(petId));
	}

}
