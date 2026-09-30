package com.prueba.prueba.controller;

// Importa List para manejar colecciones de clientes
import java.util.List;

// Importa Optional para manejar búsquedas que pueden devolver o no un cliente
import java.util.Optional;

// Importa ResponseEntity para construir respuestas HTTP más controladas
import org.springframework.http.ResponseEntity;

// Importa DeleteMapping para manejar solicitudes HTTP DELETE
import org.springframework.web.bind.annotation.DeleteMapping;

// Importa GetMapping para manejar solicitudes HTTP GET
import org.springframework.web.bind.annotation.GetMapping;

// Importa PathVariable para capturar valores enviados en la URL
import org.springframework.web.bind.annotation.PathVariable;

// Importa PostMapping para manejar solicitudes HTTP POST
import org.springframework.web.bind.annotation.PostMapping;

// Importa PutMapping para manejar solicitudes HTTP PUT
import org.springframework.web.bind.annotation.PutMapping;

// Importa RequestBody para recibir datos JSON enviados en el cuerpo de la petición
import org.springframework.web.bind.annotation.RequestBody;

// Importa RequestMapping para definir la ruta base del controlador
import org.springframework.web.bind.annotation.RequestMapping;

// Importa RestController para indicar que esta clase será una API REST
import org.springframework.web.bind.annotation.RestController;

// Importa la entidad Cliente
import com.prueba.prueba.model.Cliente;

// Importa la capa Service de Cliente
import com.prueba.prueba.service.ClienteService;

import org.springframework.web.bind.annotation.CrossOrigin;

/*
    @RestController indica que esta clase será un controlador REST.

    Esto significa que recibirá peticiones HTTP y devolverá respuestas
    normalmente en formato JSON.
*/
@CrossOrigin(origins = "http://localhost:5173")
@RestController

/*
    @RequestMapping define la ruta base del controlador.

    Todas las rutas de este controlador iniciarán con:
    /api/clientes
*/
@RequestMapping("/api/clientes")
public class ClienteController {

    /*
        Se declara una variable final de ClienteService.

        El Controller no debe acceder directamente al Repository.
        Por eso se comunica con la capa Service.
    */
    private final ClienteService clienteService;

    /*
        Constructor del Controller.

        Spring Boot inyecta automáticamente una instancia de ClienteService.
        Esta es una forma recomendada de inyección de dependencias.
    */
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /*
        Método para listar todos los clientes.

        @GetMapping indica que este método responderá
        a solicitudes HTTP GET.

        Ruta:
        GET http://localhost:8080/api/clientes
    */
    @GetMapping
    public List<Cliente> listarClientes() {

        // Llama al Service para obtener la lista de clientes
        return clienteService.listarClientes();
    }

    /*
        Método para buscar un cliente por ID.

        @PathVariable toma el valor del ID directamente desde la URL.

        Ruta:
        GET http://localhost:8080/api/clientes/1
    */
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long idCliente) {

        // Busca el cliente usando el Service
        Optional<Cliente> cliente = clienteService.buscarClientePorId(idCliente);

        /*
            Si el cliente existe, se devuelve con código HTTP 200 OK.
            Si no existe, se devuelve código HTTP 404 NOT FOUND.
        */
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
        Método para guardar un nuevo cliente.

        @PostMapping indica que este método responderá
        a solicitudes HTTP POST.

        @RequestBody permite recibir los datos JSON enviados
        desde Postman, frontend o una aplicación externa.

        Ruta:
        POST http://localhost:8080/api/clientes
    */
    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {

        // Envía el cliente al Service para guardarlo en la base de datos
        return clienteService.guardarCliente(cliente);
    }

    /*
        Método para actualizar un cliente existente.

        @PutMapping indica que este método responderá
        a solicitudes HTTP PUT.

        Ruta:
        PUT http://localhost:8080/api/clientes/1
    */
    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long idCliente,
            @RequestBody Cliente cliente) {

        // Busca si el cliente existe antes de actualizarlo
        Optional<Cliente> clienteExistente = clienteService.buscarClientePorId(idCliente);

        // Si el cliente no existe, devuelve 404 NOT FOUND
        if (clienteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Asigna el ID recibido en la URL al objeto cliente
        cliente.setIdCliente(idCliente);

        // Guarda los cambios usando la capa Service
        Cliente clienteActualizado = clienteService.guardarCliente(cliente);

        // Retorna el cliente actualizado con código 200 OK
        return ResponseEntity.ok(clienteActualizado);
    }

    /*
        Método para eliminar un cliente por ID.

        @DeleteMapping indica que este método responderá
        a solicitudes HTTP DELETE.

        Ruta:
        DELETE http://localhost:8080/api/clientes/1
    */
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long idCliente) {

        // Llama al Service para intentar eliminar el cliente
        boolean eliminado = clienteService.eliminarCliente(idCliente);

        /*
            Si se eliminó correctamente, devuelve 204 NO CONTENT.
            Si no existe el cliente, devuelve 404 NOT FOUND.
        */
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
