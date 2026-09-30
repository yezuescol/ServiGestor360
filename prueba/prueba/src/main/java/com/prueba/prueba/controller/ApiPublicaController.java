package com.prueba.prueba.controller;

import com.prueba.prueba.service.ApiPublicaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publica")
@CrossOrigin(origins = "http://localhost:5173")
public class ApiPublicaController {

    private final ApiPublicaService apiPublicaService;

    public ApiPublicaController(ApiPublicaService apiPublicaService) {
        this.apiPublicaService = apiPublicaService;
    }

    @GetMapping("/usuarios")
    public String obtenerUsuariosExternos() {
        return apiPublicaService.obtenerUsuariosExternos();
    }
}