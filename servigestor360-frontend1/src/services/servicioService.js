import axios from 'axios'

const API = 'http://localhost:8080/api/servicios'

const getAuthHeaders = () => {
  const token = localStorage.getItem('token')

  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
}

export const getServicios = () =>
  axios.get(API, getAuthHeaders())

export const crearServicio = (servicio) =>
  axios.post(API, servicio, getAuthHeaders())

export const actualizarServicio = (idServicio, servicio) =>
  axios.put(
    `${API}/${idServicio}`,
    servicio,
    getAuthHeaders()
  )

export const eliminarServicio = (idServicio) =>
  axios.delete(
    `${API}/${idServicio}`,
    getAuthHeaders()
  )