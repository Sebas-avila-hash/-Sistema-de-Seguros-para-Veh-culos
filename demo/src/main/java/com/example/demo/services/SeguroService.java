package com.example.demo.services;

import com.example.demo.entity.Seguro;
import java.util.List;

public interface SeguroService {

    Seguro guardar(Seguro seguro);
    List<Seguro> listar();
    Seguro actualizar(String idSeguro, Seguro seguroNuevo);
    void eliminar(String idSeguro);
    List<Seguro> buscarPorVehiculo(Long idVehiculo);
}

