package de.codeboje.springboot.tutorials.datamongodb;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, String>{
	
	
	Address findByStreetAndCityAndCountry(String street, String city, String country);
}
