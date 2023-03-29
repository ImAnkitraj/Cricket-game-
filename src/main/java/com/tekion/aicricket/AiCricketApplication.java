package com.tekion.aicricket;

import com.tekion.aicricket.datasource.model.CricketMatch;
import com.tekion.aicricket.service.CricketMatchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiCricketApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AiCricketApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
