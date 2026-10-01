// Importa Hooks de React
import { useState, useEffect } from 'react'

// Importa servicio de clientes para llenar el select
import { getClientes } from "../services/ClienteService";

// Importa servicios de solicitudes
import {
  getSolicitudes,
  crearSolicitud,
  actualizarSolicitud,
  eliminarSolicitud
} from '../services/solicitudService'

// Importa tabla de solicitudes
import SolicitudTable from '../components/SolicitudTable'

// Importa estilos del módulo
import '../styles/Solicitud.css'

// Componente principal del módulo SolicitudServicio
function SolicitudServicio() {

  // Estado del formulario
  const [solicitud, setSolicitud] = useState({
    descripcion: '',
    tipoServicio: '',
    estado: '',
    fechaSolicitud: '',
    direccionServicio: '',
    idCliente: ''
  })

  // Estado para cargar clientes en el select
  const [clientes, setClientes] = useState([])

  // Estado para almacenar solicitudes en la tabla
  const [solicitudes, setSolicitudes] = useState([])

  // Controla si estamos creando o editando
  const [modoEdicion, setModoEdicion] = useState(false)

  // Guarda el ID de la solicitud que se está editando
  const [idEditando, setIdEditando] = useState(null)

  // Consulta clientes desde Spring Boot
  const obtenerClientes = async () => {
    try {
      const respuesta = await getClientes()
      setClientes(respuesta.data)
    } catch (error) {
      console.error('Error al obtener clientes:', error)
    }
  }

  // Consulta solicitudes desde Spring Boot
  const obtenerSolicitudes = async () => {
    try {
      const respuesta = await getSolicitudes()
      setSolicitudes(respuesta.data)
    } catch (error) {
      console.error('Error al obtener solicitudes:', error)
    }
  }

  // Carga clientes y solicitudes al abrir el módulo
  useEffect(() => {
    obtenerClientes()
    obtenerSolicitudes()
  }, [])

  // Actualiza el formulario cuando el usuario escribe o selecciona
  const handleChange = (e) => {
    setSolicitud({
      ...solicitud,
      [e.target.name]: e.target.value
    })
  }

  // Limpia formulario y cancela edición
  const limpiarFormulario = () => {
    setSolicitud({
      descripcion: '',
      tipoServicio: '',
      estado: '',
      fechaSolicitud: '',
      direccionServicio: '',
      idCliente: ''
    })

    setModoEdicion(false)
    setIdEditando(null)
  }

  // Carga una solicitud seleccionada en el formulario
  const editarSolicitud = (solicitudSeleccionada) => {
    setSolicitud({
      descripcion: solicitudSeleccionada.descripcion,
      tipoServicio: solicitudSeleccionada.tipoServicio,
      estado: solicitudSeleccionada.estado,
      fechaSolicitud: solicitudSeleccionada.fechaSolicitud,
      direccionServicio: solicitudSeleccionada.direccionServicio,
      idCliente: solicitudSeleccionada.cliente?.idCliente || ''
    })

    setIdEditando(solicitudSeleccionada.idSolicitud)
    setModoEdicion(true)
  }

  // Guarda una nueva solicitud o actualiza una existente
  const guardarSolicitud = async () => {
    try {
      if (!solicitud.idCliente) {
        alert('Debe seleccionar un cliente')
        return
      }

      const { idCliente, ...datosSolicitud } = solicitud

      if (modoEdicion) {
          await actualizarSolicitud(idEditando, idCliente, datosSolicitud)
          } else {
            await crearSolicitud(idCliente, datosSolicitud)
          }

          const respuesta = await getSolicitudes()
          setSolicitudes(respuesta.data)
          limpiarFormulario()

    } catch (error) {
      console.error('Error al guardar solicitud:', error)
    }
  }

  // Elimina una solicitud
  const borrarSolicitud = async (idSolicitud) => {
    const confirmar = window.confirm(
      '¿Está seguro de eliminar esta solicitud?'
    )

    if (!confirmar) {
      return
    }

    try {
      await eliminarSolicitud(idSolicitud)
      obtenerSolicitudes()
    } catch (error) {
      console.error('Error al eliminar solicitud:', error)
    }
  }

  return (
    <section className="solicitud-module">

      <div className="module-header">
        <div>
          <span className="module-tag">Módulo Solicitudes</span>
          <h2>Gestión de Solicitudes de Servicio</h2>
          <p>Registra solicitudes asociadas a clientes existentes.</p>
        </div>
      </div>

      <div className="card form-card">
        <h3>
          {modoEdicion ? 'Editar Solicitud' : 'Formulario Solicitud'}
        </h3>

        <form className="solicitud-form">

          <div className="form-group">
            <label>Descripción</label>
            <input
              type="text"
              name="descripcion"
              value={solicitud.descripcion}
              onChange={handleChange}
              placeholder="Ingrese la descripción"
            />
          </div>

          <div className="form-group">
            <label>Tipo de Servicio</label>
            <select
              name="tipoServicio"
              value={solicitud.tipoServicio}
              onChange={handleChange}
            >
              <option value="">Seleccione un tipo</option>
              <option value="INSTALACION">Instalación</option>
              <option value="REPARACION">Reparación</option>
              <option value="MANTENIMIENTO">Mantenimiento</option>
              <option value="CONFIGURACION">Configuración</option>
              <option value="SOPORTE_TECNICO">Soporte técnico</option>
            </select>
          </div>

          <div className="form-group">
            <label>Estado</label>
            <select
              name="estado"
              value={solicitud.estado}
              onChange={handleChange}
            >
              <option value="">Seleccione un estado</option>
              <option value="PENDIENTE">Pendiente</option>
              <option value="EN_PROCESO">En proceso</option>
              <option value="FINALIZADA">Finalizada</option>
              <option value="CANCELADA">Cancelada</option>
            </select>
          </div>

          <div className="form-group">
            <label>Fecha Solicitud</label>
            <input
              type="date"
              name="fechaSolicitud"
              value={solicitud.fechaSolicitud}
              onChange={handleChange}
            />
          </div>

          <div className="form-group full">
            <label>Dirección del Servicio</label>
            <input
              type="text"
              name="direccionServicio"
              value={solicitud.direccionServicio}
              onChange={handleChange}
              placeholder="Ingrese la dirección del servicio"
            />
          </div>

          <div className="form-group full">
            <label>Cliente Asociado</label>
            <select
              name="idCliente"
              value={solicitud.idCliente}
              onChange={handleChange}
            >
              <option value="">Seleccione un cliente</option>

              {clientes.map((cliente) => (
                <option
                  key={cliente.idCliente}
                  value={cliente.idCliente}
                >
                  {cliente.nombres} {cliente.apellidos}
                </option>
              ))}
            </select>
          </div>

          <div className="form-actions">
            <button
              type="button"
              className="btn-primary"
              onClick={guardarSolicitud}
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
          <h3>Solicitudes Registradas</h3>
          <p>Listado de solicitudes consultadas desde Spring Boot.</p>
        </div>

        <SolicitudTable
          solicitudes={solicitudes}
          onEditar={editarSolicitud}
          onEliminar={borrarSolicitud}
        />
      </div>

    </section>
  )
}

export default SolicitudServicio