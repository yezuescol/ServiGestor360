package com.prueba.prueba.controller;

// Importa la entidad Usuario
import com.prueba.prueba.model.Usuario;

// Importa la interfaz del servicio que contiene la lógica de negocio
import com.prueba.prueba.service.UsuarioService;

// Permite validar automáticamente los datos recibidos en el cuerpo de la petición
import jakarta.validation.Valid;

// Clase utilizada para construir respuestas HTTP personalizadas
import org.springframework.http.ResponseEntity;

// Importa las anotaciones necesarias para construir un controlador REST
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de administrar
 * todas las operaciones CRUD del módulo Usuario.
 *
 * Todas las peticiones serán atendidas bajo la URL:
 * http://localhost:8080/api/usuarios
 */
@RestController

// Define la ruta base del controlador
@RequestMapping("/api/usuarios")

// Permite que el Frontend desarrollado en React
// pueda consumir este servicio web.
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    /**
     * Inyección de dependencia del servicio Usuario.
     *
     * El controlador NO accede directamente al Repository,
     * sino que delega todas las operaciones al Service,
     * siguiendo la arquitectura por capas.
     */
    private final UsuarioService usuarioService;

    /**
     * Constructor utilizado por Spring Boot para
     * inyectar automáticamente el servicio.
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Lista todos los usuarios registrados.
     *
     * Método HTTP:
     * GET
     *
     * URL:
     * http://localhost:8080/api/usuarios
     *
     * Retorna una lista de usuarios.
     */
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    /**
     * Busca un usuario por su identificador.
     *
     * Método HTTP:
     * GET
     *
     * URL:
     * http://localhost:8080/api/usuarios/{id}
     *
     * Si el usuario existe devuelve HTTP 200.
     * Si no existe devuelve HTTP 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {

        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Registra un nuevo usuario.
     *
     * Método HTTP:
     * POST
     *
     * URL:
     * http://localhost:8080/api/usuarios
     *
     * @Valid verifica automáticamente
     * las validaciones definidas en la entidad Usuario.
     *
     * @RequestBody convierte el JSON recibido
     * en un objeto Usuario.
     */
    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {

        return usuarioService.guardarUsuario(usuario);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * Método HTTP:
     * PUT
     *
     * URL:
     * http://localhost:8080/api/usuarios/{id}
     *
     * Recibe:
     * - El ID del usuario.
     * - El objeto Usuario con la información actualizada.
     *
     * Devuelve HTTP 200 cuando la actualización
     * se realiza correctamente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(

            @PathVariable Long id,

            @Valid @RequestBody Usuario usuario) {

        Usuario actualizado =
                usuarioService.actualizarUsuario(id, usuario);

        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un usuario existente.
     *
     * Método HTTP:
     * DELETE
     *
     * URL:
     * http://localhost:8080/api/usuarios/{id}
     *
     * Devuelve HTTP 204 (No Content)
     * cuando la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}