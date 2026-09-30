package com.prueba.prueba.model;

import java.util.ArrayList;
import java.util.List;
// Permite ocultar algunas propiedades internas de un objeto
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

// Importa la anotación Entity
// Permite convertir la clase en una tabla de base de datos
import jakarta.persistence.Entity;

// Importa la anotación Table
// Permite definir el nombre de la tabla
import jakarta.persistence.Table;

// Importa la anotación Id
// Define la llave primaria
import jakarta.persistence.Id;

// Importa GeneratedValue
// Permite generar IDs automáticos
import jakarta.persistence.GeneratedValue;

// Importa GenerationType
// Permite definir el tipo de generación del ID
import jakarta.persistence.GenerationType;

// Importa Column
// Permite configurar columnas de la tabla
import jakarta.persistence.Column;

/*
    @Entity indica que esta clase
    representa una tabla en la base de datos.
*/
@Entity

/*
    Define el nombre de la tabla en MySQL.
*/
@Table(name = "cliente")

// Clase Cliente
public class Cliente {

    /*
        @Id define la llave primaria.
    */
    @Id

    /*
        Genera automáticamente el ID
        usando autoincremento.
    */
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /*
        Variable que representa
        la llave primaria de la tabla.
    */
    private Long idCliente;

    /*
        Define la columna nombres.
    */
    @Column(name = "nombres")

    /*
        Variable nombres.
    */
    private String nombres;

    /*
        Define la columna apellidos.
    */
    @Column(name = "apellidos")

    /*
        Variable apellidos.
    */
    private String apellidos;

    /*
        Define la columna correo_electronico.
    */
    @Column(name = "correo_electronico")

    /*
        Variable correoElectronico.
    */
    private String correoElectronico;

    /*
    Relación OneToMany:
    Un cliente puede tener muchas solicitudes de servicio.

    mappedBy = "cliente" indica que la relación está controlada
    desde el atributo cliente en la entidad SolicitudServicio.
    */
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudServicio> solicitudes = new ArrayList<>();


    /*
        Constructor vacío obligatorio para JPA.
    */
    public Cliente() {

    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    /*
        Obtiene el ID del cliente.
    */
    public Long getIdCliente() {
        return idCliente;
    }

    /*
        Asigna el ID del cliente.
    */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /*
        Obtiene los nombres del cliente.
    */
    public String getNombres() {
        return nombres;
    }

    /*
        Asigna los nombres del cliente.
    */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /*
        Obtiene los apellidos del cliente.
    */
    public String getApellidos() {
        return apellidos;
    }

    /*
        Asigna los apellidos del cliente.
    */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /*
        Obtiene el correo electrónico.
    */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /*
        Asigna el correo electrónico.
    */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<SolicitudServicio> getSolicitudes() {
    return solicitudes;
}

    public void setSolicitudes(List<SolicitudServicio> solicitudes) {
        this.solicitudes = solicitudes;
    }

}