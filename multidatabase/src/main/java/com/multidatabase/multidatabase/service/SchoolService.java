/**
 * 
 */
package com.multidatabase.multidatabase.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.multidatabase.multidatabase.school.Books;
import com.multidatabase.multidatabase.schoolRepo.SchoolRepository;

@Service
public class SchoolService {

	@Autowired
	SchoolRepository schoolRepository;

	public List<Books> getFindAll() {
		org.springframework.data.domain.Pageable pagable=PageRequest.of(0, 10);
		org.springframework.data.domain.Page<Books> page=schoolRepository.findAll(pagable);
		return page.getContent();
	}

}
