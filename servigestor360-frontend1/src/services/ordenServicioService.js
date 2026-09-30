import axios from 'axios'

const API_URL = 'http://localhost:8080/api/ordenes'

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