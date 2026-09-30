// Componente encargado de mostrar la tabla de clientes
function ClienteTable({ clientes, onEditar, onEliminar }) {
  return (
    <table className="cliente-table">

      {/* Encabezado de la tabla */}
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombres</th>
          <th>Apellidos</th>
          <th>Correo</th>
          <th>Acciones</th>
        </tr>
      </thead>

      {/* Cuerpo de la tabla */}
      <tbody>

        {/* Recorre la lista de clientes y crea una fila por cada registro */}
        {clientes.map((cliente) => (
          <tr key={cliente.idCliente}>
            <td>{cliente.idCliente}</td>
            <td>{cliente.nombres}</td>
            <td>{cliente.apellidos}</td>
            <td>{cliente.correoElectronico}</td>

            <td>
              {/* Envía el cliente seleccionado al formulario para editarlo */}
              <button
                type="button"
                className="btn-edit"
                onClick={() => onEditar(cliente)}
              >
                Editar
              </button>

              {/* Envía el ID del cliente para eliminarlo */}
              <button
                type="button"
                className="btn-delete"
                onClick={() => onEliminar(cliente.idCliente)}
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

// Exporta la tabla para usarla en Cliente.jsx
export default ClienteTable