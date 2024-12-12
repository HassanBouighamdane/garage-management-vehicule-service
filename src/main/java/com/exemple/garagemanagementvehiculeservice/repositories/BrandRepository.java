package com.exemple.garagemanagementvehiculeservice.repositories;

import com.exemple.garagemanagementvehiculeservice.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByNameIgnoreCase(String name);
}
