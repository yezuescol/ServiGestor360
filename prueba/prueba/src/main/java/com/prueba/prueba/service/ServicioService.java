package com.prueba.prueba.service;

import com.prueba.prueba.model.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> listarServicios();

    Optional<Servicio> buscarPorId(Long idServicio);

    Servicio guardarServicio(Servicio servicio);

    Servicio actualizarServicio(Long idServicio, Servicio servicio);

    void eliminarServicio(Long idServicio);

    Optional<Servicio> buscarPorNombreServicio(String nombreServicio);
}
