package com.prueba.prueba.controller;

import com.prueba.prueba.dto.LoginRequest;
import com.prueba.prueba.dto.LoginResponse;
import com.prueba.prueba.model.Usuario;
import com.prueba.prueba.repository.UsuarioRepository;
import com.prueba.prueba.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario no encontrado");
        }

        if (usuario.getActivo() == null || !usuario.getActivo()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario inactivo");
        }

        if (!usuario.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(usuario.getCorreo(), usuario.getRol());

        LoginResponse response = new LoginResponse(
                "Autenticación satisfactoria",
                usuario.getCorreo(),
                usuario.getRol(),
                token,
                "Bearer"
        );

        return ResponseEntity.ok(response);
    }
}