import { API_BASE_URL } from "../config/api";
import axios from 'axios'

const API = `${API_BASE_URL}/api/publica/colombia`;

export const getDepartamentos = () =>
  axios.get(`${API}/departamentos`)

export const getRegiones = () =>
  axios.get(`${API}/regiones`)

export const getMunicipiosPorDepartamento = (idDepartamento) =>
  axios.get(`${API}/departamentos/${idDepartamento}/municipios`)