package com.exemple.garagemanagementvehiculeservice.controllers;

import com.exemple.garagemanagementvehiculeservice.constants.Paths;
import com.exemple.garagemanagementvehiculeservice.dtos.VehiculeDto;
import com.exemple.garagemanagementvehiculeservice.entities.Vehicule;
import com.exemple.garagemanagementvehiculeservice.exceptions.BusinessException;
import com.exemple.garagemanagementvehiculeservice.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.BASE_API+"/vehicule")
public class VehiculeController {
    private final VehiculeService vehiculeService;
    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Vehicule>> getAll(){
        return new ResponseEntity<>(vehiculeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id){
        try{
            Vehicule vehicule=vehiculeService.getById(id);
            return new ResponseEntity<>(vehicule,HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/owner")
    public ResponseEntity<List<Vehicule>> getAllByOwner(@RequestParam String ownerId){
        return new ResponseEntity<>(vehiculeService.getAllByOwner(ownerId),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Vehicule> createVehicule(@RequestBody VehiculeDto vehiculeDto){
        try{
            return new ResponseEntity<>(vehiculeService.createVehicule(vehiculeDto),HttpStatus.CREATED);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*

    The business logic of the update need to be done

     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Long id, @RequestBody VehiculeDto vehiculeDto){
        try{
            return null;
        }catch (BusinessException e){
            return null;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            vehiculeService.deleteVehicule(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
