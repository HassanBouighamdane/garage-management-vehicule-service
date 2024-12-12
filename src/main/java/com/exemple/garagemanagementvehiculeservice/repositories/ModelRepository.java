package com.exemple.garagemanagementvehiculeservice.repositories;

import com.exemple.garagemanagementvehiculeservice.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrand_Id(Long id);
    Optional<Model> findByName(String name);
}
