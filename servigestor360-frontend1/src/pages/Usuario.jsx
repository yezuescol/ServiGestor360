import { useState, useEffect } from 'react'

import UsuarioTable from '../components/UsuarioTable'

import {
  listarUsuarios,
  crearUsuario,
  actualizarUsuario,
  eliminarUsuario
} from '../services/usuarioService'

import '../styles/Cliente.css'

function Usuario() {
  const [usuario, setUsuario] = useState({
    nombres: '',
    apellidos: '',
    correo: '',
    password: '',
    rol: 'ADMIN',
    activo: true
  })

  const [usuarios, setUsuarios] = useState([])
  const [modoEdicion, setModoEdicion] = useState(false)
  const [idEditando, setIdEditando] = useState(null)

  const handleChange = (e) => {
    const { name, value } = e.target

    setUsuario({
      ...usuario,
      [name]: value
    })
  }

  const obtenerUsuarios = async () => {
    try {
      const respuesta = await listarUsuarios()
      setUsuarios(respuesta.data)
    } catch (error) {
      console.error('Error al obtener usuarios:', error)
    }
  }

  useEffect(() => {
    obtenerUsuarios()
  }, [])

  const limpiarFormulario = () => {
    setUsuario({
      nombres: '',
      apellidos: '',
      correo: '',
      password: '',
      rol: 'ADMIN',
      activo: true
    })

    setModoEdicion(false)
    setIdEditando(null)
  }

  const editarUsuario = (usuarioSeleccionado) => {
    setUsuario({
      nombres: usuarioSeleccionado.nombres,
      apellidos: usuarioSeleccionado.apellidos,
      correo: usuarioSeleccionado.correo,
      password: usuarioSeleccionado.password,
      rol: usuarioSeleccionado.rol,
      activo: usuarioSeleccionado.activo
    })

    setIdEditando(usuarioSeleccionado.id)
    setModoEdicion(true)
  }

  const guardarUsuario = async () => {
    try {
      if (modoEdicion) {
        await actualizarUsuario(idEditando, usuario)
        console.log('Usuario actualizado correctamente')
      } else {
        await crearUsuario(usuario)
        console.log('Usuario guardado correctamente')
      }

      limpiarFormulario()
      obtenerUsuarios()
    } catch (error) {
      console.error('Error al guardar o actualizar usuario:', error)
    }
  }

  const borrarUsuario = async (idUsuario) => {
    const confirmar = window.confirm(
      '¿Está seguro de eliminar este usuario?'
    )

    if (!confirmar) {
      return
    }

    try {
      await eliminarUsuario(idUsuario)
      obtenerUsuarios()
      console.log('Usuario eliminado correctamente')
    } catch (error) {
      console.error('Error al eliminar usuario:', error)
    }
  }

  return (
    <section className="cliente-module">
      <div className="module-header">
        <div>
          <span className="module-tag">Módulo Usuario</span>
          <h2>Gestión de Usuarios</h2>
          <p>Registra, consulta y administra los usuarios del sistema.</p>
        </div>
      </div>

      <div className="card form-card">
        <h3>
          {modoEdicion ? 'Editar Usuario' : 'Formulario Usuario'}
        </h3>

        <form className="cliente-form">
          <div className="form-group">
            <label>Nombres</label>
            <input
              type="text"
              name="nombres"
              value={usuario.nombres}
              onChange={handleChange}
              placeholder="Ingrese los nombres"
            />
          </div>

          <div className="form-group">
            <label>Apellidos</label>
            <input
              type="text"
              name="apellidos"
              value={usuario.apellidos}
              onChange={handleChange}
              placeholder="Ingrese los apellidos"
            />
          </div>

          <div className="form-group">
            <label>Correo</label>
            <input
              type="email"
              name="correo"
              value={usuario.correo}
              onChange={handleChange}
              placeholder="Ingrese el correo"
            />
          </div>

          <div className="form-group">
            <label>Contraseña</label>
            <input
              type="password"
              name="password"
              value={usuario.password}
              onChange={handleChange}
              placeholder="Ingrese la contraseña"
            />
          </div>

          <div className="form-group">
            <label>Rol</label>
            <select
              name="rol"
              value={usuario.rol}
              onChange={handleChange}
            >
              <option value="ADMIN">ADMIN</option>
              <option value="TECNICO">TECNICO</option>
              <option value="CLIENTE">CLIENTE</option>
            </select>
          </div>

          <div className="form-actions">
            <button
              type="button"
              className="btn-primary"
              onClick={guardarUsuario}
            >
              {modoEdicion ? 'Actualizar' : 'Guardar'}
            </button>

            <button
              type="button"
              className="btn-secondary"
              onClick={limpiarFormulario}
            >
              Limpiar
            </button>
          </div>
        </form>
      </div>

      <div className="card table-card">
        <div className="table-title">
          <h3>Usuarios Registrados</h3>
          <p>Listado de usuarios consultados desde Spring Boot.</p>
        </div>

        <UsuarioTable
          usuarios={usuarios}
          onEditar={editarUsuario}
          onEliminar={borrarUsuario}
        />
      </div>
    </section>
  )
}

export default Usuario