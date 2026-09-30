import axios from 'axios'

const API = 'http://localhost:8080/api/clientes'

const getAuthHeaders = () => {
  const token = localStorage.getItem('token')

  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
}

export const getClientes = () => axios.get(API, getAuthHeaders())

export const crearCliente = (cliente) =>
  axios.post(API, cliente, getAuthHeaders())

export const actualizarCliente = (idCliente, cliente) =>
  axios.put(`${API}/${idCliente}`, cliente, getAuthHeaders())

export const eliminarCliente = (idCliente) =>
  axios.delete(`${API}/${idCliente}`, getAuthHeaders())