package com.prueba.prueba.repository;

import com.prueba.prueba.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    Optional<Tecnico> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}