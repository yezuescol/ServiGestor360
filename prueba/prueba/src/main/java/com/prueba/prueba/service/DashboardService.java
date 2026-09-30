package com.prueba.prueba.service;

import com.prueba.prueba.dto.DashboardResumenDTO;

/*
 * ============================================================
 * INTERFAZ DashboardService
 * ============================================================
 *
 * Define las operaciones que estarán disponibles para obtener
 * la información del Dashboard.
 *
 * La implementación de esta interfaz se realizará en la clase
 * DashboardServiceImpl.
 *
 * Trabajar mediante interfaces es una buena práctica porque:
 *
 * - Desacopla el controlador de la implementación.
 * - Facilita el mantenimiento.
 * - Permite cambiar la implementación sin modificar el Controller.
 * - Facilita las pruebas unitarias.
 */

public interface DashboardService {

    /**
     * Obtiene un resumen general del sistema con los
     * principales indicadores que serán mostrados en
     * el Dashboard.
     *
     * @return DashboardResumenDTO con los indicadores del sistema.
     */
    DashboardResumenDTO obtenerResumen();

}