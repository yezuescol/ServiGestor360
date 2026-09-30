import axios from "axios";

const API_URL = "http://localhost:8080/api/usuarios";

const getAuthHeaders = () => {
    const token = localStorage.getItem("token");

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};

export const listarUsuarios = () => {
    return axios.get(API_URL, getAuthHeaders());
};

export const crearUsuario = (usuario) => {
    return axios.post(API_URL, usuario, getAuthHeaders());
};

export const actualizarUsuario = (id, usuario) => {
    return axios.put(`${API_URL}/${id}`, usuario, getAuthHeaders());
};

export const eliminarUsuario = (id) => {
    return axios.delete(`${API_URL}/${id}`, getAuthHeaders());
};