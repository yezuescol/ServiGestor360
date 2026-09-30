import axios from 'axios'

const API = 'http://localhost:8080/api/publica/colombia'

export const getDepartamentos = () =>
  axios.get(`${API}/departamentos`)

export const getRegiones = () =>
  axios.get(`${API}/regiones`)

export const getMunicipiosPorDepartamento = (idDepartamento) =>
  axios.get(`${API}/departamentos/${idDepartamento}/municipios`)