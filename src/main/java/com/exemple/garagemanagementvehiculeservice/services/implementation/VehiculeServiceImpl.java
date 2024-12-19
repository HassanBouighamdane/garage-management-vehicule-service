package com.exemple.garagemanagementvehiculeservice.services.implementation;

import com.exemple.garagemanagementvehiculeservice.dtos.VehiculeDto;
import com.exemple.garagemanagementvehiculeservice.entities.Brand;
import com.exemple.garagemanagementvehiculeservice.entities.Model;
import com.exemple.garagemanagementvehiculeservice.entities.Vehicule;
import com.exemple.garagemanagementvehiculeservice.exceptions.BusinessException;
import com.exemple.garagemanagementvehiculeservice.repositories.VehiculeRepository;
import com.exemple.garagemanagementvehiculeservice.services.BrandService;
import com.exemple.garagemanagementvehiculeservice.services.ModelService;
import com.exemple.garagemanagementvehiculeservice.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository, BrandService brandService, ModelService modelService){
        this.vehiculeRepository=vehiculeRepository;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @Override
    public List<Vehicule> getAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public List<Vehicule> getAllByOwner(String ownerId) {
        return vehiculeRepository.findByOwnerId(ownerId);
    }

    @Override
    public Vehicule createVehicule(VehiculeDto vehiculeDto) {
        Optional<Vehicule> existingVehicule=
                vehiculeRepository.findByRegistrationNumber(vehiculeDto.registrationNumber());
        if(existingVehicule.isPresent()){
            throw new BusinessException("Vehicule already exists");
        }
        Brand brand= brandService.getBrandByName(vehiculeDto.brand());
        Model model=modelService.getModelByName(vehiculeDto.model());
        Date purchaseDate = parseDate(vehiculeDto.purchaseDate());
        int year=Integer.parseInt(vehiculeDto.year());
        double mileage=Double.parseDouble(vehiculeDto.mileage());
        Vehicule vehicule = new Vehicule(
                vehiculeDto.registrationNumber(),
                year,
                vehiculeDto.color(),
                mileage,
                purchaseDate,
                vehiculeDto.ownerId(),
                brand,
                model,
                vehiculeDto.fuelType(),
                vehiculeDto.status()
        );
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule getById(Long id) {
        Optional<Vehicule> vehicule=vehiculeRepository.findById(id);
        if(vehicule.isEmpty()){
            throw new BusinessException("Vehicule Not found");
        }
        return vehicule.get();
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        Optional<Vehicule> existingVehicule=
                vehiculeRepository.findById(vehicule.getVin());
        if(existingVehicule.isEmpty()){
            throw new BusinessException("Vehicule not found");
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteVehicule(Long id) {
        Optional<Vehicule> vehicule=vehiculeRepository.findById(id);
        if(vehicule.isEmpty()){
            throw new BusinessException("Vehicule Not found");
        }
        vehiculeRepository.delete(vehicule.get());
    }


    private Date parseDate(String purchaseDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(purchaseDateStr.trim(), formatter);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
