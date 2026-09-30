function DetalleSolicitudTable({
  detalles,
  onEditar,
  onEliminar
}) {
  return (
    <table className="cliente-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Solicitud</th>
          <th>Servicio</th>
          <th>Técnico</th>
          <th>Cantidad</th>
          <th>Precio unitario</th>
          <th>Subtotal</th>
          <th>Estado</th>
          <th>Fecha</th>
          <th>Observaciones</th>
          <th>Acciones</th>
        </tr>
      </thead>

      <tbody>
        {detalles.map((detalle) => (
          <tr key={detalle.idDetalle}>
            <td>{detalle.idDetalle}</td>

            <td>
              {detalle.descripcionSolicitud}
              <br />
              <small>
                Solicitud #{detalle.idSolicitud}
              </small>
            </td>

            <td>
              {detalle.nombreServicio}
            </td>

            <td>
              {detalle.nombreTecnico}
            </td>

            <td>
              {detalle.cantidad}
            </td>

            <td>
              {Number(
                detalle.precioUnitario
              ).toLocaleString('es-CO', {
                style: 'currency',
                currency: 'COP'
              })}
            </td>

            <td>
              {Number(
                detalle.subtotal
              ).toLocaleString('es-CO', {
                style: 'currency',
                currency: 'COP'
              })}
            </td>

            <td>
              {detalle.estadoDetalle}
            </td>

            <td>
              {detalle.fechaAsignacion}
            </td>

            <td>
              {detalle.observaciones}
            </td>

            <td>
              <button
                type="button"
                className="btn-edit"
                onClick={() =>
                  onEditar(detalle)
                }
              >
                Editar
              </button>

              <button
                type="button"
                className="btn-delete"
                onClick={() =>
                  onEliminar(
                    detalle.idDetalle
                  )
                }
              >
                Eliminar
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default DetalleSolicitudTable