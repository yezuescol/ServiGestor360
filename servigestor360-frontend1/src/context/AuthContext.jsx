import {
  createContext,
  useEffect,
  useState
} from "react";

import {
  login as loginService,
  logout as logoutService,
  getToken,
  getRol,
  getUsuario
} from "../services/authService";

/*
 * ==========================================================
 * CONTEXTO GLOBAL DE AUTENTICACIÓN
 * ==========================================================
 *
 * Este contexto administra:
 *
 * - Token JWT
 * - Usuario autenticado
 * - Rol
 * - Inicio de sesión
 * - Cierre de sesión
 * - Estado de autenticación
 *
 * ==========================================================
 */

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {

  /*
   * ==========================================
   * Estados Globales
   * ==========================================
   */

  const [token, setToken] = useState(getToken());

  const [usuario, setUsuario] = useState(getUsuario());

  const [rol, setRol] = useState(getRol());

  const [autenticado, setAutenticado] =
    useState(Boolean(getToken()));

  const [cargando, setCargando] =
    useState(false);

  const [error, setError] =
    useState("");

  /*
   * ==========================================
   * Mantiene sincronizado el estado
   * cuando cambia el Token.
   * ==========================================
   */

  useEffect(() => {

    setAutenticado(Boolean(token));

  }, [token]);

  /*
   * ==========================================
   * Inicio de Sesión
   * ==========================================
   */

  const iniciarSesion = async (
    correo,
    password
  ) => {

    try {

      setCargando(true);

      setError("");

      /*
       * Solicita autenticación
       * al Backend.
       */

      const respuesta =
        await loginService(
          correo,
          password
        );

      /*
       * ==========================================
       * Validación de la respuesta.
       * ==========================================
       */

      if (
        !respuesta ||
        !respuesta.token ||
        !respuesta.correo ||
        !respuesta.rol
      ) {

        throw new Error(
          "La respuesta del servidor es inválida."
        );

      }

      /*
       * ==========================================
       * Normaliza el Rol
       *
       * Si el backend devuelve:
       *
       * ROLE_ADMIN
       *
       * quedará:
       *
       * ADMIN
       * ==========================================
       */

      const rolNormalizado =
        respuesta.rol
          .replace("ROLE_", "")
          .toUpperCase();

      /*
       * ==========================================
       * Guarda la sesión.
       * ==========================================
       */

      localStorage.setItem(
        "token",
        respuesta.token
      );

      localStorage.setItem(
        "usuario",
        respuesta.correo
      );

      localStorage.setItem(
        "rol",
        rolNormalizado
      );

      /*
       * ==========================================
       * Actualiza el Contexto.
       * ==========================================
       */

      setToken(
        respuesta.token
      );

      setUsuario(
        respuesta.correo
      );

      setRol(
        rolNormalizado
      );

      setAutenticado(true);

      return {

        exito: true,

        datos: respuesta

      };

    }
    catch (error) {

      console.error(
        "Error al iniciar sesión:",
        error
      );

      let mensaje =
        "No fue posible iniciar sesión.";

      if (
        error.response?.status === 401
      ) {

        mensaje =
          error.response.data;

      }

      else if (
        error.response?.status === 403
      ) {

        mensaje =
          "Acceso denegado.";

      }

      else if (
        error.code === "ERR_NETWORK"
      ) {

        mensaje =
          "No fue posible conectar con el servidor.";

      }

      else if (
        error.message
      ) {

        mensaje =
          error.message;

      }

      /*
       * Limpia la sesión
       * por seguridad.
       */

      logoutService();

      setToken(null);

      setUsuario(null);

      setRol(null);

      setAutenticado(false);

      setError(
        mensaje
      );

      return {

        exito: false,

        mensaje

      };

    }

    finally {

      setCargando(false);

    }

  };

  /*
   * ==========================================
   * Cerrar Sesión
   * ==========================================
   */

  const cerrarSesion = () => {

    logoutService();

    setToken(null);

    setUsuario(null);

    setRol(null);

    setAutenticado(false);

    setError("");

  };

  /*
   * ==========================================
   * Verifica Roles
   * ==========================================
   */

  const tieneRol = (...rolesPermitidos) => {

    return rolesPermitidos.includes(rol);

  };

  /*
   * ==========================================
   * Información Global
   * ==========================================
   */

  const value = {

    token,

    usuario,

    rol,

    autenticado,

    cargando,

    error,

    iniciarSesion,

    cerrarSesion,

    tieneRol

  };

  return (

    <AuthContext.Provider value={value}>

      {children}

    </AuthContext.Provider>

  );

}