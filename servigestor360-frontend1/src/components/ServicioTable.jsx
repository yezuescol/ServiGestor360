function ServicioTable({ servicios, onEditar, onEliminar }) {
  return (
    <table className="cliente-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre del servicio</th>
          <th>Descripción</th>
          <th>Precio base</th>
          <th>Duración estimada</th>
          <th>Activo</th>
          <th>Acciones</th>
        </tr>
      </thead>

      <tbody>
        {servicios.map((servicio) => (
          <tr key={servicio.idServicio}>
            <td>{servicio.idServicio}</td>
            <td>{servicio.nombreServicio}</td>
            <td>{servicio.descripcion}</td>

            <td>
              {Number(servicio.precioBase).toLocaleString('es-CO', {
                style: 'currency',
                currency: 'COP'
              })}
            </td>

            <td>{servicio.duracionEstimada} horas</td>

            <td>{servicio.activo ? 'Sí' : 'No'}</td>

            <td>
              <button
                type="button"
                className="btn-edit"
                onClick={() => onEditar(servicio)}
              >
                Editar
              </button>

              <button
                type="button"
                className="btn-delete"
                onClick={() => onEliminar(servicio.idServicio)}
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

export default ServicioTable