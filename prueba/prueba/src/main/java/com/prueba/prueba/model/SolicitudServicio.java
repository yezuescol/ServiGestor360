package com.prueba.prueba.model;

// Importa LocalDate para manejar fechas
import java.time.LocalDate;

// Importa las anotaciones de JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Importa validaciones
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*
    @Entity indica que esta clase será una tabla en la base de datos.
*/
@Entity

/*
    @Table define el nombre real de la tabla en MySQL.
*/
@Table(name = "solicitud_servicio")
public class SolicitudServicio {

    /*
        Llave primaria de la tabla solicitud_servicio.
    */
    @Id

    /*
        Genera el ID automáticamente con autoincremento.
    */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    /*
        Descripción de la solicitud.
        No puede estar vacía.
    */
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    /*
        Tipo de servicio solicitado.
        Ejemplo: Instalación, mantenimiento, reparación.
    */
    @NotBlank(message = "El tipo de servicio es obligatorio")
    @Size(max = 100, message = "El tipo de servicio no puede superar los 100 caracteres")
    @Column(name = "tipo_servicio", nullable = false, length = 100)
    private String tipoServicio;

    /*
        Estado de la solicitud.
        Se guarda como texto usando EnumType.STRING.
    */
    @NotNull(message = "El estado de la solicitud es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoSolicitud estado;

    /*
        Fecha de la solicitud.
        No puede ser una fecha pasada.
    */
    @NotNull(message = "La fecha de solicitud es obligatoria")
    //@FutureOrPresent(message = "La fecha de solicitud no puede ser anterior a la fecha actual")
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    /*
        Dirección donde se prestará el servicio.
    */
    @NotBlank(message = "La dirección del servicio es obligatoria")
    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    @Column(name = "direccion_servicio", nullable = false, length = 255)
    private String direccionServicio;

    /*
    Relación ManyToOne:
    Muchas solicitudes pertenecen a un solo cliente.
    */
    @ManyToOne(fetch = FetchType.LAZY)

    /*
        Crea la columna id_cliente como clave foránea hacia cliente.id_cliente.
    */
    /*@JsonIgnore*/
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    /*
        Constructor vacío obligatorio para JPA.
    */
    public SolicitudServicio() {
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getDireccionServicio() {
        return direccionServicio;
    }

    public void setDireccionServicio(String direccionServicio) {
        this.direccionServicio = direccionServicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
