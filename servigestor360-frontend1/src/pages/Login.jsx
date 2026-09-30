import { useContext, useState } from 'react'

import { AuthContext } from '../context/AuthContext'

import '../styles/Login.css'

function Login() {
  /*
   * Acceso al contexto global de autenticación.
   */
  const {
    iniciarSesion,
    cargando,
    error
  } = useContext(AuthContext)

  /*
   * Estados de los campos del formulario.
   */
  const [correo, setCorreo] = useState('')
  const [password, setPassword] = useState('')

  /*
   * Ejecuta el inicio de sesión.
   */
  const manejarLogin = async (evento) => {
    evento.preventDefault()

    /*
     * AuthContext realiza la solicitud al backend,
     * almacena el token y actualiza autenticado.
     */
    await iniciarSesion(
      correo.trim(),
      password
    )

    /*
     * No necesitamos navigate().
     *
     * Cuando autenticado cambia a true,
     * App.jsx se vuelve a renderizar y muestra
     * automáticamente la aplicación.
     */
  }

  return (
    <div className="login-container">

      <div className="login-card">

        <h1>ServiGestor360</h1>

        <p className="subtitulo">
          Gestión Inteligente de Servicios
        </p>

        <form onSubmit={manejarLogin}>

          <div className="campo">

            <label htmlFor="correo">
              Correo electrónico
            </label>

            <input
              id="correo"
              name="correo"
              type="email"
              placeholder="Ingrese su correo"
              value={correo}
              onChange={(evento) =>
                setCorreo(evento.target.value)
              }
              autoComplete="email"
              disabled={cargando}
              required
            />

          </div>

          <div className="campo">

            <label htmlFor="password">
              Contraseña
            </label>

            <input
              id="password"
              name="password"
              type="password"
              placeholder="Ingrese su contraseña"
              value={password}
              onChange={(evento) =>
                setPassword(evento.target.value)
              }
              autoComplete="current-password"
              disabled={cargando}
              required
            />

          </div>

          {error && (
            <div
              className="mensaje-error"
              role="alert"
            >
              {error}
            </div>
          )}

          <button
            type="submit"
            disabled={cargando}
          >
            {cargando
              ? 'Autenticando...'
              : 'Iniciar sesión'}
          </button>

        </form>

      </div>

    </div>
  )
}

export default Login