package com.prueba.prueba.service;

import com.prueba.prueba.model.DetalleSolicitud;

import java.util.List;
import java.util.Optional;

public interface DetalleSolicitudService {

    List<DetalleSolicitud> listarDetalles();

    Optional<DetalleSolicitud> buscarPorId(Long idDetalle);

    DetalleSolicitud guardarDetalle(DetalleSolicitud detalleSolicitud);

    DetalleSolicitud actualizarDetalle(
            Long idDetalle,
            DetalleSolicitud detalleSolicitud
    );

    void eliminarDetalle(Long idDetalle);

    List<DetalleSolicitud> listarPorSolicitud(Long idSolicitud);

    List<DetalleSolicitud> listarPorServicio(Long idServicio);

    List<DetalleSolicitud> listarPorTecnico(Long idTecnico);

}
