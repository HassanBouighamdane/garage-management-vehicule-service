package com.exemple.garagemanagementvehiculeservice.repositories;

import com.exemple.garagemanagementvehiculeservice.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByOwnerId(String ownerId);
    Optional<Vehicule> findByRegistrationNumber(String registrationNumber);
}
