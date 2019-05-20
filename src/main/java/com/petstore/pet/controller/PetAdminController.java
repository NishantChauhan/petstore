package com.petstore.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.pet.entities.Pet;
import com.petstore.pet.repository.PetRepository;
import com.petstore.pet.utilities.filestorage.StorageService;

@RestController
public class PetAdminController {

	@Autowired
	PetRepository petRepository;

	@Autowired
	StorageService fileStoreService;

	// Add Pet

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/pet", method = { RequestMethod.POST }, produces = { "application/json" })
	Pet addPet(@RequestBody Pet pet) throws Exception {
		petRepository.addPet(pet);
		return petRepository.findPetById(pet.getId());
	}

	// Update Pet
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.POST }, produces = { "application/json" })
	Pet updatePetByNameStatus(@PathVariable String petId, @RequestParam String name, @RequestParam String status)
			throws Exception {
		petRepository.updatePetByNameStatus(Long.valueOf(petId), name, status);
		return petRepository.findPetById(Long.valueOf(petId));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.PUT }, produces = { "application/json" })
	Pet updatePet(@PathVariable String petId, @RequestBody Pet pet) throws Exception {
		petRepository.updatePet(Long.valueOf(petId), pet);
		return petRepository.findPetById(Long.valueOf(petId));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.DELETE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	String deletePet(@PathVariable String petId) throws Exception {
		return petRepository.deletePet(Long.valueOf(petId)) ? "Pet Deleted Successfully" : "Failure while deleting pet";
	}

	// Upload Image

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/pet/{petId}/uploadImage", method = { RequestMethod.POST }, produces = {
			"application/json"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	Pet uploadPetImage(@PathVariable String petId, @RequestBody MultipartFile image) throws Exception {

		petRepository.uploadImage(Long.valueOf(petId), image);

		return petRepository.findPetById(Long.valueOf(petId));
	}


}
