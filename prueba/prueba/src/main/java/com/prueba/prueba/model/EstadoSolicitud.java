package com.prueba.prueba.model;

/*
    Enum que define los estados permitidos
    para una solicitud de servicio.

    Usar enum evita guardar estados escritos incorrectamente.
*/
public enum EstadoSolicitud {

    // La solicitud fue registrada, pero aún no se ha iniciado
    PENDIENTE,

    // La solicitud está siendo atendida
    EN_PROCESO,

    // La solicitud fue terminada correctamente
    FINALIZADA,

    // La solicitud fue cancelada
    CANCELADA
}
