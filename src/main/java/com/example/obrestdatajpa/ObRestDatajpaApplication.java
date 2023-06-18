package com.example.obrestdatajpa;

import com.example.obrestdatajpa.controllers.HelloController;
import com.example.obrestdatajpa.entities.Laptop;
import com.example.obrestdatajpa.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		//Ejercicio 1: prueba Hello word
		HelloController helloController = context.getBean(HelloController.class);
		System.out.println(helloController.helloWord());
		//Ejercicio2
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
		//CRUD
		Laptop laptop1 = new Laptop(null, "Samsung", "a3", 1000, true);
		Laptop laptop2 = new Laptop(null, "Azus", "tren", 1300, true);
		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
		System.out.println(laptopRepository.count());
		System.out.println(laptop1);
	}
}
