package com.multidatabase.multidatabase;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultidatabaseApplication {
	private static Logger logger  = LoggerFactory.getLogger(MultidatabaseApplication.class);
	public static void main(String[] args) {    	
		logger.info("Main Application Sebahat");
		logger.trace("Message");
		SpringApplication.run(MultidatabaseApplication.class, args);
	}

}
