import { useEffect, useState } from 'react'

import DetalleSolicitudTable from '../components/DetalleSolicitudTable'

import {
  getDetalles,
  crearDetalle,
  actualizarDetalle,
  eliminarDetalle
} from '../services/detalleSolicitudService'

import { getSolicitudes } from '../services/solicitudService'
import { getServicios } from '../services/servicioService'
import { getTecnicos } from '../services/tecnicoService'

import '../styles/Cliente.css'

function DetalleSolicitud() {
  const [detalle, setDetalle] = useState({
    idSolicitud: '',
    idServicio: '',
    idTecnico: '',
    cantidad: 1,
    precioUnitario: '',
    observaciones: '',
    estadoDetalle: 'ASIGNADO',
    fechaAsignacion: ''
  })

  const [detalles, setDetalles] = useState([])
  const [solicitudes, setSolicitudes] = useState([])
  const [servicios, setServicios] = useState([])
  const [tecnicos, setTecnicos] = useState([])

  const [modoEdicion, setModoEdicion] = useState(false)
  const [idEditando, setIdEditando] = useState(null)

  const estadosDetalle = [
    'PENDIENTE',
    'ASIGNADO',
    'EN_PROCESO',
    'FINALIZADO',
    'CANCELADO'
  ]

  const handleChange = (e) => {
    const { name, value } = e.target

    setDetalle({
      ...detalle,
      [name]: value
    })
  }

  const obtenerDetalles = async () => {
    try {
      const respuesta = await getDetalles()
      setDetalles(respuesta.data)
    } catch (error) {
      console.error('Error al obtener detalles:', error)
    }
  }

  const obtenerSolicitudes = async () => {
    try {
      const respuesta = await getSolicitudes()
      setSolicitudes(respuesta.data)
    } catch (error) {
      console.error('Error al obtener solicitudes:', error)
    }
  }

  const obtenerServicios = async () => {
    try {
      const respuesta = await getServicios()
      setServicios(respuesta.data)
    } catch (error) {
      console.error('Error al obtener servicios:', error)
    }
  }

  const obtenerTecnicos = async () => {
    try {
      const respuesta = await getTecnicos()
      setTecnicos(respuesta.data)
    } catch (error) {
      console.error('Error al obtener técnicos:', error)
    }
  }

  useEffect(() => {
    obtenerDetalles()
    obtenerSolicitudes()
    obtenerServicios()
    obtenerTecnicos()
  }, [])

  const limpiarFormulario = () => {
    setDetalle({
      idSolicitud: '',
      idServicio: '',
      idTecnico: '',
      cantidad: 1,
      precioUnitario: '',
      observaciones: '',
      estadoDetalle: 'ASIGNADO',
      fechaAsignacion: ''
    })

    setModoEdicion(false)
    setIdEditando(null)
  }

  const editarDetalle = (detalleSeleccionado) => {
    setDetalle({
      idSolicitud: detalleSeleccionado.idSolicitud,
      idServicio: detalleSeleccionado.idServicio,
      idTecnico: detalleSeleccionado.idTecnico,
      cantidad: detalleSeleccionado.cantidad,
      precioUnitario: detalleSeleccionado.precioUnitario,
      observaciones: detalleSeleccionado.observaciones || '',
      estadoDetalle: detalleSeleccionado.estadoDetalle,
      fechaAsignacion: detalleSeleccionado.fechaAsignacion
    })

    setIdEditando(detalleSeleccionado.idDetalle)
    setModoEdicion(true)

    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    })
  }

  const guardarDetalle = async () => {
    if (
      !detalle.idSolicitud ||
      !detalle.idServicio ||
      !detalle.idTecnico ||
      !detalle.cantidad ||
      !detalle.precioUnitario ||
      !detalle.estadoDetalle ||
      !detalle.fechaAsignacion
    ) {
      alert('Por favor complete los campos obligatorios')
      return
    }

    const detalleEnviar = {
      solicitud: {
        idSolicitud: Number(detalle.idSolicitud)
      },
      servicio: {
        idServicio: Number(detalle.idServicio)
      },
      tecnico: {
        idTecnico: Number(detalle.idTecnico)
      },
      cantidad: Number(detalle.cantidad),
      precioUnitario: Number(detalle.precioUnitario),
      observaciones: detalle.observaciones,
      estadoDetalle: detalle.estadoDetalle,
      fechaAsignacion: detalle.fechaAsignacion
    }

    try {
      if (modoEdicion) {
        await actualizarDetalle(idEditando, detalleEnviar)
      } else {
        await crearDetalle(detalleEnviar)
      }

      limpiarFormulario()
      await obtenerDetalles()
    } catch (error) {
      console.error('Error al guardar detalle:', error)

      if (error.response?.status === 403) {
        alert(
          'Acceso denegado. Verifique el token JWT y los permisos del usuario.'
        )
        return
      }

      if (error.response?.data) {
        console.error('Respuesta del backend:', error.response.data)
      }

      alert('No fue posible guardar el detalle de la solicitud')
    }
  }

  const borrarDetalle = async (idDetalle) => {
    const confirmar = window.confirm(
      '¿Está seguro de eliminar este detalle?'
    )

    if (!confirmar) {
      return
    }

    try {
      await eliminarDetalle(idDetalle)
      await obtenerDetalles()
    } catch (error) {
      console.error('Error al eliminar detalle:', error)
      alert('No fue posible eliminar el detalle')
    }
  }

  const seleccionarServicio = (e) => {
    const idServicio = e.target.value

    const servicioSeleccionado = servicios.find(
      (servicio) =>
        servicio.idServicio === Number(idServicio)
    )

    setDetalle({
      ...detalle,
      idServicio,
      precioUnitario: servicioSeleccionado
        ? servicioSeleccionado.precioBase
        : ''
    })
  }

  return (
    <section className="cliente-module">
      <div className="module-header">
        <div>
          <span className="module-tag">
            Módulo Detalle de Solicitud
          </span>

          <h2>Gestión de Detalles de Solicitud</h2>

          <p>
            Relaciona solicitudes, servicios y técnicos, calcula subtotales
            y prepara la información para la generación de la orden de servicio.
          </p>
        </div>
      </div>

      <div className="card form-card">
        <h3>
          {modoEdicion
            ? 'Editar Detalle de Solicitud'
            : 'Formulario Detalle de Solicitud'}
        </h3>

        <form className="cliente-form">
          <div className="form-group">
            <label>Solicitud</label>

            <select
              name="idSolicitud"
              value={detalle.idSolicitud}
              onChange={handleChange}
            >
              <option value="">
                Seleccione una solicitud
              </option>

              {solicitudes.map((solicitud) => (
                <option
                  key={solicitud.idSolicitud}
                  value={solicitud.idSolicitud}
                >
                  #{solicitud.idSolicitud} - {solicitud.descripcion}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Servicio</label>

            <select
              name="idServicio"
              value={detalle.idServicio}
              onChange={seleccionarServicio}
            >
              <option value="">
                Seleccione un servicio
              </option>

              {servicios.map((servicio) => (
                <option
                  key={servicio.idServicio}
                  value={servicio.idServicio}
                >
                  {servicio.nombreServicio}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Técnico</label>

            <select
              name="idTecnico"
              value={detalle.idTecnico}
              onChange={handleChange}
            >
              <option value="">
                Seleccione un técnico
              </option>

              {tecnicos.map((tecnico) => (
                <option
                  key={tecnico.idTecnico}
                  value={tecnico.idTecnico}
                >
                  {tecnico.nombres} {tecnico.apellidos}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Cantidad</label>

            <input
              type="number"
              name="cantidad"
              min="1"
              step="1"
              value={detalle.cantidad}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Precio unitario</label>

            <input
              type="number"
              name="precioUnitario"
              min="0"
              step="0.01"
              value={detalle.precioUnitario}
              onChange={handleChange}
              placeholder="Precio del servicio"
            />
          </div>

          <div className="form-group">
            <label>Estado</label>

            <select
              name="estadoDetalle"
              value={detalle.estadoDetalle}
              onChange={handleChange}
            >
              {estadosDetalle.map((estado) => (
                <option key={estado} value={estado}>
                  {estado}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Fecha de asignación</label>

            <input
              type="date"
              name="fechaAsignacion"
              value={detalle.fechaAsignacion}
              onChange={handleChange}
            />
          </div>

          <div className="form-group full">
            <label>Observaciones</label>

            <textarea
              name="observaciones"
              value={detalle.observaciones}
              onChange={handleChange}
              rows="4"
              maxLength="500"
              placeholder="Ingrese observaciones del servicio"
            />
          </div>

          <div className="form-actions">
            <button
              type="button"
              className="btn-primary"
              onClick={guardarDetalle}
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
          <h3>Detalles Registrados</h3>

          <p>
            Listado de servicios asignados a las solicitudes del sistema.
          </p>
        </div>

        <div className="table-container">
          <DetalleSolicitudTable
            detalles={detalles}
            onEditar={editarDetalle}
            onEliminar={borrarDetalle}
          />
        </div>
      </div>
    </section>
  )
}

export default DetalleSolicitud