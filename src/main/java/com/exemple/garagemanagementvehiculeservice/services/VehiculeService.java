package com.exemple.garagemanagementvehiculeservice.services;

import com.exemple.garagemanagementvehiculeservice.dtos.VehiculeDto;
import com.exemple.garagemanagementvehiculeservice.entities.Vehicule;

import java.util.List;

public interface VehiculeService {
    List<Vehicule> getAll();
    List<Vehicule> getAllByOwner(String ownerId);
    Vehicule createVehicule(VehiculeDto vehiculeDto);
    Vehicule getById(Long id);
    Vehicule updateVehicule(Vehicule vehicule);
    void deleteVehicule(Long id);
}
