package de.codeboje.springboot.tutorials.datamongodb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserWithRefRepository extends PagingAndSortingRepository<UserWithRef, String>{
	
	Page<UserWithRef> findAllByUsername(@Param("username") String username, Pageable page);
}
