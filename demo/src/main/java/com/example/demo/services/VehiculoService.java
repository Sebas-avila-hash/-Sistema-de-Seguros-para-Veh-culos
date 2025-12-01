package com.example.demo.services;

import com.example.demo.entity.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    Vehiculo guardar(Vehiculo vehiculo);
    List<Vehiculo> listar();
    Optional<Vehiculo> buscarPorId(Long idVehiculo);
    Vehiculo actualizar(Long idVehiculo, Vehiculo vehiculoNuevo);
    void eliminar(Long idVehiculo);

}
