import axios from 'axios'

const API = 'http://localhost:8080/api/detalles'

const getAuthHeaders = () => {

    const token = localStorage.getItem('token')

    return {

        headers: {

            Authorization: `Bearer ${token}`

        }

    }

}

// Obtener todos los detalles
export const getDetalles = () =>
    axios.get(API, getAuthHeaders())

// Crear detalle
export const crearDetalle = (detalle) =>
    axios.post(API, detalle, getAuthHeaders())

// Actualizar detalle
export const actualizarDetalle = (idDetalle, detalle) =>
    axios.put(`${API}/${idDetalle}`, detalle, getAuthHeaders())

// Eliminar detalle
export const eliminarDetalle = (idDetalle) =>
    axios.delete(`${API}/${idDetalle}`, getAuthHeaders())