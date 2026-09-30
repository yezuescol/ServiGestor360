function OrdenServicioView({ orden }) {
  const formatearMoneda = (valor) =>
    Number(valor || 0).toLocaleString('es-CO', {
      style: 'currency',
      currency: 'COP'
    })

  const formatearFecha = (fecha) => {
    if (!fecha) {
      return ''
    }

    return new Date(`${fecha}T00:00:00`).toLocaleDateString('es-CO')
  }

  const imprimirOrden = () => {
    window.print()
  }

  return (
    <div className="card orden-servicio-card">
      <div className="orden-documento">
        <div className="orden-encabezado">
          <div>
            <h2>ServiGestor360</h2>
            <p>Sistema de gestión de servicios técnicos</p>
          </div>

          <div className="orden-numero">
            <strong>ORDEN DE SERVICIO</strong>
            <span>#{orden.idSolicitud}</span>
          </div>
        </div>

        <div className="orden-seccion">
          <h3>Información del cliente</h3>

          <div className="orden-grid">
            <div>
              <strong>Nombre:</strong>
              <span>{orden.nombreCliente}</span>
            </div>

            <div>
              <strong>Correo:</strong>
              <span>{orden.correoCliente}</span>
            </div>

            <div>
              <strong>Identificador:</strong>
              <span>{orden.idCliente}</span>
            </div>
          </div>
        </div>

        <div className="orden-seccion">
          <h3>Información de la solicitud</h3>

          <div className="orden-grid">
            <div>
              <strong>Descripción:</strong>
              <span>{orden.descripcionSolicitud}</span>
            </div>

            <div>
              <strong>Tipo de servicio:</strong>
              <span>{orden.tipoServicio}</span>
            </div>

            <div>
              <strong>Estado:</strong>
              <span>{orden.estadoSolicitud}</span>
            </div>

            <div>
              <strong>Fecha:</strong>
              <span>{formatearFecha(orden.fechaSolicitud)}</span>
            </div>
          </div>
        </div>

        <div className="orden-seccion">
          <h3>Detalle de servicios</h3>

          <div className="table-container">
            <table className="cliente-table orden-table">
              <thead>
                <tr>
                  <th>Servicio</th>
                  <th>Técnico</th>
                  <th>Especialidad</th>
                  <th>Cantidad</th>
                  <th>Precio unitario</th>
                  <th>Subtotal</th>
                  <th>Estado</th>
                  <th>Observaciones</th>
                </tr>
              </thead>

              <tbody>
                {orden.detalles?.map((detalle) => (
                  <tr key={detalle.idDetalle}>
                    <td>{detalle.nombreServicio}</td>
                    <td>{detalle.nombreTecnico}</td>
                    <td>{detalle.especialidadTecnico}</td>
                    <td>{detalle.cantidad}</td>
                    <td>{formatearMoneda(detalle.precioUnitario)}</td>
                    <td>{formatearMoneda(detalle.subtotal)}</td>
                    <td>{detalle.estadoDetalle}</td>
                    <td>{detalle.observaciones}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        <div className="orden-total">
          <span>Total general</span>
          <strong>{formatearMoneda(orden.totalGeneral)}</strong>
        </div>

        <div className="orden-firmas">
          <div>
            <div className="firma-linea" />
            <span>Firma del técnico</span>
          </div>

          <div>
            <div className="firma-linea" />
            <span>Firma del cliente</span>
          </div>
        </div>

        <div className="orden-acciones">
          <button
            type="button"
            className="btn-primary"
            onClick={imprimirOrden}
          >
            Imprimir orden
          </button>
        </div>
      </div>
    </div>
  )
}

export default OrdenServicioView