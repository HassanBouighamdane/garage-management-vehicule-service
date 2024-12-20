package com.exemple.garagemanagementvehiculeservice.services;

import com.exemple.garagemanagementvehiculeservice.entities.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(String name);
    List<Brand> getAll();
    Brand getBrandById(Long id);
    Brand getBrandByName(String name);
    Brand updateBrand(Long id,String name);
    void deleteBrand(Long id);
    Brand addModelToBrand(Long id,String modelName);

    Brand addModelToBrandByName(String name, String modelName);
}
