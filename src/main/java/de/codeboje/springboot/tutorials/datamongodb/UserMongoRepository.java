package de.codeboje.springboot.tutorials.datamongodb;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserMongoRepository extends PagingAndSortingRepository<User, String>{
	
	Page<User> findAllByUsername(@Param("username") String username, Pageable page);
	
	List<User> findAllBy(TextCriteria criteria);
}
