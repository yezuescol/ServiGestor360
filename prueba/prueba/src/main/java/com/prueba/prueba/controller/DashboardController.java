package com.prueba.prueba.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.dto.DashboardResumenDTO;
import com.prueba.prueba.service.DashboardService;

/*
 * ============================================================
 * CONTROLADOR REST DEL DASHBOARD
 * ============================================================
 *
 * Este controlador recibe las solicitudes HTTP relacionadas
 * con el Dashboard de ServiGestor360.
 *
 * Su responsabilidad es:
 *
 * 1. Recibir la petición enviada desde el frontend.
 * 2. Solicitar la información al DashboardService.
 * 3. Retornar el resumen mediante una respuesta HTTP en JSON.
 *
 * El controlador no consulta directamente los repositorios.
 * La lógica de negocio se mantiene en DashboardServiceImpl.
 */

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    /*
     * Servicio encargado de construir el resumen del Dashboard.
     *
     * Se declara como final porque la referencia será asignada
     * mediante el constructor y no debería modificarse después.
     */
    private final DashboardService dashboardService;

    /*
     * Inyección de dependencias mediante constructor.
     *
     * Spring identifica automáticamente la implementación
     * DashboardServiceImpl y la inyecta en este controlador.
     *
     * Esta forma es preferible a utilizar @Autowired directamente
     * sobre el atributo, porque:
     *
     * - Facilita las pruebas unitarias.
     * - Garantiza que la dependencia sea obligatoria.
     * - Permite declarar el atributo como final.
     * - Mejora la claridad y mantenibilidad del código.
     */
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /*
     * ============================================================
     * ENDPOINT: OBTENER RESUMEN DEL DASHBOARD
     * ============================================================
     *
     * Método HTTP:
     * GET
     *
     * Ruta completa:
     * GET /api/dashboard/resumen
     *
     * Este método solicita los indicadores al servicio y los
     * devuelve dentro de un ResponseEntity con estado HTTP 200.
     */
    @GetMapping("/resumen")
    public ResponseEntity<DashboardResumenDTO> obtenerResumen() {

        DashboardResumenDTO resumen = dashboardService.obtenerResumen();

        return ResponseEntity.ok(resumen);
    }
}