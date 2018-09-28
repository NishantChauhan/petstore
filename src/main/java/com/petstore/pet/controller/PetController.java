package com.petstore.pet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.petstore.pet.entities.Category;
import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Tag;
import com.petstore.pet.repository.PetRepository;
import com.petstore.pet.utilities.filestorage.StorageService;

@RestController
public class PetController {

	@Autowired
	PetRepository petRepository;

	@Autowired
	StorageService fileStoreService;
	
	final static  Logger logger = LoggerFactory.getLogger(PetController.class);

	/** 
	 * Regex Mapping to support Angular Mapping within Tomcat 
	 * 
	 * */
	@GetMapping(value = "/**/{[path:[^\\.]*}")
	public ModelAndView forward() {
		return new ModelAndView("/index.html");
	}

	@RequestMapping(path = "/photoURL/{id}/{imageName}", method = { RequestMethod.GET })
	public ResponseEntity<Resource> loadImage(
			@PathVariable String id, @PathVariable String imageName) throws Exception {

		String imageFilePath = fileStoreService.preparePhotoURLPath(true,Long.valueOf(id));
		HttpHeaders responseHeaders = new HttpHeaders();
		Resource imageFile = fileStoreService.loadFileAsResource(imageFilePath + imageName);
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(imageFile.contentLength());
		return new ResponseEntity<Resource>(imageFile, responseHeaders, HttpStatus.CREATED);
	}

	
	/**
	 * URL mapping for fetching pet based on Pet Id
	 * 
	 * */
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

	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.DELETE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	String deletePet(@PathVariable String petId) throws Exception {
		return petRepository.deletePet(Long.valueOf(petId)) ? "Pet Deleted Successfully" : "Failure while deleting pet";
	}

	// Upload Image

	@RequestMapping(path = "/pet/{petId}/uploadImage", method = { RequestMethod.POST }, produces = {
			"application/json"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	Pet uploadPetImage(@PathVariable String petId, @RequestBody MultipartFile image) throws Exception {

		petRepository.uploadImage(Long.valueOf(petId), image);

		return petRepository.findPetById(Long.valueOf(petId));
	}

	@RequestMapping(path = "/fetchAllCategories", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Category> fetchAllCategories() throws Exception {

		return petRepository.fetchAllCategories();
	}
	
	@RequestMapping(path = "/fetchAllTags", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Tag> fetchAllTags() throws Exception {

		return petRepository.fetchAllTags();
	}

	/**
	 * @author Nishant
	 * @param None
	 * @return List of Pets
	 *  
	 * */
	@RequestMapping(path = "/fetchAllPets", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> fetchAllPets() throws Exception {

		return petRepository.fetchAllPets();
	}

}
