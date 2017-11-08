package com.charles.lesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.charles"})
public class Lesson3Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson3Application.class, args);
	}
}
