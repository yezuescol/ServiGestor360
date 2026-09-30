package com.prueba.prueba.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrdenServicioResponse {

    /*
     * Información de la orden
     */
    private Long idSolicitud;
    private LocalDate fechaSolicitud;
    private String estadoSolicitud;

    /*
     * Información del cliente
     */
    private Long idCliente;
    private String nombreCliente;
    private String correoCliente;

    /*
     * Información de la solicitud
     */
    private String descripcionSolicitud;
    private String tipoServicio;

    /*
     * Detalles de la orden
     */
    private List<DetalleOrdenResponse> detalles;

    /*
     * Total general
     */
    private BigDecimal totalGeneral;

    public OrdenServicioResponse() {
    }

    public OrdenServicioResponse(
            Long idSolicitud,
            LocalDate fechaSolicitud,
            String estadoSolicitud,
            Long idCliente,
            String nombreCliente,
            String correoCliente,
            String descripcionSolicitud,
            String tipoServicio,
            List<DetalleOrdenResponse> detalles,
            BigDecimal totalGeneral
    ) {
        this.idSolicitud = idSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.descripcionSolicitud = descripcionSolicitud;
        this.tipoServicio = tipoServicio;
        this.detalles = detalles;
        this.totalGeneral = totalGeneral;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public List<DetalleOrdenResponse> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenResponse> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }
}