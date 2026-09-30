package com.prueba.prueba.controller;

import com.prueba.prueba.model.Servicio;
import com.prueba.prueba.service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "http://localhost:5173")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<Servicio> listarServicios() {
        return servicioService.listarServicios();
    }

    @GetMapping("/{idServicio}")
    public ResponseEntity<Servicio> buscarPorId(@PathVariable Long idServicio) {

        return servicioService.buscarPorId(idServicio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servicio crearServicio(
            @Valid @RequestBody Servicio servicio) {

        return servicioService.guardarServicio(servicio);
    }

    @PutMapping("/{idServicio}")
    public ResponseEntity<Servicio> actualizarServicio(
            @PathVariable Long idServicio,
            @Valid @RequestBody Servicio servicio) {

        Servicio actualizado =
                servicioService.actualizarServicio(idServicio, servicio);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idServicio}")
    public ResponseEntity<Void> eliminarServicio(
            @PathVariable Long idServicio) {

        servicioService.eliminarServicio(idServicio);

        return ResponseEntity.noContent().build();
    }
}
