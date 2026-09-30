package com.prueba.prueba.controller;

import com.prueba.prueba.service.TecnicoApiExternaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publica/tecnicos-externos")
@CrossOrigin(origins = "http://localhost:5173")
public class TecnicoApiExternaController {

    private final TecnicoApiExternaService tecnicoApiExternaService;

    public TecnicoApiExternaController(TecnicoApiExternaService tecnicoApiExternaService) {
        this.tecnicoApiExternaService = tecnicoApiExternaService;
    }

    @GetMapping
    public String obtenerTecnicosExternos() {
        return tecnicoApiExternaService.obtenerUsuariosExternos();
    }
}