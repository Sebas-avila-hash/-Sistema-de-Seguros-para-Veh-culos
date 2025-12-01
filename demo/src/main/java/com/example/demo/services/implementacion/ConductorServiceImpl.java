package com.example.demo.services.implementacion;

import com.example.demo.entity.Conductor;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repositorio.ConductorRepository;
import com.example.demo.repositorio.VehiculoRepository;
import com.example.demo.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Conductor guardar(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    @Override
    public List<Conductor> listar() {
        return conductorRepository.findAll();
    }

    @Override
    public Conductor actualizar(Long idConductor, Conductor conductorNuevo) {
        Conductor existente = conductorRepository.findById(idConductor)
                .orElseThrow(() -> new IllegalStateException("Conductor no encontrado"));

        existente.setNombre(conductorNuevo.getNombre());
        existente.setLicencia(conductorNuevo.getLicencia());
        existente.setTelefono(conductorNuevo.getTelefono());
        existente.setDireccion(conductorNuevo.getDireccion());
        existente.setActivo(conductorNuevo.getActivo());
        return conductorRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Conductor> conductorOpt = conductorRepository.findById(id);

        if (conductorOpt.isPresent()) {
            Conductor conductor = conductorOpt.get();

            // Si tiene vehículo, romper relación
            if (conductor.getVehiculo() != null) {
                Vehiculo vehiculo = conductor.getVehiculo();
                vehiculo.setConductor(null);
            }

            conductorRepository.delete(conductor);
        }
    }
}
