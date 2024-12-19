package com.exemple.garagemanagementvehiculeservice.entities;

import com.exemple.garagemanagementvehiculeservice.entities.enums.FuelType;
import com.exemple.garagemanagementvehiculeservice.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="vehicules")
public class Vehicule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long vin;
    @Column(unique=true)
    private String registrationNumber;
    private int year;
    private String color;
    private double mileage;
    private Date purchaseDate;
    private String ownerId;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Model model;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Vehicule(String registrationNumber, int year, String color, double mileage, Date purchaseDate,
                    String ownerId, Brand brand, Model model, FuelType fuelType, Status status) {
        this.registrationNumber = registrationNumber;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.purchaseDate = purchaseDate;
        this.ownerId = ownerId;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.status = status;
    }

}
