package com.exemple.garagemanagementvehiculeservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="brands")
@Getter @Setter
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    @OneToMany
    private List<Model> models;

}
