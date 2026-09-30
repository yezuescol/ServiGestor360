package com.prueba.prueba.controller;


// Importa List para retornar listas
import java.util.List;

// Importa ResponseEntity para manejar respuestas HTTP
import org.springframework.http.ResponseEntity;

// Importa anotaciones REST
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importa Valid para activar validaciones
import jakarta.validation.Valid;

// Importa la entidad SolicitudServicio
import com.prueba.prueba.model.SolicitudServicio;

// Importa el servicio
import com.prueba.prueba.service.SolicitudServicioService;

// Permite recibir peticiones desde React
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")


/*
    Controlador REST para gestionar solicitudes de servicio.
*/
@RestController

/*
    Ruta base del módulo SolicitudServicio.
*/
@RequestMapping("/api/solicitudes")
public class SolicitudServicioController {

    /*
        Servicio de solicitudes.
    */
    private final SolicitudServicioService solicitudServicioService;

    /*
        Constructor para inyección de dependencias.
    */
    public SolicitudServicioController(SolicitudServicioService solicitudServicioService) {
        this.solicitudServicioService = solicitudServicioService;
    }

    /*
        Lista todas las solicitudes.

        GET http://localhost:8080/api/solicitudes
    */
    @GetMapping
    public List<SolicitudServicio> listarSolicitudes() {
        return solicitudServicioService.listarSolicitudes();
    }

    /*
        Busca una solicitud por ID.

        GET http://localhost:8080/api/solicitudes/1
    */
    @GetMapping("/{idSolicitud}")
    public SolicitudServicio buscarSolicitudPorId(@PathVariable Long idSolicitud) {
        return solicitudServicioService.buscarSolicitudPorId(idSolicitud);
    }

    /*
        Lista solicitudes de un cliente específico.

        GET http://localhost:8080/api/solicitudes/cliente/1
    */
    @GetMapping("/cliente/{idCliente}")
    public List<SolicitudServicio> listarSolicitudesPorCliente(@PathVariable Long idCliente) {
        return solicitudServicioService.listarSolicitudesPorCliente(idCliente);
    }

    /*
        Crea una solicitud para un cliente existente.

        POST http://localhost:8080/api/solicitudes/cliente/1
    */
    @PostMapping("/cliente/{idCliente}")
    public SolicitudServicio crearSolicitud(
            @PathVariable Long idCliente,
            @Valid @RequestBody SolicitudServicio solicitudServicio) {
        return solicitudServicioService.crearSolicitud(idCliente, solicitudServicio);
    }

    /*
        Actualiza una solicitud existente.

        PUT http://localhost:8080/api/solicitudes/1
    */
    @PutMapping("/{idSolicitud}/cliente/{idCliente}")
    public ResponseEntity<String> actualizarSolicitud(
        @PathVariable Long idSolicitud,
        @PathVariable Long idCliente,
        @RequestBody SolicitudServicio solicitudServicio) {

    solicitudServicioService.actualizarSolicitud(idSolicitud, idCliente, solicitudServicio);

    return ResponseEntity.ok("Solicitud actualizada correctamente");
    }

   
    /*
        Elimina una solicitud por ID.

        DELETE http://localhost:8080/api/solicitudes/1
    */
    @DeleteMapping("/{idSolicitud}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long idSolicitud) {

        // Llama al servicio para eliminar
        solicitudServicioService.eliminarSolicitud(idSolicitud);

        // Retorna 204 No Content
        return ResponseEntity.noContent().build();
    }
}
