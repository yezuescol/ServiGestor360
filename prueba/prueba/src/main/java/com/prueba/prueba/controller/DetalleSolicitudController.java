package com.prueba.prueba.controller;

import com.prueba.prueba.model.DetalleSolicitud;
import com.prueba.prueba.service.DetalleSolicitudService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.prueba.prueba.dto.DetalleSolicitudResponse;

@RestController
@RequestMapping("/api/detalles")
@CrossOrigin(origins = "http://localhost:5173")
public class DetalleSolicitudController {

    private final DetalleSolicitudService detalleSolicitudService;

    public DetalleSolicitudController(
            DetalleSolicitudService detalleSolicitudService) {

        this.detalleSolicitudService = detalleSolicitudService;
    }

    private DetalleSolicitudResponse convertirAResponse(
            DetalleSolicitud detalle) {

        DetalleSolicitudResponse response = new DetalleSolicitudResponse();

        response.setIdDetalle(detalle.getIdDetalle());

        response.setIdSolicitud(
                detalle.getSolicitud().getIdSolicitud());

        response.setDescripcionSolicitud(
                detalle.getSolicitud().getDescripcion());

        response.setIdServicio(
                detalle.getServicio().getIdServicio());

        response.setNombreServicio(
                detalle.getServicio().getNombreServicio());

        response.setIdTecnico(
                detalle.getTecnico().getIdTecnico());

        response.setNombreTecnico(
                detalle.getTecnico().getNombres()
                        + " "
                        + detalle.getTecnico().getApellidos());

        response.setCantidad(detalle.getCantidad());
        response.setPrecioUnitario(detalle.getPrecioUnitario());
        response.setSubtotal(detalle.getSubtotal());
        response.setObservaciones(detalle.getObservaciones());
        response.setEstadoDetalle(detalle.getEstadoDetalle());
        response.setFechaAsignacion(detalle.getFechaAsignacion());

        return response;
    }

    /*
     * Lista todos los detalles registrados
     */
    @GetMapping
    public ResponseEntity<List<DetalleSolicitudResponse>> listarDetalles() {

        List<DetalleSolicitudResponse> respuestas = detalleSolicitudService
                .listarDetalles()
                .stream()
                .map(this::convertirAResponse)
                .toList();

        return ResponseEntity.ok(respuestas);
    }

    /*
     * Buscar un detalle por ID
     */
    @GetMapping("/{idDetalle}")
    public ResponseEntity<DetalleSolicitud> buscarPorId(
            @PathVariable Long idDetalle) {

        return detalleSolicitudService.buscarPorId(idDetalle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Crear un nuevo detalle
     */
    @PostMapping
    public ResponseEntity<DetalleSolicitudResponse> crearDetalle(
            @Valid @RequestBody DetalleSolicitud detalleSolicitud) {

        DetalleSolicitud guardado = detalleSolicitudService.guardarDetalle(
                detalleSolicitud);

        DetalleSolicitudResponse response = convertirAResponse(guardado);

        return ResponseEntity.ok(response);
    }

    /*
     * Actualizar un detalle
     */
    @PutMapping("/{idDetalle}")
    public ResponseEntity<DetalleSolicitudResponse> actualizarDetalle(
            @PathVariable Long idDetalle,
            @Valid @RequestBody DetalleSolicitud detalleSolicitud) {

        DetalleSolicitud actualizado = detalleSolicitudService.actualizarDetalle(
                idDetalle,
                detalleSolicitud);

        DetalleSolicitudResponse response = convertirAResponse(actualizado);

        return ResponseEntity.ok(response);
    }

    /*
     * Eliminar un detalle
     */
    @DeleteMapping("/{idDetalle}")
    public ResponseEntity<Void> eliminarDetalle(
            @PathVariable Long idDetalle) {

        detalleSolicitudService.eliminarDetalle(idDetalle);

        return ResponseEntity.noContent().build();
    }

    /*
     * Consultar todos los detalles
     * pertenecientes a una solicitud
     */
    @GetMapping("/solicitud/{idSolicitud}")
    public List<DetalleSolicitud> listarPorSolicitud(

            @PathVariable Long idSolicitud) {

        return detalleSolicitudService
                .listarPorSolicitud(idSolicitud);
    }

    /*
     * Consultar todos los detalles
     * de un servicio
     */
    @GetMapping("/servicio/{idServicio}")
    public List<DetalleSolicitud> listarPorServicio(

            @PathVariable Long idServicio) {

        return detalleSolicitudService
                .listarPorServicio(idServicio);
    }

    /*
     * Consultar todos los detalles
     * asignados a un técnico
     */
    @GetMapping("/tecnico/{idTecnico}")
    public List<DetalleSolicitud> listarPorTecnico(

            @PathVariable Long idTecnico) {

        return detalleSolicitudService
                .listarPorTecnico(idTecnico);
    }

    @GetMapping("/prueba")
    public ResponseEntity<String> prueba() {
        return ResponseEntity.ok("DetalleSolicitudController funcionando");
    }

}