// Importa Hooks de React
import { useState, useEffect } from 'react'

// Importa el componente tabla
import ClienteTable from '../components/ClienteTable'

// Importa funciones del servicio Cliente
import {
  getClientes,
  crearCliente,
  actualizarCliente,
  eliminarCliente
} from '../services/clienteService'

// Importa estilos del módulo Cliente
import '../styles/Cliente.css'

// Página principal del módulo Cliente
function Cliente() {

  // Estado del formulario
  const [cliente, setCliente] = useState({
    nombres: '',
    apellidos: '',
    correoElectronico: ''
  })

  // Estado para la lista de clientes
  const [clientes, setClientes] = useState([])

  // Controla si el formulario está creando o editando
  const [modoEdicion, setModoEdicion] = useState(false)

  // Guarda el ID del cliente seleccionado para editar
  const [idEditando, setIdEditando] = useState(null)

  // Actualiza el formulario cuando el usuario escribe
  const handleChange = (e) => {
    setCliente({
      ...cliente,
      [e.target.name]: e.target.value
    })
  }

  // Consulta clientes desde Spring Boot
  const obtenerClientes = async () => {
    try {
      const respuesta = await getClientes()
      setClientes(respuesta.data)
    } catch (error) {
      console.error('Error al obtener clientes:', error)
    }
  }

  // Carga clientes al abrir el componente
  useEffect(() => {
    obtenerClientes()
  }, [])

  // Limpia el formulario y cancela edición
  const limpiarFormulario = () => {
    setCliente({
      nombres: '',
      apellidos: '',
      correoElectronico: ''
    })

    setModoEdicion(false)
    setIdEditando(null)
  }

  // Carga los datos del cliente seleccionado en el formulario
  const editarCliente = (clienteSeleccionado) => {
    setCliente({
      nombres: clienteSeleccionado.nombres,
      apellidos: clienteSeleccionado.apellidos,
      correoElectronico: clienteSeleccionado.correoElectronico
    })

    setIdEditando(clienteSeleccionado.idCliente)
    setModoEdicion(true)
  }

  // Guarda un nuevo cliente o actualiza uno existente
  const guardarCliente = async () => {
    try {
      if (modoEdicion) {
        // Actualiza el cliente existente con PUT
        await actualizarCliente(idEditando, cliente)
        console.log('Cliente actualizado correctamente')
      } else {
        // Crea un cliente nuevo con POST
        await crearCliente(cliente)
        console.log('Cliente guardado correctamente')
      }

      // Limpia el formulario
      limpiarFormulario()

      // Actualiza la tabla
      obtenerClientes()

    } catch (error) {
      console.error('Error al guardar o actualizar cliente:', error)
    }
  }

  // Elimina un cliente existente
  const borrarCliente = async (idCliente) => {

    // Confirma la eliminación antes de enviar la petición
    const confirmar = window.confirm(
      '¿Está seguro de eliminar este cliente?'
    )

    // Si el usuario cancela, se detiene el proceso
    if (!confirmar) {
      return
    }

    try {
      // Elimina el cliente en el backend
      await eliminarCliente(idCliente)

      // Actualiza la tabla después de eliminar
      obtenerClientes()

      console.log('Cliente eliminado correctamente')

    } catch (error) {
      console.error('Error al eliminar cliente:', error)
    }
  }

  return (
    <section className="cliente-module">

      {/* Encabezado del módulo */}
      <div className="module-header">
        <div>
          <span className="module-tag">Módulo Cliente</span>
          <h2>Gestión de Clientes</h2>
          <p>Registra, consulta y administra la información básica de los clientes.</p>
        </div>
      </div>

      {/* Tarjeta del formulario */}
      <div className="card form-card">
        <h3>
          {modoEdicion ? 'Editar Cliente' : 'Formulario Cliente'}
        </h3>

        <form className="cliente-form">

          {/* Campo nombres */}
          <div className="form-group">
            <label>Nombres</label>
            <input
              type="text"
              name="nombres"
              value={cliente.nombres}
              onChange={handleChange}
              placeholder="Ingrese los nombres"
            />
          </div>

          {/* Campo apellidos */}
          <div className="form-group">
            <label>Apellidos</label>
            <input
              type="text"
              name="apellidos"
              value={cliente.apellidos}
              onChange={handleChange}
              placeholder="Ingrese los apellidos"
            />
          </div>

          {/* Campo correo */}
          <div className="form-group full">
            <label>Correo Electrónico</label>
            <input
              type="email"
              name="correoElectronico"
              value={cliente.correoElectronico}
              onChange={handleChange}
              placeholder="Ingrese el correo electrónico"
            />
          </div>

          {/* Botones del formulario */}
          <div className="form-actions">

            {/* Guarda o actualiza según el modo */}
            <button
              type="button"
              className="btn-primary"
              onClick={guardarCliente}
            >
              {modoEdicion ? 'Actualizar' : 'Guardar'}
            </button>

            {/* Limpia el formulario y cancela edición */}
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

      {/* Tarjeta de la tabla */}
      <div className="card table-card">
        <div className="table-title">
          <h3>Clientes Registrados</h3>
          <p>Listado de clientes consultados desde Spring Boot.</p>
        </div>

        {/* Envía datos y funciones al componente tabla */}
        <ClienteTable
          clientes={clientes}
          onEditar={editarCliente}
          onEliminar={borrarCliente}
        />
      </div>

    </section>
  )
}

// Exporta la página Cliente
export default Cliente