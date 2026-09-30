package com.prueba.prueba.dto;

import java.math.BigDecimal;

public class DetalleOrdenResponse {

    private Long idDetalle;

    private Long idServicio;

    private String nombreServicio;

    private Long idTecnico;

    private String nombreTecnico;

    private String especialidadTecnico;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subtotal;

    private String observaciones;

    private String estadoDetalle;

    public DetalleOrdenResponse() {
    }

    public DetalleOrdenResponse(
            Long idDetalle,
            Long idServicio,
            String nombreServicio,
            Long idTecnico,
            String nombreTecnico,
            String especialidadTecnico,
            Integer cantidad,
            BigDecimal precioUnitario,
            BigDecimal subtotal,
            String observaciones,
            String estadoDetalle
    ) {
        this.idDetalle = idDetalle;
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.idTecnico = idTecnico;
        this.nombreTecnico = nombreTecnico;
        this.especialidadTecnico = especialidadTecnico;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.observaciones = observaciones;
        this.estadoDetalle = estadoDetalle;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Long getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Long idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getEspecialidadTecnico() {
        return especialidadTecnico;
    }

    public void setEspecialidadTecnico(String especialidadTecnico) {
        this.especialidadTecnico = especialidadTecnico;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(String estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }
}