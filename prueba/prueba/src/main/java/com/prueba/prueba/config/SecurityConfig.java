package com.prueba.prueba.config;

import java.util.List;

import com.prueba.prueba.security.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/*
 * ============================================================
 * CONFIGURACIÓN DE SEGURIDAD DE SERVIGESTOR360
 * ============================================================
 *
 * Esta clase configura:
 *
 * - Seguridad mediante JWT.
 * - Rutas públicas.
 * - Rutas protegidas por roles.
 * - Política CORS para React + Vite.
 * - Aplicación sin sesiones en el servidor.
 */

@Configuration
public class SecurityConfig {

    /*
     * Filtro personalizado encargado de interceptar
     * las peticiones HTTP y validar el token JWT.
     */
    private final JwtFilter jwtFilter;

    /*
     * Inyección de dependencias mediante constructor.
     */
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /*
     * ============================================================
     * CADENA DE FILTROS DE SEGURIDAD
     * ============================================================
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                /*
                 * Desactiva CSRF porque la aplicación utiliza
                 * una API REST con autenticación mediante JWT.
                 */
                .csrf(csrf -> csrf.disable())

                /*
                 * Habilita CORS y utiliza el Bean
                 * corsConfigurationSource definido más abajo.
                 */
                .cors(cors -> cors.configurationSource(
                        corsConfigurationSource()))

                /*
                 * Configura la aplicación sin sesiones.
                 *
                 * Cada petición debe enviar su propio token JWT.
                 */
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                /*
                 * Configuración de permisos y roles.
                 */
                .authorizeHttpRequests(auth -> auth

                        /*
                         * Rutas públicas.
                         */
                        .requestMatchers(
                                "/",
                                "/error",
                                "/error/**",
                                "/api/auth/**",
                                "/api/publica/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/webjars/**"
                        ).permitAll()

                        /*
                         * Rutas protegidas por rol.
                         */

                        .requestMatchers("/api/usuarios/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/clientes/**")
                        .hasAnyRole("ADMIN", "TECNICO")

                        .requestMatchers("/api/solicitudes/**")
                        .hasAnyRole("ADMIN", "TECNICO")

                        .requestMatchers("/api/tecnicos/**")
                        .hasAnyRole("ADMIN", "TECNICO")

                        .requestMatchers("/api/servicios/**")
                        .hasAnyRole("ADMIN", "TECNICO")

                        .requestMatchers("/api/detalles/**")
                        .hasAnyRole("ADMIN", "TECNICO")

                        .requestMatchers("/api/ordenes/**")
                        .hasAnyRole("ADMIN", "TECNICO")

                        .requestMatchers("/api/dashboard/**")
                        .hasAnyRole("ADMIN", "TECNICO", "CLIENTE")

                        /*
                         * Cualquier otra ruta requiere
                         * autenticación.
                         */
                        .anyRequest()
                        .authenticated()
                )

                /*
                 * Desactiva autenticación HTTP Basic.
                 */
                .httpBasic(httpBasic -> httpBasic.disable())

                /*
                 * Desactiva el formulario de inicio de sesión
                 * predeterminado de Spring Security.
                 */
                .formLogin(form -> form.disable())

                /*
                 * Agrega el filtro JWT antes del filtro estándar
                 * de autenticación de Spring Security.
                 */
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    /*
     * ============================================================
     * CONFIGURACIÓN CORS
     * ============================================================
     *
     * Permite que React + Vite, ejecutándose en el puerto 5173,
     * pueda consumir la API de Spring Boot en el puerto 8080.
     */

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        /*
         * Origen autorizado.
         */
        configuration.setAllowedOriginPatterns(
                List.of("*")
);

        /*
         * Métodos HTTP permitidos.
         */
        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
        );

        /*
         * Encabezados permitidos.
         *
         * Aquí se incluye Authorization para enviar el JWT.
         */
        configuration.setAllowedHeaders(
                List.of(
                        "Authorization",
                        "Content-Type",
                        "Accept",
                        "Origin",
                        "X-Requested-With"
                )
        );

        /*
         * Permite devolver encabezados al frontend.
         */
        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        /*
         * Permite el envío de credenciales.
         */
        configuration.setAllowCredentials(true);

        /*
         * Registra la configuración CORS
         * para todos los endpoints.
         */
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}