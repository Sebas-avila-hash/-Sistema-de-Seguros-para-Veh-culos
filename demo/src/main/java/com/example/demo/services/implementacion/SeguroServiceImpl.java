package com.example.demo.services.implementacion;

import com.example.demo.entity.Seguro;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repositorio.ConductorRepository;
import com.example.demo.repositorio.SeguroRepository;
import com.example.demo.repositorio.VehiculoRepository;
import com.example.demo.services.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguroServiceImpl implements SeguroService {

    @Autowired
    private SeguroRepository seguroRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Seguro guardar(Seguro seguro) {

        if (seguro == null) {
            throw new RuntimeException("El objeto seguro no puede estar vacío");
        }

        if (seguro.getVehiculo() == null) {
            throw new RuntimeException("Debes enviar el objeto vehiculo");
        }

        if (seguro.getVehiculo().getIdVehiculo() == null) {
            throw new RuntimeException("Debes enviar el idVehiculo dentro de vehiculo");
        }

        Vehiculo vehiculo = vehiculoRepository.findById(
                seguro.getVehiculo().getIdVehiculo()
        ).orElseThrow(() -> new RuntimeException("El vehículo con ese id no existe."));

        seguro.setVehiculo(vehiculo);

        return seguroRepository.save(seguro);
    }

    @Override
    public List<Seguro> listar() {
        return seguroRepository.findAll();
    }

    @Override
    public Seguro actualizar(String idSeguro, Seguro seguroNuevo) {
        Seguro existente = seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new IllegalStateException("Seguro no encontrado"));

        existente.setCompania(seguroNuevo.getCompania());
        existente.setNumeroPoliza(seguroNuevo.getNumeroPoliza());
        existente.setFechaInicio(seguroNuevo.getFechaInicio());
        existente.setFechaVencimiento(seguroNuevo.getFechaVencimiento());

        return seguroRepository.save(existente);
    }

    @Override
    public void eliminar(String idSeguro) {
        seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new IllegalStateException("Seguro no encontrado"));

        seguroRepository.deleteById(idSeguro);
    }
    @Override
    public List<Seguro> buscarPorVehiculo(Long idVehiculo) {

        vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        return seguroRepository.findByVehiculo_IdVehiculo(idVehiculo);
    }
}

