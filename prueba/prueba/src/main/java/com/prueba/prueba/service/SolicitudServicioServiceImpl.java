package com.prueba.prueba.service;

// Importa List para manejar colecciones
import java.util.List;

// Importa Service para marcar esta clase como lógica de negocio
import org.springframework.stereotype.Service;

// Importa la excepción personalizada
import com.prueba.prueba.exception.RecursoNoEncontradoException;

// Importa las entidades necesarias
import com.prueba.prueba.model.Cliente;
import com.prueba.prueba.model.SolicitudServicio;

// Importa los repositorios necesarios
import com.prueba.prueba.repository.ClienteRepository;
import com.prueba.prueba.repository.SolicitudServicioRepository;

/*
    Implementación de la lógica de negocio
    para SolicitudServicio.
*/
@Service
public class SolicitudServicioServiceImpl implements SolicitudServicioService {

    /*
        Repository de solicitudes.
    */
    private final SolicitudServicioRepository solicitudServicioRepository;

    /*
        Repository de clientes.
        Se necesita para validar que el cliente exista.
    */
    private final ClienteRepository clienteRepository;

    /*
        Constructor para inyección de dependencias.
    */
    public SolicitudServicioServiceImpl(
            SolicitudServicioRepository solicitudServicioRepository,
            ClienteRepository clienteRepository) {
        this.solicitudServicioRepository = solicitudServicioRepository;
        this.clienteRepository = clienteRepository;
    }

    /*
        Lista todas las solicitudes registradas.
    */
    @Override
    public List<SolicitudServicio> listarSolicitudes() {
        return solicitudServicioRepository.findAll();
    }

    /*
        Busca una solicitud por su ID.
        Si no existe, lanza una excepción personalizada.
    */
    @Override
    public SolicitudServicio buscarSolicitudPorId(Long idSolicitud) {
        return solicitudServicioRepository.findById(idSolicitud)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Solicitud no encontrada con ID: " + idSolicitud));
    }

    /*
        Lista solicitudes asociadas a un cliente específico.
    */
    @Override
    public List<SolicitudServicio> listarSolicitudesPorCliente(Long idCliente) {

        // Verifica que el cliente exista antes de consultar sus solicitudes
        if (!clienteRepository.existsById(idCliente)) {
            throw new RecursoNoEncontradoException("Cliente no encontrado con ID: " + idCliente);
        }

        // Retorna las solicitudes asociadas al cliente
        return solicitudServicioRepository.findByClienteIdCliente(idCliente);
    }

    /*
        Crea una solicitud asociada a un cliente existente.
    */
    @Override
    public SolicitudServicio crearSolicitud(Long idCliente, SolicitudServicio solicitudServicio) {

        // Busca el cliente en la base de datos
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Cliente no encontrado con ID: " + idCliente));

        // Asocia el cliente encontrado a la solicitud
        solicitudServicio.setCliente(cliente);

        // Guarda la solicitud en la base de datos
        return solicitudServicioRepository.save(solicitudServicio);
    }

    /*
        Actualiza una solicitud existente.
    */
    @Override
    //public SolicitudServicio actualizarSolicitud(Long idSolicitud, SolicitudServicio solicitudServicio) {
    public SolicitudServicio actualizarSolicitud(Long idSolicitud, Long idCliente, SolicitudServicio solicitudServicio){
       
    // Busca la solicitud existente
        SolicitudServicio solicitudExistente = buscarSolicitudPorId(idSolicitud);
        
        // Busca el cliente seleccionado
        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "Cliente no encontrado con ID: " + idCliente));

        // Actualiza la descripción
        solicitudExistente.setDescripcion(solicitudServicio.getDescripcion());

        // Actualiza el tipo de servicio
        solicitudExistente.setTipoServicio(solicitudServicio.getTipoServicio());

        // Actualiza el estado
        solicitudExistente.setEstado(solicitudServicio.getEstado());

        // Actualiza la fecha
        solicitudExistente.setFechaSolicitud(solicitudServicio.getFechaSolicitud());

        // Actualiza la dirección
        solicitudExistente.setDireccionServicio(solicitudServicio.getDireccionServicio());

        // Actualiza el cliente asociado
        solicitudExistente.setCliente(cliente);


        // Guarda los cambios
        solicitudServicioRepository.save(solicitudExistente);

        // Retorna nuevamente la solicitud ya actualizada
        return solicitudServicioRepository.findById(idSolicitud)
        .orElseThrow(() -> new RecursoNoEncontradoException(
                "Solicitud no encontrada con ID: " + idSolicitud));
    }

    /*
        Elimina una solicitud por ID.
    */
    @Override
    public void eliminarSolicitud(Long idSolicitud) {

        // Valida que la solicitud exista
        SolicitudServicio solicitudExistente = buscarSolicitudPorId(idSolicitud);

        // Elimina la solicitud encontrada
        solicitudServicioRepository.delete(solicitudExistente);
    }
}
