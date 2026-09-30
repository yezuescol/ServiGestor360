import { API_BASE_URL } from "../config/api";
import axios from 'axios'

const API = `${API_BASE_URL}/api/tecnicos`;

const getAuthHeaders = () => {
  const token = localStorage.getItem('token')

  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
}

export const getTecnicos = () =>
  axios.get(API, getAuthHeaders())

export const crearTecnico = (tecnico) =>
  axios.post(API, tecnico, getAuthHeaders())

export const actualizarTecnico = (idTecnico, tecnico) =>
  axios.put(`${API}/${idTecnico}`, tecnico, getAuthHeaders())

export const eliminarTecnico = (idTecnico) =>
  axios.delete(`${API}/${idTecnico}`, getAuthHeaders())