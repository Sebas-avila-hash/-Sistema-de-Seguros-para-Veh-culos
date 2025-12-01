package com.example.demo.controller;

import com.example.demo.entity.Vehiculo;
import com.example.demo.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/register")
    public ResponseEntity<?> guardar(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevo = vehiculoService.guardar(vehiculo);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listar() {
        List<Vehiculo> vehiculos = vehiculoService.listar();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoService.buscarPorId(id);
        return vehiculo.isPresent()
                ? ResponseEntity.ok(vehiculo.get())
                : ResponseEntity.status(404).body("Vehículo no encontrado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoDB = vehiculoService.actualizar(id, vehiculo);
            return ResponseEntity.ok(vehiculoDB);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.ok("Seguro eliminado correctamente");
    }
}