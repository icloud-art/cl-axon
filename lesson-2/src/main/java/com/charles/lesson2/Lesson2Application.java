package com.charles.lesson2;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
@ComponentScan(basePackages = {"com.charles"})
public class Lesson2Application {

	private static final Logger LOGGER = getLogger(SpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Lesson2Application.class, args);
	}
}
