package com.prueba.prueba.controller;

import com.prueba.prueba.service.ColombiaApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publica/colombia")
@CrossOrigin(origins = "http://localhost:5173")
public class ColombiaApiController {

    private final ColombiaApiService colombiaApiService;

    public ColombiaApiController(ColombiaApiService colombiaApiService) {
        this.colombiaApiService = colombiaApiService;
    }

    @GetMapping("/departamentos")
    public String obtenerDepartamentos() {
        return colombiaApiService.obtenerDepartamentos();
    }

    @GetMapping("/regiones")
    public String obtenerRegiones() {
        return colombiaApiService.obtenerRegiones();
    }

    @GetMapping("/departamentos/{idDepartamento}/municipios")
    public String obtenerMunicipiosPorDepartamento(
            @PathVariable Long idDepartamento) {

        return colombiaApiService
                .obtenerMunicipiosPorDepartamento(idDepartamento);
    }
}
