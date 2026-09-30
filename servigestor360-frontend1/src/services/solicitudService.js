import axios from 'axios'

const API = 'http://localhost:8080/api/solicitudes'

const getAuthHeaders = () => {
  const token = localStorage.getItem('token')

  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
}

export const getSolicitudes = () =>
  axios.get(API, getAuthHeaders())

export const crearSolicitud = (idCliente, solicitud) =>
  axios.post(`${API}/cliente/${idCliente}`, solicitud, getAuthHeaders())

export const actualizarSolicitud = (idSolicitud, idCliente, solicitud) =>
  axios.put(`${API}/${idSolicitud}/cliente/${idCliente}`, solicitud, getAuthHeaders())

export const eliminarSolicitud = (idSolicitud) =>
  axios.delete(`${API}/${idSolicitud}`, getAuthHeaders())