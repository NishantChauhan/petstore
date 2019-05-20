package com.petstore.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.petstore.pet.entities.Category;
import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Tag;
import com.petstore.pet.repository.PetRepository;
import com.petstore.pet.utilities.filestorage.StorageService;

@RestController
public class PetUserController {

	@Autowired
	PetRepository petRepository;

	@Autowired
	StorageService fileStoreService;

	/**
	 * Regex Mapping to support Angular Mapping within Tomcat
	 *
	 * */
	@RequestMapping(value = "/api/**/{[path:[^\\\\.]*}")
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
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/pet/{petId}", method = { RequestMethod.GET }, produces = { "application/json" })
	Pet fetchPet(@PathVariable String petId) throws NumberFormatException, Exception {
		if (petId == null) {
			return null;
		}

		return petRepository.findPetById(Long.valueOf(petId));
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/findPetsByStatus", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByStatus(@RequestParam String status) throws Exception {
		return petRepository.findPetsByStatus(status);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/findPetsByName", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByName(@RequestParam String name) throws Exception {
		return petRepository.findPetsByName(name);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/findPetsByCategory", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByCategory(@RequestParam String category) throws Exception {

		return petRepository.findPetsByCategory(category);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/findPetsByTags", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> findPetsByTags(@RequestParam String tags) throws Exception {
		return petRepository.findPetsByTags(tags);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/fetchAllCategories", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Category> fetchAllCategories() throws Exception {

		return petRepository.fetchAllCategories();
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/fetchAllPets", method = { RequestMethod.GET }, produces = { "application/json" })
	List<Pet> fetchAllPets() throws Exception {

		return petRepository.fetchAllPets();
	}

}
