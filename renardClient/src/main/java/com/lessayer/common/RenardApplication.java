package com.lessayer.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenardApplication implements CommandLineRunner {

	// private Logger logger = LoggerFactory.getLogger(this.getClass());


	public static void main(String[] args) {

		SpringApplication.run(RenardApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}

}
