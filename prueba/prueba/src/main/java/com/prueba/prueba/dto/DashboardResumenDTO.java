package com.prueba.prueba.dto;

/*
 * ============================================================
 * DTO DE RESUMEN DEL DASHBOARD
 * ============================================================
 *
 * Esta clase se utiliza para transportar hacia el frontend
 * los indicadores principales del sistema ServiGestor360.
 *
 * DTO significa Data Transfer Object.
 *
 * Su objetivo es agrupar en una sola respuesta los datos
 * necesarios para construir el Dashboard, evitando que React
 * tenga que realizar múltiples solicitudes al backend.
 *
 * Ejemplo de respuesta JSON:
 *
 * {
 *   "clientes": 10,
 *   "usuarios": 5,
 *   "tecnicos": 3,
 *   "servicios": 8,
 *   "solicitudesPendientes": 4,
 *   "ordenesActivas": 2,
 *   "serviciosFinalizados": 12
 * }
 */

public class DashboardResumenDTO {

    /*
     * Cantidad total de clientes
     * registrados en el sistema.
     */
    private long clientes;

    /*
     * Cantidad total de usuarios
     * registrados en el sistema.
     */
    private long usuarios;

    /*
     * Cantidad total de técnicos
     * registrados en el sistema.
     */
    private long tecnicos;

    /*
     * Cantidad total de servicios
     * disponibles en el catálogo.
     */
    private long servicios;

    /*
     * Cantidad de solicitudes que se
     * encuentran en estado pendiente.
     */
    private long solicitudesPendientes;

    /*
     * Cantidad de órdenes de servicio
     * que se encuentran activas.
     */
    private long ordenesActivas;

    /*
     * Cantidad de servicios u órdenes
     * que han sido finalizados.
     */
    private long serviciosFinalizados;

    /*
     * Constructor vacío.
     *
     * Es requerido por algunas herramientas
     * de serialización y deserialización como Jackson.
     */
    public DashboardResumenDTO() {
    }

    /*
     * Constructor completo.
     *
     * Permite crear el DTO asignando todos
     * los indicadores en una sola instrucción.
     */
    public DashboardResumenDTO(
            long clientes,
            long usuarios,
            long tecnicos,
            long servicios,
            long solicitudesPendientes,
            long ordenesActivas,
            long serviciosFinalizados
    ) {
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.tecnicos = tecnicos;
        this.servicios = servicios;
        this.solicitudesPendientes = solicitudesPendientes;
        this.ordenesActivas = ordenesActivas;
        this.serviciosFinalizados = serviciosFinalizados;
    }

    /*
     * ============================================================
     * MÉTODOS GET
     * ============================================================
     *
     * Permiten consultar los valores almacenados en el DTO.
     *
     * Jackson utiliza estos métodos para convertir
     * el objeto Java en una respuesta JSON.
     */

    public long getClientes() {
        return clientes;
    }

    public long getUsuarios() {
        return usuarios;
    }

    public long getTecnicos() {
        return tecnicos;
    }

    public long getServicios() {
        return servicios;
    }

    public long getSolicitudesPendientes() {
        return solicitudesPendientes;
    }

    public long getOrdenesActivas() {
        return ordenesActivas;
    }

    public long getServiciosFinalizados() {
        return serviciosFinalizados;
    }

    /*
     * ============================================================
     * MÉTODOS SET
     * ============================================================
     *
     * Permiten modificar los valores almacenados en el DTO.
     */

    public void setClientes(long clientes) {
        this.clientes = clientes;
    }

    public void setUsuarios(long usuarios) {
        this.usuarios = usuarios;
    }

    public void setTecnicos(long tecnicos) {
        this.tecnicos = tecnicos;
    }

    public void setServicios(long servicios) {
        this.servicios = servicios;
    }

    public void setSolicitudesPendientes(long solicitudesPendientes) {
        this.solicitudesPendientes = solicitudesPendientes;
    }

    public void setOrdenesActivas(long ordenesActivas) {
        this.ordenesActivas = ordenesActivas;
    }

    public void setServiciosFinalizados(long serviciosFinalizados) {
        this.serviciosFinalizados = serviciosFinalizados;
    }
}
