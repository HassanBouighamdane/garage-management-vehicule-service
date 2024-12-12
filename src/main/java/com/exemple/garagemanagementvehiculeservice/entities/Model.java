package com.exemple.garagemanagementvehiculeservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="models")
@Getter @Setter
public class Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @ManyToOne
    private Brand brand;
}
