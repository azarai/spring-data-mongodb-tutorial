package de.codeboje.springboot.tutorials.datamongodb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataMongodbTutorialApplicationTests {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserWithRefRepository refRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserMongoRepository umRepo;
	
	@Test
	public void contextLoads() {
	}

	@Before
	public void before() {
		repo.deleteAll();
		addressRepo.deleteAll();
	}
	
	@Test
	public void createUser() {
		User horst = new User();
		horst.setFirstname("Horst");
		horst.setLastname("Mustermann");
		horst.setUsername("horstm");
		
		User horstDb = repo.save(horst);
		assertNotNull(horstDb);
		assertNotNull(horstDb.getId());
		assertEquals(horst.getFirstname(), horstDb.getFirstname());
	}
	
	@Test
	public void createUserWithAddress() {
		User horst = new User();
		horst.setFirstname("Horst");
		horst.setLastname("Mustermann");
		horst.setUsername("horstm");
		Address address = new Address();
		address.setCity("Frankfurt");
		address.setCountry("Germany");
		address.setStreet("Zeil 3");
		address.setZipCode("60384");

		horst.setHomeAddress(address);
		
		
		User horstDb = repo.save(horst);
		assertNotNull(horstDb);
		assertNotNull(horstDb.getId());
		assertEquals(horst.getFirstname(), horstDb.getFirstname());
	}
	
	@Test
	public void search() {
		createUser();
		
		Page<User> users = repo.findAllByUsername("horstm", new PageRequest(0, 10));
		
		assertFalse(users.getContent().isEmpty());
		
		assertEquals("horstm", users.getContent().get(0).getUsername());	
	}
	
	@Test
	public void createUserWithRefAndAddress() {
		UserWithRef horst = new UserWithRef();
		horst.setFirstname("Horst");
		horst.setLastname("Mustermann");
		horst.setUsername("horstm");
		
		
		Address address = new Address();
		address.setCity("Frankfurt");
		address.setCountry("Germany");
		address.setStreet("Zeil 1");
		address.setZipCode("60384");

		address = addressRepo.save(address);
		
		horst.setHomeAddress(address);
		
		
		UserWithRef horstDb = refRepo.save(horst);
		assertNotNull(horstDb);
		assertNotNull(horstDb.getId());
		assertEquals(horst.getFirstname(), horstDb.getFirstname());
	}
	
	@Test
	public void searchAddress() {
		Address address = new Address();
		address.setCity("Frankfurt");
		address.setCountry("Germany");
		address.setStreet("Zeil 1");
		address.setZipCode("60384");

		Address addressDb = addressRepo.save(address);
		assertNotNull(addressDb);
		
		Address addressSearchResult = addressRepo.findByStreetAndCityAndCountry("Zeil 1", "Frankfurt", "Germany");
		
		assertEquals(addressDb.getId(), addressSearchResult.getId());
	}
	
	@Test
	public void searchUser() {
		createUserWithAddress();
		TextCriteria search = TextCriteria.forDefaultLanguage().matching("horst");
		List<User> r = umRepo.findAllBy(search);
		
		assertNotNull(r);
		assertFalse(r.isEmpty());
		r.stream().forEach(u -> System.out.println(u.getUsername() +  " " + u.getTextScore()));

	}
	
}
