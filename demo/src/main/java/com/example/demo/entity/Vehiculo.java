package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehiculo")
    private Long idVehiculo;

    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name= "idConductor", referencedColumnName = "idConductor", nullable = true)
    private Conductor conductor;

    @JsonIgnore
    @OneToMany(mappedBy = "vehiculo")
    private List<Seguro> seguros;
}
