package com.example.demo.repositorio;

import com.example.demo.entity.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguroRepository extends JpaRepository<Seguro, String > {
    List<Seguro> findByVehiculo_IdVehiculo(Long idVehiculo);
}
