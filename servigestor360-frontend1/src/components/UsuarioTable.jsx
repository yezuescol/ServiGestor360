function UsuarioTable({ usuarios, onEditar, onEliminar }) {
  return (
    <table className="cliente-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombres</th>
          <th>Apellidos</th>
          <th>Correo</th>
          <th>Rol</th>
          <th>Activo</th>
          <th>Acciones</th>
        </tr>
      </thead>

      <tbody>
        {usuarios.map((usuario) => (
          <tr key={usuario.id}>
            <td>{usuario.id}</td>
            <td>{usuario.nombres}</td>
            <td>{usuario.apellidos}</td>
            <td>{usuario.correo}</td>
            <td>{usuario.rol}</td>
            <td>{usuario.activo ? 'Sí' : 'No'}</td>

            <td>
              <button
                type="button"
                className="btn-edit"
                onClick={() => onEditar(usuario)}
              >
                Editar
              </button>

              <button
                type="button"
                className="btn-delete"
                onClick={() => onEliminar(usuario.id)}
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

export default UsuarioTable