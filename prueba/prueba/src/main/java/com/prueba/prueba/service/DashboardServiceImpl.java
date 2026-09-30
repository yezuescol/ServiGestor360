package com.prueba.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.prueba.dto.DashboardResumenDTO;
import com.prueba.prueba.repository.ClienteRepository;
import com.prueba.prueba.repository.DetalleSolicitudRepository;
import com.prueba.prueba.repository.ServicioRepository;
import com.prueba.prueba.repository.SolicitudServicioRepository;
import com.prueba.prueba.repository.TecnicoRepository;
import com.prueba.prueba.repository.UsuarioRepository;

/*
 * ============================================================
 * IMPLEMENTACIÓN DEL SERVICIO DEL DASHBOARD
 * ============================================================
 *
 * Esta clase contiene la lógica de negocio necesaria para
 * construir el resumen del Dashboard.
 *
 * Consulta los diferentes repositorios, obtiene los indicadores
 * principales y construye un DashboardResumenDTO que será
 * enviado al frontend.
 */

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private SolicitudServicioRepository solicitudServicioRepository;

    @Autowired
    private DetalleSolicitudRepository detalleSolicitudRepository;

    @Override
    public DashboardResumenDTO obtenerResumen() {

        DashboardResumenDTO resumen = new DashboardResumenDTO();

        // Total de clientes
        resumen.setClientes(clienteRepository.count());

        // Total de usuarios
        resumen.setUsuarios(usuarioRepository.count());

        // Total de técnicos
        resumen.setTecnicos(tecnicoRepository.count());

        // Total de servicios
        resumen.setServicios(servicioRepository.count());

        /*
         * Por el momento utilizaremos el total de solicitudes
         * registradas. Más adelante podremos cambiar esta lógica
         * para contar únicamente las solicitudes con estado
         * "PENDIENTE".
         */
        resumen.setSolicitudesPendientes(solicitudServicioRepository.count());

        /*
         * Mientras implementamos el módulo completo de órdenes,
         * utilizaremos la cantidad de detalles de solicitud
         * como indicador temporal.
         */
        resumen.setOrdenesActivas(detalleSolicitudRepository.count());

        /*
         * Valor temporal.
         * Posteriormente se reemplazará por una consulta real
         * de servicios finalizados.
         */
        resumen.setServiciosFinalizados(0);

        return resumen;
    }
}