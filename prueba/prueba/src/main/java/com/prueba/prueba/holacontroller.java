// Define el paquete donde está ubicada la clase
package com.prueba.prueba;

// Importa la anotación para manejar peticiones GET
import org.springframework.web.bind.annotation.GetMapping;

// Importa la anotación que convierte la clase en un controlador REST
import org.springframework.web.bind.annotation.RestController;

// Indica que esta clase será un controlador REST
@RestController
public class holacontroller {

    // Define la ruta raíz "/"
    @GetMapping("/")

    // Método que retorna un texto al navegador
    public String inicio() {

        // Texto que se mostrará en pantalla
        return "Hola, Spring Boot está funcionando correctamente";
    }
}
