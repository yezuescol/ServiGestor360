package com.prueba.prueba.service;

import com.prueba.prueba.dto.DetalleOrdenResponse;
import com.prueba.prueba.dto.OrdenServicioResponse;
import com.prueba.prueba.model.Cliente;
import com.prueba.prueba.model.DetalleSolicitud;
import com.prueba.prueba.model.SolicitudServicio;
import com.prueba.prueba.repository.DetalleSolicitudRepository;
import com.prueba.prueba.repository.SolicitudServicioRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdenServicioServiceImpl
                implements OrdenServicioService {

        private final SolicitudServicioRepository solicitudServicioRepository;
        private final DetalleSolicitudRepository detalleSolicitudRepository;

        public OrdenServicioServiceImpl(
                        SolicitudServicioRepository solicitudServicioRepository,
                        DetalleSolicitudRepository detalleSolicitudRepository) {
                this.solicitudServicioRepository = solicitudServicioRepository;
                this.detalleSolicitudRepository = detalleSolicitudRepository;
        }

        @Override
        public OrdenServicioResponse obtenerOrdenPorSolicitud(
                        Long idSolicitud) {

                /*
                 * Consulta la solicitud principal.
                 */
                SolicitudServicio solicitud = solicitudServicioRepository
                                .findSolicitudByIdSolicitud(idSolicitud)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Solicitud no encontrada"));

                /*
                 * Obtiene el cliente relacionado con la solicitud.
                 */
                Cliente cliente = solicitud.getCliente();

                /*
                 * Consulta todos los detalles asociados
                 * a la solicitud.
                 */
                List<DetalleSolicitud> detalles = detalleSolicitudRepository
                                .findBySolicitudIdSolicitud(idSolicitud);

                /*
                 * Convierte cada DetalleSolicitud
                 * en un DetalleOrdenResponse.
                 */
                List<DetalleOrdenResponse> detallesResponse = detalles
                                .stream()
                                .map(this::convertirDetalle)
                                .toList();

                /*
                 * Calcula el total general sumando
                 * todos los subtotales.
                 */
                BigDecimal totalGeneral = detallesResponse
                                .stream()
                                .map(DetalleOrdenResponse::getSubtotal)
                                .filter(subtotal -> subtotal != null)
                                .reduce(
                                                BigDecimal.ZERO,
                                                BigDecimal::add);

                /*
                 * Construye el DTO principal.
                 */
                OrdenServicioResponse orden = new OrdenServicioResponse();

                orden.setIdSolicitud(
                                solicitud.getIdSolicitud());

                orden.setFechaSolicitud(
                                solicitud.getFechaSolicitud());

                orden.setEstadoSolicitud(
                                solicitud.getEstado().name());

                orden.setIdCliente(
                                cliente.getIdCliente());

                orden.setNombreCliente(
                                cliente.getNombres()
                                                + " "
                                                + cliente.getApellidos());

                orden.setCorreoCliente(
                                cliente.getCorreoElectronico());

                /*
                 * Ajusta este getter si en Cliente.java
                 * el campo tiene otro nombre.
                 */

                orden.setDescripcionSolicitud(
                                solicitud.getDescripcion());

                orden.setTipoServicio(
                                solicitud.getTipoServicio());

                orden.setDetalles(
                                detallesResponse);

                orden.setTotalGeneral(
                                totalGeneral);

                return orden;
        }

        /*
         * Convierte una entidad DetalleSolicitud
         * en un DTO DetalleOrdenResponse.
         */
        private DetalleOrdenResponse convertirDetalle(
                        DetalleSolicitud detalle) {

                DetalleOrdenResponse response = new DetalleOrdenResponse();

                response.setIdDetalle(
                                detalle.getIdDetalle());

                response.setIdServicio(
                                detalle.getServicio().getIdServicio());

                response.setNombreServicio(
                                detalle.getServicio().getNombreServicio());

                response.setIdTecnico(
                                detalle.getTecnico().getIdTecnico());

                response.setNombreTecnico(
                                detalle.getTecnico().getNombres()
                                                + " "
                                                + detalle.getTecnico().getApellidos());

                response.setEspecialidadTecnico(
                                detalle.getTecnico().getEspecialidad());

                response.setCantidad(
                                detalle.getCantidad());

                response.setPrecioUnitario(
                                detalle.getPrecioUnitario());

                response.setSubtotal(
                                detalle.getSubtotal());

                response.setObservaciones(
                                detalle.getObservaciones());

                response.setEstadoDetalle(
                                detalle.getEstadoDetalle().name());

                return response;
        }
}
