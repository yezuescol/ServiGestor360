import { API_BASE_URL } from "../config/api";
import axios from 'axios'

const API_URL = `${API_BASE_URL}/api/ordenes`;

const obtenerOrdenServicio = (idSolicitud) => {

    const token = localStorage.getItem('token')

    return axios.get(

        `${API_URL}/${idSolicitud}`,

        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    )
}

export {
    obtenerOrdenServicio
}