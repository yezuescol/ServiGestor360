package com.prueba.prueba.controller;

import com.prueba.prueba.dto.OrdenServicioResponse;
import com.prueba.prueba.service.OrdenServicioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "http://localhost:5173")
public class OrdenServicioController {

    private final OrdenServicioService ordenServicioService;

    public OrdenServicioController(
            OrdenServicioService ordenServicioService) {

        this.ordenServicioService = ordenServicioService;
    }

    /*
     * Obtiene una Orden de Servicio completa
     * a partir del ID de la solicitud.
     */
    @GetMapping("/{idSolicitud}")
    public ResponseEntity<OrdenServicioResponse> obtenerOrdenServicio(

            @PathVariable Long idSolicitud) {

        OrdenServicioResponse orden =
                ordenServicioService
                        .obtenerOrdenPorSolicitud(idSolicitud);

        return ResponseEntity.ok(orden);
    }

}