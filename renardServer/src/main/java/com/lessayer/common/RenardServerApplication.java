package com.lessayer.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class RenardServerApplication implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		
		SpringApplication.run(RenardServerApplication.class, args);
	
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/renardServer/*").
					allowedOrigins("http://localhost:8080");
			}
		};
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Starting renard service...");
		
	}
	
}
