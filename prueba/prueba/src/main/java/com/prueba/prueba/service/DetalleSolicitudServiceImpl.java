package com.prueba.prueba.service;

import com.prueba.prueba.model.DetalleSolicitud;
import com.prueba.prueba.model.Servicio;
import com.prueba.prueba.model.SolicitudServicio;
import com.prueba.prueba.model.Tecnico;

import com.prueba.prueba.repository.DetalleSolicitudRepository;
import com.prueba.prueba.repository.ServicioRepository;
import com.prueba.prueba.repository.SolicitudServicioRepository;
import com.prueba.prueba.repository.TecnicoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleSolicitudServiceImpl
                implements DetalleSolicitudService {

        private final DetalleSolicitudRepository detalleSolicitudRepository;

        private final SolicitudServicioRepository solicitudServicioRepository;

        private final ServicioRepository servicioRepository;

        private final TecnicoRepository tecnicoRepository;

        public DetalleSolicitudServiceImpl(
                        DetalleSolicitudRepository detalleSolicitudRepository,
                        SolicitudServicioRepository solicitudServicioRepository,
                        ServicioRepository servicioRepository,
                        TecnicoRepository tecnicoRepository) {
                this.detalleSolicitudRepository = detalleSolicitudRepository;
                this.solicitudServicioRepository = solicitudServicioRepository;
                this.servicioRepository = servicioRepository;
                this.tecnicoRepository = tecnicoRepository;
        }

        @Override
        public List<DetalleSolicitud> listarDetalles() {
                return detalleSolicitudRepository.findAll();
        }

        @Override
        public Optional<DetalleSolicitud> buscarPorId(Long idDetalle) {
                return detalleSolicitudRepository.findById(idDetalle);
        }

        @Override
        public DetalleSolicitud guardarDetalle(
                        DetalleSolicitud detalleSolicitud) {

                Long idSolicitud = detalleSolicitud
                                .getSolicitud()
                                .getIdSolicitud();

                Long idServicio = detalleSolicitud
                                .getServicio()
                                .getIdServicio();

                Long idTecnico = detalleSolicitud
                                .getTecnico()
                                .getIdTecnico();

                SolicitudServicio solicitud = solicitudServicioRepository
                                .findById(idSolicitud)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Solicitud no encontrada"));

                Servicio servicio = servicioRepository
                                .findById(idServicio)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Servicio no encontrado"));

                Tecnico tecnico = tecnicoRepository
                                .findById(idTecnico)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Técnico no encontrado"));

                detalleSolicitud.setSolicitud(solicitud);

                detalleSolicitud.setServicio(servicio);

                detalleSolicitud.setTecnico(tecnico);

                if (detalleSolicitud.getPrecioUnitario() == null) {
                        detalleSolicitud.setPrecioUnitario(
                                        servicio.getPrecioBase());
                }

                if (detalleSolicitud.getCantidad() == null
                                || detalleSolicitud.getCantidad() < 1) {

                        throw new RuntimeException(
                                        "La cantidad debe ser mayor que cero");
                }

                BigDecimal subtotal = detalleSolicitud
                                .getPrecioUnitario()
                                .multiply(
                                                BigDecimal.valueOf(
                                                                detalleSolicitud.getCantidad()));

                detalleSolicitud.setSubtotal(subtotal);

                if (detalleSolicitud.getFechaAsignacion() == null) {
                        detalleSolicitud.setFechaAsignacion(
                                        LocalDate.now());
                }

                return detalleSolicitudRepository.save(
                                detalleSolicitud);
        }

        @Override
        public DetalleSolicitud actualizarDetalle(
                        Long idDetalle,
                        DetalleSolicitud detalleSolicitud) {

                DetalleSolicitud existente = detalleSolicitudRepository
                                .findById(idDetalle)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Detalle no encontrado"));

                Long idSolicitud = detalleSolicitud
                                .getSolicitud()
                                .getIdSolicitud();

                Long idServicio = detalleSolicitud
                                .getServicio()
                                .getIdServicio();

                Long idTecnico = detalleSolicitud
                                .getTecnico()
                                .getIdTecnico();

                SolicitudServicio solicitud = solicitudServicioRepository
                                .findById(idSolicitud)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Solicitud no encontrada"));

                Servicio servicio = servicioRepository
                                .findById(idServicio)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Servicio no encontrado"));

                Tecnico tecnico = tecnicoRepository
                                .findById(idTecnico)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Técnico no encontrado"));

                existente.setSolicitud(solicitud);

                existente.setServicio(servicio);

                existente.setTecnico(tecnico);

                existente.setCantidad(
                                detalleSolicitud.getCantidad());

                existente.setPrecioUnitario(
                                detalleSolicitud.getPrecioUnitario());

                existente.setObservaciones(
                                detalleSolicitud.getObservaciones());

                existente.setEstadoDetalle(
                                detalleSolicitud.getEstadoDetalle());

                existente.setFechaAsignacion(
                                detalleSolicitud.getFechaAsignacion());

                BigDecimal subtotal = existente
                                .getPrecioUnitario()
                                .multiply(
                                                BigDecimal.valueOf(
                                                                existente.getCantidad()));

                existente.setSubtotal(subtotal);

                // Guarda el registro
                DetalleSolicitud guardado = detalleSolicitudRepository.save(existente);

                // Lo consulta nuevamente con EntityGraph
                return detalleSolicitudRepository
                                .findDetalleByIdDetalle(guardado.getIdDetalle())
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "No fue posible consultar el detalle actualizado"));
        }

        @Override
        public void eliminarDetalle(Long idDetalle) {

                if (!detalleSolicitudRepository.existsById(idDetalle)) {

                        throw new RuntimeException(
                                        "Detalle no encontrado");
                }

                detalleSolicitudRepository.deleteById(idDetalle);
        }

        @Override
        public List<DetalleSolicitud> listarPorSolicitud(
                        Long idSolicitud) {
                return detalleSolicitudRepository
                                .findBySolicitudIdSolicitud(idSolicitud);
        }

        @Override
        public List<DetalleSolicitud> listarPorServicio(
                        Long idServicio) {
                return detalleSolicitudRepository
                                .findByServicioIdServicio(idServicio);
        }

        @Override
        public List<DetalleSolicitud> listarPorTecnico(
                        Long idTecnico) {
                return detalleSolicitudRepository
                                .findByTecnicoIdTecnico(idTecnico);
        }
}