import axios from "axios";

/*
 * ==========================================================
 * SERVICIO DEL DASHBOARD
 * ==========================================================
 *
 * Este archivo centraliza todas las peticiones HTTP
 * relacionadas con el Dashboard.
 *
 * Actualmente únicamente obtiene el resumen general,
 * pero posteriormente podremos agregar nuevos métodos,
 * por ejemplo:
 *
 * obtenerGraficas()
 * obtenerIndicadores()
 * obtenerActividadReciente()
 * obtenerEstadisticasMensuales()
 *
 * De esta manera mantenemos una arquitectura organizada
 * y escalable.
 */

const API_URL = "http://localhost:8080/api/dashboard";

/*
 * ==========================================================
 * Obtiene el token almacenado durante el inicio de sesión.
 * ==========================================================
 */

const obtenerToken = () => {

    return localStorage.getItem("token");

};

/*
 * ==========================================================
 * Construye el encabezado Authorization.
 * ==========================================================
 */

const obtenerHeaders = () => {

    return {

        headers: {

            Authorization: `Bearer ${obtenerToken()}`

        }

    };

};

/*
 * ==========================================================
 * Obtiene el resumen del Dashboard.
 * ==========================================================
 */

const obtenerResumen = async () => {

    const response = await axios.get(

        `${API_URL}/resumen`,

        obtenerHeaders()

    );

    return response.data;

};

/*
 * ==========================================================
 * Exportación del servicio
 * ==========================================================
 */

const dashboardService = {

    obtenerResumen

};

export default dashboardService;