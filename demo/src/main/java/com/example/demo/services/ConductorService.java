package com.example.demo.services;

import com.example.demo.entity.Conductor;

import java.util.List;

public interface ConductorService {

    Conductor guardar(Conductor conductor);
    List<Conductor> listar();
    Conductor actualizar(Long idConductor, Conductor conductorNuevo);
    void eliminar(Long idConductor);
}
