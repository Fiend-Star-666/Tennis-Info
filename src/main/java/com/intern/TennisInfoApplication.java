package com.intern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@SpringBootApplication
public class TennisInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisInfoApplication.class, args);
	}

}

/*
spring.datasource.url=jdbc:mysql://localhost:3306/tennisplayers
spring.datasource.password=0000
spring.datasource.username=root

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
*/