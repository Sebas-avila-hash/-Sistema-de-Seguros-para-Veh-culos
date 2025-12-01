package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seguro {
    @Id
    @Column(name = "idSeguro")
    private String idSeguro = java.util.UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name= "idVehiculo", referencedColumnName = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "compania")
    private String compania;

    @Column(name = "numeroPoliza")
    private String numeroPoliza;

    @Column(name = "fechaInicio")
    private String fechaInicio;

    @Column(name = "fechaVencimiento")
    private String fechaVencimiento;
}
