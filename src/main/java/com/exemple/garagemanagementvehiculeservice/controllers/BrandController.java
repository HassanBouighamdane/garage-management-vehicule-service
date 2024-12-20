package com.exemple.garagemanagementvehiculeservice.controllers;

import com.exemple.garagemanagementvehiculeservice.constants.Paths;
import com.exemple.garagemanagementvehiculeservice.entities.Brand;
import com.exemple.garagemanagementvehiculeservice.entities.Model;
import com.exemple.garagemanagementvehiculeservice.exceptions.BusinessException;
import com.exemple.garagemanagementvehiculeservice.services.BrandService;
import com.exemple.garagemanagementvehiculeservice.services.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.BASE_API+"/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;

    }

    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAll(){
        return new ResponseEntity<>(brandService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id){
        try{
            Brand brand= brandService.getBrandById(id);
            return new ResponseEntity<>(brand,HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<Brand> getBrandByName(@RequestParam String name){
        try{
            Brand brand= brandService.getBrandByName(name);
            return new ResponseEntity<>(brand,HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
        try{
            Brand newBrand=brandService.createBrand(brand.getName());
            return new ResponseEntity<>(newBrand,HttpStatus.CREATED);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id,@RequestBody Brand brand){
        try{
            Brand updatedBrand=brandService.updateBrand(id,brand.getName());
            return new ResponseEntity<>(updatedBrand,HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
        try{
            brandService.deleteBrand(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(BusinessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
    @GetMapping("/{id}/models")
    public ResponseEntity<List<Model>> getAllModelsOfBrand(@PathVariable Long id){
        try{
            Brand brand= brandService.getBrandById(id);
            return new ResponseEntity<>(brand.getModels(),HttpStatus.OK);
        }catch(BusinessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
*/
    @GetMapping("/{name}/models")
    public ResponseEntity<List<Model>> getAllModelsOfBrandByName(@PathVariable String name){
        try{
            Brand brand= brandService.getBrandByName(name);
            return new ResponseEntity<>(brand.getModels(),HttpStatus.OK);
        }catch(BusinessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
/*
    @PostMapping("/{id}/models")
    public ResponseEntity<Brand> addModelToBrand(@PathVariable Long id,@RequestBody Model model){
        try{
            Brand brand= brandService.addModelToBrand(id,model.getName());
            return new ResponseEntity<>(brand,HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
*/
    @PostMapping("/{name}/models")
    public ResponseEntity<Brand> addModelToBrandByName(@PathVariable String name,@RequestBody Model model){
        try{
            Brand brand= brandService.addModelToBrandByName(name,model.getName());
            return new ResponseEntity<>(brand,HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
