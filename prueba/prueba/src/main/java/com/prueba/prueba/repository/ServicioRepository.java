package com.prueba.prueba.repository;

import com.prueba.prueba.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Optional<Servicio> findByNombreServicio(String nombreServicio);

    boolean existsByNombreServicio(String nombreServicio);
}