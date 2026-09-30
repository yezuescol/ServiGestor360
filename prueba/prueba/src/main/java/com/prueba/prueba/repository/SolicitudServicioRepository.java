package com.prueba.prueba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.prueba.model.EstadoSolicitud;
import com.prueba.prueba.model.SolicitudServicio;

@Repository
public interface SolicitudServicioRepository
        extends JpaRepository<SolicitudServicio, Long> {

    @Override
    @EntityGraph(attributePaths = "cliente")
    List<SolicitudServicio> findAll();

    @EntityGraph(attributePaths = "cliente")
    Optional<SolicitudServicio> findSolicitudByIdSolicitud(
            Long idSolicitud
    );

    List<SolicitudServicio> findByClienteIdCliente(
            Long idCliente
    );

    List<SolicitudServicio> findByEstado(
            EstadoSolicitud estado
    );
}