package com.example.ejercicios;

import com.example.ejercicios.entities.Laptop;
import com.example.ejercicios.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EjerciciosApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EjerciciosApplication.class, args);
        LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
        Laptop laptop1 = new Laptop(null, "Sony", "Vaio", 2020);
        Laptop laptop2 = new Laptop(null, "Acer", "Predator", 2022);
        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);
    }

}
