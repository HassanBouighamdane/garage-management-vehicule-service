package com.exemple.garagemanagementvehiculeservice.dtos;

import com.exemple.garagemanagementvehiculeservice.entities.enums.FuelType;
import com.exemple.garagemanagementvehiculeservice.entities.enums.Status;

import java.util.Date;

public record VehiculeDto(
                          String registrationNumber,
                          int year,
                          String color,
                          float mileage,
                          String purchaseDate,
                          String ownerId,
                          String brand,
                          String model,
                          FuelType fuelType,
                          Status status) {
}
