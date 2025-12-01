package com.example.demo.controller;

import com.example.demo.entity.Conductor;
import com.example.demo.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductor")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;


    @PostMapping("/register")
    public ResponseEntity<?> guardar(@RequestBody Conductor conductor) {
        Conductor nuevo = conductorService.guardar(conductor);
        return ResponseEntity.ok(nuevo);
    }


    @GetMapping("/list")
    public ResponseEntity<?> listar() {
        List<Conductor> conductores = conductorService.listar();
        return ResponseEntity.ok(conductores);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Conductor conductor) {
        try {
            // Crear objeto parcial con los datos nuevos
            Conductor actualizar = new Conductor();
            actualizar.setNombre(conductor.getNombre());
            actualizar.setLicencia(conductor.getLicencia());
            actualizar.setTelefono(conductor.getTelefono());
            actualizar.setDireccion(conductor.getDireccion());
            actualizar.setActivo(conductor.getActivo());

            Conductor conductorDB = conductorService.actualizar(id, actualizar);
            return ResponseEntity.ok(conductorDB);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        conductorService.eliminar(id);
        return ResponseEntity.ok("Conductor eliminado correctamente");
    }
}
