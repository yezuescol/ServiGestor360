import { useContext } from "react";

import {
  Box,
  Divider,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Typography
} from "@mui/material";

import DashboardIcon from "@mui/icons-material/Dashboard";
import PeopleIcon from "@mui/icons-material/People";
import ManageAccountsIcon from "@mui/icons-material/ManageAccounts";
import EngineeringIcon from "@mui/icons-material/Engineering";
import BuildIcon from "@mui/icons-material/Build";
import AssignmentIcon from "@mui/icons-material/Assignment";
import FormatListBulletedIcon from "@mui/icons-material/FormatListBulleted";
import ReceiptLongIcon from "@mui/icons-material/ReceiptLong";

import { AuthContext } from "../../context/AuthContext";

/*
 * =====================================================
 * MENÚ LATERAL DE SERVIGESTOR360
 * =====================================================
 *
 * Este componente muestra las opciones de navegación
 * disponibles según el rol del usuario autenticado.
 *
 * Roles:
 *
 * ADMIN
 * TECNICO
 * CLIENTE
 *
 * El componente recibe:
 *
 * moduloActivo:
 * nombre del módulo que se está mostrando.
 *
 * setModuloActivo:
 * función utilizada para cambiar el módulo activo.
 */

const Sidebar = ({
  moduloActivo,
  setModuloActivo
}) => {
  /*
   * Obtiene el rol del usuario desde
   * el contexto global de autenticación.
   */
  const { rol } = useContext(AuthContext);

  /*
   * Ancho fijo del menú lateral.
   */
  const anchoSidebar = 260;

  /*
   * Lista general de módulos.
   *
   * Cada opción contiene:
   *
   * nombre:
   * texto mostrado en el menú.
   *
   * modulo:
   * valor asignado a moduloActivo.
   *
   * icono:
   * icono representativo del módulo.
   *
   * roles:
   * usuarios autorizados para ver la opción.
   */
  const opcionesMenu = [
    {
      nombre: "Dashboard",
      modulo: "dashboard",
      icono: <DashboardIcon />,
      roles: [
        "ADMIN",
        "TECNICO",
        "CLIENTE"
      ]
    },
    {
      nombre: "Clientes",
      modulo: "clientes",
      icono: <PeopleIcon />,
      roles: [
        "ADMIN",
        "TECNICO"
      ]
    },
    {
      nombre: "Usuarios",
      modulo: "usuarios",
      icono: <ManageAccountsIcon />,
      roles: [
        "ADMIN"
      ]
    },
    {
      nombre: "Técnicos",
      modulo: "tecnicos",
      icono: <EngineeringIcon />,
      roles: [
        "ADMIN"
      ]
    },
    {
      nombre: "Servicios",
      modulo: "servicios",
      icono: <BuildIcon />,
      roles: [
        "ADMIN",
        "TECNICO"
      ]
    },
    {
      nombre: "Solicitudes",
      modulo: "solicitudes",
      icono: <AssignmentIcon />,
      roles: [
        "ADMIN",
        "TECNICO",
        "CLIENTE"
      ]
    },
    {
      nombre: "Detalles de solicitud",
      modulo: "detalles",
      icono: <FormatListBulletedIcon />,
      roles: [
        "ADMIN",
        "TECNICO"
      ]
    },
    {
      nombre: "Órdenes de servicio",
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
   * Filtra las opciones del menú
   * según el rol autenticado.
   */
  const opcionesPermitidas =
    opcionesMenu.filter((opcion) =>
      opcion.roles.includes(rol)
    );

  /*
   * Cambia el módulo activo cuando
   * el usuario selecciona una opción.
   */
  const seleccionarModulo = (modulo) => {
    setModuloActivo(modulo);
  };

  return (
    <Box
      component="aside"
      sx={{
        width: anchoSidebar,
        minWidth: anchoSidebar,
        minHeight: "calc(100vh - 64px)",
        backgroundColor: "#ffffff",
        borderRight: "1px solid #e0e0e0",
        boxShadow: 1
      }}
    >
      {/* Encabezado del menú */}

      <Box
        sx={{
          px: 3,
          py: 2
        }}
      >
        <Typography
          variant="overline"
          color="text.secondary"
          sx={{
            fontWeight: 700,
            letterSpacing: 1.2
          }}
        >
          Menú principal
        </Typography>
      </Box>

      <Divider />

      {/* Opciones permitidas */}

      <List
        sx={{
          px: 1.5,
          py: 2
        }}
      >
        {opcionesPermitidas.map(
          (opcion) => (
            <ListItemButton
              key={opcion.modulo}
              selected={
                moduloActivo ===
                opcion.modulo
              }
              onClick={() =>
                seleccionarModulo(
                  opcion.modulo
                )
              }
              sx={{
                mb: 0.7,
                borderRadius: 2,
                minHeight: 48,

                "&.Mui-selected": {
                  backgroundColor:
                    "primary.main",
                  color: "primary.contrastText"
                },

                "&.Mui-selected:hover": {
                  backgroundColor:
                    "primary.dark"
                },

                "&.Mui-selected .MuiListItemIcon-root":
                  {
                    color:
                      "primary.contrastText"
                  },

                "&:hover": {
                  backgroundColor:
                    "action.hover"
                }
              }}
            >
              <ListItemIcon
                sx={{
                  minWidth: 42,
                  color:
                    moduloActivo ===
                    opcion.modulo
                      ? "inherit"
                      : "text.secondary"
                }}
              >
                {opcion.icono}
              </ListItemIcon>

              <ListItemText
                primary={opcion.nombre}
                primaryTypographyProps={{
                  fontSize: 14,
                  fontWeight:
                    moduloActivo ===
                    opcion.modulo
                      ? 700
                      : 500
                }}
              />
            </ListItemButton>
          )
        )}
      </List>
    </Box>
  );
};

export default Sidebar;