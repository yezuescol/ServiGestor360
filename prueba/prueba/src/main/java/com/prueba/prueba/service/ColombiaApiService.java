package com.prueba.prueba.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ColombiaApiService {

    private final RestTemplate restTemplate;

    public ColombiaApiService() {
        this.restTemplate = new RestTemplate();
    }

    public String obtenerDepartamentos() {
        String url = "https://api-colombia.com/api/v1/Department";
        return restTemplate.getForObject(url, String.class);
    }

    public String obtenerRegiones() {
        String url = "https://api-colombia.com/api/v1/Region";
        return restTemplate.getForObject(url, String.class);
    }

    public String obtenerMunicipiosPorDepartamento(Long idDepartamento) {
        String url = "https://api-colombia.com/api/v1/Department/"
                + idDepartamento
                + "/cities";

        return restTemplate.getForObject(url, String.class);
    }
}