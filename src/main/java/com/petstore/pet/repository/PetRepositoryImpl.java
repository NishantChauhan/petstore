package com.petstore.pet.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Pet.StatusEnum;
import com.petstore.utilities.LoggerUtil;

@Repository
public class PetRepositoryImpl implements PetRepository {

	final static Logger logger = LoggerFactory.getLogger(PetRepositoryImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public Pet mapPet(String petJsonString) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public Pet findPetById(Long id) {
		LoggerUtil.entry(logger);
		Pet pet = null;
		Session session = this.sessionFactory.getCurrentSession();

		TypedQuery<Pet> query = session.getNamedQuery("findPetById");
		query.setParameter("pet_id", id);
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			pet = pets.get(0);
			logger.info("Fetched pet with id: " + pet.getId());
			LoggerUtil.exit(logger);
			return pet;

		}

		LoggerUtil.exit(logger);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Pet> findPetByStatus(String status) {
		LoggerUtil.entry(logger);
		Session session = this.sessionFactory.getCurrentSession();

		TypedQuery<Pet> query = session.getNamedQuery("findPetByStatus");
		query.setParameter("status", StatusEnum.valueOf(status));
		List<Pet> pets = query.getResultList();
		
		if(pets!=null && pets.size()>=1) {
			logger.info("Fetched "+ pets.size()+" pets with status "+ status );
			LoggerUtil.exit(logger);
			return pets;
		}
		LoggerUtil.exit(logger);
		return null;
	}

	@Override
	public List<Pet> findPetByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> findPetByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> findPetByTags(String tags) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// ADD PET
	
	
	public boolean addPet(Pet pet) {

		LoggerUtil.entry(logger);

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

		LoggerUtil.exit(logger);
		return true;
	}

	public boolean deletePet(Long id) {
		LoggerUtil.entry(logger);

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
		
		
		LoggerUtil.exit(logger);

		return true;
	}

	public boolean updatePet(Long petId, Pet pet) {
		LoggerUtil.entry(logger);

		try  {
			Session session = this.sessionFactory.getCurrentSession();

				Transaction tx = session.beginTransaction();
				session.update(pet);
				tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		LoggerUtil.exit(logger);
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
