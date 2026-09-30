import { useContext } from "react";

import { AuthContext } from "../../context/AuthContext";

import {
    AppBar,
    Toolbar,
    Typography,
    Box,
    Button,
    Avatar,
    Chip
} from "@mui/material";

import LogoutIcon from "@mui/icons-material/Logout";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";

/*
 * =====================================================
 * NAVBAR PRINCIPAL
 * =====================================================
 *
 * Barra superior utilizada en toda la aplicación.
 *
 * Muestra:
 *
 * - Nombre del sistema
 * - Usuario autenticado
 * - Rol
 * - Botón Cerrar sesión
 *
 */

const Navbar = () => {

    /*
     * Obtiene la información
     * del usuario autenticado.
     */

    const {

        usuario,

        rol,

        cerrarSesion

    } = useContext(AuthContext);

    return (

        <AppBar
            position="static"
            elevation={2}
        >

            <Toolbar>

                {/* Nombre del sistema */}

                <Typography
                    variant="h6"
                    sx={{
                        fontWeight: "bold"
                    }}
                >

                    ServiGestor360

                </Typography>

                {/* Espaciador */}

                <Box sx={{ flexGrow: 1 }} />

                {/* Usuario */}

                <Avatar
                    sx={{
                        bgcolor: "#1976d2",
                        mr: 1
                    }}
                >

                    <AccountCircleIcon />

                </Avatar>

                <Box
                    sx={{
                        mr: 3
                    }}
                >

                    <Typography
                        variant="body2"
                    >

                        {usuario}

                    </Typography>

                    <Chip
                        label={rol}
                        color="success"
                        size="small"
                    />

                </Box>

                {/* Botón salir */}

                <Button
                    color="inherit"
                    startIcon={<LogoutIcon />}
                    onClick={cerrarSesion}
                >

                    Cerrar sesión

                </Button>

            </Toolbar>

        </AppBar>

    );

};

export default Navbar;