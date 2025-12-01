package com.example.demo.repositorio;

import com.example.demo.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepository extends JpaRepository<Conductor, Long > {
}
