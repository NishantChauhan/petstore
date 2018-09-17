package com.petstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.petstore.pet.entities.Category;
import com.petstore.pet.entities.Pet;
import com.petstore.pet.entities.Pet.StatusEnum;
import com.petstore.pet.entities.PhotoURL;
import com.petstore.pet.entities.Tag;
import com.petstore.pet.repository.PetRepository;
import com.petstore.pet.repository.impl.PetRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PetStoreRepositoryTest {
	
	@Mock
	SessionFactory sessionFactory;
	
	@Mock
	Session session;
	
	@InjectMocks
	PetRepositoryImpl petRepImpl;
	
	PetRepository petRespository;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		petRespository = petRepImpl;
	}
	
	@Test
	public void testFindPetById() throws Exception{
		when(this.sessionFactory.getCurrentSession()).thenReturn(session);
		@SuppressWarnings("unchecked")
		Query<Pet> queryMock = mock (Query.class);
		when(session.getNamedQuery("findPetById")).thenReturn(queryMock);
		when(queryMock.getResultList()).thenReturn(Arrays.asList(new Pet[] {getDummyPet()}));
				
		Pet pet = petRespository.findPetById(Long.valueOf(1L));
		
		assertNotNull(pet);
		assertEquals(pet.getId(),Long.valueOf(1L));

		when(queryMock.getResultList()).thenReturn(null);
		
		pet = petRespository.findPetById(Long.valueOf(1L));

		assertNull(pet);
	}
	
	
	
	public static Pet getDummyPet() {

		// Pet Category
		Category category = new Category();
		category.setId(1L);
		category.setName("bulldog");
		
		
		// Photo URL for Pet
		PhotoURL photoURL = new PhotoURL();
		photoURL.setUrl("http://localhost:8080/photoURLs/Spike/Spike.jpg");
		List<PhotoURL> photoURLs = Arrays.asList(new PhotoURL[] { photoURL });

		
		// Pet Tags
		Tag tag1 = new Tag();
		tag1.setId(1L);
		tag1.setName("hates-cat");
		Tag tag2 = new Tag();
		tag2.setId(1L);
		tag2.setName("loves-mice");

		List<Tag> tag = Arrays.asList(new Tag[] { tag1, tag2 });

		// Pet Details
		Pet dummyPet = new Pet();
		dummyPet.setId(1L);
		dummyPet.setName("Spike");
		dummyPet.setTags(tag);
		dummyPet.setCategory(category);
		dummyPet.setStatus(StatusEnum.AVAILABLE);
		dummyPet.setPhotoUrls(photoURLs);

		return dummyPet;
	}

}
