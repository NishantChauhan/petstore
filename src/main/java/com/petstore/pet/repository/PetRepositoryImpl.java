package com.petstore.pet.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petstore.pet.entities.Pet;
import com.petstore.utilities.LoggerUtil;


@Repository
public class PetRepositoryImpl implements PetRepository{
	
	final static Logger logger = LoggerFactory.getLogger(PetRepositoryImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	public Pet mapPet(String petJsonString) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Pet findPetById(Long id) {
		LoggerUtil.entry(logger);

		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Pet> query = session.getNamedQuery("findPetById");
		query.setParameter("pet_id", id);
		List<Pet> pets= query.getResultList();
		Pet pet = pets.get(0); 
		logger.info("Fetched pet with id: " + pet.getId());
		LoggerUtil.exit(logger);
		return pet;
	}
	
	public boolean addPet(Pet pet) {
		
		LoggerUtil.entry(logger);

		logger.info("Added pet with id: " + pet.getId());
		logger.info("Pet Details: ");
		logger.info(pet.toString());
		
		LoggerUtil.exit(logger);
		return false;
	}
	
	public boolean deletePet(Long id) {
		LoggerUtil.entry(logger);
		
		LoggerUtil.exit(logger);

		return false;
	}
	
	public boolean updatePet (Pet pet) {
		LoggerUtil.entry(logger);
		
		LoggerUtil.exit(logger);

		return false;
	}

	/*
	public static Pet getDummyPet(){
		
		Pet dummyPet = new Pet();
		dummyPet.setId(1L);
		dummyPet.setName("Spike");
		Category category = new Category();
		category.setId(1L);
		category.setName("bulldog");
		dummyPet.setCategory(category);
		dummyPet.setStatus(StatusEnum.AVAILABLE);
		dummyPet.setPhotoUrls(Arrays.asList(new String[] {"http://localhost:8080/photoURLs/Spike/Spike.jpg"}));
		Tag tag1 = new Tag();
		tag1.setId(1L);
		tag1.setName("hates-cat");
		
		Tag tag2 = new Tag();
		tag2.setId(1L);
		tag2.setName("loves-mice");

		List<Tag> tag = Arrays.asList(new Tag[] {tag1,tag2});
		dummyPet.setTags(tag );
		
		return dummyPet;
	}
	
*/
	}
