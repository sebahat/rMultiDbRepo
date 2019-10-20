package com.multidatabase.multidatabase.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multidatabase.multidatabase.school.Books;
import com.multidatabase.multidatabase.service.SchoolService;

@RestController
public class ControllerExample {
	@Autowired
	SchoolService service;
	
	private static Logger logger  = LoggerFactory.getLogger(ControllerExample.class);
  
	
	@GetMapping(path = "/getHello")
	public List<Books> getHello() {
		logger.info("sebahat");
		
        return service.getFindAll();
	}
}
