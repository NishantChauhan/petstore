package com.petstore.pet.repository.impl;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.pet.entities.Category;
import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Pet.StatusEnum;
import com.petstore.pet.entities.PhotoURL;
import com.petstore.pet.entities.Tag;
import com.petstore.pet.repository.PetRepository;
import com.petstore.pet.utilities.filestorage.StorageService;

@Repository
@PropertySource("classpath:application.properties")
public class PetRepositoryImpl implements PetRepository {

	final static Logger logger = LoggerFactory.getLogger(PetRepositoryImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	StorageService storageService;
	

	public Pet mapPet(String petJsonString) {
		return null;
	}


	@Override
	public Pet findPetById(Long id) {
		logger.debug("ENTRY");
		Pet pet = null;
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Pet> query = session.getNamedQuery("findPetById");
		query.setParameter("pet_id", id);
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			pet = pets.get(0);
			logger.info("Fetched pet with id: " + pet.getId());
			logger.debug("EXIT");
			return pet;

		}

		logger.debug("EXIT");
		return null;
	}

	
	@Override
	public List<Pet> findPetsByStatus(String status) {
		logger.debug("ENTRY");
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Pet> query = session.getNamedQuery("findPetsByStatus");
		query.setParameter("status", StatusEnum.valueOf(status));
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			logger.info("Fetched "+ pets.size()+" pets with status "+ status );
			logger.debug("EXIT");
			return pets;
		}
		logger.debug("EXIT");
		return null;
	}

	@Override
	public List<Pet> findPetsByName(String name) {
		logger.debug("ENTRY");
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Pet> query = session.getNamedQuery("findPetsByName");
		query.setParameter("name", name);
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			logger.info("Fetched "+ pets.size()+" pets with name "+ name );
			logger.debug("EXIT");
			return pets;
		}
		logger.debug("EXIT");
		return null;
	}

	@Override
	public List<Pet> findPetsByCategory(String category) {
		logger.debug("ENTRY");
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Pet> query = session.getNamedQuery("findPetsByCategory");
		query.setParameter("category", category);
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			logger.info("Fetched "+ pets.size()+" pets with category "+ category );
			logger.debug("EXIT");
			return pets;
		}
		logger.debug("EXIT");
		return null;
	}

	@Override
	public List<Pet> findPetsByTags(String tags) {
		logger.debug("ENTRY");
		
		if("".equals(tags) || tags == null){
			return null;
		}
		
		List<String> tagList = Arrays.asList(tags.split(","));
		
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Pet> query = session.getNamedQuery("findPetsByTags");		
		query.setParameter("tags", tagList);
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			logger.info("Fetched "+ pets.size()+" pets with tags "+ tags );
			logger.debug("EXIT");
			return pets;
		}
		logger.debug("EXIT");
		return null;
	}

	
	// ADD PET
	
	@Override
	public boolean addPet(Pet pet) {

		logger.debug("ENTRY");

		try  {
			Session session = this.sessionFactory.getCurrentSession();

			Transaction tx = session.beginTransaction();
			session.save(pet);
			tx.commit();

			if (logger.isDebugEnabled()) {
				Pet fetchedPet = session.get(Pet.class, pet.getId());
				logger.info("Added pet with id: " + fetchedPet.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		logger.debug("EXIT");
		return true;
	}

	@Override
	public boolean deletePet(Long id) {
		logger.debug("ENTRY");

		Pet fetchedPet = findPetById(id);

		try  {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.delete(fetchedPet);
			tx.commit();
			logger.info("Delete pet with id: " + fetchedPet.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		logger.debug("EXIT");

		return true;
	}

	@Override
	public boolean updatePet(Long petId, Pet pet) {
		logger.debug("ENTRY");

		try  {
			Session session = this.sessionFactory.getCurrentSession();

				Transaction tx = session.beginTransaction();
				session.update(pet);
				tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		logger.debug("EXIT");
		return true;
	}

	@Override
	public boolean updatePetByNameStatus(Long id, String name, String status) {

		Pet fetchedPet = findPetById(id);
		fetchedPet.setName(name);
		fetchedPet.setStatus(StatusEnum.valueOf(status));

		try  {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(fetchedPet);
			tx.commit();
			logger.info("Updated pet with id: " + fetchedPet.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	// Upload Image

	@Override
	public boolean uploadImage(Long id, MultipartFile image) throws Exception {

		Pet fetchedPet = findPetById(id);

		if(fetchedPet==null) {
			throw new Exception ("Pet Not Found");
		}
		
		if(image ==null) {
			throw new Exception ("Image Not Present in the request");
		}
		
		String imageName=StringUtils.cleanPath(image.getOriginalFilename());
		String storagePath = storageService.preparePhotoURLPath(true,fetchedPet.getId());
		String persistPath = storageService.preparePhotoURLPath(false,fetchedPet.getId());
		PhotoURL photoURL = new PhotoURL();
		photoURL.setUrl(persistPath+imageName);
		
		storageService.init(Paths.get(new URI(storagePath)));
		storageService.store(image,Paths.get(new URI(storagePath)));

		
		List<PhotoURL> photoUrlList= fetchedPet.getPhotoUrls();
		photoUrlList.add(0,photoURL);
		Set<String> photoUrlSet = new HashSet<>();
		photoUrlList.removeIf(p -> !photoUrlSet.add(p.getUrl()));

		fetchedPet.setPhotoUrls(photoUrlList);

		try  {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(fetchedPet);
			tx.commit();
			logger.info("Uploaded image "+storagePath+imageName+" for pet with id: " + fetchedPet.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}


	@Override
	public List<Category> fetchAllCategories() throws Exception {
		logger.debug("ENTRY");
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Category> query = session.getNamedQuery("fetchAllCategories");
		List<Category> categories = query.getResultList();
		

		logger.debug("EXIT");
		return categories;
	}


	@Override
	public List<Tag> fetchAllTags() throws Exception {
		logger.debug("ENTRY");
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Tag> query = session.getNamedQuery("fetchAllTags");
		List<Tag> tags = query.getResultList();
		

		logger.debug("EXIT");
		return tags;
	}
	
	public List<Pet> fetchAllPets() throws Exception {
		logger.debug("ENTRY");
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<Pet> query = session.getNamedQuery("fetchAllPets");
		List<Pet> tags = query.getResultList();
		

		logger.debug("EXIT");
		return tags;
		
	}

	/*
	 * public static Pet getDummyPet(){
	 * 
	 * Pet dummyPet = new Pet(); dummyPet.setId(1L); dummyPet.setName("Spike");
	 * Category category = new Category(); category.setId(1L);
	 * category.setName("bulldog"); dummyPet.setCategory(category);
	 * dummyPet.setStatus(StatusEnum.AVAILABLE);
	 * dummyPet.setPhotoUrls(Arrays.asList(new String[]
	 * {"http://localhost:8080/photoURLs/Spike/Spike.jpg"})); Tag tag1 = new Tag();
	 * tag1.setId(1L); tag1.setName("hates-cat");
	 * 
	 * Tag tag2 = new Tag(); tag2.setId(1L); tag2.setName("loves-mice");
	 * 
	 * List<Tag> tag = Arrays.asList(new Tag[] {tag1,tag2}); dummyPet.setTags(tag );
	 * 
	 * return dummyPet; }
	 * 
	 */
}
