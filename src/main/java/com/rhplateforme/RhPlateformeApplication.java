package com.rhplateforme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rhplateforme.mail.EmailSenderService;
import com.rhplateforme.service.UserService;



@SpringBootApplication
public class RhPlateformeApplication {
	@Autowired
	UserService userService;
	@Autowired
	private EmailSenderService senderService;

	@Bean
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RhPlateformeApplication.class, args);
	}

}
