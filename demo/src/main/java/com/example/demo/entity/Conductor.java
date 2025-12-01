package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConductor")
    private Long idConductor;

    @Column(name="nombre")
    private String nombre;

    @Column(name="licencia")
    private String licencia;

    @Column(name="telefono")
    private String telefono;

    @Column(name="direccion")
    private String direccion;

    @Column(name="activo")
    private Boolean activo;

    @JsonIgnore
    @OneToOne(mappedBy = "conductor")
    private Vehiculo vehiculo;
}
