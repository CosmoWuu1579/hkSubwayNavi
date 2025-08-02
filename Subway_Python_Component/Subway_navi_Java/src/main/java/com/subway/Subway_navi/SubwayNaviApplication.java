package com.subway.Subway_navi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:5000")
public class SubwayNaviApplication {
	private static final Logger log = LoggerFactory.getLogger(SubwayNaviApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SubwayNaviApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			log.info("RUNNING");
		};
	}
}