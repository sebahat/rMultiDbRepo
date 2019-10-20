package com.multidatabase.multidatabase.libraryRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multidatabase.multidatabase.library.Books;

@Repository
public interface LibraryRepository  extends JpaRepository<Books, Long>{
	
	
	

}
