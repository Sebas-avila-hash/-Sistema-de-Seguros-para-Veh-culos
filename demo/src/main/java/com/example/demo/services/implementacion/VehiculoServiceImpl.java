package com.example.demo.services.implementacion;

import com.example.demo.entity.Conductor;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repositorio.ConductorRepository;
import com.example.demo.repositorio.VehiculoRepository;
import com.example.demo.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ConductorRepository conductorRepository;


    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> buscarPorId(Long idVehiculo) {
        return vehiculoRepository.findById(idVehiculo);
    }

    @Override
    public Vehiculo actualizar(Long idVehiculo, Vehiculo vehiculoNuevo) {
        Vehiculo existente = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        existente.setPlaca(vehiculoNuevo.getPlaca());
        existente.setMarca(vehiculoNuevo.getMarca());
        existente.setModelo(vehiculoNuevo.getModelo());
        existente.setAnio(vehiculoNuevo.getAnio());
        existente.setColor(vehiculoNuevo.getColor());

        if (vehiculoNuevo.getConductor() != null) {
            Conductor c = conductorRepository.findById(
                    vehiculoNuevo.getConductor().getIdConductor()
            ).orElseThrow(() -> new RuntimeException("Conductor no encontrado"));

            // asignar en ambos lados
            existente.setConductor(c);
            c.setVehiculo(existente);
            conductorRepository.save(c);
        }

        return vehiculoRepository.save(existente);
    }

    @Override
    public void eliminar(Long idVehiculo) {
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));


        if (vehiculo.getConductor() != null) {
            Conductor c = vehiculo.getConductor();
            c.setVehiculo(null);
            vehiculo.setConductor(null);
            conductorRepository.save(c);
            vehiculoRepository.save(vehiculo);
        }

        vehiculoRepository.delete(vehiculo);
    }

}