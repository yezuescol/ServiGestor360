package com.prueba.prueba.service;

// Importa List para manejar listas de solicitudes
import java.util.List;

// Importa la entidad SolicitudServicio
import com.prueba.prueba.model.SolicitudServicio;

/*
    Interfaz que define las operaciones del módulo SolicitudServicio.
*/
public interface SolicitudServicioService {

    /*
        Lista todas las solicitudes.
    */
    List<SolicitudServicio> listarSolicitudes();

    /*
        Busca una solicitud por ID.
    */
    SolicitudServicio buscarSolicitudPorId(Long idSolicitud);

    /*
        Lista solicitudes por cliente.
    */
    List<SolicitudServicio> listarSolicitudesPorCliente(Long idCliente);

    /*
        Crea una solicitud asociada a un cliente existente.
    */
    SolicitudServicio crearSolicitud(Long idCliente, SolicitudServicio solicitudServicio);

    /*
        Actualiza una solicitud existente.
    */
    //SolicitudServicio actualizarSolicitud(Long idSolicitud, SolicitudServicio solicitudServicio);
    SolicitudServicio actualizarSolicitud(Long idSolicitud, Long idCliente, SolicitudServicio solicitudServicio);
    /*
        Elimina una solicitud por ID.
    */
    void eliminarSolicitud(Long idSolicitud);
}
