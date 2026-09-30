import {
  useContext,
  useState
} from "react";


import "./App.css";

/*
 * Páginas correspondientes
 * a los módulos del sistema.
 */
import Cliente from "./pages/Cliente";
import SolicitudServicio from "./pages/SolicitudServicio";
import Usuario from "./pages/Usuario";
import Tecnico from "./pages/Tecnico";
import Servicio from "./pages/Servicio";
import DetalleSolicitud from "./pages/DetalleSolicitud";
import OrdenServicio from "./pages/OrdenServicio";
import Login from "./pages/Login";

import Dashboard from "./pages/Dashboard";

/*
 * Layout principal de la aplicación.
 *
 * Integra:
 *
 * - Navbar
 * - Sidebar
 * - Contenido central
 */
import Layout from "./components/layout/Layout";

/*
 * Contexto global de autenticación.
 */
import { AuthContext } from "./context/AuthContext";

/*
 * =====================================================
 * COMPONENTE PRINCIPAL DE SERVIGESTOR360
 * =====================================================
 *
 * Este componente controla:
 *
 * - La validación de la sesión.
 * - La visualización del Login.
 * - El módulo seleccionado.
 * - La integración del Layout.
 */

function App() {
  /*
   * Módulo que se mostrará inicialmente
   * después del inicio de sesión.
   *
   * El primer módulo será el Dashboard.
   */
  const [
    moduloActivo,
    setModuloActivo
  ] = useState("dashboard");

  /*
   * Obtiene el estado de autenticación
   * desde AuthContext.
   */
  const {
    autenticado
  } = useContext(AuthContext);

  /*
   * Si el usuario no está autenticado,
   * se muestra únicamente la pantalla
   * de inicio de sesión.
   */
  if (!autenticado) {
    return <Login />;
  }

  /*
   * =====================================================
   * FUNCIÓN PARA MOSTRAR EL MÓDULO ACTIVO
   * =====================================================
   *
   * Evalúa el valor de moduloActivo
   * y devuelve el componente correspondiente.
   */
  const renderizarModulo = () => {
    switch (moduloActivo) {
      /*
       * Dashboard temporal.
       *
       * Más adelante será reemplazado
       * por el componente Dashboard.jsx.
       */
      case "dashboard":
        return (
          <Dashboard
            setModuloActivo={setModuloActivo}
          />
        );

      /*
       * Módulo de clientes.
       */
      case "clientes":
        return <Cliente />;

      /*
       * Módulo de solicitudes.
       */
      case "solicitudes":
        return <SolicitudServicio />;

      /*
       * Módulo de usuarios.
       */
      case "usuarios":
        return <Usuario />;

      /*
       * Módulo de técnicos.
       */
      case "tecnicos":
        return <Tecnico />;

      /*
       * Módulo de servicios.
       */
      case "servicios":
        return <Servicio />;

      /*
       * Módulo de detalles
       * de las solicitudes.
       */
      case "detalles":
        return <DetalleSolicitud />;

      /*
       * Módulo de órdenes
       * de servicio.
       */
      case "ordenes":
        return <OrdenServicio />;

      /*
       * Si se recibe un módulo desconocido,
       * se muestra el Dashboard.
       */
      default:
        return (
          <Box>
            <Typography
              variant="h5"
              sx={{
                fontWeight: 600
              }}
            >
              Módulo no disponible
            </Typography>

            <Typography
              color="text.secondary"
              sx={{
                mt: 1
              }}
            >
              La opción seleccionada no se
              encuentra registrada.
            </Typography>
          </Box>
        );
    }
  };

  /*
   * =====================================================
   * ESTRUCTURA PRINCIPAL
   * =====================================================
   *
   * Layout recibe:
   *
   * moduloActivo:
   * módulo seleccionado actualmente.
   *
   * setModuloActivo:
   * función que permite cambiar el módulo
   * desde el Sidebar.
   *
   * Dentro del Layout se renderiza
   * el módulo seleccionado.
   */
  return (
    <Layout
      moduloActivo={moduloActivo}
      setModuloActivo={setModuloActivo}
    >
      {renderizarModulo()}
    </Layout>
  );
}

export default App;