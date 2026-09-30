import axios from "axios";

const API_URL = "http://localhost:8080/api/auth";

/*
 * Realiza el inicio de sesión
 */
const login = async (correo, password) => {

    const response = await axios.post(
        `${API_URL}/login`,
        {
            correo,
            password
        }
    );

    return response.data;
};

/*
 * Cierra la sesión
 */
const logout = () => {

    localStorage.removeItem("token");
    localStorage.removeItem("usuario");
    localStorage.removeItem("rol");
};

/*
 * Obtiene el token almacenado
 */
const getToken = () => {

    return localStorage.getItem("token");
};

/*
 * Obtiene el rol
 */
const getRol = () => {

    return localStorage.getItem("rol");
};

/*
 * Obtiene el correo
 */
const getUsuario = () => {

    return localStorage.getItem("usuario");
};

/*
 * Verifica si existe una sesión activa
 */
const estaAutenticado = () => {

    return getToken() !== null;
};

export {

    login,
    logout,
    getToken,
    getRol,
    getUsuario,
    estaAutenticado
};