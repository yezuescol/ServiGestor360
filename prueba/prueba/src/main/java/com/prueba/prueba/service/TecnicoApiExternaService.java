package com.prueba.prueba.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TecnicoApiExternaService {

    private final RestTemplate restTemplate;

    public TecnicoApiExternaService() {
        this.restTemplate = new RestTemplate();
    }

    public String obtenerUsuariosExternos() {
        String url = "https://jsonplaceholder.typicode.com/users";
        return restTemplate.getForObject(url, String.class);
    }
}