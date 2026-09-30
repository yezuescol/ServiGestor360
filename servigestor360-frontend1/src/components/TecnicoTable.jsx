function TecnicoTable({ tecnicos, onEditar, onEliminar }) {
  return (
    <table className="cliente-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombres</th>
          <th>Apellidos</th>
          <th>Correo</th>
          <th>Teléfono</th>
          <th>Especialidad</th>
          <th>Departamento</th>
          <th>Municipio</th>
          <th>Activo</th>
          <th>Acciones</th>
        </tr>
      </thead>

      <tbody>
        {tecnicos.map((tecnico) => (
          <tr key={tecnico.idTecnico}>
            <td>{tecnico.idTecnico}</td>
            <td>{tecnico.nombres}</td>
            <td>{tecnico.apellidos}</td>
            <td>{tecnico.correo}</td>
            <td>{tecnico.telefono}</td>
            <td>{tecnico.especialidad}</td>
            <td>{tecnico.departamento}</td>
            <td>{tecnico.municipio}</td>
            <td>{tecnico.activo ? 'Sí' : 'No'}</td>
            <td>
              <button
                type="button"
                className="btn-edit"
                onClick={() => onEditar(tecnico)}
              >
                Editar
              </button>

              <button
                type="button"
                className="btn-delete"
                onClick={() => onEliminar(tecnico.idTecnico)}
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

export default TecnicoTable