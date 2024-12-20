package com.exemple.garagemanagementvehiculeservice.services.implementation;

import com.exemple.garagemanagementvehiculeservice.entities.Brand;
import com.exemple.garagemanagementvehiculeservice.entities.Model;
import com.exemple.garagemanagementvehiculeservice.exceptions.BusinessException;
import com.exemple.garagemanagementvehiculeservice.repositories.BrandRepository;
import com.exemple.garagemanagementvehiculeservice.repositories.ModelRepository;
import com.exemple.garagemanagementvehiculeservice.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;


    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository){
        this.brandRepository=brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public Brand createBrand(String name) {
        Optional<Brand> brand=brandRepository.findByNameIgnoreCase(name);
        if(brand.isPresent()){
            throw new BusinessException("Brand already exists");
        }
        Brand newBrand=new Brand();
        newBrand.setName(name);
        return brandRepository.save(newBrand);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        Optional<Brand> brand=brandRepository.findById(id);
        if(brand.isEmpty()){
            throw new BusinessException("Brand Not found");
        }
        return brand.get();
    }

    @Override
    public Brand getBrandByName(String name) {
        Optional<Brand> brand=brandRepository.findByNameIgnoreCase(name);
        if(brand.isEmpty()){
            throw new BusinessException("Brand not found");
        }
        return brand.get();
    }

    @Override
    public Brand updateBrand(Long id, String name) {
        Optional<Brand> brand=brandRepository.findById(id);
        if(brand.isEmpty()) {
            throw new BusinessException("Brand Not found");
        }
        Brand updatedBrand=brand.get();
        updatedBrand.setName(name);
        return brandRepository.save(updatedBrand);
    }

    @Override
    public void deleteBrand(Long id) {
        Optional<Brand> brand=brandRepository.findById(id);
        if(brand.isEmpty()){
            throw new BusinessException("Brand not found");
        }
        brandRepository.delete(brand.get());
    }

    @Override
    public Brand addModelToBrand(Long id,String modelName) {
        Optional<Brand> existingBrand=brandRepository.findById(id);
        if(existingBrand.isEmpty()){
            throw new BusinessException("Brand not found");
        }
        Optional<Model> model=modelRepository.findByName(modelName);
        Brand brand=existingBrand.get();
        if(model.isPresent()){
            brand.getModels().add(model.get());
            return brandRepository.save(brand);
        }
        Model newModel=new Model();
        newModel.setName(modelName);
        newModel=modelRepository.save(newModel);
        brand.getModels().add(newModel);
        return brandRepository.save(brand);
    }

    @Override
    public Brand addModelToBrandByName(String name, String modelName) {
        Optional<Brand> existingBrand = brandRepository.findByNameIgnoreCase(name);
        if (existingBrand.isEmpty()) {
            throw new BusinessException("Brand not found");
        }
        Optional<Model> model = modelRepository.findByName(modelName);
        Brand brand = existingBrand.get();
        if (model.isPresent()) {
            brand.getModels().add(model.get());
            return brandRepository.save(brand);
        }
        Model newModel = new Model();
        newModel.setName(modelName);
        newModel = modelRepository.save(newModel);
        brand.getModels().add(newModel);
        return brandRepository.save(brand);
    }
}
