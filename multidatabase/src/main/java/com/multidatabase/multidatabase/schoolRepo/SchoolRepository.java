package com.multidatabase.multidatabase.schoolRepo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.multidatabase.multidatabase.school.Books;


@Repository
public interface SchoolRepository extends PagingAndSortingRepository<Books, Long> {
       
	
}
