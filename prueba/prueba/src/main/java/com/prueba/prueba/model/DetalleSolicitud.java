package com.prueba.prueba.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "detalle_solicitud")
public class DetalleSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudServicio solicitud;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "precio_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Size(max = 500)
    @Column(length = 500)
    private String observaciones;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_detalle", nullable = false, length = 30)
    private EstadoDetalle estadoDetalle;

    @NotNull
    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(
            Long idDetalle,
            SolicitudServicio solicitud,
            Servicio servicio,
            Tecnico tecnico,
            Integer cantidad,
            BigDecimal precioUnitario,
            BigDecimal subtotal,
            String observaciones,
            EstadoDetalle estadoDetalle,
            LocalDate fechaAsignacion) {
        this.idDetalle = idDetalle;
        this.solicitud = solicitud;
        this.servicio = servicio;
        this.tecnico = tecnico;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.observaciones = observaciones;
        this.estadoDetalle = estadoDetalle;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public SolicitudServicio getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudServicio solicitud) {
        this.solicitud = solicitud;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
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

    public EstadoDetalle getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(EstadoDetalle estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
