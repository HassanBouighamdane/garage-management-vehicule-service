package com.exemple.garagemanagementvehiculeservice.dtos;

import com.exemple.garagemanagementvehiculeservice.entities.enums.FuelType;
import com.exemple.garagemanagementvehiculeservice.entities.enums.Status;


public record VehiculeDto(
                          String registrationNumber,
                          String year,
                          String color,
                          String mileage,
                          String purchaseDate,
                          String ownerId,
                          String brand,
                          String model,
                          FuelType fuelType,
                          Status status) {
}
