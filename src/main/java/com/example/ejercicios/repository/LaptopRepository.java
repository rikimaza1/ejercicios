package com.example.ejercicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ejercicios.entities.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
