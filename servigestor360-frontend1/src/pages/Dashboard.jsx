import { useContext, useEffect, useState } from "react";

import {
  Avatar,
  Box,
  Button,
  Chip,
  Paper,
  Typography
} from "@mui/material";

import DashboardIcon from "@mui/icons-material/Dashboard";
import PeopleIcon from "@mui/icons-material/People";
import ManageAccountsIcon from "@mui/icons-material/ManageAccounts";
import EngineeringIcon from "@mui/icons-material/Engineering";
import BuildIcon from "@mui/icons-material/Build";
import AssignmentIcon from "@mui/icons-material/Assignment";
import ReceiptLongIcon from "@mui/icons-material/ReceiptLong";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import PendingActionsIcon from "@mui/icons-material/PendingActions";
import AccessTimeIcon from "@mui/icons-material/AccessTime";

import { AuthContext } from "../context/AuthContext";
import dashboardService from "../services/dashboardService";

/*
 * ==========================================================
 * DASHBOARD PRINCIPAL DE SERVIGESTOR360
 * ==========================================================
 *
 * Este componente representa el centro de control
 * de la aplicación.
 *
 * Muestra:
 *
 * - Mensaje de bienvenida.
 * - Usuario autenticado.
 * - Rol del usuario.
 * - Indicadores generales.
 * - Accesos rápidos a los módulos.
 *
 * Recibe la propiedad setModuloActivo para cambiar
 * de módulo desde las tarjetas del Dashboard.
 */

