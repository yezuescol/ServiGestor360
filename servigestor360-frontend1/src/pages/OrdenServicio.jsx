import { useEffect, useState } from 'react'

import OrdenServicioView from '../components/OrdenServicioView'

import { obtenerOrdenServicio } from '../services/ordenServicioService'
import { getSolicitudes } from '../services/solicitudService'

import '../styles/Cliente.css'

function OrdenServicio() {
  const [solicitudes, setSolicitudes] = useState([])
  const [idSolicitud, setIdSolicitud] = useState('')
  const [orden, setOrden] = useState(null)
  const [cargando, setCargando] = useState(false)
  const [mensajeError, setMensajeError] = useState('')

  const cargarSolicitudes = async () => {
    try {
      const respuesta = await getSolicitudes()
      setSolicitudes(respuesta.data)
    } catch (error) {
      console.error('Error al obtener solicitudes:', error)
      setMensajeError(
        'No fue posible consultar las solicitudes disponibles.'
      )
    }
  }

  useEffect(() => {
    cargarSolicitudes()
  }, [])

  const consultarOrden = async () => {
    if (!idSolicitud) {
      alert('Seleccione una solicitud')
      return
    }

    try {
      setCargando(true)
      setMensajeError('')
      setOrden(null)

      const respuesta = await obtenerOrdenServicio(idSolicitud)

      setOrden(respuesta.data)
    } catch (error) {
      console.error('Error al obtener la orden de servicio:', error)

      if (error.response?.status === 403) {
        setMensajeError(
          'Acceso denegado. Verifique el token JWT y los permisos del usuario.'
        )
      } else if (error.response?.status === 404) {
        setMensajeError(
          'No se encontró la solicitud seleccionada.'
        )
      } else {
        setMensajeError(
          'No fue posible generar la Orden de Servicio.'
        )
      }
    } finally {
      setCargando(false)
    }
  }

  const limpiarConsulta = () => {
    setIdSolicitud('')
    setOrden(null)
    setMensajeError('')
  }

  return (
    <section className="cliente-module">
      <div className="module-header">
        <div>
          <span className="module-tag">
            Módulo Orden de Servicio
          </span>

          <h2>Orden de Servicio</h2>

          <p>
            Consulte una solicitud y visualice la información consolidada
            del cliente, los servicios asignados, los técnicos responsables
            y el valor total de la orden.
          </p>
        </div>
      </div>

      <div className="card form-card">
        <h3>Consultar Orden</h3>

        <div className="cliente-form">
          <div className="form-group full">
            <label>Solicitud de servicio</label>

            <select
              value={idSolicitud}
              onChange={(event) =>
                setIdSolicitud(event.target.value)
              }
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

          <div className="form-actions">
            <button
              type="button"
              className="btn-primary"
              onClick={consultarOrden}
              disabled={cargando}
            >
              {cargando ? 'Consultando...' : 'Generar Orden'}
            </button>

            <button
              type="button"
              className="btn-secondary"
              onClick={limpiarConsulta}
              disabled={cargando}
            >
              Limpiar
            </button>
          </div>
        </div>

        {mensajeError && (
          <p
            style={{
              marginTop: '18px',
              color: '#dc2626',
              fontWeight: '700',
              textAlign: 'center'
            }}
          >
            {mensajeError}
          </p>
        )}
      </div>

      {orden && (
        <OrdenServicioView orden={orden} />
      )}
    </section>
  )
}

export default OrdenServicio