package com.exemple.garagemanagementvehiculeservice.services.implementation;

import com.exemple.garagemanagementvehiculeservice.entities.Brand;
import com.exemple.garagemanagementvehiculeservice.entities.Model;
import com.exemple.garagemanagementvehiculeservice.exceptions.BusinessException;
import com.exemple.garagemanagementvehiculeservice.repositories.ModelRepository;
import com.exemple.garagemanagementvehiculeservice.services.BrandService;
import com.exemple.garagemanagementvehiculeservice.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandService brandService;
    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService){
        this.modelRepository=modelRepository;
        this.brandService = brandService;

    }

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> getAllModelsOfBrand(String brandName) {
        try{
            Brand brand=brandService.getBrandByName(brandName);
            return modelRepository.findByBrand_Id(brand.getId());
        }catch(BusinessException ex){
            return null;
        }
    }

    @Override
    public Model createModel(String name, String brandName) {
        try{
            Brand brand=brandService.getBrandByName(brandName);
            Optional<Model> existingModel=modelRepository.findByName(name);
            if(existingModel.isPresent()){
                throw new BusinessException("Model already exists");
            }
            Model model=new Model();
            model.setName(name);
            model.setBrand(brand);
            return modelRepository.save(model);
        }catch(BusinessException e) {
            return null;
        }
    }

    @Override
    public Model getModelById(Long id) {
        Optional<Model> model=modelRepository.findById(id);
        if(model.isEmpty()){
            throw new BusinessException("Model not found");
        }
        return model.get();
    }

    @Override
    public Model getModelByName(String name) {
        Optional<Model> model=modelRepository.findByName(name);
        if(model.isEmpty()){
            throw new BusinessException("Model Not found");
        }
        return model.get();
    }

    @Override
    public Model updateModel(Long id, String name) {
        Optional<Model> model=modelRepository.findById(id);
        if(model.isEmpty()){
            throw new BusinessException("Model not found");
        }
        Model updatedModel=model.get();
        updatedModel.setName(name);
        return modelRepository.save(updatedModel);
    }

    @Override
    public void deleteModel(Long id) {
        Optional<Model> model=modelRepository.findById(id);
        if(model.isEmpty()){
            throw new BusinessException("Model not found");
        }
        modelRepository.delete(model.get());
    }
}
