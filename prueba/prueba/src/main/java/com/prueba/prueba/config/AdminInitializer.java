package com.prueba.prueba.config;

import com.prueba.prueba.model.Usuario;
import com.prueba.prueba.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Value("${BOOTSTRAP_ADMIN_NOMBRES:}")
    private String nombres;

    @Value("${BOOTSTRAP_ADMIN_APELLIDOS:}")
    private String apellidos;

    @Value("${BOOTSTRAP_ADMIN_EMAIL:}")
    private String correo;

    @Value("${BOOTSTRAP_ADMIN_PASSWORD:}")
    private String password;

    public AdminInitializer(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {

        if (nombres.isBlank()
                || apellidos.isBlank()
                || correo.isBlank()
                || password.isBlank()) {

            System.out.println(
                    "Administrador inicial no configurado."
            );
            return;
        }

        if (usuarioRepository.existsByCorreo(correo)) {
            System.out.println(
                    "El administrador inicial ya existe."
            );
            return;
        }

        Usuario administrador = new Usuario(
                null,
                nombres,
                apellidos,
                correo,
                password,
                "ADMIN",
                true
        );

        usuarioRepository.save(administrador);

        System.out.println(
                "Administrador inicial creado correctamente."
        );
    }
}