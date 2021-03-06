package com.curso.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.cursomc.services.DBService;
import com.curso.cursomc.services.EmailService;
import com.curso.cursomc.services.MockEmailService;
import com.curso.cursomc.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class TestConfig {
	
	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiatetestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
		//return new SmtpEmailService();
	}
}
