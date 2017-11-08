package com.charles.lesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.charles"})
public class Lesson2Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson2Application.class, args);
	}
}