const Dashboard = ({ setModuloActivo }) => {
  /*
   * Obtiene la información del usuario
   * autenticado desde AuthContext.
   */
  const {
    usuario,
    rol
  } = useContext(AuthContext);

  /*
 * ==========================================================
 * Estados del Dashboard
 * ==========================================================
 */

  const [resumen, setResumen] = useState({

    clientes: 0,
    usuarios: 0,
    tecnicos: 0,
    servicios: 0,
    solicitudesPendientes: 0,
    ordenesActivas: 0,
    serviciosFinalizados: 0

  });

  const [loading, setLoading] = useState(true);

  /*
   * Obtiene un nombre legible a partir
   * del correo electrónico.
   *
   * Ejemplo:
   *
   * admin@servigestor.com
   *
   * Resultado:
   *
   * admin
   */
  const nombreUsuario =
    usuario?.split("@")[0] || "Usuario";

  /*
* ==========================================================
* Obtiene los indicadores desde el backend.
* ==========================================================
*/

  useEffect(() => {

    const cargarDashboard = async () => {

      try {

        const datos =
          await dashboardService.obtenerResumen();

        console.log("================================");
        console.log("Respuesta Dashboard");
        console.log(datos);
        console.log("================================");

        setResumen(datos);

      } catch (error) {

        console.error("ERROR DASHBOARD");
        console.error(error);

        if (error.response) {

          console.log("Status:", error.response.status);
          console.log("Body:", error.response.data);

        }

      } finally {

        setLoading(false);

      }

    };

    cargarDashboard();

  }, []);

  /*
   * Indicadores principales.
   *
   * En esta primera versión utilizan valores
   * temporales. Posteriormente serán reemplazados
   * con datos obtenidos desde el backend.
   */
  const indicadores = [
    {
      titulo: "Clientes registrados",
      valor: loading ? "..." : resumen.clientes,
      descripcion: "Total de clientes del sistema",
      icono: <PeopleIcon />,
      colorFondo: "#e3f2fd",
      colorIcono: "#1565c0"
    },
    {
      titulo: "Solicitudes pendientes",
      valor: loading ? "..." : resumen.solicitudesPendientes,
      descripcion: "Solicitudes que requieren atención",
      icono: <PendingActionsIcon />,
      colorFondo: "#fff3e0",
      colorIcono: "#ef6c00"
    },
    {
      titulo: "Órdenes activas",
      valor: loading ? "..." : resumen.ordenesActivas,
      descripcion: "Órdenes actualmente en proceso",
      icono: <AccessTimeIcon />,
      colorFondo: "#f3e5f5",
      colorIcono: "#7b1fa2"
    },
    {
      titulo: "Servicios finalizados",
      valor: loading ? "..." : resumen.serviciosFinalizados,
      descripcion: "Servicios completados exitosamente",
      icono: <CheckCircleIcon />,
      colorFondo: "#e8f5e9",
      colorIcono: "#2e7d32"
    }
  ];

  /*
   * Accesos rápidos disponibles.
   *
   * Cada opción define:
   *
   * titulo:
   * nombre mostrado al usuario.
   *
   * modulo:
   * valor utilizado por moduloActivo.
   *
   * roles:
   * perfiles autorizados para ver el acceso.
   */
  const accesosRapidos = [
    {
      titulo: "Clientes",
      descripcion:
        "Consultar y administrar los clientes registrados.",
      modulo: "clientes",
      icono: <PeopleIcon />,
      roles: [
        "ADMIN",
        "TECNICO"
      ]
    },
    {
      titulo: "Usuarios",
      descripcion:
        "Gestionar las cuentas de acceso al sistema.",
      modulo: "usuarios",
      icono: <ManageAccountsIcon />,
      roles: [
        "ADMIN"
      ]
    },
    {
      titulo: "Técnicos",
      descripcion:
        "Administrar el personal técnico disponible.",
      modulo: "tecnicos",
      icono: <EngineeringIcon />,
      roles: [
        "ADMIN"
      ]
    },
    {
      titulo: "Servicios",
      descripcion:
        "Consultar y configurar el catálogo de servicios.",
      modulo: "servicios",
      icono: <BuildIcon />,
      roles: [
        "ADMIN",
        "TECNICO"
      ]
    },
    {
      titulo: "Solicitudes",
      descripcion:
        "Revisar las solicitudes de servicio registradas.",
      modulo: "solicitudes",
      icono: <AssignmentIcon />,
      roles: [
        "ADMIN",
        "TECNICO",
        "CLIENTE"
      ]
    },
    {
      titulo: "Órdenes de servicio",
      descripcion:
        "Consultar el estado de las órdenes generadas.",
      modulo: "ordenes",
      icono: <ReceiptLongIcon />,
      roles: [
        "ADMIN",
        "TECNICO",
        "CLIENTE"
      ]
    }
  ];

  /*
   * Filtra los accesos rápidos de acuerdo
   * con el rol del usuario autenticado.
   */
  const accesosPermitidos =
    accesosRapidos.filter((acceso) =>
      acceso.roles.includes(rol)
    );

  /*
   * Permite abrir un módulo desde
   * una tarjeta del Dashboard.
   */
  const abrirModulo = (modulo) => {
    if (
      typeof setModuloActivo === "function"
    ) {
      setModuloActivo(modulo);
    }
  };

  return (
    <Box>
      {/*
       * ==================================================
       * ENCABEZADO DEL DASHBOARD
       * ==================================================
       */}

      <Paper
        elevation={0}
        sx={{
          mb: 4,
          p: {
            xs: 3,
            md: 4
          },
          borderRadius: 4,
          color: "#ffffff",
          background:
            "linear-gradient(135deg, #1565c0 0%, #42a5f5 100%)"
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: {
              xs: "column",
              sm: "row"
            },
            alignItems: {
              xs: "flex-start",
              sm: "center"
            },
            justifyContent: "space-between",
            gap: 3
          }}
        >
          <Box>
            <Box
              sx={{
                display: "flex",
                alignItems: "center",
                gap: 1,
                mb: 1
              }}
            >
              <DashboardIcon />

              <Typography
                variant="overline"
                sx={{
                  fontWeight: 700,
                  letterSpacing: 1.2
                }}
              >
                Centro de control
              </Typography>
            </Box>

            <Typography
              variant="h4"
              component="h1"
              sx={{
                fontWeight: 700,
                mb: 1
              }}
            >
              Bienvenido, {nombreUsuario}
            </Typography>

            <Typography
              variant="body1"
              sx={{
                maxWidth: 650,
                opacity: 0.92
              }}
            >
              Desde este panel puedes consultar el estado
              general del sistema y acceder rápidamente a
              los módulos de ServiGestor360.
            </Typography>
          </Box>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 2,
              backgroundColor:
                "rgba(255, 255, 255, 0.15)",
              borderRadius: 3,
              px: 2,
              py: 1.5
            }}
          >
            <Avatar
              sx={{
                width: 48,
                height: 48,
                backgroundColor: "#ffffff",
                color: "#1565c0",
                fontWeight: 700
              }}
            >
              {nombreUsuario
                .charAt(0)
                .toUpperCase()}
            </Avatar>

            <Box>
              <Typography
                variant="body2"
                sx={{
                  opacity: 0.9
                }}
              >
                Sesión activa
              </Typography>

              <Typography
                variant="body1"
                sx={{
                  fontWeight: 600
                }}
              >
                {usuario}
              </Typography>

              <Chip
                label={rol}
                size="small"
                sx={{
                  mt: 0.7,
                  backgroundColor: "#ffffff",
                  color: "#1565c0",
                  fontWeight: 700
                }}
              />
            </Box>
          </Box>
        </Box>
      </Paper>

      {/*
       * ==================================================
       * INDICADORES GENERALES
       * ==================================================
       */}

      <Box
        sx={{
          mb: 4
        }}
      >
        <Typography
          variant="h5"
          component="h2"
          sx={{
            fontWeight: 700,
            mb: 0.5
          }}
        >
          Resumen general
        </Typography>

        <Typography
          variant="body2"
          color="text.secondary"
          sx={{
            mb: 2.5
          }}
        >
          Indicadores principales de la operación.
        </Typography>

        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: {
              xs: "1fr",
              sm: "repeat(2, 1fr)",
              xl: "repeat(4, 1fr)"
            },
            gap: 2.5
          }}
        >
          {indicadores.map((indicador) => (
            <Paper
              key={indicador.titulo}
              elevation={0}
              sx={{
                p: 2.5,
                borderRadius: 3,
                border:
                  "1px solid",
                borderColor:
                  "divider",
                transition:
                  "transform 0.2s ease, box-shadow 0.2s ease",

                "&:hover": {
                  transform:
                    "translateY(-4px)",
                  boxShadow: 4
                }
              }}
            >
              <Box
                sx={{
                  display: "flex",
                  alignItems: "flex-start",
                  justifyContent:
                    "space-between",
                  gap: 2
                }}
              >
                <Box>
                  <Typography
                    variant="body2"
                    color="text.secondary"
                    sx={{
                      mb: 1
                    }}
                  >
                    {indicador.titulo}
                  </Typography>

                  <Typography
                    variant="h3"
                    sx={{
                      fontWeight: 700,
                      lineHeight: 1
                    }}
                  >
                    {indicador.valor}
                  </Typography>
                </Box>

                <Avatar
                  sx={{
                    backgroundColor:
                      indicador.colorFondo,
                    color:
                      indicador.colorIcono
                  }}
                >
                  {indicador.icono}
                </Avatar>
              </Box>

              <Typography
                variant="caption"
                color="text.secondary"
                sx={{
                  display: "block",
                  mt: 2
                }}
              >
                {indicador.descripcion}
              </Typography>
            </Paper>
          ))}
        </Box>
      </Box>

      {/*
       * ==================================================
       * ACCESOS RÁPIDOS
       * ==================================================
       */}

      <Box>
        <Typography
          variant="h5"
          component="h2"
          sx={{
            fontWeight: 700,
            mb: 0.5
          }}
        >
          Accesos rápidos
        </Typography>

        <Typography
          variant="body2"
          color="text.secondary"
          sx={{
            mb: 2.5
          }}
        >
          Ingresa directamente a los módulos disponibles
          para tu rol.
        </Typography>

        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: {
              xs: "1fr",
              md: "repeat(2, 1fr)",
              xl: "repeat(3, 1fr)"
            },
            gap: 2.5
          }}
        >
          {accesosPermitidos.map((acceso) => (
            <Paper
              key={acceso.modulo}
              elevation={0}
              sx={{
                p: 3,
                borderRadius: 3,
                border:
                  "1px solid",
                borderColor:
                  "divider",
                display: "flex",
                flexDirection: "column",
                minHeight: 210,
                transition:
                  "transform 0.2s ease, box-shadow 0.2s ease",

                "&:hover": {
                  transform:
                    "translateY(-4px)",
                  boxShadow: 4
                }
              }}
            >
              <Avatar
                sx={{
                  mb: 2,
                  backgroundColor:
                    "primary.main",
                  color:
                    "primary.contrastText"
                }}
              >
                {acceso.icono}
              </Avatar>

              <Typography
                variant="h6"
                sx={{
                  fontWeight: 700,
                  mb: 1
                }}
              >
                {acceso.titulo}
              </Typography>

              <Typography
                variant="body2"
                color="text.secondary"
                sx={{
                  flexGrow: 1,
                  mb: 2
                }}
              >
                {acceso.descripcion}
              </Typography>

              <Button
                variant="text"
                endIcon={
                  <ArrowForwardIcon />
                }
                onClick={() =>
                  abrirModulo(
                    acceso.modulo
                  )
                }
                sx={{
                  alignSelf: "flex-start",
                  px: 0,
                  fontWeight: 700
                }}
              >
                Abrir módulo
              </Button>
            </Paper>
          ))}
        </Box>
      </Box>
    </Box>
  );
};

export default Dashboard;