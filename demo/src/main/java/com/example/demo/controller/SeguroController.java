package com.example.demo.controller;

import com.example.demo.entity.Seguro;
import com.example.demo.services.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguro")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    @PostMapping("/register")
    public ResponseEntity<?> guardar(@RequestBody Seguro seguro){
        try {
            Seguro nuevo = seguroService.guardar(seguro);
            return ResponseEntity.ok(nuevo);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }


    @GetMapping("/list")
    public ResponseEntity<?> listar(){
        List<Seguro> seguros= seguroService.listar();
        return ResponseEntity.ok(seguros);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id,@RequestBody Seguro seguro){
        try{
            Seguro actualizar = new Seguro();
            actualizar.setCompania(seguro.getCompania());
            actualizar.setNumeroPoliza(seguro.getNumeroPoliza());
            actualizar.setFechaInicio(seguro.getFechaInicio());
            actualizar.setFechaVencimiento(seguro.getFechaVencimiento());

            Seguro seguroDB = seguroService.actualizar(id,actualizar);
            return ResponseEntity.ok(seguroDB);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id){
        seguroService.eliminar(id);
        return ResponseEntity.ok("Vehículo eliminado correctamente");
    }
    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<?> obtenerSegurosPorVehiculo(@PathVariable Long idVehiculo) {
        try {
            List<Seguro> seguros = seguroService.buscarPorVehiculo(idVehiculo);
            return ResponseEntity.ok(seguros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
