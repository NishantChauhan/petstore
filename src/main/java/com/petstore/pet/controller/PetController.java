package com.petstore.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class PetController {

	@Autowired
	PetRepository petRepository;
	
	@Autowired
	StorageService fileStoreService;
	

	@RequestMapping(path = "/photoURL/{category}/{name}-id-{id}/{imageName}", method = { RequestMethod.GET })
	public ResponseEntity<Resource> loadImage(@PathVariable String category, @PathVariable String name,
			@PathVariable String id, @PathVariable String imageName) throws Exception {
		
		String imageFilePath = fileStoreService.preparePhotoURLPath(category, name, Long.valueOf(id));
		HttpHeaders responseHeaders = new HttpHeaders();
		Resource imageFile = fileStoreService.loadFileAsResource(imageFilePath+imageName);
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(imageFile.contentLength());
		return new ResponseEntity<Resource>(imageFile,responseHeaders, HttpStatus.CREATED);
	}
	// Find Pets

	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.GET }, produces = { "application/json" })
	Pet fetchPet(@PathVariable String petId) throws NumberFormatException, Exception {
		if (petId == null) {
			return null;
		}

		return petRepository.findPetById(Long.valueOf(petId));
	}

	@RequestMapping(path = "/findPetsByStatus", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByStatus(@RequestParam String status) throws Exception {
		return petRepository.findPetsByStatus(status);
	}

	@RequestMapping(path = "/findPetsByName", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByName(@RequestParam String name) throws Exception {
		return petRepository.findPetsByName(name);
	}

	@RequestMapping(path = "/findPetsByCategory", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByCategory(@RequestParam String category) throws Exception {

		return petRepository.findPetsByCategory(category);
	}

	@RequestMapping(path = "/findPetsByTags", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByTags(@RequestParam String tags) throws Exception {
		return petRepository.findPetsByTags(tags);
	}

	// Add Pet

	@RequestMapping(path = "/pet", method = { RequestMethod.POST }, produces = { "application/json" })
	Pet addPet(@RequestBody Pet pet) throws Exception {
		petRepository.addPet(pet);
		return petRepository.findPetById(pet.getId());
	}

	// Update Pet

	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.POST }, produces = { "application/json" })
	Pet updatePetByNameStatus(@PathVariable String petId, @RequestParam String name, @RequestParam String status)
			throws Exception {
		petRepository.updatePetByNameStatus(Long.valueOf(petId), name, status);
		return petRepository.findPetById(Long.valueOf(petId));
	}

	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.PUT }, produces = { "application/json" })
	Pet updatePet(@PathVariable String petId, @RequestBody Pet pet) throws Exception {
		petRepository.updatePet(Long.valueOf(petId), pet);
		return petRepository.findPetById(Long.valueOf(petId));
	}

	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.DELETE }, produces = { "application/json" })
	String deletePet(@PathVariable String petId) throws Exception {
		return petRepository.deletePet(Long.valueOf(petId)) ? "Pet Deleted Successfully" : "Failure while deleting pet";
	}

	// Upload Image

	@RequestMapping(path = "/pet/{petId}/uploadImage", method = { RequestMethod.POST }, produces = {
			"application/json" }, consumes = { "multipart/form-data" })
	Pet uploadPetImage(@PathVariable String petId, @RequestBody MultipartFile image) throws Exception {

		petRepository.uploadImage(Long.valueOf(petId), image);

		return petRepository.findPetById(Long.valueOf(petId));
	}

}
