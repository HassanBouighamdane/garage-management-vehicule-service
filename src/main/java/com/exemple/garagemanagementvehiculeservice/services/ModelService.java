package com.exemple.garagemanagementvehiculeservice.services;

import com.exemple.garagemanagementvehiculeservice.entities.Model;

import java.util.List;

public interface ModelService {
    List<Model> getAll();
    List<Model> getAllModelsOfBrand(String brandName);
    Model createModel(String name,String brandName);
    Model getModelById(Long id);
    Model getModelByName(String name);
    Model updateModel(Long id,String name);
    void deleteModel(Long id);
}
