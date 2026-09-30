package com.prueba.prueba.service;

import com.prueba.prueba.dto.OrdenServicioResponse;

public interface OrdenServicioService {

    OrdenServicioResponse obtenerOrdenPorSolicitud(
            Long idSolicitud
    );
}
